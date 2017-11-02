package com.blikoon.recyclerview;

/**
 * Created by gakwaya on 2017/11/1.
 */

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
        outRect.set(mInsetMargin, 0, mInsetMargin, 0);

    }
}
