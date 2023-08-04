package com.example.android.instantnews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;

import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.instantnews.dummy.DummyContent;

import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;


public class GlobalFragment extends Fragment {
    public static String defaultApi = "https://content.guardianapis.com/search?api-key=f5da285a-5274-42cd-9d8d-abc1c3fd2f12&from-date=2020-09-26";
    NewsFeedAdapter newsFeedAdapter;
    ProgressBar progressBar;
    ListView listView;
    TextView textView;
    List<NewsFeed> a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_global, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        textView = (TextView) view.findViewById(R.id.default_text);



        new JSONTask().execute();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsFeed atInstantNews = newsFeedAdapter.getItem(i);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(atInstantNews.getUrl()));
                startActivity(intent);
            }
        });
        return view;


    }

    public class JSONTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Uri baseUri = Uri.parse(defaultApi);
            Uri.Builder uriBuilder = baseUri.buildUpon();
            uriBuilder.appendQueryParameter(getString(R.string.tags_query), getString(R.string.tags_value));
            a=JSONParser.getNewsFeedData(uriBuilder.toString());

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            newsFeedAdapter = new NewsFeedAdapter(getActivity().getApplicationContext(), a);
            listView.setAdapter(newsFeedAdapter);

            NewsFeed datavalue = new NewsFeed();
            int i = datavalue.getDataValue();






        }
    }






}