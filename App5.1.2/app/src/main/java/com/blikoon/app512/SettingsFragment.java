package com.blikoon.app512;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load preference data from XML
        addPreferencesFromResource(R.xml.settings);
    }
}
