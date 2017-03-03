/*
        * Use RecyclerView to show ListItems
        * Decide how your  items are laid out using LayoutManagers
        * Supported LayoutManagers:
        *   LinearLayoutManagers
        *   GridLayoutManagers
        *   Interface between the View and actual data using adapters
        *   Adapters have to extend RecyclerView.Adapter
        *   Adapters have to implement three key methods:
        *    onCreateViewHolder() : Loads the layout file that embodies the looks of a single item in the list
        *    onBindViewHolder () : Stuffs the data in the item from the data set in the adapter
        *    getItemCount () : used to report the number of items to the RecyclerView
        *   Add a subclass of RecyclerView.ViewHolder that implements the logic to manipulate single viewItems
        *   Control the item span count on each row/column in GridLayoutManager using GridStaggerLookup
        *   Control the spacing between items using RecyclerView.ItemDecoration
        *   ItemDecoration can be customized even more to add stunning drawn effects( Look at ConnectorDecoration)
        *
 */



package com.blikoon.app181;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
        SimpleItemAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private SimpleItemAdapter mAdapter;
    /* Layout Managers */
    private LinearLayoutManager mHorizontalManager;
    private LinearLayoutManager mVerticalManager;
    private GridLayoutManager mVerticalGridManager;
    private GridLayoutManager mHorizontalGridManager;
    /* Decorations */
    private ConnectorDecoration mConnectors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView = new RecyclerView(this);
        mHorizontalManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        mVerticalManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mVerticalGridManager = new GridLayoutManager(this,
                2, /* Number of grid columns */
                LinearLayoutManager.VERTICAL, /* Orient grid vertically */
                false);
        mHorizontalGridManager = new GridLayoutManager(this,
                3, /* Number of grid rows */
                LinearLayoutManager.HORIZONTAL, /* Orient grid horizontally */
                false);
        //Connector line decorations for vertical grid
        mConnectors = new ConnectorDecoration(this);
        //Stagger the vertical grid
        mVerticalGridManager.setSpanSizeLookup(new GridStaggerLookup());
        mAdapter = new SimpleItemAdapter(this);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        //Apply margins decoration to all collections
        mRecyclerView.addItemDecoration(new InsetDecoration(this));
        //Default to vertical layout
        selectLayoutManager(R.id.action_vertical);


        setContentView(mRecyclerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return selectLayoutManager(item.getItemId());
    }


    private boolean selectLayoutManager(int id) {
        switch (id) {
            case R.id.action_vertical:
                mRecyclerView.setLayoutManager(mVerticalManager);
                mRecyclerView.removeItemDecoration(mConnectors);
                return true;
            case R.id.action_horizontal:
                mRecyclerView.setLayoutManager(mHorizontalManager);

                mRecyclerView.removeItemDecoration(mConnectors);
                return true;
            case R.id.action_grid_vertical:
                mRecyclerView.setLayoutManager(mVerticalGridManager);
                mRecyclerView.addItemDecoration(mConnectors);
                return true;
            case R.id.action_grid_horizontal:
                mRecyclerView.setLayoutManager(mHorizontalGridManager);
                mRecyclerView.removeItemDecoration(mConnectors);
                return true;
            case R.id.action_add_item:
                //Insert a new item
                mAdapter.insertItemAtIndex("Android Recipes", 1);
                return true;
            case R.id.action_remove_item:
                //Remove the first item
                mAdapter.removeItemAtIndex(1);
                return true;
            default:
                return false;
        }
    }

    /** OnItemClickListener Methods */
    @Override
    public void onItemClick(SimpleItemAdapter.ItemHolder item, int position) {
        Toast.makeText(this, item.getSummary(), Toast.LENGTH_SHORT).show();
    }
}
