package com.example.android.instantnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsFeedAdapter extends ArrayAdapter<NewsFeed> {

    private List<NewsFeed> makeNewsList;
    private Context mContext;

    public NewsFeedAdapter(Context context, List<NewsFeed> objects) {
        super(context, 0, objects);
        mContext = context;
        makeNewsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.adapter_list_view, parent, false);
        NewsFeed atInstantNews = makeNewsList.get(position);
        String string = null;
        TextView tvTitle;
        tvTitle = (TextView) convertView.findViewById(R.id.title_of_artical_tv);
        string = atInstantNews.getTitltOfArtical();
        tvTitle.setText(string);
        TextView tvDate;
        TextView tvSectionName;
        tvSectionName = (TextView) convertView.findViewById(R.id.section_name_tv);
        string = atInstantNews.getSectionName();
        tvSectionName.setText(string);
        tvDate = (TextView) convertView.findViewById(R.id.date_tv);
        string = atInstantNews.getDate();
        tvDate.setText(string);
        TextView tvAuthorName;
        tvAuthorName = (TextView) convertView.findViewById(R.id.author_name_tv);
        string = atInstantNews.getAuthorName();
        tvAuthorName.setText(string);
        return convertView;
    }
}
