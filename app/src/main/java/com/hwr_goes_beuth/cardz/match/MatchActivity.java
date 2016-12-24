package com.hwr_goes_beuth.cardz.match;

import android.os.Bundle;

import com.hwr_goes_beuth.cardz.core.presentation.PresentedActivity;

/**
 * Created by Project0rion on 11.12.2016.
 */
public class MatchActivity extends PresentedActivity<MatchPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MatchPresenter createPresenter() {
        return new MatchPresenter();
    }

    @Override
    protected void update() {

    }
}
