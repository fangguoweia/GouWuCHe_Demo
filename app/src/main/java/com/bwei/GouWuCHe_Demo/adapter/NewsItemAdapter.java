package com.bwei.GouWuCHe_Demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.GouWuCHe_Demo.R;
import com.bwei.GouWuCHe_Demo.bean.NewsBean;
import com.bwei.GouWuCHe_Demo.button.ButtonView;

import java.util.List;

/**
 * 子item适配器
 */
public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.News2ViewHolder> {

    private Context context;
    private List<NewsBean.DataBean.ListBean> listBeanlist;

    public NewsItemAdapter(Context context, List<NewsBean.DataBean.ListBean> listBeanlist) {
        this.context = context;
        this.listBeanlist = listBeanlist;
    }

    @NonNull
    @Override
    public News2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载子布局
        View itemview = LayoutInflater.from(context).inflate(R.layout.news_zi_item_layout, parent, false);
        News2ViewHolder viewHolder = new News2ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull News2ViewHolder holder, int position) {

        NewsBean.DataBean.ListBean bean = listBeanlist.get(position);
        holder.pricetxt.setText("优惠价:$"+bean.getBargainPrice());
        holder.titletxt.setText(bean.getTitle());
        String[] imges = bean.getImages().split("\\|");
        //判断数组大小，防止空指针
        if (imges!=null && imges.length>0){
            Glide.with(context).load(imges[0]).into(holder.ziimageview);
        }else {
            holder.ziimageview.setImageResource(R.mipmap.ic_launcher);
        }
        holder.zicheckbox.setChecked(bean.isSelected());
        holder.ButtonView.setNumEt(bean.getTotalNum());
    }

    @Override
    public int getItemCount() {
        return listBeanlist.size() == 0 ? 0 : listBeanlist.size();
    }

    public class News2ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ziimageview;
        private CheckBox zicheckbox;
        private TextView titletxt;
        private TextView pricetxt;
        private ButtonView ButtonView;

        public News2ViewHolder(View itemView) {
            super(itemView);
            zicheckbox = itemView.findViewById(R.id.zicheckbox);
            titletxt = itemView.findViewById(R.id.titletxt);
            pricetxt = itemView.findViewById(R.id.pricetxt);
            ziimageview = itemView.findViewById(R.id.ziimageview);
            ButtonView = itemView.findViewById(R.id.jiajianqi);
        }
    }
}
