package com.hwr_goes_beuth.cardz.core.modules;

import com.hwr_goes_beuth.cardz.core.presentation.ViewManager;
import com.hwr_goes_beuth.cardz.core.presentation.PresenterCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Project0rion on 04.12.2016.
 */
@Module
public class ViewModule {

    @Provides
    @Singleton
    ViewManager providesViewManager() {
        return new ViewManager();
    }

    @Provides
    @Singleton
    PresenterCache providesPresenterCache() {
        return new PresenterCache();
    }
}
