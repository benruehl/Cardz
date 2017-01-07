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
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.match.handView.HandViewAdapter;
import com.hwr_goes_beuth.cardz.match.handView.MarginItemDecoration;

/**
 * Created by Project0rion on 11.12.2016.
 */
public class MatchActivity extends PresentedActivity<MatchPresenter> {

    private SimpleCardView cardSlot_MatchUserLeft;
    private SimpleCardView cardSlot_MatchUserCenterLeft;
    private SimpleCardView cardSlot_MatchUserCenter;
    private SimpleCardView cardSlot_MatchUserCenterRight;
    private SimpleCardView cardSlot_MatchUserRight;
    private SimpleCardView cardSlot_OpponentLeft;
    private SimpleCardView cardSlot_OpponentCenterLeft;
    private SimpleCardView cardSlot_OpponentCenter;
    private SimpleCardView cardSlot_OpponentCenterRight;
    private SimpleCardView cardSlot_OpponentRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        findViewById(R.id.run_phase_button).setOnTouchListener(mRunPhaseTouchListener);

        cardSlot_MatchUserLeft = ((SimpleCardView)findViewById(R.id.match_user_field_left));
        cardSlot_MatchUserCenterLeft = ((SimpleCardView)findViewById(R.id.match_user_field_center_left));
        cardSlot_MatchUserCenter = ((SimpleCardView)findViewById(R.id.match_user_field_center));
        cardSlot_MatchUserCenterRight = ((SimpleCardView)findViewById(R.id.match_user_field_center_right));
        cardSlot_MatchUserRight = ((SimpleCardView)findViewById(R.id.match_user_field_right));
        cardSlot_OpponentLeft = ((SimpleCardView)findViewById(R.id.match_opponent_field_left));
        cardSlot_OpponentCenterLeft = ((SimpleCardView)findViewById(R.id.match_opponent_field_center_left));
        cardSlot_OpponentCenter = ((SimpleCardView)findViewById(R.id.match_opponent_field_center));
        cardSlot_OpponentCenterRight = ((SimpleCardView)findViewById(R.id.match_opponent_field_center_right));
        cardSlot_OpponentRight = ((SimpleCardView)findViewById(R.id.match_opponent_field_right));


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

        cardSlot_OpponentLeft.setCardFacedUp(getPresenter().getCardById(opponentField.getLeftCardId()));
        cardSlot_OpponentCenterLeft.setCardFacedUp(getPresenter().getCardById(opponentField.getCenterLeftCardId()));
        cardSlot_OpponentCenter.setCardFacedUp(getPresenter().getCardById(opponentField.getCenterCardId()));
        cardSlot_OpponentCenterRight.setCardFacedUp(getPresenter().getCardById(opponentField.getCenterRightCardId()));
        cardSlot_OpponentRight.setCardFacedUp(getPresenter().getCardById(opponentField.getRightCardId()));
    }

    private void updateMatchUser() {
        ((TextView)findViewById(R.id.match_user_deck_button)).setText(Integer.toString(getPresenter().getMatchUserDeckCount()));

        Field matchUserField = getPresenter().getMatchUserField();

        cardSlot_MatchUserLeft.setCardFacedUp(getPresenter().getCardById(matchUserField.getLeftCardId()));
        cardSlot_MatchUserCenterLeft.setCardFacedUp(getPresenter().getCardById(matchUserField.getCenterLeftCardId()));
        cardSlot_MatchUserCenter.setCardFacedUp(getPresenter().getCardById(matchUserField.getCenterCardId()));
        cardSlot_MatchUserCenterRight.setCardFacedUp(getPresenter().getCardById(matchUserField.getCenterRightCardId()));
        cardSlot_MatchUserRight.setCardFacedUp(getPresenter().getCardById(matchUserField.getRightCardId()));

        cardSlot_MatchUserLeft.enableDropping(cardViewDropHandler);
        cardSlot_MatchUserCenterLeft.enableDropping(cardViewDropHandler);
        cardSlot_MatchUserCenter.enableDropping(cardViewDropHandler);
        cardSlot_MatchUserCenterRight.enableDropping(cardViewDropHandler);
        cardSlot_MatchUserRight.enableDropping(cardViewDropHandler);

        RecyclerView handView = (RecyclerView) findViewById(R.id.match_user_hand);
        handView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        handView.setLayoutManager(layoutManager);
        handView.addItemDecoration(new MarginItemDecoration(this, R.dimen.match_card_margin));
        HandViewAdapter adapter = new HandViewAdapter(getPresenter().getMatchUserHand(), true);
        adapter.setItemsDraggable();
        handView.setAdapter(adapter);
    }

    private final SimpleCardView.CardViewDropHandler cardViewDropHandler = new SimpleCardView.CardViewDropHandler() {
        @Override
        public void onDroppedCard(SimpleCardView dropTarget, Card sourceCard) {

        }
    };
}
