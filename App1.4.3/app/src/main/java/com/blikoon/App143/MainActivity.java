/*
    ///--->>>AnimatorSet
    *We use AnimatorSet to chain animations
    * Chaining animations in Java Code is verbose so we do that in xml
    * Put your animation xml files in the res/animator folder
    * Inflate your animation xml file like this :
       mFlipper = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip);
    *Set a target to your animation like this:
        mFlipper.setTarget(mFlipImage);// In App1.4.2 , ObjectAnimator.ofFloat() did this for us implicitly

    *Add an update listener to the correct ObjectAnimator
         //The animations are probably stored in some kind of container and you
         //retrieve them in the order they are stored in inside the flip.xml
         //animation file.
         ObjectAnimator flipAnimator = (ObjectAnimator) mFlipper.getChildAnimations().get(0);
         flipAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {...}


 */

package com.blikoon.App143;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private boolean mIsHeads;

    ////This is the core of this example
    private AnimatorSet mFlipper;


    private Bitmap mHeadsImage, mTailsImage;
    private ImageView mFlipImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHeadsImage = BitmapFactory.decodeResource(getResources(), R.drawable.heads);
        mTailsImage = BitmapFactory.decodeResource(getResources(), R.drawable.tails);
        mFlipImage = (ImageView)findViewById(R.id.flip_image);
        mFlipImage.setImageResource(R.drawable.heads);
        mIsHeads = true;
        mFlipper = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip);
        mFlipper.setTarget(mFlipImage);
        ObjectAnimator flipAnimator = (ObjectAnimator) mFlipper.getChildAnimations().get(0);
        flipAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getAnimatedFraction() >= 0.25f && mIsHeads) {
                    mFlipImage.setImageBitmap(mTailsImage);
                    mIsHeads = false;
                }
                if (animation.getAnimatedFraction() >= 0.75f && !mIsHeads) {
                    mFlipImage.setImageBitmap(mHeadsImage);
                    mIsHeads = true;
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            mFlipper.start();
            return true;
        }
        return super.onTouchEvent(event);
    }


}
