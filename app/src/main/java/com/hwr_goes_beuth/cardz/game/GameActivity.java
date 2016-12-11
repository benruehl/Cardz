package com.hwr_goes_beuth.cardz.game;

import com.hwr_goes_beuth.cardz.core.presentation.PresentedActivity;

/**
 * Created by Project0rion on 11.12.2016.
 */
public class GameActivity extends PresentedActivity<GamePresenter> {

    @Override
    protected GamePresenter createPresenter() {
        return new GamePresenter();
    }

    @Override
    protected void update() {

    }
}
