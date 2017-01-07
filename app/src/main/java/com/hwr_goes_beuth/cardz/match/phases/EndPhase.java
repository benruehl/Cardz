package com.hwr_goes_beuth.cardz.match.phases;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.app.App;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentManager;
import com.hwr_goes_beuth.cardz.match.MatchPhase;

/**
 * Created by Project0rion on 30.12.2016.
 */
public class EndPhase extends MatchPhase {

    public EndPhase(DAOFactory daoFactory, OpponentManager opponentManager) {
        super(daoFactory, opponentManager);
    }

    @Override
    public String getPhaseTitle() {
        return App.getContext().getString(R.string.match_phase_title_end);
    }

    @Override
    public void run() {

    }

    @Override
    public boolean canGoToNextPhase() {
        return false;
    }

    @Override
    public MatchPhase getNextPhase() {
        return null;
    }

    @Override
    public boolean canUserPerformAction() {
        return false;
    }

    @Override
    public com.hwr_goes_beuth.cardz.entities.enums.MatchPhase getMappedPhase() {
        return com.hwr_goes_beuth.cardz.entities.enums.MatchPhase.End;
    }
}
