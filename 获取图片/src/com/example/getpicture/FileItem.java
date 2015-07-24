package com.example.getpicture;import java.io.Serializable;import android.text.TextUtils;public  class FileItem implements Serializable {	private String fileId;// 文件ID	private String fileUrl;// 文件网络地址	private String fileName;// 文件名	private transient String fileLocalPath;//本地处理地址	public String getFileLocalPath() {		return fileLocalPath;			}	public void setFileLocalPath(String fileLocalPath) {		this.fileLocalPath = fileLocalPath;	}	public boolean hasUpload(){		return !TextUtils.isEmpty(fileUrl);	}	public String getLocalFileUri() {		if(TextUtils.isEmpty(fileLocalPath)){			return "";		}		return "file:///" + fileLocalPath;	}		public String getFileId() {		return fileId;	}	public void setFileId(String fileId) {		this.fileId = fileId;	}	public String getFileUrl() {		return fileUrl;	}		public final String getFileUri() {		if(TextUtils.isEmpty(fileUrl)){			return "";		}		return UrlHelper.getDomain()+"/"+fileUrl;	}	public void setFileUrl(String fileUrl) {		this.fileUrl = fileUrl;	}	public String getFileName() {		return fileName;	}	public void setFileName(String fileName) {		this.fileName = fileName;	}}