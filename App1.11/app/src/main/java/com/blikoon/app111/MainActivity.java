/*
        *Show Section Headers in RecyclerView
        *Use the  getItemViewType(int position) override to specify different view types
        *Use differently styled ViewHolders in the onBindViewHolder override
        *In this example ,we have a header viewType and an item viewType
        *The layout for the header viewtype ViewHolder is styled with a blueish background to highlight it
        *You can add in more customized headers and item layouts, this example is just making a point.
 */

package com.blikoon.app111;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        SectionsAdapter adapter = new SectionsAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);


        adapter.addSection("Fruits", new String[]{"Apples", "Oranges", "Bananas", "Mangos"});
        adapter.addSection("Vegetables", new String[]{"Carrots", "Peas", "Broccoli"});
        adapter.addSection("Meats", new String[]{"Pork", "Chicken", "Beef", "Lamb"});

        setContentView(recyclerView);
    }
}
