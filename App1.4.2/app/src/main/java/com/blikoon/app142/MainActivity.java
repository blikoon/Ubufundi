/*
    ///-->> Object Animator

    ObjectAnimator is more robust in that it not only animates Views but
    Also other objects.It also allows you to have updates on the progress
    of the animation.This example gives a basic template you can use as a
    starting point for more complex animations.
    More in depth details here :
    https://developer.android.com/guide/topics/graphics/prop-animation.html#property-vs-view
 */


package com.blikoon.app142;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity  extends Activity {
    private boolean mIsHeads;
    private ObjectAnimator mFlipper;
    private Bitmap mHeadsImage, mTailsImage;
    private ImageView mFlipImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHeadsImage = BitmapFactory.decodeResource(getResources(), R.drawable.heads);
        mTailsImage = BitmapFactory.decodeResource(getResources(), R.drawable.tails);
        mFlipImage = (ImageView)findViewById(R.id.flip_image);
        mFlipImage.setImageBitmap(mHeadsImage);
        mIsHeads = true;

        mFlipper = ObjectAnimator.ofFloat(mFlipImage, "rotationY", 0f, 360f);

        mFlipper.setDuration(500);
        mFlipper.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
