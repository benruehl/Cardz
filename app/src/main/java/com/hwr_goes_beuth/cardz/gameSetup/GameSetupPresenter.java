package com.hwr_goes_beuth.cardz.gameSetup;

import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.core.presentation.ViewManager;

import javax.inject.Inject;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class GameSetupPresenter extends ActivityPresenter {

    @Inject
    ViewManager mViewManager;

    public void startGame() {

    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
