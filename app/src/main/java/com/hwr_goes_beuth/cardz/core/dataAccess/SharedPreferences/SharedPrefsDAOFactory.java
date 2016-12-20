package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.FieldDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.HandDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchUserDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.OpponentDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.UserDAO;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsDAOFactory extends DAOFactory {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    UserDAO userDAO;
    MatchDAO matchDAO;
    MatchUserDAO matchUserDAO;
    OpponentDAO opponentDAO;
    DeckDAO deckDAO;
    HandDAO handDAO;
    FieldDAO fieldDAO;
    CardDAO cardDAO;

    public SharedPrefsDAOFactory(SharedPreferences prefs, Gson gson) {
        sharedPreferences = prefs;
        this.gson = gson;
    }

    @Override
    public UserDAO getUserDAO() {

        if (userDAO == null)
            userDAO = new SharedPrefsUserDAO(sharedPreferences, gson, getDeckDAO());

        return userDAO;
    }

    @Override
    public MatchDAO getMatchDAO() {
        if (matchDAO == null)
            matchDAO = new SharedPrefsMatchDAO(sharedPreferences, gson, getMatchUserDAO(), getOpponentDAO());

        return matchDAO;
    }

    @Override
    public MatchUserDAO getMatchUserDAO() {
        if (matchUserDAO == null)
            matchUserDAO = new SharedPrefsMatchUserDAO(sharedPreferences, gson);

        return matchUserDAO;
    }

    @Override
    public OpponentDAO getOpponentDAO() {
        if (opponentDAO == null)
            opponentDAO = new SharedPrefsOpponentDAO(sharedPreferences, gson);

        return opponentDAO;
    }

    @Override
    public DeckDAO getDeckDAO() {
        if (deckDAO == null)
            deckDAO = new SharedPrefsDeckDAO(sharedPreferences, gson, getCardDAO());

        return deckDAO;
    }

    @Override
    public HandDAO getHandDAO() {
        if (handDAO == null)
            handDAO = new SharedPrefsHandDAO(sharedPreferences, gson, getCardDAO());

        return handDAO;
    }

    @Override
    public FieldDAO getFieldDAO() {
        if (fieldDAO == null)
            fieldDAO = new SharedPrefsFieldDAO(sharedPreferences, gson);

        return fieldDAO;
    }

    @Override
    public CardDAO getCardDAO() {
        if (cardDAO == null)
            cardDAO = new SharedPrefsCardDAO(sharedPreferences, gson);

        return cardDAO;
    }
}
