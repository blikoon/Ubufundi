/*
    *MinSdk:1
    * Intercept link click events and apply your own custom logic
    * The WebClient.shouldOverrideUrlLoading override method is the key
    * Returning false tells the web view to load the url
    * The default setting is to return false
    * Returning true informs the system that you have handled the event and the system does nothing
    * In this example we applied a simple logic
       Allow the webview to handle link clicks for resources from the web server located at www.blikoon.com
       Ignore link clicks for resources located elsewhere and show a friendly toast message.
    * Taking from here you can implement whatever logic your project requires.
    * Using this technique, full applications can be designed to navigate through hundreds of pages just by custom handling of link clicks in the webview. Use your imagination!
 */
package com.blikoon.app321;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        //Add a client to the view
        webview.setWebViewClient(mClient);
        webview.loadUrl("http://www.imiziki.com/products");
        setContentView(webview);
    }

    private WebViewClient mClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,
                                                String url) {
            Uri request = Uri.parse(url);
            Log.d("BLIKOON","Request Uri : " + request.toString());
            Log.d("BLIKOON","Authority : " + request.getAuthority());
            if(TextUtils.equals(request.getAuthority(),
                    "www.blikoon.com")) {

                //Allow the load
                return false;
            }
            Toast.makeText(MainActivity.this,
                    "Sorry, buddy",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
    };



}
