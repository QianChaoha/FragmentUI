package com.sjkj.myapplication.http;

import android.app.ProgressDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sjkj.myapplication.http.parser.BackResult;
import com.sjkj.myapplication.interfaces.NetRequestResult;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by QianChao on 2015/8/14.
 */
public class NetJsonRequest<T extends BackResult> {
    Gson mGson = new GsonBuilder().disableHtmlEscaping().create();
    private ProgressDialog mProgressDialog;
    private RequestQueue mRequestQueue;

    public NetJsonRequest(ProgressDialog mProgressDialog, RequestQueue mRequestQueue) {
        this.mProgressDialog = mProgressDialog;
        this.mRequestQueue = mRequestQueue;
    }

    public void netJsonRequest(int method, String url, JSONObject param, final Class<T> mClass, final NetRequestResult mRequestResult, Response.ErrorListener errorListener) {
        mProgressDialog.show();
        if (errorListener == null) {
            //errorListener为null，使用默认的errorListener
            errorListener = new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    mProgressDialog.dismiss();
                    if (volleyError != null) {
                        System.out.println(volleyError.getMessage()+"");
                    }
                }
            };
        }
        JsonObjectRequest request = new JsonObjectRequest(method, url, param, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                mProgressDialog.dismiss();
                String json = response.toString();
                System.out.println("response" + json);
                try {
                    T t = mGson.fromJson(json, mClass);
                    mRequestResult.onResponse(t);
                } catch (Exception e) {
                    if (e != null) {
                        System.out.println(e.getMessage()+"");
                    }
                }
            }
        }, errorListener);
        mRequestQueue.add(request);
    }
}
