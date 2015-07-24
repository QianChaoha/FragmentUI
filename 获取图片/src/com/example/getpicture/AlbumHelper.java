package com.example.getpicture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.text.TextUtils;

//ר��������
public class AlbumHelper {
	private Context context;
	private ContentResolver cr;

	/**
	 * �Ƿ񴴽���ͼƬ��
	 */
	private boolean hasBuildImagesBucketList = false;
	// ����ͼ�б�(id,path)
	private HashMap<String, String> thumbnailList = new HashMap<String, String>();
	// ר���б�
	// private List<HashMap<String, String>> albumList = new
	// ArrayList<HashMap<String, String>>();
	private HashMap<String, ImageBucket> bucketList = new HashMap<String, ImageBucket>();
	private static AlbumHelper instance;

	private AlbumHelper() {
	}

	public static AlbumHelper getInstance() {
		if (instance == null) {
			instance = new AlbumHelper();
		}
		return instance;
	}

	public void setNull() {
		instance = null;
	}

	/**
	 * ��ʼ��
	 * 
	 * @param context
	 */
	public void init(Context context) {
		if (this.context == null) {
			this.context = context;
			cr = context.getContentResolver();
		}
	}

	/**
	 * �õ�����ͼ
	 */
	private void getThumbnail() {
		String[] projection = { Thumbnails._ID, Thumbnails.IMAGE_ID, Thumbnails.DATA };
		Cursor cursor = cr.query(Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null, null);
		if (cursor.moveToFirst()) {
			int _id;
			int image_id;
			String image_path;
			int _idColumn = cursor.getColumnIndex(Thumbnails._ID);
			int image_idColumn = cursor.getColumnIndex(Thumbnails.IMAGE_ID);
			int dataColumn = cursor.getColumnIndex(Thumbnails.DATA);
			do {
				// Get the field values
				_id = cursor.getInt(_idColumn);
				image_id = cursor.getInt(image_idColumn);
				image_path = cursor.getString(dataColumn);
				thumbnailList.put(String.valueOf(image_id), image_path);
			} while (cursor.moveToNext());
		}
	}

	// /**
	// * �õ�ԭͼ
	// */
	// void getAlbum() {
	// String[] projection = { Albums._ID, Albums.ALBUM, Albums.ALBUM_ART,
	// Albums.ALBUM_KEY, Albums.ARTIST, Albums.NUMBER_OF_SONGS };
	// Cursor cursor = cr.query(Albums.EXTERNAL_CONTENT_URI, projection, null,
	// null, null);
	// if (cursor.moveToFirst()) {
	// int _id;
	// String album;
	// String albumArt;
	// String albumKey;
	// String artist;
	// int numOfSongs;
	//
	// int _idColumn = cursor.getColumnIndex(Albums._ID);
	// int albumColumn = cursor.getColumnIndex(Albums.ALBUM);
	// int albumArtColumn = cursor.getColumnIndex(Albums.ALBUM_ART);
	// int albumKeyColumn = cursor.getColumnIndex(Albums.ALBUM_KEY);
	// int artistColumn = cursor.getColumnIndex(Albums.ARTIST);
	// int numOfSongsColumn = cursor.getColumnIndex(Albums.NUMBER_OF_SONGS);
	//
	// do {
	// // Get the field values
	// _id = cursor.getInt(_idColumn);
	// album = cursor.getString(albumColumn);
	// albumArt = cursor.getString(albumArtColumn);
	// albumKey = cursor.getString(albumKeyColumn);
	// artist = cursor.getString(artistColumn);
	// numOfSongs = cursor.getInt(numOfSongsColumn);
	// HashMap<String, String> hash = new HashMap<String, String>();
	// hash.put("_id", _id + "");
	// hash.put("album", album);
	// hash.put("albumArt", albumArt);
	// hash.put("albumKey", albumKey);
	// hash.put("artist", artist);
	// hash.put("numOfSongs", numOfSongs + "");
	// albumList.add(hash);
	// } while (cursor.moveToNext());
	//
	// }
	//
	// }

