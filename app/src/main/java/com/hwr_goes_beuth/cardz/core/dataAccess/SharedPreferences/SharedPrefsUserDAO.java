package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.UserDAO;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsUserDAO implements UserDAO {

    private SharedPrefsDAOContext context;
    private MatchDAO matchDAO;
    private DeckDAO deckDAO;
    private CardDAO cardDAO;

    public SharedPrefsUserDAO(SharedPrefsDAOContext context, MatchDAO matchDAO, DeckDAO deckDAO, CardDAO cardDAO) {
        this.context = context;
        this.matchDAO = matchDAO;
        this.deckDAO = deckDAO;
        this.cardDAO = cardDAO;
    }

    @Override
    public User getOrCreateCurrentUser() {
        User recentUser = getRecentUser();

        if (recentUser == null)
            recentUser = createUser();

        return recentUser;
    }

    @Override
    public void updateUser(User user) {
        context.saveToPrefs(user);
    }

    @Override
    public void deleteCurrentUser() {
        context.clearPrefs();
    }

    private User getRecentUser() {
        return context.loadFromPrefs(User.class, 0);
    }

    private User createUser() {
        User newUser = new User();

        /* user has always id = 0 because only a single user is supported */
        // newUser.setId(context.getNextId());

        Deck newRaptorDeck = deckDAO.createRaptorDeck();
        Deck newSharkDeck = deckDAO.createSharkDeck();

        newUser.setRaptorDeckId(newRaptorDeck.getId());
        newUser.setSharkDeckId(newSharkDeck.getId());

        context.saveToPrefs(newUser);

        return newUser;
    }

    @Override
    public List<Card> getCollectedCards(User user) {
        List<Card> cards = new ArrayList<>();

        for (long cardId : user.getCollectedCardIds()) {
            Card card = cardDAO.getCard(cardId);
            cards.add(card);
        }

        return cards;
    }

    @Override
    public Match getCurrentMatch(User user) {
        return matchDAO.getMatch(user.getCurrentMatchId());
    }

    @Override
    public Deck getRaptorDeck(User user) {
        return deckDAO.getDeck(user.getRaptorDeckId());
    }

    @Override
    public Deck getSharkDeck(User user) {
        return deckDAO.getDeck(user.getSharkDeckId());
    }

    @Override
    public void deleteCurrentMatch(User user) {
        matchDAO.deleteMatch(user.getCurrentMatchId());
        user.setCurrentMatchId(0);
        updateUser(user);
    }
}
