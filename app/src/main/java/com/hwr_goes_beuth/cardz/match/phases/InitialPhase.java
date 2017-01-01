package com.hwr_goes_beuth.cardz.match.phases;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.app.App;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.match.MatchPhase;

import java.util.Collections;

/**
 * Created by Project0rion on 30.12.2016.
 */
public class InitialPhase extends MatchPhase {

    private static final int CARDS_TO_DRAW = 4;
    private boolean completed;

    public InitialPhase(DAOFactory daoFactory) {
        super(daoFactory);
        completed = false;
    }

    @Override
    public String getPhaseTitle() {
        return App.getContext().getString(R.string.match_phase_title_initial);
    }

    @Override
    public void run() {
        ValidateInitialState();

        User currentUser = getDaoFactory().getUserDAO().getOrCreateCurrentUser();
        Match currentMatch = getDaoFactory().getUserDAO().getCurrentMatch(currentUser);
        Player opponent = getDaoFactory().getMatchDAO().getOpponent(currentMatch);
        Player matchUser = getDaoFactory().getMatchDAO().getMatchUser(currentMatch);

        shuffleDeck(opponent);
        shuffleDeck(matchUser);

        letPlayerDrawCards(opponent, CARDS_TO_DRAW);
        letPlayerDrawCards(matchUser, CARDS_TO_DRAW);

        completed = true;
    }

    private void shuffleDeck(Player player) {
        Deck playerDeck = getDaoFactory().getPlayerDAO().getDeck(player);
        Collections.shuffle(playerDeck.getCardIds());
        getDaoFactory().getDeckDAO().updateDeck(playerDeck);
    }

    private void letPlayerDrawCards(Player player, int amount) {
        Deck playerDeck = getDaoFactory().getPlayerDAO().getDeck(player);
        Hand playerHand = getDaoFactory().getPlayerDAO().getHand(player);

        for (int i = 0; i < amount; i++) {
            long drawnCardId = playerDeck.getCardIds().get(0);
            playerHand.getCardIds().add(drawnCardId);
            playerDeck.getCardIds().remove(drawnCardId);
            getDaoFactory().getDeckDAO().updateDeck(playerDeck);
            getDaoFactory().getHandDAO().updateHand(playerHand);
        }
    }

    @Override
    public boolean canGoToNextPhase() {
        return completed;
    }

    @Override
    public MatchPhase getNextPhase() {
        return new MatchUsersTurnPhase(getDaoFactory());
    }

    @Override
    public com.hwr_goes_beuth.cardz.entities.enums.MatchPhase getMappedPhase() {
        return com.hwr_goes_beuth.cardz.entities.enums.MatchPhase.Initial;
    }

    private void ValidateInitialState() {
        User user = getDaoFactory().getUserDAO().getOrCreateCurrentUser();
        Match currentMatch = getDaoFactory().getUserDAO().getCurrentMatch(user);
        Player matchUser = getDaoFactory().getMatchDAO().getMatchUser(currentMatch);
        Player opponent = getDaoFactory().getMatchDAO().getOpponent(currentMatch);

        ValidateDeckIsNotEmpty(opponent);
        ValidateDeckIsNotEmpty(matchUser);
        ValidateHandIsEmpty(opponent);
        ValidateHandIsEmpty(matchUser);
        ValidateFieldIsEmpty(opponent);
        ValidateFieldIsEmpty(matchUser);
    }

    private void ValidateDeckIsNotEmpty(Player player) {
        Deck deck = getDaoFactory().getPlayerDAO().getDeck(player);

        if (deck.getCardIds().isEmpty())
            throw new IllegalStateException("deck must not be empty in the initial match phase");
    }

    private void ValidateHandIsEmpty(Player player) {
        Hand hand = getDaoFactory().getPlayerDAO().getHand(player);

        if (!hand.getCardIds().isEmpty())
            throw new IllegalStateException("hand must be empty in the initial match phase");
    }

    private void ValidateFieldIsEmpty(Player player) {
        Field field = getDaoFactory().getPlayerDAO().getField(player);

        if (field.getLeftCardId() != 0 ||
            field.getCenterLeftCardId() != 0 ||
            field.getCenterCardId() != 0 ||
            field.getCenterRightCardId() != 0 ||
            field.getRightCardId() != 0) {

            throw new IllegalStateException("field must be empty in the initial match phase");
        }
    }
}
