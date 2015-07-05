package com.smart.android.cnblogs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart.android.cnblogs.R;

import java.util.zip.Inflater;

/**
 * Created by feng on 2015/7/5.
 */
public class TopicRecyclerAdapter extends RecyclerView.Adapter<TopicRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private String[] data;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public TopicRecyclerAdapter(Context mContext, String[] data) {
        super();
        this.data = data;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mRootView = LayoutInflater.from(mContext).inflate(R.layout.item_topic_recyclerview, viewGroup, false);
//        View view = View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null);
        ViewHolder viewHolder = new ViewHolder(mRootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mTextTitle.setText(data[position]);
        if (null != onItemClickListener) {
            viewHolder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = viewHolder.getLayoutPosition();
                    onItemClickListener.onItemClick(v, layoutPosition);
                }
            });
            viewHolder.mItemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = viewHolder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(v, layoutPosition);
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mItemView;
        public TextView mTextTitle;

        public ViewHolder(View itemView) {
            super(itemView);
//            mTextTitle = (TextView) itemView;
            mTextTitle = (TextView) itemView.findViewById(R.id.text_title_item_topic);
            mItemView = itemView.findViewById(R.id.linear_item_topic_container);
        }
    }
}
