package com.hwr_goes_beuth.cardz.match.handView;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Project0rion on 08.01.2017.
 */

public class OverlapItemDecoration extends RecyclerView.ItemDecoration {

    private float horizontalOverlap;

    public OverlapItemDecoration(Context context, int itemWidthResourceId) {
        float itemWidth = context.getResources().getDimension(itemWidthResourceId);
        horizontalOverlap = itemWidth - (itemWidth / 4);
    }

    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        // test
        if (view != null)
            return;

        if (parent.getChildAdapterPosition(view) != 0 && parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)
            outRect.set(- (int)horizontalOverlap, 0, 0, 0);
    }
}
