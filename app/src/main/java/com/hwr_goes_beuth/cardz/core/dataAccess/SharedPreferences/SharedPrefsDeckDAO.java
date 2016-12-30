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
public class SharedPrefsDeckDAO implements DeckDAO {

    private SharedPrefsDAOContext context;
    private CardDAO cardDAO;

    public SharedPrefsDeckDAO(SharedPrefsDAOContext context, CardDAO cardDAO) {
        this.context = context;
        this.cardDAO = cardDAO;
    }

    @Override
    public Deck getDeck(long id) {
        return context.loadFromPrefs(Deck.class, id);
    }

    @Override
    public Deck createRaptorDeck() {
        return createDeck(Faction.Raptor);
    }

    @Override
    public Deck createSharkDeck() {
        return createDeck(Faction.Shark);
    }

    @Override
    public void updateDeck(Deck deck) {
        context.saveToPrefs(deck);
    }

    private Deck createDeck(Faction faction) {
        Deck newDeck = new Deck();
        newDeck.setId(context.getNextId());
        newDeck.setFaction(faction);

        context.saveToPrefs(newDeck);

        return newDeck;
    }

    @Override
    public void deleteDeck(long deckId) {
        Deck deck = getDeck(deckId);

        for (long cardId : deck.getCardIds()) {
            cardDAO.deleteCard(cardId);
        }

        context.deleteFromPrefs(deck);
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
    public Card createCardInDeck(Deck deck) {
        Card newCard = cardDAO.createCard();
        newCard.setFaction(deck.getFaction());
        cardDAO.updateCard(newCard);

        deck.getCardIds().add(newCard.getId());
        updateDeck(deck);

        return newCard;
    }
}
