package com.hwr_goes_beuth.cardz.gameSetup;

import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.presentation.PresentedActivity;
import com.hwr_goes_beuth.cardz.game.opponents.Opponent;

import java.util.ArrayList;
import java.util.List;

public class GameSetupActivity extends PresentedActivity<GameSetupPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
        findViewById(R.id.start_match_button).setOnTouchListener(mStartMatchTouchListener);
        TextView text = (TextView) findViewById(R.id.setup_title);
        update();
    }

    @Override
    protected GameSetupPresenter createPresenter() {
        return new GameSetupPresenter();
    }

    @Override
    protected void update() {
        List<Opponent> availableOpponents = getPresenter().getAvailableOpponents();
        ArrayList<Opponent> opponentList = new ArrayList<>(availableOpponents);

        GridView opponentGrid = (GridView) findViewById(R.id.opponent_grid);
        opponentGrid.setNumColumns(availableOpponents.size());
        opponentGrid.setAdapter(new GameSetupCustomAdapter(this, getPresenter(), opponentList));


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
