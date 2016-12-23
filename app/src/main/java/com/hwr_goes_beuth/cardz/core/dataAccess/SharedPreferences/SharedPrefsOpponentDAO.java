package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;


import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.FieldDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.HandDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.OpponentDAO;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.Opponent;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsOpponentDAO implements OpponentDAO {

    private SharedPrefsDAOContext context;
    private DeckDAO deckDAO;
    private FieldDAO fieldDAO;
    private HandDAO handDAO;

    public SharedPrefsOpponentDAO(SharedPrefsDAOContext context, DeckDAO deckDAO, FieldDAO fieldDAO, HandDAO handDAO) {
        this.context = context;
        this.deckDAO = deckDAO;
        this.fieldDAO = fieldDAO;
        this.handDAO = handDAO;
    }

    @Override
    public Opponent getOpponent(long id) {
        return context.loadFromPrefs(Opponent.class, id);
    }

    @Override
    public Opponent createSharkOpponent() {
        Deck opponentDeck = deckDAO.createSharkDeck();
        return createOpponent(opponentDeck);
    }

    @Override
    public Opponent createRaptorOpponent() {
        Deck opponentDeck = deckDAO.createRaptorDeck();
        return createOpponent(opponentDeck);
    }

    @Override
    public void deleteOpponent(long opponentId) {
        Opponent opponent = getOpponent(opponentId);

        deckDAO.deleteDeck(opponent.getDeckId());
        fieldDAO.deleteField(opponent.getFieldId());
        handDAO.deleteHand(opponent.getHandId());

        context.deleteFromPrefs(opponent);
    }

    @Override
    public Deck getDeck(Opponent opponent) {
        return deckDAO.getDeck(opponent.getDeckId());
    }

    private Opponent createOpponent(Deck deck) {
        Opponent newOpponent = new Opponent();
        newOpponent.setId(context.getNextId());

        Field newField = fieldDAO.createField();
        Hand newHand = handDAO.createHand();

        newOpponent.setDeckId(deck.getId());
        newOpponent.setFieldId(newField.getId());
        newOpponent.setHandId(newHand.getId());

        context.saveToPrefs(newOpponent);

        return newOpponent;
    }
}
