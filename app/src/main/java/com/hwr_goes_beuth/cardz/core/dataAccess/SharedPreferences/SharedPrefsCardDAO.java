package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.UserDAO;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.User;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsCardDAO extends AbstractSharedPrefsDAO implements CardDAO {

    public SharedPrefsCardDAO(SharedPreferences sharedPreferences, Gson gson) {
        super(sharedPreferences, gson);
    }

    @Override
    public Card getCard(long id) {
        return loadFromPrefs(Card.class, id);
    }

    @Override
    public Card createCard() {
        Card newCard = new Card();
        saveToPrefs(newCard);
        return newCard;
    }

    @Override
    public void updateCard(Card card) {
        saveToPrefs(card);
    }

    @Override
    public void deleteCard(Card card) {
        deleteFromPrefs(card);
    }
}