	/**
	 * �õ�ͼƬ��
	 */
	private void buildImagesBucketList() {
		// ��������ͼ����
		getThumbnail();
		// �����������
		String columns[] = new String[] { Media._ID, Media.BUCKET_ID, Media.PICASA_ID, Media.DATA, Media.DISPLAY_NAME, Media.TITLE, Media.SIZE,
				Media.BUCKET_DISPLAY_NAME };
		// �õ�һ���α�
		Cursor cur = cr.query(Media.EXTERNAL_CONTENT_URI, columns, null, null, null);
		if (cur.moveToFirst()) {
			// ��ȡָ���е�����
			int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
			int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
			int photoNameIndex = cur.getColumnIndexOrThrow(Media.DISPLAY_NAME);
			int photoTitleIndex = cur.getColumnIndexOrThrow(Media.TITLE);
			int photoSizeIndex = cur.getColumnIndexOrThrow(Media.SIZE);
			int bucketDisplayNameIndex = cur.getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
			int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);
			int picasaIdIndex = cur.getColumnIndexOrThrow(Media.PICASA_ID);
			// ��ȡͼƬ����
			int totalNum = cur.getCount();

			do {
				String _id = cur.getString(photoIDIndex);
				String name = cur.getString(photoNameIndex);
				String path = cur.getString(photoPathIndex);
				String title = cur.getString(photoTitleIndex);
				String size = cur.getString(photoSizeIndex);
				String bucketName = cur.getString(bucketDisplayNameIndex);
				String bucketId = cur.getString(bucketIdIndex);
				String picasaId = cur.getString(picasaIdIndex);

				ImageBucket bucket = bucketList.get(bucketId);
				// ��������ھʹ����µ�
				if (bucket == null) {
					bucket = new ImageBucket();
					bucketList.put(bucketId, bucket);
					bucket.imageList = new ArrayList<ImageItem>();
					bucket.bucketName = bucketName;
				}
				ImageItem imageItem = new ImageItem();
				imageItem.imageId = _id;
				imageItem.imagePath = path;
				imageItem.imageName = name;
				imageItem.thumbnailPath = thumbnailList.get(_id);
				bucket.imageList.add(imageItem);
			} while (cur.moveToNext());
		}
		hasBuildImagesBucketList = true;
	}

	/**
	 * �õ�ͼƬ��
	 * 
	 * @param refresh
	 * @return
	 */
	public List<ImageBucket> getImagesBucketList(boolean refresh) {
		if (refresh || (!refresh && !hasBuildImagesBucketList)) {
			buildImagesBucketList();
		}
		List<ImageBucket> tmpList = new ArrayList<ImageBucket>();
		Iterator<Entry<String, ImageBucket>> itr = bucketList.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, ImageBucket> entry = (Map.Entry<String, ImageBucket>) itr.next();
			tmpList.add(entry.getValue());
		}
		return tmpList;
	}

	// /**
	// * �õ�ԭʼͼ��·��
	// *
	// * @param image_id
	// * @return
	// */
	// String getOriginalImagePath(String image_id) {
	// String path = null;
	// Log.i(TAG, "---(^o^)----" + image_id);
	// String[] projection = { Media._ID, Media.DATA };
	// Cursor cursor = cr.query(Media.EXTERNAL_CONTENT_URI, projection,
	// Media._ID + "=" + image_id, null, null);
	// if (cursor != null) {
	// cursor.moveToFirst();
	// path = cursor.getString(cursor.getColumnIndex(Media.DATA));
	//
	// }
	// return path;
	// }
	// ������
	public static class ImageBucket {
		public String bucketName;
		public List<ImageItem> imageList = new Stack<ImageItem>();
	}

	// һ��ͼƬ����
	public class ImageItem implements Serializable {
		private static final long serialVersionUID = 1L;
		public String imageId;
		public String thumbnailPath;
		public String imagePath;// ����·��
		public String imageName;
		public boolean isSelected = false;

		public String getThumbnailPath() {
			if (!TextUtils.isEmpty(thumbnailPath)) {
				return "file:///" + thumbnailPath;
			}
			return "file:///" + imagePath;
		}
	}
}
