package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.entities.Entity;

/**
 * Created by Project0rion on 20.12.2016.
 */
public abstract class AbstractSharedPrefsDAO {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public AbstractSharedPrefsDAO(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    protected <T extends Entity> T loadFromPrefs(Class<T> entityType, long entityId) {
        String json = sharedPreferences.getString(createIdentifer(entityType, entityId), "");
        return gson.fromJson(json, entityType);
    }

    protected void saveToPrefs(Entity entity) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String json = gson.toJson(entity);
        prefsEditor.putString(createIdentifer(entity.getClass(), entity.getId()), json);
        prefsEditor.commit();
    }

    protected void deleteFromPrefs(Entity entity) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.remove(createIdentifer(entity.getClass(), entity.getId()));
        prefsEditor.commit();
    }

    private <T extends Entity> String createIdentifer(Class<T> entityType, long entityId) {
        return entityType.getName() + "_" + entityId;
    }
}
