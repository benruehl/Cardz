package com.hwr_goes_beuth.cardz.menu;

import android.content.Context;

import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.presentation.ViewManager;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.gameSetup.GameSetupActivity;

import javax.inject.Inject;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class MainMenuPresenter extends ActivityPresenter {

    @Inject
    ViewManager mViewManager;

    public void goToGame(Context sourceView) {
        mViewManager.switchView(sourceView, GameSetupActivity.class);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
