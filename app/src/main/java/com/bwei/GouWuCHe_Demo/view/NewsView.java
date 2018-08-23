package com.bwei.GouWuCHe_Demo.view;

import com.bwei.GouWuCHe_Demo.bean.NewsBean;

/**
 * view视图层
 */
public interface NewsView {

    void success(NewsBean newsBean);
    void failure(String msg);
}
