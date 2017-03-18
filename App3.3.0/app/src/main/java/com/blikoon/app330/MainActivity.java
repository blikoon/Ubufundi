/*
    * MinSdk : 1
    * Interface between Java code and javascript code in the webview
    * JavascriptInterface allows you to:
    *       .call javascript code from android java
    *       .call java code from webview javascript
    * This example calls javascript code from java and both retrives and sets some data
    * It is also possible to call Java code from javascript, something like :
    *
    *
         WebView webView = (WebView) findViewById(R.id.webview);
        webView.addJavascriptInterface(new WebAppInterface(this), "android");
           <input type="button" value="Say hello" onClick="showAndroidToast('Hello Android!')" />

        <script type="text/javascript">
            function showAndroidToast(toast) {
             Android.showToast(toast);
            }
        </script>
        More on this : https://developer.android.com/guide/webapps/webview.html
        
 */

package com.blikoon.app330;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        //JavaScript is not enabled by default
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(mClient);
        //Attach the custom interface to the view
        webview.addJavascriptInterface(new MyJavaScriptInterface(), "BRIDGE");
        setContentView(webview);
        webview.loadUrl("file:///android_asset/form.html");
    }

    private static final String JS_SETELEMENT =
            "javascript:document.getElementById('%s').value='%s'";
    private static final String JS_GETELEMENT =
            "javascript:window.BRIDGE"
                    + ".storeElement('%s',document.getElementById('%s').value)";
    private static final String ELEMENTID = "emailAddress";
    private WebViewClient mClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //Before leaving the page, attempt to retrieve the email
        // using JavaScript
            executeJavascript(view,
                    String.format(JS_GETELEMENT, ELEMENTID, ELEMENTID) );
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
        //When page loads, inject address into page using JavaScript
            SharedPreferences prefs = getPreferences(Activity.MODE_PRIVATE);
            executeJavascript(view, String.format(JS_SETELEMENT, ELEMENTID,
                    prefs.getString(ELEMENTID, "")) );
        }
    };
    private void executeJavascript(WebView view, String script) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.evaluateJavascript(script, null);
        } else {
            view.loadUrl(script);
        }
    }

    private class MyJavaScriptInterface {
        //Store an element in preferences
        @JavascriptInterface
        public void storeElement(String id, String element) {
            SharedPreferences.Editor edit =
                    getPreferences(Activity.MODE_PRIVATE).edit();
            edit.putString(id, element);
            edit.commit();
        //If element is valid, raise a Toast
            if(!TextUtils.isEmpty(element)) {
                Toast.makeText(MainActivity.this, element, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
