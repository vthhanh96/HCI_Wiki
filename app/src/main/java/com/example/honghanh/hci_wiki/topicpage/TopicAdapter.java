package com.example.honghanh.hci_wiki.topicpage;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends BaseAdapter {

    private List<Topic> mList;
    private Context mContext;
    private OnItemTopicClickListener mListener;

    public interface OnItemTopicClickListener {
        void onItemClick(Topic topic, View view);
    }

    public void setOnItemTopicClickListener(OnItemTopicClickListener listener) {
        mListener = listener;
    }

    public TopicAdapter(Context context) {
        mList = new ArrayList<>();
        mList.add(new Topic("Science", R.drawable.science));
        mList.add(new Topic("Music", R.drawable.music));
        mList.add(new Topic("Space", R.drawable.space));
        mList.add(new Topic("Architect", R.drawable.architect));
        mList.add(new Topic("Art", R.drawable.art));
        mList.add(new Topic("Invention", R.drawable.invention));
        mList.add(new Topic("Biology", R.drawable.biology));
        mList.add(new Topic("Nation", R.drawable.nation));
        mList.add(new Topic("Science", R.drawable.science));
        mList.add(new Topic("Art", R.drawable.art));
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TopicHolder viewHolder;
        if(convertView == null) {
            viewHolder = new TopicHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_topic, parent, false);
            viewHolder.imgTopic = (ImageView) convertView.findViewById(R.id.img_topic);
            viewHolder.tvTopic = (TextView) convertView.findViewById(R.id.tv_topic);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TopicHolder) convertView.getTag();
        }

        final Topic topic = mList.get(position);

        viewHolder.tvTopic.setText(topic.getName());
        Glide.with(mContext).load(topic.getImage()).into(viewHolder.imgTopic);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.onItemClick(topic, v);
                }
            }
        });

        return convertView;
    }

    class TopicHolder {
        ImageView imgTopic;
        TextView tvTopic;
    }
}
