package com.hwr_goes_beuth.cardz.match;

import android.content.Context;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentManager;

/**
 * Created by Project0rion on 30.12.2016.
 */
public abstract class MatchPhase {

    private DAOFactory daoFactory;
    private OpponentManager opponentManager;

    public MatchPhase(DAOFactory daoFactory, OpponentManager opponentManager) {
        this.daoFactory = daoFactory;
        this.opponentManager = opponentManager;
    }

    public abstract String getPhaseTitle();

    public abstract void run();
    public abstract boolean canGoToNextPhase();
    public abstract MatchPhase getNextPhase();
    public abstract boolean canUserPerformAction();
    public abstract com.hwr_goes_beuth.cardz.entities.enums.MatchPhase getMappedPhase();

    protected DAOFactory getDaoFactory() {
        return daoFactory;
    }

    public OpponentManager getOpponentManager() {
        return opponentManager;
    }
}
