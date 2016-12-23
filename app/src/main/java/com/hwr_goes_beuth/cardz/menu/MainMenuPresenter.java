package com.hwr_goes_beuth.cardz.menu;

import android.content.Context;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.dataAccess.UserDAO;
import com.hwr_goes_beuth.cardz.core.presentation.ViewManager;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.match.MatchActivity;
import com.hwr_goes_beuth.cardz.gameSetup.GameSetupActivity;

import java.util.concurrent.Callable;

import javax.inject.Inject;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class MainMenuPresenter extends ActivityPresenter {

    @Inject
    ViewManager mViewManager;

    @Inject
    DAOFactory mDAOFactory;

    public void startGame(final Context sourceView) {
        UserDAO userDAO = mDAOFactory.getUserDAO();

        final User recentUser = userDAO.getOrCreateCurrentUser();
        final Match unfinishedMatch = userDAO.getCurrentMatch(recentUser);

        if (unfinishedMatch == null)
            startNewMatch(sourceView);
        else {
            mViewManager.confirm(sourceView)
                    .title(sourceView.getString(R.string.confirm_resume_match_title))
                    .content(sourceView.getString(R.string.confirm_resume_match_content))
                    .positiveButtonText(sourceView.getString(R.string.confirm_resume_match_positive))
                    .negativeButtonText(sourceView.getString(R.string.confirm_resume_match_negative))
                    .isCancelable(true)
                    .onConfirm(new Callable() {
                        @Override
                        public Object call() throws Exception {
                            resumeMatch(sourceView);
                            return null;
                        }
                    })
                    .onRefuse(new Callable() {
                        @Override
                        public Object call() throws Exception {
                            deleteCurrentMatch(recentUser);
                            startNewMatch(sourceView);
                            return null;
                        }
                    })
                    .build().show();
        }

    }

    private void startNewMatch(Context sourceView) {
        mViewManager.switchView(sourceView, GameSetupActivity.class);
    }

    private void deleteCurrentMatch(User user) {
        mDAOFactory.getUserDAO().deleteCurrentMatch(user);
    }

    private void resumeMatch(Context sourceView) {
        mViewManager.switchView(sourceView, MatchActivity.class);
    }

    public void resetUserData(final Context sourceView) {
        mViewManager.confirm(sourceView)
                .title(sourceView.getString(R.string.confirm_reset_user_data_title))
                .content(sourceView.getString(R.string.confirm_reset_user_data_content))
                .positiveButtonText(sourceView.getString(R.string.confirm_reset_user_data_positive))
                .negativeButtonText(sourceView.getString(R.string.confirm_reset_user_data_negative))
                .isCancelable(true)
                .onConfirm(new Callable() {
                    @Override
                    public Object call() throws Exception {
                        resetUserData();
                        return null;
                    }
                })
                .build().show();
    }

    private void resetUserData() {
        mDAOFactory.getUserDAO().deleteCurrentUser();
        mDAOFactory.getUserDAO().getOrCreateCurrentUser();
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
