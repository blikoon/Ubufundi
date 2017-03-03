/*
        *Customize Empty Data Sets
        *Show a visual representation that the data set is empty
        * Show some kind or refresh button to load data into the view
        * Override getItemViewType(int position) to let the adapter know which kind of view it is dealing with.
        * NOTE : There is not actual data in the adapter of this example.In onBindViewHolder, we are simply tricking the view into thinking it has more items to display using notifyItemRangeInserted(0, 10)
        *
 */

package com.blikoon.app19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new EmptyStateAdapter(MainActivity.this));
    }
}


