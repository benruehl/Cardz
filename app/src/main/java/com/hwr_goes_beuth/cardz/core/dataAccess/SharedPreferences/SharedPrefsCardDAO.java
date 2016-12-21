package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.entities.Card;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsCardDAO implements CardDAO {

    private SharedPrefsDAOContext context;

    public SharedPrefsCardDAO(SharedPrefsDAOContext context) {
        this.context = context;
    }

    @Override
    public Card getCard(long id) {
        return context.loadFromPrefs(Card.class, id);
    }

    @Override
    public Card createCard() {
        Card newCard = new Card();
        newCard.setId(context.getNextId());
        context.saveToPrefs(newCard);
        return newCard;
    }

    @Override
    public void updateCard(Card card) {
        context.saveToPrefs(card);
    }

    @Override
    public void deleteCard(Card card) {
        context.deleteFromPrefs(card);
    }
}
