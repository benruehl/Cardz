package com.hwr_goes_beuth.cardz.match;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.presentation.PresentedActivity;
import com.hwr_goes_beuth.cardz.core.presentation.customViews.SimpleCardView;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.match.handView.HandViewAdapter;
import com.hwr_goes_beuth.cardz.match.handView.MarginItemDecoration;

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

        updateOpponent();
        updateMatchUser();
    }

    private void updateOpponent() {
        Field opponentField = getPresenter().getOpponentField();

        ((SimpleCardView)findViewById(R.id.match_opponent_field_left)).setCard(getPresenter().getCardById(opponentField.getLeftCardId()));
        ((SimpleCardView)findViewById(R.id.match_opponent_field_center_left)).setCard(getPresenter().getCardById(opponentField.getCenterLeftCardId()));
        ((SimpleCardView)findViewById(R.id.match_opponent_field_center)).setCard(getPresenter().getCardById(opponentField.getCenterCardId()));
        ((SimpleCardView)findViewById(R.id.match_opponent_field_center_right)).setCard(getPresenter().getCardById(opponentField.getCenterRightCardId()));
        ((SimpleCardView)findViewById(R.id.match_opponent_field_right)).setCard(getPresenter().getCardById(opponentField.getRightCardId()));
    }

    private void updateMatchUser() {
        ((TextView)findViewById(R.id.match_user_deck_button)).setText(Integer.toString(getPresenter().getMatchUserDeckCount()));

        Field matchUserField = getPresenter().getMatchUserField();

        ((SimpleCardView)findViewById(R.id.match_user_field_left)).setCard(getPresenter().getCardById(matchUserField.getLeftCardId()));
        ((SimpleCardView)findViewById(R.id.match_user_field_center_left)).setCard(getPresenter().getCardById(matchUserField.getCenterLeftCardId()));
        ((SimpleCardView)findViewById(R.id.match_user_field_center)).setCard(getPresenter().getCardById(matchUserField.getCenterCardId()));
        ((SimpleCardView)findViewById(R.id.match_user_field_center_right)).setCard(getPresenter().getCardById(matchUserField.getCenterRightCardId()));
        ((SimpleCardView)findViewById(R.id.match_user_field_right)).setCard(getPresenter().getCardById(matchUserField.getRightCardId()));

        RecyclerView handView = (RecyclerView) findViewById(R.id.match_user_hand);
        handView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        handView.setLayoutManager(layoutManager);
        handView.addItemDecoration(new MarginItemDecoration(this, R.dimen.match_card_margin));
        RecyclerView.Adapter adapter = new HandViewAdapter(getPresenter().getMatchUserHand());
        handView.setAdapter(adapter);
    }
}
