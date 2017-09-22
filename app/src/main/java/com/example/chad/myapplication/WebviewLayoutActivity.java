package com.example.chad.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WebviewLayoutActivity extends AppCompatActivity {
    private WebView mWebView; // 웹뷰
    private WebSettings mWebSettings; // 웹뷰세팅
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout );
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("WebView Example");
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        //progressDialog.setTitle("Loading...");
        progressDialog.setMessage("잠시만 기다려 주세요.");
        progressDialog.setCancelable(false);

        final EditText address = (EditText)findViewById(R.id.address);

        Button get = (Button)findViewById(R.id.go);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.loadUrl("http://" + address.getText().toString());
            }
        });

        // 웹뷰 세팅
        mWebView = (WebView)findViewById(R.id.webview); // 레이어와 연결
        mWebView.setWebViewClient(new MyWebViewClient()); // 클릭시 새창 안뜨게
        mWebView.setWebChromeClient(new MyWebChromeClient()); // alert 경고창 사용

        mWebSettings = mWebView.getSettings(); // 세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 자바스크립트 사용 허용
        mWebView.addJavascriptInterface(new WebAppInterface(this), "android");
        mWebSettings.setGeolocationEnabled(true);

        // 화면 비율 관련
        mWebSettings.setUseWideViewPort(true);  // wide viewport를 사용하도록 설정
        mWebSettings.setLoadWithOverviewMode(true); // 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
        mWebView.setInitialScale(100); // 비율 조정
        //mWebSettings.setDefaultFontSize(8); // 기본 폰트 사이즈 지정
        //mWebSettings.setMinimumFontSize(8); // 폰트 사이즈 지정

        // 웹뷰 멀티 터치 가능하게 (줌기능)
        mWebSettings.setBuiltInZoomControls(true);  // 줌 아이콘 사용 설정
        mWebSettings.setSupportZoom(true); // 손으로 확대, 축소를 할 수 있도록 사용

        mWebSettings.setPluginState(WebSettings.PluginState.ON_DEMAND); // 프러그인을 사용 설정
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);   // 웹뷰가 캐시를 사용하지 않도록 설정

        mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);   // 페이지 크기 자동 조절?

        /*mWebView.goBack();  // 뒤로 가기
        mWebView.goForward();   // 앞으로 가기
        mWebView.reload();  // 새로고침*/

        mWebView.clearCache(true);  // 캐시 지우기
        //mWebView.stopLoading(); // 로딩 중단

        // 스크롤 없애기
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        //mWebView.loadUrl("http://m.nate.com"); // 원하는 URL 입력
        //mWebView.loadUrl("file:///android_asset/hello.html");
        //mWebView.loadData("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><html><body>Hello, 마이크!</body></html>", "text/html", "utf-8");
        mWebView.loadUrl("file:///android_asset/hello.html");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }

    private class MyWebViewClient extends WebViewClient {
        /*@Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("www.example.com")) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }*/

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressDialog.setMessage("Loading");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //et.setText(url);
            WebviewLayoutActivity.this.getSupportActionBar().setTitle(url);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressDialog.show();
            progressDialog.setProgress(0);
            WebviewLayoutActivity.this.setProgress(newProgress * 1000);
            if(newProgress >= 100) progressDialog.dismiss();
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    }
}
