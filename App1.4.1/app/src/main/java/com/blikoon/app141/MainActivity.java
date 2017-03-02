/*
    ///Animating view properties with ViewPropertyAnimator
    In this example ,we use a ViewPropertyAnimator to animate a view object.
    *Call View.animate() and get a ViewPropertyAnimator object
    * On it, you can call functions to animate properties like alpha ,
      translation, rotation...

     *IMPORTANT : This works on all classes inheriting the view class like TextViews
                  and Buttons.Android itself may be using things like these to create
                  those stunning effects in material themes.(DON'T QUOTE ME ON THIS THOUGH)

     *API : 12

 */

package com.blikoon.app141;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    View viewToAnimate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.toggleButton);
        button.setOnClickListener(this);
        viewToAnimate = findViewById(R.id.theView);
    }

    @Override
    public void onClick(View v) {
        if (viewToAnimate.getAlpha() > 0f) {
            //If the view is visible, slide it out to the right
            viewToAnimate.animate().alpha(0f).translationX(1000f);
        } else {
            //If the view is hidden, do a fade-in in place
            //Property Animations actually modify the view, so
            // we have to reset the view's location first
            viewToAnimate.setTranslationX(0f);
            viewToAnimate.animate().alpha(1f);
        }
    }
}
