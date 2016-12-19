package com.hwr_goes_beuth.cardz.core.modules;

import android.app.Application;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences.SharedPrefsDAOFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Project0rion on 19.12.2016.
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    DAOFactory providesDAOFactory(Application application, Gson gson) {
        return new SharedPrefsDAOFactory(PreferenceManager.getDefaultSharedPreferences(application), gson);
    }
}
