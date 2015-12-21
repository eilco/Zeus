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


public class facebookfragment extends android.support.v4.app.Fragment {

    static String url ;
/*
    public void setUrl(String url) {
        this.url = url;
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.facebook_fragment,container,false);
        if (url== null) {
            url = "https://www.facebook.com/calais.emploi/";
        }
        WebView view = (WebView)rootview.findViewById(R.id.webView);
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
