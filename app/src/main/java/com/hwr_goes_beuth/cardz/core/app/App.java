package com.hwr_goes_beuth.cardz.core.app;

import com.hwr_goes_beuth.cardz.core.modules.AppModule;
import com.hwr_goes_beuth.cardz.core.modules.ViewModule;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class App extends android.app.Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .viewModule(new ViewModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
