/*
     * Animate Fragment transitions with custom animations
     * This example works for fragments from the android support library(v4)
     * Use it by setting a custom animation to the FragmentTransaction object as shown
     FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        //Add the animation
        transaction.setCustomAnimations(R.anim.activity_open_enter,
                R.anim.activity_open_exit);

        //end of animation

        transaction
                .replace(R.id.fragment_container, fragment2);
        transaction.commit();

        *Tried passing the four open and close animations at once in onFragment1Message but the system just ignored.Had to explicitly set the close animations in fragment2
        *We can also handle the fragment animation logic inside the fragment class itself so all transitions of its instances are animated. App1.13.3 does just that.
        *!!!!!setCustomAnimations() must be called before add(), replace(), or any other action method, or the animation will not run. It is good practice to simply call this method first in the transaction block.
 */

package com.blikoon.app1132;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragment1ClickListener {


    public void onFragment1Message( String message)
    {

        Fragment2 fragment2 = new Fragment2();
        Bundle args = new Bundle();
        args.putString("fragment1_message",message);
        fragment2.setArguments(args);


        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        //Add the animation
        transaction.setCustomAnimations(R.anim.activity_open_enter,
                R.anim.activity_open_exit);

        //end of animation

        transaction
                .replace(R.id.fragment_container, fragment2);
        transaction.commit();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load the Fragment in the place holder
        if(findViewById(R.id.fragment_container) != null)
        {

            //Should check for savedInstancestate but leave it for now.
            Fragment1 fragment1 = new Fragment1();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.add(R.id.fragment_container, fragment1);
            transaction.commit();

        }



    }
}
