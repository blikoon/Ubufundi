package com.blikoon.app132;
/*
    Drawing completely custom views-----------API 1
    *Must implement 3 override methods:
       # void onMeasure(int widthMeasureSpec, heightMeasureSpec)
       # void onSizeChanged(int w, int h,int oldw, int oldh)
       # void onDraw(Canvas canvas)

    *Measurement Constraints:
       # AT_MOST : used when the layout parameters of your view( from xml of Java code) are
                   match parent.You set the width and height of the view here to match
                   the layout param
       #EXACTLY  : used when in layout params, the width or height is set to a fixed value
                   again you set the width or height to values that work for you here.

       #UNSPECIFIED : used when the view is unconstrained and you need to figure out how big
                      it should be.A good example is when the layout param is wrap_content

 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
