package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.UserDAO;
import com.hwr_goes_beuth.cardz.entities.User;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsUserDAO implements UserDAO {

    SharedPreferences sharedPreferences;
    DeckDAO deckDAO;

    public SharedPrefsUserDAO(SharedPreferences sharedPreferences, DeckDAO deckDAO) {
        this.sharedPreferences = sharedPreferences;
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
        return null;
    }

    private User createUser() {
        User newUser = new User();
        newUser.setRaptorDeck(deckDAO.createRaptorDeck());
        newUser.setSharkDeck(deckDAO.createSharkDeck());

        return newUser;
    }
}
