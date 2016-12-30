package com.hwr_goes_beuth.cardz.match;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.game.cards.CardRepository;
import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.game.opponents.Opponent;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentManager;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by Project0rion on 11.12.2016.
 */
public class MatchPresenter extends ActivityPresenter {

    @Inject
    DAOFactory mDAOFactory;

    @Inject
    OpponentManager mOpponentManager;

    @Inject
    CardRepository mCardRepository;

    @Override
    public void init() {
        Player opponentPlayer = getCurrentMatchOpponent();
        Opponent opponent = mOpponentManager.getOpponent(opponentPlayer);
    }

    public Collection<Card> getMatchUserHand() {
        Hand matchUserHand = mDAOFactory.getPlayerDAO().getHand(getCurrentMatchUser());
        return mDAOFactory.getHandDAO().getCards(matchUserHand);
    }

    private Player getCurrentMatchUser() {
        Match currentMatch = getCurrentMatch();
        Player matchUser = mDAOFactory.getMatchDAO().getMatchUser(currentMatch);
        return matchUser;
    }

    private Player getCurrentMatchOpponent() {
        Match currentMatch = getCurrentMatch();
        Player opponentPlayer = mDAOFactory.getMatchDAO().getOpponent(currentMatch);
        return opponentPlayer;
    }

    private Match getCurrentMatch() {
        User currentUser = mDAOFactory.getUserDAO().getOrCreateCurrentUser();
        Match currentMatch = mDAOFactory.getUserDAO().getCurrentMatch(currentUser);
        return currentMatch;
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
