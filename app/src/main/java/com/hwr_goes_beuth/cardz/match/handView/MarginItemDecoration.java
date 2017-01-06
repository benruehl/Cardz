package com.hwr_goes_beuth.cardz.match.handView;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Project0rion on 06.01.2017.
 */

public class MarginItemDecoration extends RecyclerView.ItemDecoration {

    private float margin;

    public MarginItemDecoration(Context context, int marginResourceId) {
        margin = context.getResources().getDimension(marginResourceId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (!(parent.getLayoutManager() instanceof LinearLayoutManager))
            return;

        // set margins between items depending on orientation
        // do not set double margins for first and last item

        if (((LinearLayoutManager)parent.getLayoutManager()).getOrientation() == LinearLayoutManager.HORIZONTAL) {
            if (parent.getChildAdapterPosition(view) != 0)
                outRect.left = (int)margin;
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)
                outRect.right = (int)margin;

        } else {
            if (parent.getChildAdapterPosition(view) != 0)
                outRect.top = (int)margin;
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)
                outRect.bottom = (int)margin;
        }
    }
}
