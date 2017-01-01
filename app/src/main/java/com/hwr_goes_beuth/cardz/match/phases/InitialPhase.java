package com.hwr_goes_beuth.cardz.match.phases;

import android.content.Context;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.app.App;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Deck;
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
        // TODO: fix bug: match user has no cards in his deck
        letPlayerDrawCards(matchUser, CARDS_TO_DRAW);

        completed = true;
    }

    private void ValidateInitialState() {
        // TODO
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
}
