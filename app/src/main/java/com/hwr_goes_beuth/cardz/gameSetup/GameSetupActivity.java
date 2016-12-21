package com.hwr_goes_beuth.cardz.gameSetup;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.presentation.PresentedActivity;

public class GameSetupActivity extends PresentedActivity<GameSetupPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
        findViewById(R.id.start_match_button).setOnTouchListener(mStartMatchTouchListener);
    }

    @Override
    protected GameSetupPresenter createPresenter() {
        return new GameSetupPresenter();
    }

    @Override
    protected void update() {

    }

    private final View.OnTouchListener mStartMatchTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != MotionEvent.ACTION_DOWN)
                return true;

            getPresenter().startMatch(view.getContext());
            return false;
        }
    };
}
