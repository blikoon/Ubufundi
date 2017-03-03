/*
    *Show images in ListViews using CardView and RecyclerView
    * Use android.support.v7.widget.CardView and android.support.percent.PercentRelativeLayout to wrap around the layout used by your ViewHolder subclass
    * CardView gives you those nice Material rectangles you can stuff your viewItem in
    * PercentRelativeLayout allows easy resizing of items for 1:1 or 16:9 ration required by Material design
    * Only show item image and title when user is scrolling and display detailed description when the item is explicitly tapped and becomes the "current item".This is taken care of in the onBindViewHolder override.
 */

package com.blikoon.app110;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = new RecyclerView(this);
        setContentView(recyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new CardViewAdapter(this));
    }
}
