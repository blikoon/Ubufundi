package com.blikoon.app530;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText email, message;
    CheckBox age;
    Button submit;

    SharedPreferences formStore;

    boolean submitSuccess = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        message = (EditText) findViewById(R.id.message);
        age = (CheckBox) findViewById(R.id.age);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);

        //Retrieve or create the preferences object
        formStore = getPreferences(Activity.MODE_PRIVATE);
        /*
            getPreferences is good when the data  you store is just for that activity.
            If the data is shared between several activities, use :

                mPreferences = getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

            instead.PREF_NAME can be any string stored in one activity for example like:
                public static final String PREF_NAME = "myPreferences";
         */
    }

    @Override
    public void onResume() {
        super.onResume();
        //Restore the form data
        email.setText(formStore.getString("email", ""));
        message.setText(formStore.getString("message", ""));
        age.setChecked(formStore.getBoolean("age", false));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (submitSuccess) {
            //Editor calls can be chained together
            formStore.edit().clear().commit();
        } else {
            //Store the form data
            SharedPreferences.Editor editor = formStore.edit();
            editor.putString("email", email.getText().toString());
            editor.putString("message", message.getText().toString());
            editor.putBoolean("age", age.isChecked());
            editor.commit();
        }
    }

    @Override
    public void onClick(View v) {

        //DO SOME WORK SUBMITTING A MESSAGE

        //Mark the operation successful
        submitSuccess = true;
        //Close
        finish();
    }
}
