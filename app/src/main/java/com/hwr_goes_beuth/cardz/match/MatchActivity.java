package com.hwr_goes_beuth.cardz.match;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.presentation.PresentedActivity;

/**
 * Created by Project0rion on 11.12.2016.
 */
public class MatchActivity extends PresentedActivity<MatchPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        findViewById(R.id.run_phase_button).setOnTouchListener(mRunPhaseTouchListener);
        update();
    }

    private final View.OnTouchListener mRunPhaseTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != MotionEvent.ACTION_DOWN)
                return true;

            getPresenter().runMatchPhase();
            return false;
        }
    };

    @Override
    protected MatchPresenter createPresenter() {
        return new MatchPresenter();
    }

    @Override
    protected void update() {
        ((TextView)findViewById(R.id.match_phase_title)).setText(getPresenter().getCurrentPhaseTitle());
        ((TextView)findViewById(R.id.match_user_deck_button)).setText(Integer.toString(getPresenter().getMatchUserDeckCount()));
    }
}
