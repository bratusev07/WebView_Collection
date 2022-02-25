package com.example.masterclass;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private int count = 3;
    private ArrayList<Model> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.youtube, "https://youtube.com"));
        models.add(new Model(R.drawable.yandex, "https://yandex.com"));
        models.add(new Model(R.drawable.google, "https://google.com"));
        models.add(new Model(R.drawable.vk, "https://vk.com"));
        models.add(new Model(R.drawable.youtube, "https://youtube.com"));
        models.add(new Model(R.drawable.yandex, "https://yandex.com"));

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(models.get(count).getLink());

        WebViewClient webViewClient = new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        };

        webView.setWebViewClient(webViewClient);

        findViewById(R.id.ic_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count == models.size()-1) count = 1;
                ((ImageView)findViewById(R.id.ic_center)).setImageResource(models.get(count).getResId());
                ((ImageView)findViewById(R.id.ic_left)).setImageResource(models.get(count-1).getResId());
                ((ImageView)findViewById(R.id.ic_right)).setImageResource(models.get(count+1).getResId());

                webView.loadUrl(models.get(count).getLink());
            }
        });

        findViewById(R.id.ic_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                if(count == 0) count = models.size()-2;
                ((ImageView)findViewById(R.id.ic_center)).setImageResource(models.get(count).getResId());
                ((ImageView)findViewById(R.id.ic_left)).setImageResource(models.get(count-1).getResId());
                ((ImageView)findViewById(R.id.ic_right)).setImageResource(models.get(count+1).getResId());

                webView.loadUrl(models.get(count).getLink());
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}