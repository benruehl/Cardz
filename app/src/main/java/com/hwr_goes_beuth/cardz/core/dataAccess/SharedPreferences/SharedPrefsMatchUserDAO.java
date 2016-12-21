package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;


import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.FieldDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.HandDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchUserDAO;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.MatchUser;
import com.hwr_goes_beuth.cardz.entities.Opponent;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsMatchUserDAO implements MatchUserDAO {

    private SharedPrefsDAOContext context;
    private DeckDAO deckDAO;
    private FieldDAO fieldDAO;
    private HandDAO handDAO;

    public SharedPrefsMatchUserDAO(SharedPrefsDAOContext context, DeckDAO deckDAO, FieldDAO fieldDAO, HandDAO handDAO) {
        this.context = context;
        this.deckDAO = deckDAO;
        this.fieldDAO = fieldDAO;
        this.handDAO = handDAO;
    }

    @Override
    public MatchUser getMatchUser(long id) {
        return context.loadFromPrefs(MatchUser.class, id);
    }

    @Override
    public MatchUser createSharkMatchUser() {
        Deck matchUserDeck = deckDAO.createSharkDeck();
        return createMatchUser(matchUserDeck);
    }

    @Override
    public MatchUser createRaptorMatchUser() {
        Deck matchUserDeck = deckDAO.createRaptorDeck();
        return createMatchUser(matchUserDeck);
    }

    private MatchUser createMatchUser(Deck deck) {
        MatchUser newMatchUser = new MatchUser();
        newMatchUser.setId(context.getNextId());

        Field newField = fieldDAO.createField();
        Hand newHand = handDAO.createHand();

        newMatchUser.setDeckId(deck.getId());
        newMatchUser.setFieldId(newField.getId());
        newMatchUser.setHandId(newHand.getId());

        context.saveToPrefs(newMatchUser);

        return newMatchUser;
    }

    @Override
    public void deleteMatchUser(long matchUserId) {
        MatchUser matchUser = getMatchUser(matchUserId);

        deckDAO.deleteDeck(matchUser.getDeckId());
        fieldDAO.deleteField(matchUser.getFieldId());
        handDAO.deleteHand(matchUser.getHandId());

        context.deleteFromPrefs(matchUser);
    }
}
