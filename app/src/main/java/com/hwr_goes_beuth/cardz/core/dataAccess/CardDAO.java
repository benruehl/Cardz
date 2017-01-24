package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Card;
import java.util.List;

public interface CardDAO {

    Card getCard(long id);
    Card createCard();
    void updateCard(Card card);
    void deleteCard(long id);
}
