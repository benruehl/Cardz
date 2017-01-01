package com.hwr_goes_beuth.cardz.match.phases;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.app.App;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.match.MatchPhase;

/**
 * Created by Project0rion on 30.12.2016.
 */
public class EndPhase extends MatchPhase {

    public EndPhase(DAOFactory daoFactory) {
        super(daoFactory);
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
}
