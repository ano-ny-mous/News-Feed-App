package com.example.android.instantnews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

class NewsFeedLoader extends AsyncTaskLoader<List<NewsFeed>> {
    private String loadUrl;

    public NewsFeedLoader(Context context, String JsonUrl) {
        super(context);
        loadUrl = JsonUrl;
    }

    @Override
    public List<NewsFeed> loadInBackground() {
        return JSONParser.getNewsFeedData(loadUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
