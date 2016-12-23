package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Hand;

import java.util.List;

/**
 * Created by Project0rion on 19.12.2016.
 */
public interface HandDAO {

    Hand getHand(long id);
    Hand createHand();
    void deleteHand(long handId);

    List<Card> getCards(Hand hand);
}