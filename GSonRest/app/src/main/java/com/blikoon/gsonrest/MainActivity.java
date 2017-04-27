package com.blikoon.gsonrest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import java.util.List;
import cz.msebera.android.httpclient.Header;





public class MainActivity extends AppCompatActivity {
    public static final String LOGTAG = "GsonRestApiDemo";
    private Button fetchButton;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new AsyncHttpClient();
        client.setUserAgent("RestApiTutorial");


        fetchButton = (Button) findViewById(R.id.fetchButton);
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.get(
                        "https://api.github.com/orgs/blikoon/repos",
                        new TextHttpResponseHandler() {
                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Log.d(LOGTAG,"request FAILED. ResponseString :"+ responseString + " Error : "+ throwable.getMessage());

                            }

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                Log.d(LOGTAG,"request SUCCEEDED.");
                                List<Repo> orgList = RepoResponse.parseJSON(responseString);

                                Log.d(LOGTAG," We got "+ orgList.size() + "Repos");
                                for( Repo org :orgList)
                                {
                                    Log.d(LOGTAG, "Repo :"+ org.getFullName());
                                }
                            }
                        });
            }
        });
    }
}
