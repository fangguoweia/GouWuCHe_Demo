package com.bwei.GouWuCHe_Demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.GouWuCHe_Demo.R;
import com.bwei.GouWuCHe_Demo.bean.NewsBean;

import java.util.List;

/**
 * 父item适配器
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context context;
    private List<NewsBean.DataBean> list;

    public NewsAdapter(Context context, List<NewsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载父item布局
        View itemView = LayoutInflater.from(context).inflate(R.layout.news_fu_item_layout, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        //获取每个属性
        NewsBean.DataBean bean = list.get(position);
        holder.shangjianame.setText(bean.getSellerName());
        holder.shangjiabox.setChecked(bean.isSelected());
        //设置子布局
        holder.fuitemRecy.setLayoutManager(new LinearLayoutManager(context));
        NewsItemAdapter newsItemAdapter = new NewsItemAdapter(context,bean.getList());
        holder.fuitemRecy.setAdapter(newsItemAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size();
    }
    //上拉加载
    public void addPageData(List<NewsBean.DataBean> data) {
        if (list!=null){
            list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        private CheckBox shangjiabox;
        private TextView shangjianame;
        private RecyclerView fuitemRecy;

        public NewsViewHolder(View itemView) {
            super(itemView);
            shangjiabox = itemView.findViewById(R.id.shangjiabox);
            shangjianame = itemView.findViewById(R.id.shangjianame);
            fuitemRecy = itemView.findViewById(R.id.fuitemRecy);
        }
    }
}
