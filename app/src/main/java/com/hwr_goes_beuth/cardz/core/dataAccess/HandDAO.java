package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Hand;
import java.util.List;

public interface HandDAO {

    Hand getHand(long id);
    Hand createHand();
    void updateHand(Hand hand);
    void deleteHand(long id);

    List<Card> getCards(Hand hand);
}
