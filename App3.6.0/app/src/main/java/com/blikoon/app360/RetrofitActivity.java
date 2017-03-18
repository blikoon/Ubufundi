/*
    From http://api.learn2crack.com:
    The response json is :
        { "android": [ { "ver": "1.5", "name": "Cupcake", "api": "API level 3" }, { "ver": "1.6", "name": "Donut", "api": "API level 4" }, { "ver": "2.0 - 2.1", "name": "Eclair", "api": "API level 5 - 7" }, { "ver": "2.2", "name": "Froyo", "api": "API level 8" }, { "ver": "2.3", "name": "Gingerbread", "api": "API level 9 - 10" }, { "ver": "3.0 - 3.2", "name": "Honeycomb", "api": "API level 11 - 13" }, { "ver": "4.0", "name": "Ice Cream Sandwich", "api": "API level 14 - 15" }, { "ver": "4.1 - 4.3", "name": "JellyBean", "api": "API level 16 - 18" }, { "ver": "4.4", "name": "KitKat", "api": "API level 19" }, { "ver": "5.0 - 5.1", "name": "Lollipop", "api": "API level 21 - 22" }, { "ver": "6.0", "name": "Marshmallow", "api": "API level 23" }, { "ver": "7.0 - 7.1", "name": "Nougat", "api": "API level 24 - 25" } ] }

        This is an object and it is handled by JSONResponse logic.



    From the book : the response json is as follows : an array of json objects

                [
  {
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
  },
  {
    "userId": 1,
    "id": 2,
    "title": "qui est esse",
    "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
  },
  {
    "userId": 10,
    "id": 100,
    "title": "at nam consequatur ea labore ea harum",
    "body": "cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut"
  }
]

    This is handled by expecting List<Post> from the get call.
    You can use these templates in your further apps.

 */






package com.blikoon.app360;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class RetrofitActivity extends AppCompatActivity implements Callback<JSONResponse> {

    private static final String KEY_POSTS = "posts";
    private static final String REST_API_BASEURL ="http://api.learn2crack.com"; //"http://jsonplaceholder.typicode.com/";
    private PostsService postsService;
    private Call<JSONResponse> listCall;
    private List<Post> posts;
    private PostAdapter postAdapter;




    ///INTERNAL OVERIDES

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        postAdapter = new PostAdapter();
        ((RecyclerView) findViewById(R.id.card_recycler_view))
                .setAdapter(postAdapter);
        ((RecyclerView) findViewById(R.id.card_recycler_view))
                .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_API_BASEURL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        postsService = retrofit.create(PostsService.class);

        // Make sure we can handle rotation change without having to reload from the REST API
        if(savedInstanceState != null) {
            posts = savedInstanceState.getParcelableArrayList(KEY_POSTS);
            postAdapter.notifyDataSetChanged();
        }

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(posts != null) {
            outState.putParcelableArrayList(KEY_POSTS, new ArrayList<Parcelable>(posts));
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listCall != null) {
            listCall.cancel();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public void doRefresh(MenuItem item) {
        if (listCall == null) {
            listCall = postsService.getPosts();
            listCall.enqueue(this);
        } else {
            listCall.cancel();
            listCall = null;
        }
    }
    @Override
    public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
        if (response.isSuccessful()) {
            Log.d("GAKWAYA","Got a successful response."+ call.toString());

            //SANA
            JSONResponse jsonResponse = response.body();
            posts = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
            //SANA


            //posts = response.body();
            postAdapter.notifyDataSetChanged();
        }
        listCall = null;

    }

    @Override
    public void onFailure(Call<JSONResponse> call, Throwable t) {
        Toast.makeText(this, "Failed to fetch remote data , Message : " + t.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d("GAKWANDI","Failed to fetch remote data. Message : " +t.getMessage());
        Log.d("GAKWANDI","Failed to fetch remote data. Message : " +call.toString());

        listCall = null;

    }


    ////Iner private classes
    private class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
            return new PostViewHolder(view);

        }

        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            Post post = posts.get(position);
            holder.bindData(post);
        }

        @Override
        public int getItemCount() {
            return posts != null ? posts.size() : 0;
        }
    }




    private class PostViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_name;
        private final TextView tv_version;
        private final TextView tv_api_level;

        public PostViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.android_name);
            tv_version = (TextView) itemView.findViewById(R.id.android_version);
            tv_api_level = (TextView) itemView.findViewById(R.id.android_api_level);
        }

        public void bindData(Post post) {
            tv_name.setText(post.name);
            tv_version.setText(post.ver);
            tv_api_level.setText(post.api);
        }
    }






}
