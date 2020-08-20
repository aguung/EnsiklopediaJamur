package com.ensiklopedia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

public class ContentActivity extends AppCompatActivity {

    public static final String URL = "URL";
    public static final String TITLE = "TITLE";
    private WebView web;
    private  MediaPlayer mp;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        final String url = getIntent().getStringExtra(URL);
        String title = getIntent().getStringExtra(TITLE);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);

        mp = MediaPlayer.create(this, R.raw.button);

        web = findViewById(R.id.webView);
        WebSettings settings = web.getSettings();
        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mp.start();
                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);

        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if (checkConnection()) {
            web.loadUrl(url);
        } else {
            showDialog(url);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        mp.start();
        return true;
    }

    private void showDialog(final String url) {
        new AlertDialog.Builder(ContentActivity.this)
                .setTitle("Informasi")
                .setMessage("Anda Tidak Memiliki Jaringan Internet")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setPositiveButton("Recconect", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (checkConnection()) {
                            web.loadUrl(url);
                        } else {
                            showDialog(url);
                        }
                    }
                })
                .create()
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean checkConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        if (Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }
}
