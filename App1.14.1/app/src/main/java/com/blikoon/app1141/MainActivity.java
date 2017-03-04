/*
    *A more advanced example of transforming child view of ViewGroups
    * Currently not working and nothing is shown when you run the app
    * Logcat out :com.blikoon.app1141 W/View: PerspectiveScrollContentView not displayed because it is too large to fit into a software layer (or drawing cache), needs 11796480 bytes, only 8294400 available
    * It is complaining that the view is somehow to large.
    * Will investigate this more
    *
 */

package com.blikoon.app1141;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HorizontalScrollView parentView = new HorizontalScrollView(this);
        PerspectiveScrollContentView contentView =
                new PerspectiveScrollContentView(this);
        //Disable hardware acceleration for this view, dynamic adjustment
        // of child transformations does not currently work in hardware.
        //You can also disable for the entire Activity or Application
        // with android:hardwareAccelerated="false" in the manifest,
        // but it is better to disable acceleration in as few places
        // as possible for best performance.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            contentView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        //Add a handful of images to scroll through
        for (int i = 0; i < 20; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.ic_launcher);
            contentView.addView(iv);
        }
        //Add the views to the display
        parentView.addView(contentView);
        setContentView(parentView);
    }



}
