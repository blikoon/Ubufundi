/*
        * Implement the Animation logic inside a native fragment subclass.
        *  Just like App1.13.4 , the animations are in /res/animator and wrapped in ObjectAnimator objects
        *
 */

package com.blikoon.app1135;

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


                NativeFragment fragment = new NativeFragment();
                Bundle bundleArgs = new Bundle();
                bundleArgs.putString("fragment_name","Second");
                fragment.setArguments(bundleArgs);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //Set the transition value to trigger the correct animations
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.add(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        button2 = (Button) findViewById(R.id.go_to_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NativeFragment fragment = new NativeFragment();
                Bundle bundleArgs = new Bundle();
                bundleArgs.putString("fragment_name","SecondAire");
                fragment.setArguments(bundleArgs);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //Set the transition value to trigger the correct animations
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.add(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        NativeFragment fragment = new NativeFragment();
        Bundle bundleArgs = new Bundle();
        bundleArgs.putString("fragment_name","First");
        fragment.setArguments(bundleArgs);

        if ( findViewById(R.id.fragment_container) != null)
        {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            //Set the transition value to trigger the correct animations
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.add(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
