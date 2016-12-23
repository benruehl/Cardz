package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;

import java.util.List;

/**
 * Created by Project0rion on 19.12.2016.
 */
public interface DeckDAO {

    Deck getDeck(long id);
    Deck createRaptorDeck();
    Deck createSharkDeck();
    void updateDeck(Deck deck);
    void deleteDeck(long deckId);

    List<Card> getCards(Deck deck);
    Card createCardInDeck(Deck deck);
}
