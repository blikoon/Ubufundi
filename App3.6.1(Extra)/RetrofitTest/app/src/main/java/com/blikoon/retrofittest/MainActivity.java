/*

        The tutorial for this example can be found at :
         https://www.learn2crack.com/2016/02/recyclerview-json-parsing.html
        This is the json you should get from the rest api url:
        ///////////////////////////////////////////////////////
        { "android": [ { "ver": "1.5", "name": "Cupcake", "api": "API level 3" }, { "ver": "1.6", "name": "Donut", "api": "API level 4" }, { "ver": "2.0 - 2.1", "name": "Eclair", "api": "API level 5 - 7" }, { "ver": "2.2", "name": "Froyo", "api": "API level 8" }, { "ver": "2.3", "name": "Gingerbread", "api": "API level 9 - 10" }, { "ver": "3.0 - 3.2", "name": "Honeycomb", "api": "API level 11 - 13" }, { "ver": "4.0", "name": "Ice Cream Sandwich", "api": "API level 14 - 15" }, { "ver": "4.1 - 4.3", "name": "JellyBean", "api": "API level 16 - 18" }, { "ver": "4.4", "name": "KitKat", "api": "API level 19" }, { "ver": "5.0 - 5.1", "name": "Lollipop", "api": "API level 21 - 22" }, { "ver": "6.0", "name": "Marshmallow", "api": "API level 23" }, { "ver": "7.0 - 7.1", "name": "Nougat", "api": "API level 24 - 25" } ] }
        ////////////////////////////////////////////////////////
 */
package com.blikoon.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> data;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
