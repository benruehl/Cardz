package com.hwr_goes_beuth.cardz.match.phases;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.app.App;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.match.MatchHelper;
import com.hwr_goes_beuth.cardz.match.MatchPhase;

/**
 * Created by Project0rion on 30.12.2016.
 */
public class OpponentsTurnPhase extends MatchPhase {

    private boolean drewCard;

    public OpponentsTurnPhase(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public String getPhaseTitle() {
        return App.getContext().getString(R.string.match_phase_title_opponentsTurn);
    }

    @Override
    public void run() {
        User currentUser = getDaoFactory().getUserDAO().getOrCreateCurrentUser();
        Match currentMatch = getDaoFactory().getUserDAO().getCurrentMatch(currentUser);
        Player opponent = getDaoFactory().getMatchDAO().getOpponent(currentMatch);
        Player matchUser = getDaoFactory().getMatchDAO().getMatchUser(currentMatch);

        MatchHelper.letPlayerDrawCards(getDaoFactory(), opponent, 1);
        drewCard = true;
    }

    @Override
    public boolean canGoToNextPhase() {
        return drewCard;
    }

    @Override
    public MatchPhase getNextPhase() {
        return new MatchUsersTurnPhase(getDaoFactory());
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
