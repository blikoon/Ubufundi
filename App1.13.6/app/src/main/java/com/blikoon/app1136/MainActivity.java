/*
    *Animate fragment transactions in entire application
    *You do this at the theme level:
    * Look at res/values/styles.xml
    * Use fragments as you do it usually
    * Note : Adding fragment transitions to the theme will work only for the native implementation(android.app.Fragment). The Support Library cannot look for these attributes in a theme because they did not exist in earlier platform versions.
 */

package com.blikoon.app1136;

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


                Fragment1 fragment = new Fragment1();


                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //Set the transition value to trigger the correct animations
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        button2 = (Button) findViewById(R.id.go_to_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 fragment = new Fragment2();
                Bundle bundleArgs = new Bundle();
                bundleArgs.putString("fragment_name","SecondAire");
                fragment.setArguments(bundleArgs);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //Set the transition value to trigger the correct animations
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        Fragment1 fragment = new Fragment1();
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
