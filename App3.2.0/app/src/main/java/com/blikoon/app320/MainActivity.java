/*

        *MinSdk : 1
        *The default behaviour of the webview is to pass the the click events to the default browser on your device
        * This is because there is no web client attached to the webview by default
        * Change that by attaching your own web client as shown below:
            webview.setWebViewClient(new WebViewClient());
        *Add zooming capabilities to the webview as follows:
             webview.getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //Android 3.0+ has pinch-zoom, don't need buttons
            webview.getSettings().setDisplayZoomControls(false);
        }


 */
package com.blikoon.app320;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);


        //Allow the webview to handle zooming
        webview.getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //Android 3.0+ has pinch-zoom, don't need buttons
            webview.getSettings().setDisplayZoomControls(false);
        }

        //Add a client to the view-->> Enable to handle the link click events.
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://www.blikoon.com");
        setContentView(webview);
    }
}
