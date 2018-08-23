package com.bwei.GouWuCHe_Demo.model;

import android.os.Handler;
import android.text.TextUtils;

import com.bwei.GouWuCHe_Demo.bean.NewsBean;
import com.bwei.GouWuCHe_Demo.utils.OKHttpUtils;
import com.bwei.GouWuCHe_Demo.utils.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * model类
 */
public class NewsModel {

    Handler handler = new Handler();

    public void getNews(HashMap<String,String> params,String url,final NewsCallback newsCallback){
        OKHttpUtils.getInstance().postData(url, params, new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {
                if (newsCallback !=null){
                    newsCallback.fail("请求失败");
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String jsonResult = response.body().string();
                    if (!TextUtils.isEmpty(jsonResult)){
                        parseResult(jsonResult,newsCallback);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });
    }
    //解析购物车数据
    private void parseResult(String jsonResult, final NewsCallback newsCallback) {
        final NewsBean newsBean = new Gson().fromJson(jsonResult, NewsBean.class);
        if (newsCallback!=null&&newsBean!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    newsCallback.success(newsBean);
                }
            });
        }
    }

    public interface NewsCallback{
        void success(NewsBean newsBean);
        void fail(String msg);//回调异常
    }
}
