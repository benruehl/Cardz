package com.hwr_goes_beuth.cardz.menu;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.presentation.PresentedActivity;

public class MainMenuActivity extends PresentedActivity<MainMenuPresenter> {

    private final View.OnTouchListener mStartGameTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != MotionEvent.ACTION_DOWN)
                return true;

            getPresenter().startGame(view.getContext());
            return false;
        }
    };

    private final View.OnTouchListener mResetUserDataTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != MotionEvent.ACTION_DOWN)
                return true;

            getPresenter().resetUserData(view.getContext());
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        findViewById(R.id.start_game_button).setOnTouchListener(mStartGameTouchListener);
        findViewById(R.id.reset_user_data_button).setOnTouchListener(mResetUserDataTouchListener);
    }

    @Override
    protected MainMenuPresenter createPresenter() {
        return new MainMenuPresenter();
    }

    @Override
    protected void update() {

    }
}
