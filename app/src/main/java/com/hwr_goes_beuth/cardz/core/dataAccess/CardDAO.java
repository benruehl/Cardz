package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Card;

/**
 * Created by Project0rion on 19.12.2016.
 */
public interface CardDAO {

    Card getCard(long id);
    Card createCard();
    void updateCard(Card card);
    void deleteCard(Card card);
}
