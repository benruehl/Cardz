package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.FieldDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.HandDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.PlayerDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.UserDAO;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsDAOFactory extends DAOFactory {

    private SharedPrefsDAOContext daoContext;

    private UserDAO userDAO;
    private MatchDAO matchDAO;
    private PlayerDAO playerDAO;
    private DeckDAO deckDAO;
    private HandDAO handDAO;
    private FieldDAO fieldDAO;
    private CardDAO cardDAO;

    public SharedPrefsDAOFactory(SharedPreferences prefs, Gson gson) {
        daoContext = new SharedPrefsDAOContext(prefs, gson);
    }

    @Override
    public UserDAO getUserDAO() {

        if (userDAO == null)
            userDAO = new SharedPrefsUserDAO(daoContext, getMatchDAO(), getDeckDAO(), getCardDAO());

        return userDAO;
    }

    @Override
    public MatchDAO getMatchDAO() {
        if (matchDAO == null)
            matchDAO = new SharedPrefsMatchDAO(daoContext, getPlayerDAO());

        return matchDAO;
    }

    @Override
    public PlayerDAO getPlayerDAO() {
        if (playerDAO == null)
            playerDAO = new SharedPrefsPlayerDAO(daoContext, getDeckDAO(), getFieldDAO(), getHandDAO());

        return playerDAO;
    }

    @Override
    public DeckDAO getDeckDAO() {
        if (deckDAO == null)
            deckDAO = new SharedPrefsDeckDAO(daoContext, getCardDAO());

        return deckDAO;
    }

    @Override
    public HandDAO getHandDAO() {
        if (handDAO == null)
            handDAO = new SharedPrefsHandDAO(daoContext, getCardDAO());

        return handDAO;
    }

    @Override
    public FieldDAO getFieldDAO() {
        if (fieldDAO == null)
            fieldDAO = new SharedPrefsFieldDAO(daoContext, getCardDAO());

        return fieldDAO;
    }

    @Override
    public CardDAO getCardDAO() {
        if (cardDAO == null)
            cardDAO = new SharedPrefsCardDAO(daoContext);

        return cardDAO;
    }
}
