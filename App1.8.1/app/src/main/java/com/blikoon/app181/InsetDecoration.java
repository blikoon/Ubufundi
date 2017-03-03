package com.blikoon.app181;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class InsetDecoration extends RecyclerView.ItemDecoration {
    private int mInsetMargin;
    public InsetDecoration(Context context) {
        super();
        mInsetMargin = context.getResources()
                .getDimensionPixelOffset(R.dimen.inset_margin);
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
//Apply the calculated margin to all four edges of the child view
        outRect.set(mInsetMargin, mInsetMargin, mInsetMargin, mInsetMargin);
    }
}
