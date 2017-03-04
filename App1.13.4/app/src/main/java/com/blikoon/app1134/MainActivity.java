/*
        * Animate Native Fragments transitions( Not fragments from support libs)
        * Use these if you are not using the support libs and are targeting API11 and up
        * IMPORTANT : We are using Native fragments : android.app.Fragment;
        * The general flow is the same with minor changes to what is done in support fragments
        * Animations now live inside objectAnimator objects
        * <set xmlns:android="http://schemas.android.com/apk/res/android" >
    <objectAnimator
        android:valueFrom="90" android:valueTo="0"
        android:valueType="floatType"
        android:propertyName="rotation"
        android:duration="500"/>
    <objectAnimator
        android:valueFrom="0.0" android:valueTo="1.0"
        android:valueType="floatType"
        android:propertyName="alpha"
        android:duration="500"/>
</set>

        * We stuff the fragment inside its container as follows:
        * Fragment1 fragment =  new Fragment1();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            //Must be called first!
            ft.setCustomAnimations(R.animator.fragment_enter,
                    R.animator.fragment_exit,
                    R.animator.fragment_pop_enter,
                    R.animator.fragment_pop_exit);
            ft.add(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
 */

package com.blikoon.app1134;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.go_to_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Button 1 clicked
                Fragment1 fragment =  new Fragment1();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //Must be called first!
                ft.setCustomAnimations(R.animator.fragment_enter,
                        R.animator.fragment_exit,
                        R.animator.fragment_pop_enter,
                        R.animator.fragment_pop_exit);
                ft.add(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        button2 = (Button) findViewById(R.id.go_to_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Button 2 clicked
                Fragment2 fragment =  new Fragment2();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //Must be called first!
                ft.setCustomAnimations(R.animator.fragment_enter,
                        R.animator.fragment_exit,
                        R.animator.fragment_pop_enter,
                        R.animator.fragment_pop_exit);
                ft.add(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        //Load the Fragment in the place holder
        if(findViewById(R.id.fragment_container) != null)
        {
            Fragment1 fragment =  new Fragment1();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            //Must be called first!
            ft.setCustomAnimations(R.animator.fragment_enter,
                    R.animator.fragment_exit,
                    R.animator.fragment_pop_enter,
                    R.animator.fragment_pop_exit);
            ft.add(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();

        }
    }
}
