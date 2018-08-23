package com.bwei.GouWuCHe_Demo.utils;

import okhttp3.Call;

import java.io.IOException;

import okhttp3.Response;

/**
 * 接口回调
 */
public interface RequestCallback {

    void failure(Call call, IOException e);
    void onResponse(Call call, Response response);

}
