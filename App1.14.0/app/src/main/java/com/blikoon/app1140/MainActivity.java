/*
    * It is possible to add transformations such as rotation, scale and alpha to ViewGroups without resorting to animations.
    * This is also very convenient in applying transformations from the context of a parent view such as scale or color that changes with position
    * The first step in enabling these transformations is calling

         setStaticTranformationsEnabled(true)

       during the initialization of the ViewGroup class.(Usually in the constructor)

    * Then implement the

          getChildStaticTransformation(View child,Transformation t)

         override method and in there apply your transformations.
    * You have to return true from this method. This way, the system knows it should apply your transformations to the particular child view.
 */
package com.blikoon.app1140;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
