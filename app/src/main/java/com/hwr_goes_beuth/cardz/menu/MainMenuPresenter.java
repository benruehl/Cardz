package com.hwr_goes_beuth.cardz.menu;

import android.content.Context;

import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.dataAccess.UserDAO;
import com.hwr_goes_beuth.cardz.core.presentation.ViewManager;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.game.GameActivity;
import com.hwr_goes_beuth.cardz.gameSetup.GameSetupActivity;

import javax.inject.Inject;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class MainMenuPresenter extends ActivityPresenter {

    @Inject
    ViewManager mViewManager;

    @Inject
    DAOFactory mDAOFactory;

    public void goToGame(Context sourceView) {
        UserDAO userDAO = mDAOFactory.getUserDAO();

        User recentUser = userDAO.getOrCreateCurrentUser();
        Match unfinishedMatch = userDAO.getCurrentMatch(recentUser);

        if (unfinishedMatch != null)
            mViewManager.switchView(sourceView, GameActivity.class);
        else
            mViewManager.switchView(sourceView, GameSetupActivity.class);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
