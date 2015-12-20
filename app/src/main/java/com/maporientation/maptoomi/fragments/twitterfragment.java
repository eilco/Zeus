package com.maporientation.maptoomi.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.maporientation.maptoomi.R;

/**
 * Created by MOZZ on 24/11/2015.
 */
public class twitterfragment extends android.support.v4.app.Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.twitter_fragment,container,false);
        String url = "https://twitter.com/hashtag/maporientation";
        WebView view = (WebView)rootview.findViewById(R.id.webView2);
        view.setWebViewClient(new Callback());
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);
        return rootview;

    }
    private class Callback extends WebViewClient {  //HERE IS THE MAIN CHANGE.

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

    }


}
