package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;


import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.FieldDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.HandDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.PlayerDAO;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.Player;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsPlayerDAO implements PlayerDAO {

    private SharedPrefsDAOContext context;
    private DeckDAO deckDAO;
    private FieldDAO fieldDAO;
    private HandDAO handDAO;

    public SharedPrefsPlayerDAO(SharedPrefsDAOContext context, DeckDAO deckDAO, FieldDAO fieldDAO, HandDAO handDAO) {
        this.context = context;
        this.deckDAO = deckDAO;
        this.fieldDAO = fieldDAO;
        this.handDAO = handDAO;
    }

    @Override
    public Player getPlayer(long id) {
        return context.loadFromPrefs(Player.class, id);
    }

    @Override
    public Player createSharkPlayer() {
        Deck playerDeck = deckDAO.createSharkDeck();
        return createPlayer(playerDeck);
    }

    @Override
    public Player createRaptorPlayer() {
        Deck playerDeck = deckDAO.createRaptorDeck();
        return createPlayer(playerDeck);
    }

    @Override
    public void updatePlayer(Player player) {
        context.saveToPrefs(player);
    }

    private Player createPlayer(Deck deck) {
        Player newPlayer = new Player();
        newPlayer.setId(context.getNextId());

        Field newField = fieldDAO.createField();
        Hand newHand = handDAO.createHand();

        newPlayer.setDeckId(deck.getId());
        newPlayer.setFieldId(newField.getId());
        newPlayer.setHandId(newHand.getId());

        context.saveToPrefs(newPlayer);

        return newPlayer;
    }

    @Override
    public void deletePlayer(long playerId) {
        Player player = getPlayer(playerId);

        deckDAO.deleteDeck(player.getDeckId());
        fieldDAO.deleteField(player.getFieldId());
        handDAO.deleteHand(player.getHandId());

        context.deleteFromPrefs(player);
    }

    @Override
    public Deck getDeck(Player player) {
        return deckDAO.getDeck(player.getDeckId());
    }

    @Override
    public Hand getHand(Player player) {
        return handDAO.getHand(player.getHandId());
    }

    @Override
    public Field getField(Player player) {
        return fieldDAO.getField(player.getFieldId());
    }
}
