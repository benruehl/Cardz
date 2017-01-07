package com.hwr_goes_beuth.cardz.game.opponents;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Field;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Created by Project0rion on 23.12.2016.
 */
public interface OpponentBehavior {

    PriorityQueue<Card> getCardsToPlay(Collection<Card> playableCards);
    PriorityQueue<Opponent.CardSlotPosition> prioritizeAvailableCardSlots(Field field, Field matchUserField);
}
