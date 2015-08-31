package com.sjkj.myapplication.interfaces;


import com.sjkj.myapplication.http.parser.BackResult;

/**
 * Created by QianChao on 2015/8/14.
 */
public interface NetRequestResult<T extends BackResult> {
    void onResponse(T result);
}
