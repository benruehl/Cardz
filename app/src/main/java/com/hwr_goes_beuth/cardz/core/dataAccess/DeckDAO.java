package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import java.util.List;

public interface DeckDAO {

    Deck getDeck(long id);
    Deck createRaptorDeck();
    Deck createSharkDeck();
    void updateDeck(Deck deck);
    void deleteDeck(long deckId);

    List<Card> getCards(Deck deck);
    Card createCardInDeck(Deck deck);
}
