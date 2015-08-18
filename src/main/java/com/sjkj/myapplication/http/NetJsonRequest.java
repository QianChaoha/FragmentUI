package com.sjkj.myapplication.http;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sjkj.myapplication.interfaces.NetRequestResult;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by QianChao on 2015/8/14.
 */
public class NetJsonRequest {
    private NetRequestResult mRequestResult;
    private Class mClass;
    Gson mGson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * 将json字符串解析为实体类
     *
     * @param jsonString json字符串
     * @return 实体类
     */
//    private E createClassFromJson(String jsonString) {
//        System.out.println(getEType());
//        return (E) mGson.fromJson(jsonString, getEType());
//    }

    private Type getEType() {
        ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = pType.getActualTypeArguments();
        if (types.length > 1) {
            return types[1];
        } else {
            return types[0];
        }
    }

    public void NetJsonRequest(int method, String url, JSONObject param, final NetRequestResult mRequestResult, Response.ErrorListener errorListener,Class mClass, RequestQueue mRequestQueue) {
        this.mRequestResult = mRequestResult;
        this.mClass=mClass;
        if (errorListener == null) {
            //errorListener为null，使用默认的errorListener
            errorListener = new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    System.out.println(volleyError.getMessage());
                }
            };
        }
        JsonObjectRequest request = new JsonObjectRequest(method, url, param, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                String json = response.toString();
                System.out.println("response"+json);
                try {
                    Object object= mGson.fromJson(json, NetJsonRequest.this.mClass);
                    mRequestResult.onResponse(object);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }, errorListener);
       if (mRequestQueue!=null){
           mRequestQueue.add(request);
       }
    }

}
