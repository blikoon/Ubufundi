/*
    * Bundle the fragment transition animation into the fragment class
    * Bundle your animation logic inside the onCreateAnimation() override.
    * WARNING : The animations we see here only work with the support library version of fragments.Note that our imports are android.support.v4.app.Fragment;
 */
package com.blikoon.app1133;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load the Fragment in the place holder
        if(findViewById(R.id.fragment_container) != null)
        {

            //Should check for savedInstancestate but leave it for now.
            SupportFragment fragment1 = new SupportFragment();
            Bundle bundle = new Bundle();
            bundle.putString("fragment_name","Fragment1");
            fragment1.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.add(R.id.fragment_container, fragment1);
            transaction.commit();

        }
    }
}
