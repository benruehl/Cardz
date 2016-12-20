package com.hwr_goes_beuth.cardz.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Hand extends Entity {

    List<Long> cardIds;

    public Hand() {
        cardIds = new ArrayList<>();
    }

    public List<Long> getCardIds() {
        return cardIds;
    }
}
