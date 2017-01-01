package com.hwr_goes_beuth.cardz.match;

import android.content.Context;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;

/**
 * Created by Project0rion on 30.12.2016.
 */
public abstract class MatchPhase {

    private DAOFactory daoFactory;

    public MatchPhase(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public abstract String getPhaseTitle();

    public abstract void run();
    public abstract boolean canGoToNextPhase();
    public abstract MatchPhase getNextPhase();

    protected DAOFactory getDaoFactory() {
        return daoFactory;
    }
}
