/*
    *Use the Webview
    *   #MinSdk : 3
        #Use it to load local resources stored in the assed directory
        #The assets directory must be named exactly "assets"
        #The assets directory must be at the same level as "java" and "res"
        #Load a local asset as shown below:
            upperView.loadUrl("file:///android_asset/testfile.png");
        # "file:///android_asset/" must be there exactly as is.The part following it is just your directory structure
        #Load a html formated string resouce as shown below:
            lowerView.loadData(htmlString, "text/html", "utf-8");


 */

package com.blikoon.app311;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView upperView = (WebView)findViewById(R.id.upperview);
        //Zoom feature must be enabled
        upperView.getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        //Android 3.0+ has pinch-zoom, don't need buttons
            upperView.getSettings().setDisplayZoomControls(false);
        }
        upperView.loadUrl("file:///android_asset/testfile.png");
        WebView lowerView = (WebView)findViewById(R.id.lowerview);
        String htmlString =
                "<h1>Header</h1><p>This is HTML text<br />"
                        + "<i>Formatted in italics</i></p>";
        lowerView.loadData(htmlString, "text/html", "utf-8");
    }
}
