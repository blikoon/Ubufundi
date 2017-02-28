/*
    API Level 11
    *Using a custom Layout animation
    * Use these to apply custom animations to layout changes
    * There are five states of layout changes for which you can
      apply a custom animation:
        #APPEARING: An item that is appearing in the container

        #DISAPPEARING: An item that is disappearing from the container

        #CHANGING: An item that is changing because of a layout change, such as a resize, that
            doesn’t involve views being added or removed

        #CHANGE_APPEARING: An item changing because of another view appearing

        #CHANGE_DISAPPEARING: An item changing because of another view disappearing

        In this example APPEARING, DISAPPEARING and CHANGE_DISAPPEARING are used.

     *How to use these:
          #Create a LayoutTransition and attach it to a view(Layout)
                LayoutTransition transition = new LayoutTransition();
                mContainer.setLayoutTransition(transition);

          #Create Animators and apply them to the states you are interested
           in customizing:
                    Animator appearAnim = ObjectAnimator.ofFloat(null,
                "rotationY", 90f, 0f).setDuration(
                transition.getDuration(LayoutTransition.APPEARING));
                transition.setAnimator(LayoutTransition.APPEARING, appearAnim);
 */

package com.blikoon.app152;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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


        // Layout Changes Animation
        mContainer = (LinearLayout) findViewById(R.id.verticalContainer);
        LayoutTransition transition = new LayoutTransition();
        mContainer.setLayoutTransition(transition);
        // Override the default appear animation with a flip in
        Animator appearAnim = ObjectAnimator.ofFloat(null,
                "rotationY", 90f, 0f).setDuration(
                transition.getDuration(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.APPEARING, appearAnim);
        // Override the default disappear animation with a flip out
        Animator disappearAnim = ObjectAnimator.ofFloat(null,
                "rotationX", 0f, 90f).setDuration(
                transition.getDuration(LayoutTransition.DISAPPEARING));
        transition.setAnimator(LayoutTransition.DISAPPEARING,
                disappearAnim);
        // Override the default change with a more animated slide
        // We animate several properties at once, so we create an
        // animation out of multiple PropertyValueHolder objects.
        // This animation slides the views in and temporarily shrinks
        // the view to half size.
        PropertyValuesHolder pvhSlide =
                PropertyValuesHolder.ofFloat("y", 0, 1);
        PropertyValuesHolder pvhScaleY =
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f, 1f);
        PropertyValuesHolder pvhScaleX =
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f, 1f);
        Animator changingAppearingAnim =
                ObjectAnimator.ofPropertyValuesHolder(
                        this, pvhSlide, pvhScaleY, pvhScaleX);

        changingAppearingAnim.setDuration(
                transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING)
        );
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                changingAppearingAnim);


    }


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
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }


}
