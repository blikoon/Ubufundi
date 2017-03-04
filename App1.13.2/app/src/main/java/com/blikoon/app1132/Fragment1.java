package com.blikoon.app1132;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment1 extends Fragment {
    //Declare the interface
    OnFragment1ClickListener mCallback;

    Button button1;
    Button button2;
    Button button3;
    Button button4;


    //Host activity must implement this interface.
    public interface OnFragment1ClickListener
    {
        public void onFragment1Message( String message);

    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            mCallback = (OnFragment1ClickListener)activity;

        }catch(ClassCastException e)
        {
            throw new ClassCastException(activity.toString() +
                    " must Implement OnFragment1ClickListener");
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstancestate)
    {
        View v = inflater.inflate(R.layout.fragment1,container,
                false);
        button1 = (Button) v.findViewById(R.id.button1);
        button2 = (Button) v.findViewById(R.id.button2);
        button3 = (Button) v.findViewById(R.id.button3);
        button4 = (Button) v.findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onFragment1Message("Button1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onFragment1Message("Button2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onFragment1Message("Button3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onFragment1Message("Button4");
            }
        });


        return v;
    }

}
