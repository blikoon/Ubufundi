package com.blikoon.app181;

import android.support.v7.widget.GridLayoutManager;

public class GridStaggerLookup extends GridLayoutManager.SpanSizeLookup {
    @Override
    //Return the item span count for row/column at position
    public int getSpanSize(int position) {
        return (position % 3 == 0 ? 2 : 1);
    }
}
