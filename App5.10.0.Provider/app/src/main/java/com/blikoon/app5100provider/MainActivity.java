/*
        This example app is designed to work with app5100consumer.Make sure you check it out.

        #This app exposes settings values to be read/written by external apps
        #A ContentProvider exposes an interface to these through its CONTENT_URI
        #The settings are stored in SharedPreferences rather that a database
        #The app defines permissions that are enforced by the android system to read and
         write the settings.

        #Your Provider declared in the manifest has to be flaged exported :
            android:exported="true"
          otherwise external apps on some versions of android won't be able to
          access your settings.See http://stackoverflow.com/questions/18847302/securityexception-permission-denial-opening-provider

        #Provide an intent filter for your activity then to be called from other apps.

 */


package com.blikoon.app5100provider;

import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.os.Bundle;

public class MainActivity  extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load the preferences defaults on first run
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        addPreferencesFromResource(R.xml.preferences);
    }
}
