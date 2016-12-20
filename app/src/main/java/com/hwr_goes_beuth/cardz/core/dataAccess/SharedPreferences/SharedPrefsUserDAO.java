package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.UserDAO;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.User;

import java.util.List;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsUserDAO extends AbstractSharedPrefsDAO implements UserDAO {

    DeckDAO deckDAO;

    public SharedPrefsUserDAO(SharedPreferences sharedPreferences, Gson gson, DeckDAO deckDAO) {
        super(sharedPreferences, gson);
        this.deckDAO = deckDAO;
    }

    @Override
    public User getOrCreateRecentUser() {
        User recentUser = getRecentUser();

        if (recentUser == null)
            recentUser = createUser();

        return recentUser;
    }

    private User getRecentUser() {
        return loadFromPrefs(User.class, 0);
    }

    private User createUser() {
        User newUser = new User();

        Deck newRaptorDeck = deckDAO.createRaptorDeck();
        Deck newSharkDeck = deckDAO.createSharkDeck();

        newUser.setRaptorDeckId(newRaptorDeck.getId());
        newUser.setSharkDeckId(newSharkDeck.getId());

        saveToPrefs(newUser);

        return newUser;
    }

    @Override
    public List<Card> getCollectedCards(User user) {
        return null;
    }

    @Override
    public Match getCurrentMatch(User user) {
        return null;
    }

    @Override
    public Deck getRaptorDeck(User user) {
        return null;
    }

    @Override
    public Deck getSharkDeck(User user) {
        return null;
    }
}
