package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;

import java.util.List;

/**
 * Created by Project0rion on 19.12.2016.
 */
public interface DeckDAO {

    List<Card> getCards(Deck deck);
    Deck createRaptorDeck();
    Deck createSharkDeck();
}
