package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsDeckDAO extends AbstractSharedPrefsDAO implements DeckDAO {

    CardDAO cardDAO;

    public SharedPrefsDeckDAO(SharedPreferences sharedPreferences, Gson gson, CardDAO cardDAO) {
        super(sharedPreferences, gson);

        this.cardDAO = cardDAO;
    }

    @Override
    public List<Card> getCards(Deck deck) {
        List<Card> cards = new ArrayList<>();

        for (long cardId : deck.getCardIds()) {
            Card card = cardDAO.getCard(cardId);
            cards.add(card);
        }

        return cards;
    }

    @Override
    public Deck createRaptorDeck() {
        return createDeck(Faction.Raptor);
    }

    @Override
    public Deck createSharkDeck() {
        return createDeck(Faction.Shark);
    }

    private Deck createDeck(Faction faction) {
        Deck newDeck = new Deck();
        newDeck.setFaction(faction);

        saveToPrefs(newDeck);

        return newDeck;
    }
}
