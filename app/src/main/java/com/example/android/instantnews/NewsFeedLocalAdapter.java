package com.example.android.instantnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NewsFeedLocalAdapter extends ArrayAdapter<NewsFeedLocal> {
    private Context mContext;
    private List<NewsFeedLocal> makeNewsList;


    public NewsFeedLocalAdapter(Context mContext, List<NewsFeedLocal> makeNewsList) {
        super(mContext,0,makeNewsList);
        this.mContext = mContext;
        this.makeNewsList = makeNewsList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.adapter_list_view_local, parent, false);
        NewsFeedLocal atInstantNews = makeNewsList.get(position);
        String string = null;
        TextView tvTitle;
        tvTitle = (TextView) convertView.findViewById(R.id.show_feed);
        string = atInstantNews.getFeed();
        tvTitle.setText(string);
        Button upvotebtn=convertView.findViewById(R.id.up_vote_btn);
        final TextView upvotetext=convertView.findViewById(R.id.up_vote_text);
        upvotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upvote = upvotetext.getText().toString();
                int upvotenum=Integer.parseInt(upvote);
                upvotenum=upvotenum+1;
                upvotetext.setText(""+upvotenum);
            }
        });
        Button downvotebtn=convertView.findViewById(R.id.down_vote_btn);
        final TextView downvotetext=convertView.findViewById(R.id.down_vote_text);
        downvotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upvote = downvotetext.getText().toString();
                int downvotenum=Integer.parseInt(upvote);
                downvotenum=downvotenum+1;
                downvotetext.setText(""+downvotenum);
            }
        });


        return convertView;
    }
}
