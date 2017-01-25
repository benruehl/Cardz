package com.hwr_goes_beuth.cardz.gameSetup;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.presentation.PresentedActivity;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.game.opponents.Opponent;

import java.util.ArrayList;
import java.util.List;

public class GameSetupActivity extends PresentedActivity<GameSetupPresenter> {

    Button playerRaptor, playerShark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
        playerRaptor = (Button) findViewById(R.id.btnPlayerRaptor);
        playerShark = (Button) findViewById(R.id.btnPlayerShark);
        findViewById(R.id.start_match_button).setOnTouchListener(mStartMatchTouchListener);

        findViewById(R.id.btnPlayerRaptor).setOnTouchListener(changePlayerFactionListener);
        findViewById(R.id.btnPlayerShark).setOnTouchListener(changePlayerFactionListener);

        update();
    }

    @Override
    protected GameSetupPresenter createPresenter() {
        return new GameSetupPresenter();
    }

    @Override
    protected void update() {
        if(getPresenter().getSelectedFaction() == Faction.Raptor){
            playerRaptor.setBackground(ContextCompat.getDrawable(this, R.drawable.card_background_raptor_selected));
            playerShark.setBackground(ContextCompat.getDrawable(this, R.drawable.card_background_shark));
        } else {
            playerRaptor.setBackground(ContextCompat.getDrawable(this, R.drawable.card_background_raptor));
            playerShark.setBackground(ContextCompat.getDrawable(this, R.drawable.card_background_shark_selected));
        }

        List<Opponent> availableOpponents = getPresenter().getAvailableOpponents();
        ArrayList<Opponent> opponentList = new ArrayList<>(availableOpponents);

        GridView opponentGrid = (GridView) findViewById(R.id.opponent_grid);
        opponentGrid.setNumColumns(availableOpponents.size());
        opponentGrid.setAdapter(new GameSetupCustomAdapter(this, getPresenter(), opponentList));

        if(getPresenter().getSelectedOpponent().getFaction() == getPresenter().getSelectedFaction())
            getPresenter().setSelectedOpponent(availableOpponents.get(0));

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

    private final View.OnTouchListener changePlayerFactionListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != MotionEvent.ACTION_DOWN)
                return true;

            if(view == playerRaptor) {
                getPresenter().setSelectedFaction(Faction.Raptor);
            }

            if(view == playerShark) {
                getPresenter().setSelectedFaction(Faction.Shark);
            }
            return false;
        }
    };
}
