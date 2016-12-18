package com.hwr_goes_beuth.cardz.gameSetup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.presentation.PresentedActivity;

public class GameSetupActivity extends PresentedActivity<GameSetupPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
    }

    @Override
    protected GameSetupPresenter createPresenter() {
        return new GameSetupPresenter();
    }

    @Override
    protected void update() {

    }
}
