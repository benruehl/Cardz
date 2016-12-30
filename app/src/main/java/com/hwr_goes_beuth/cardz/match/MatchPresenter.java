package com.hwr_goes_beuth.cardz.match;

import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.game.cards.CardRepository;
import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.game.opponents.Opponent;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentManager;

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
        User currentUser = mDAOFactory.getUserDAO().getOrCreateCurrentUser();
        Match currentMatch = mDAOFactory.getUserDAO().getCurrentMatch(currentUser);
        Player opponentPlayer = mDAOFactory.getMatchDAO().getOpponent(currentMatch);

        Opponent opponent = mOpponentManager.getOpponent(opponentPlayer);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
