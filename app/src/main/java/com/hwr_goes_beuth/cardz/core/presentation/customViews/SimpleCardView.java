package com.hwr_goes_beuth.cardz.core.presentation.customViews;

import android.content.Context;
import android.transition.Visibility;
import android.util.AttributeSet;
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

        clear();
    }

    public void setCard(Card cardToDisplay) {
        if (cardToDisplay == null) {
            clear();
            return;
        }

        setDataVisibility(VISIBLE);

        damageTextView.setText(Integer.toString(cardToDisplay.getDamage()));
        healthTextView.setText(Integer.toString(cardToDisplay.getHealth()));

        switch (cardToDisplay.getFaction()) {
            case Raptor:
                setBackground(getResources().getDrawable(R.drawable.card_background_raptor));
                break;
            case Shark:
                setBackground(getResources().getDrawable(R.drawable.card_background_shark));
                break;
        }
    }

    public void clear() {
        setBackground(getResources().getDrawable(R.drawable.card_background_empty));
        setDataVisibility(INVISIBLE);
    }

    private void setDataVisibility(int visibility) {
        ((ViewGroup)rootView).getChildAt(0).setVisibility(visibility);
    }
}
