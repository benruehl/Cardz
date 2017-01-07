package com.hwr_goes_beuth.cardz.match.phases;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.app.App;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.game.opponents.Opponent;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentManager;
import com.hwr_goes_beuth.cardz.match.MatchHelper;
import com.hwr_goes_beuth.cardz.match.MatchPhase;

/**
 * Created by Project0rion on 30.12.2016.
 */
public class OpponentsTurnPhase extends MatchPhase {

    private boolean completed;

    public OpponentsTurnPhase(DAOFactory daoFactory, OpponentManager opponentManager) {
        super(daoFactory, opponentManager);
    }

    @Override
    public String getPhaseTitle() {
        return App.getContext().getString(R.string.match_phase_title_opponentsTurn);
    }

    @Override
    public void run() {
        User currentUser = getDaoFactory().getUserDAO().getOrCreateCurrentUser();
        Match currentMatch = getDaoFactory().getUserDAO().getCurrentMatch(currentUser);
        Player opponentPlayer = getDaoFactory().getMatchDAO().getOpponent(currentMatch);
        Player matchUser = getDaoFactory().getMatchDAO().getMatchUser(currentMatch);

        MatchHelper.letPlayerDrawCards(getDaoFactory(), opponentPlayer, 1);

        Opponent opponent = getOpponentManager().getOpponent(opponentPlayer);
        opponent.performMove(getDaoFactory(), opponentPlayer, getDaoFactory().getPlayerDAO().getField(matchUser));

        completed = true;
    }

    @Override
    public boolean canGoToNextPhase() {
        return completed;
    }

    @Override
    public MatchPhase getNextPhase() {
        return new MatchUsersTurnPhase(getDaoFactory(), getOpponentManager());
    }

    @Override
    public boolean canUserPerformAction() {
        return false;
    }

    @Override
    public com.hwr_goes_beuth.cardz.entities.enums.MatchPhase getMappedPhase() {
        return com.hwr_goes_beuth.cardz.entities.enums.MatchPhase.OpponentsTurn;
    }
}
