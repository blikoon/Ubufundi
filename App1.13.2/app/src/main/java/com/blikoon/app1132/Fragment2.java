package com.blikoon.app1132;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment2 extends Fragment {
    private String mMessage;
    TextView infoTextView;
    Button backButton;

    public void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mMessage =getArguments().getString("fragment1_message");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstancestate)
    {
        View v = inflater.inflate(R.layout.fragment2,container,
                false);
        infoTextView = (TextView)v.findViewById(R.id.info_text_view);
        infoTextView.setText(mMessage);

        backButton = (Button)v.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment1 fragment1 = new Fragment1();
                FragmentTransaction transaction =
                        getActivity(). getSupportFragmentManager().beginTransaction();



                //Animation
                transaction.setCustomAnimations(R.anim.activity_close_enter,
                        R.anim.activity_close_exit);
                //end of animation

                transaction
                        .replace(R.id.fragment_container, fragment1);
                transaction.commit();

            }
        });

        return v;
    }
}