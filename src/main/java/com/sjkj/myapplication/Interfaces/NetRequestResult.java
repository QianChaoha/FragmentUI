package com.sjkj.myapplication.Interfaces;


import com.sjkj.myapplication.http.parser.BackResult;

/**
 * Created by QianChao on 2015/8/14.
 */
public interface NetRequestResult<E extends BackResult> {
    void onResponse(E response);
}
