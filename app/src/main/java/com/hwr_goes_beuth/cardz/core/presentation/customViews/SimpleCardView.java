package com.hwr_goes_beuth.cardz.core.presentation.customViews;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.entities.Card;

/**
 * Created by Project0rion on 03.01.2017.
 */
public class SimpleCardView extends RelativeLayout {

    private enum CardViewState {
        Empty, FaceUp, FaceDown
    }

    private CardViewState state;
    private Card displayedCard;
    private boolean canBeDragged;

    private View rootView;
    private View factionIcon;
    private TextView damageTextView;
    private TextView healthTextView;
    private TextView costTextView;

    public SimpleCardView(Context context) {
        super(context);
        init(context);
    }

    public SimpleCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.view_simple_card, this);
        damageTextView = (TextView) rootView.findViewById(R.id.card_damage_text);
        healthTextView = (TextView) rootView.findViewById(R.id.card_health_text);

        state = CardViewState.Empty;
        updateViewState();
    }

    public void setCardFacedUp(Card cardToDisplay) {
        if (cardToDisplay == null) {
            state = CardViewState.Empty;
            return;
        }

        state = CardViewState.FaceUp;
        setCardData(cardToDisplay);
        updateViewState();
    }

    public void setCardFacedDown(Card cardToDisplay) {
        if (cardToDisplay == null) {
            state = CardViewState.Empty;
            return;
        }

        state = CardViewState.FaceDown;
        setCardData(cardToDisplay);
        updateViewState();
    }

    private void setCardData(Card cardToDisplay) {
        displayedCard = cardToDisplay;

        damageTextView.setText(Integer.toString(cardToDisplay.getDamage()));
        healthTextView.setText(Integer.toString(cardToDisplay.getHealth()));
    }

    private void updateViewState() {
        if (displayedCard == null)
            state = CardViewState.Empty;

        switch (state) {
            case Empty:
                clearCard();
                setOnTouchListener(null);
                setOnDragListener(onDragListener);
                break;
            case FaceDown:
                setDataVisibility(INVISIBLE);
                setBackground(getResources().getDrawable(R.drawable.card_background_facedown));
                setOnTouchListener(null);
                setOnDragListener(null);
                break;
            case FaceUp:
                setDataVisibility(VISIBLE);

                switch (displayedCard.getFaction()) {
                    case Raptor:
                        setBackground(getResources().getDrawable(R.drawable.card_background_raptor));
                        break;
                    case Shark:
                        setBackground(getResources().getDrawable(R.drawable.card_background_shark));
                        break;
                }

                if (canBeDragged)
                    setOnTouchListener(onTouchListener);
                else
                    setOnTouchListener(null);

                setOnDragListener(null);
                break;
        }
    }

    public void clearCard() {
        displayedCard = null;

        setBackground(getResources().getDrawable(R.drawable.card_background_empty));
        setDataVisibility(INVISIBLE);
        setOnTouchListener(null);
    }

    private void setDataVisibility(int visibility) {
        ((ViewGroup)rootView).getChildAt(0).setVisibility(visibility);
    }


    // ############# //
    // Drag and Drop //
    // ############# //

    public void enableDragging(CardViewDragHandler cardDragHandler) {
        canBeDragged = true;
        this.cardDragHandler = cardDragHandler;
    }

    public void enableDropping(CardViewDropHandler cardDropHandler) {
        this.cardDropHandler = cardDropHandler;
    }

    private CardViewDragHandler cardDragHandler;
    private CardViewDropHandler cardDropHandler;

    private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (!canBeDragged)
                    return false;

                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    };

    private final View.OnDragListener onDragListener = new View.OnDragListener() {
        Drawable dragOverDrawable = getResources().getDrawable(R.drawable.card_background_drag_over);
        Drawable validTargetDrawable = getResources().getDrawable(R.drawable.card_background_valid_target);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            SimpleCardView sourceView = (SimpleCardView) event.getLocalState();

            if (!isValidDropTarget(sourceView))
                return false;

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    v.setBackground(validTargetDrawable);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(dragOverDrawable);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(validTargetDrawable);
                    break;
                case DragEvent.ACTION_DROP:
                    cardDropHandler.onDroppedCard((SimpleCardView) v, sourceView.displayedCard);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    updateViewState();

                    if (cardDragHandler != null)
                        cardDragHandler.onDragEnded();
                default:
                    break;
            }
            return true;
        }
    };

    private boolean isValidDropTarget(View v) {
        if (!(v instanceof SimpleCardView))
            return false;

        if (((SimpleCardView)v).displayedCard == null)
            return false;

        if (cardDropHandler == null)
            return false;

        return state == CardViewState.Empty && displayedCard == null;
    }


    public interface CardViewDropHandler {
        void onDroppedCard(SimpleCardView dropTarget, Card sourceCard);
    }

    public interface CardViewDragHandler {
        void onDragEnded();
    }
}
