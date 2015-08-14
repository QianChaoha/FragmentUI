package com.sjkj.myapplication.http;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sjkj.myapplication.Interfaces.NetRequestResult;
import com.sjkj.myapplication.http.parser.BackResult;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by QianChao on 2015/8/14.
 */
public class NetJsonRequest<E extends BackResult> {
    private NetRequestResult mRequestResult;
    Gson mGson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * 将json字符串解析为实体类
     *
     * @param jsonString json字符串
     * @return 实体类
     */
    private E createClassFromJson(String jsonString) {
        return (E) mGson.fromJson(jsonString, getEType());
    }

    private Type getEType() {
        ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = pType.getActualTypeArguments();
        if (types.length > 1) {
            return types[1];
        } else {
            return types[0];
        }
    }

    public JsonObjectRequest NetJsonRequest(int method, String url, JSONObject param, final NetRequestResult mRequestResult, Response.ErrorListener errorListener) {
        this.mRequestResult = mRequestResult;
        JsonObjectRequest request = new JsonObjectRequest(method, url, param, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                String json = response.toString();
                try {
                    E e = createClassFromJson(json);
                    mRequestResult.onResponse(e);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }, errorListener);
        return request;
    }

}
