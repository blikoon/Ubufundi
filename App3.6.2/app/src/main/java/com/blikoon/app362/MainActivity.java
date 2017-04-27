package com.blikoon.app362;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<Repo>> {

    private static final String REST_API_BASEURL = "https://api.github.com/";
    public static final String LOGTAG = "BLIKOON_REPOS";
    private RepoService repoService;
    private Call<List<Repo>> listCall;//Use this to send requests to the server
    private List<Repo> repos; //This is where you store responses from the server
    private RepoAdapter repoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_repo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        repoAdapter = new RepoAdapter();
        ((RecyclerView) findViewById(R.id.content_retrofit))
                .setAdapter(repoAdapter);
        ((RecyclerView) findViewById(R.id.content_retrofit))
                .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        repoService = retrofit.create(RepoService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void doRefresh(MenuItem item) {
        if (listCall == null) {
            listCall = repoService.getRepos();
            listCall.enqueue(this);
        } else {
            listCall.cancel();
            listCall = null;
        }
    }

    @Override
    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        Log.d(LOGTAG,"Request successful");
        if (response.isSuccessful()) {
            repos = response.body();
            Log.d(LOGTAG," Got "+ repos.size() + " repositories belonging to blikoon");

            repoAdapter.notifyDataSetChanged();
        }
        listCall = null;

    }

    @Override
    public void onFailure(Call<List<Repo>> call, Throwable t) {
        Toast.makeText(this, "Failed to fetch repos!", Toast.LENGTH_SHORT).show();
        listCall = null;
    }


    private class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {
        @Override
        public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.org_repo_item, parent, false);
            return new RepoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RepoViewHolder holder, int position) {
            Repo repo = repos.get(position);
            holder.bindData(repo);
        }

        @Override
        public int getItemCount() {
            return repos != null ? repos.size() : 0;
        }
    }

    private class RepoViewHolder extends RecyclerView.ViewHolder {
        private final TextView idTextview;
        private final TextView nameTextView;
        private final  TextView fullNameTextView;
        private final  TextView descTextView;

        public RepoViewHolder(View itemView) {
            super(itemView);
            idTextview = (TextView) itemView.findViewById(R.id.id);
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            fullNameTextView = (TextView) itemView.findViewById(R.id.full_name);
            descTextView = (TextView) itemView.findViewById(R.id.desc);
        }

        public void bindData(Repo repo) {
            idTextview.setText(repo.getId().toString());
            nameTextView.setText(repo.getName());
            fullNameTextView.setText(repo.getFullName());
            descTextView.setText(repo.getDescription());
        }
    }
}



