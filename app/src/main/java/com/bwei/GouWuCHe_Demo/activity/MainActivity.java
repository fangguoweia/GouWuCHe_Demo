package com.bwei.GouWuCHe_Demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.GouWuCHe_Demo.R;
import com.bwei.GouWuCHe_Demo.adapter.NewsAdapter;
import com.bwei.GouWuCHe_Demo.bean.NewsBean;
import com.bwei.GouWuCHe_Demo.common.Api;
import com.bwei.GouWuCHe_Demo.presenter.NewsPresent;
import com.bwei.GouWuCHe_Demo.view.NewsView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

/**
 * 展示购物车列表
 */
public class MainActivity extends AppCompatActivity implements NewsView {

    private XRecyclerView xrecycler_view;
    private CheckBox allCheckbox;
    private TextView txt_zongjia;
    private int page=1;
    private NewsPresent newsPresent;
    private List<NewsBean.DataBean> list;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化数据
        initData();
    }
    //初始化数据
    private void initData() {
        //请求数据
        loadData();
    }
    //请求数据
    private void loadData() {
        HashMap<String,String> params = new HashMap<>();
        params.put("uid","71");
        params.put("page",page+"");
        //传到p层
        newsPresent = new NewsPresent(this);
        newsPresent.getNews(params, Api.GETCARTS);
    }

    private void initView() {
        xrecycler_view = findViewById(R.id.xrecycler_view);
        //txt总价
        txt_zongjia = findViewById(R.id.zongjia);
        //全选按钮
        allCheckbox = findViewById(R.id.allcheckbox);
        //设置布局管理器，线性
        xrecycler_view.setLayoutManager(new LinearLayoutManager(this));
        //设置刷新加载
        xrecycler_view.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新;
                page=1;
                loadData();
            }
            @Override
            public void onLoadMore() {
                //上拉加载
                page++;
                loadData();
            }
        });

    }

    //请求成功,展示列表
    @Override
    public void success(NewsBean newsBean) {
        if (newsBean !=null && newsBean.getData() !=null){
            list = newsBean.getData();
            //设置适配器
            newsAdapter = new NewsAdapter(MainActivity.this,list);
            xrecycler_view.setAdapter(newsAdapter);
            xrecycler_view.refreshComplete();//停止刷新
        }else {
            if (newsAdapter!=null){
                newsAdapter.addPageData(newsBean.getData());
            }
            xrecycler_view.loadMoreComplete();//停止加载
        }
    }

    //请求失败
    @Override
    public void failure(String msg) {
        Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();
    }
}
