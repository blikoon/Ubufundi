/*
    API Level 11
    *Animate Layout changes
    * Adding the property android:animateLayoutChanges="true" to y our layout
      view makes its layout changes animated.
 */

package com.blikoon.app151;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer =
                (LinearLayout) findViewById(R.id.verticalContainer);
    }

    //Add a new button that can remove itself
    public void onAddClick(View v) {
        Button button = new Button(this);
        button.setText("Click To Remove");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContainer.removeView(v);
            }
        });
        mContainer.addView(button, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
    }







}
