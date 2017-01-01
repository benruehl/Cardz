package com.hwr_goes_beuth.cardz.core.app;

import android.content.Context;

import com.hwr_goes_beuth.cardz.core.modules.AppModule;
import com.hwr_goes_beuth.cardz.core.modules.DataModule;
import com.hwr_goes_beuth.cardz.core.modules.GameModule;
import com.hwr_goes_beuth.cardz.core.modules.ViewModule;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class App extends android.app.Application {

    private static Context mAppContext;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppContext = this;

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .viewModule(new ViewModule())
                .dataModule(new DataModule())
                .gameModule(new GameModule())
                .build();
    }

    public static Context getContext() {
        return mAppContext;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
