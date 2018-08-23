package com.bwei.GouWuCHe_Demo.presenter;

import com.bwei.GouWuCHe_Demo.bean.NewsBean;
import com.bwei.GouWuCHe_Demo.model.NewsModel;
import com.bwei.GouWuCHe_Demo.view.NewsView;

import java.util.HashMap;

/**
 * p层
 * 逻辑处理层
 */
public class NewsPresent {

    private NewsModel newsModel;
    private NewsView newsView;

    public NewsPresent(NewsView newsView) {
        this.newsModel = new NewsModel();
        attachView(newsView);
    }
    //把view层绑定到p层
    private void attachView(NewsView newsView) {
        this.newsView=newsView;
    }

    public void getNews(HashMap<String,String> params,String url){
        newsModel.getNews(params, url, new NewsModel.NewsCallback() {
            @Override
            public void success(NewsBean newsBean) {
               //失败
                if (newsView!=null){
                    newsView.success(newsBean);
                }
            }

            @Override
            public void fail(String msg) {
                //成功
                newsView.failure(msg);
            }
        });
    }
    //解除绑定
    public void datachView(){
        this.newsView=null;
    }
}
