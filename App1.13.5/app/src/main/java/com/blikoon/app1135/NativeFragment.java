package com.blikoon.app1135;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NativeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String fragmentName = bundle.getString("fragment_name");
        TextView tv = new TextView(getActivity());
        tv.setText(fragmentName);
        if ( fragmentName == "First")
        {
            tv.setBackgroundColor(Color.BLUE);
        }else if (fragmentName == "Second")
        {
            tv.setBackgroundColor(Color.RED);
        }else
        {
            tv.setBackgroundColor(Color.GREEN);
        }
        return tv;
    }
    @Override
    public Animator onCreateAnimator(int transit, boolean enter,
                                     int nextAnim) {
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE:
                if (enter) {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            android.R.animator.fade_in);
                } else {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            android.R.animator.fade_out);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE:
                if (enter) {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            R.animator.fragment_pop_enter);
                } else {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            R.animator.fragment_pop_exit);
                }
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN:
            default:
                if (enter) {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            R.animator.fragment_enter);
                } else {
                    return AnimatorInflater.loadAnimator(
                            getActivity(),
                            R.animator.fragment_exit);
                }
        }
    }
}