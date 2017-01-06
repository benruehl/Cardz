package com.hwr_goes_beuth.cardz.match.handView;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.presentation.customViews.SimpleCardView;
import com.hwr_goes_beuth.cardz.entities.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Project0rion on 05.01.2017.
 */

public class HandViewAdapter extends RecyclerView.Adapter<HandViewAdapter.ViewHolder> {

    private List<Card> mDataset;

    public HandViewAdapter(Collection<Card> dataSet) {
        mDataset = new ArrayList<>(dataSet);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_simple_card, parent, false);
        Resources contextResources = parent.getContext().getResources();
        SimpleCardView cardView = new SimpleCardView(parent.getContext());
        cardView.setLayoutParams(new RecyclerView.LayoutParams((int)contextResources.getDimension(R.dimen.match_card_width), (int)contextResources.getDimension(R.dimen.match_card_height)));

        ViewHolder vh = new ViewHolder(cardView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mCardView.setCard(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleCardView mCardView;

        public ViewHolder(SimpleCardView v) {
            super(v);
            mCardView = v;
        }
    }
}
