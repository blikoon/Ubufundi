package com.blikoon.app1133;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SupportFragment extends Fragment {
    private String fragmentName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SupportFragment fragment2 = new SupportFragment();
                Bundle bundle = new Bundle();
                bundle.putString("fragment_name","Fragment2");
                fragment2.setArguments(bundle);


                FragmentTransaction transaction =
                        getActivity(). getSupportFragmentManager().beginTransaction();

                transaction
                        .replace(R.id.fragment_container, fragment2);
                transaction.commit();

            }
        });

        Bundle bundle = getArguments();
        String fragmentname = bundle.getString("fragment_name");
        if( fragmentname!= null)
        {
            tv.setText(fragmentname);
        }else
        {
            tv.setText("Fragment name not found");
        }

        tv.setBackgroundColor(Color.RED);
        return tv;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                if (enter) {
                    return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
                } else {
                    return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE:
                if (enter) {
                    return AnimationUtils.loadAnimation(getActivity(), R.anim.activity_close_enter);
                } else {
                    return AnimationUtils.loadAnimation(getActivity(), R.anim.activity_close_exit);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
            default:
                if (enter) {
                    return AnimationUtils.loadAnimation(getActivity(), R.anim.activity_open_enter);
                } else {
                    return AnimationUtils.loadAnimation(getActivity(), R.anim.activity_open_exit);
                }
        }
    }
}
