package com.hwr_goes_beuth.cardz.gameSetup;

import android.content.Context;

import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.dataAccess.UserDAO;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.core.presentation.ViewManager;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.game.GameActivity;

import javax.inject.Inject;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class GameSetupPresenter extends ActivityPresenter {

    @Inject
    ViewManager mViewManager;

    @Inject
    DAOFactory mDAOFactory;

    public void startMatch(Context context) {

        Match newMatch = mDAOFactory.getMatchDAO().createMatch();

        UserDAO userDAO = mDAOFactory.getUserDAO();
        User currentUser = userDAO.getOrCreateCurrentUser();
        currentUser.setCurrentMatchId(newMatch.getId());
        userDAO.updateUser(currentUser);

        mViewManager.switchView(context, GameActivity.class);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
