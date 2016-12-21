package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.entities.Entity;

/**
 * Created by Project0rion on 20.12.2016.
 */
public class SharedPrefsDAOContext {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPrefsDAOContext(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public <T extends Entity> T loadFromPrefs(Class<T> entityType, long entityId) {
        String json = sharedPreferences.getString(createIdentifier(entityType, entityId), "");
        return gson.fromJson(json, entityType);
    }

    public void saveToPrefs(Entity entity) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String json = gson.toJson(entity);
        prefsEditor.putString(createIdentifier(entity.getClass(), entity.getId()), json);
        prefsEditor.commit();
    }

    public void deleteFromPrefs(Entity entity) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.remove(createIdentifier(entity.getClass(), entity.getId()));
        prefsEditor.commit();
    }

    private <T extends Entity> String createIdentifier(Class<T> entityType, long entityId) {
        return entityType.getName() + "_" + entityId;
    }

    public long getNextId() {
        String lastId_Key = (SharedPrefsDAOContext.class).getName() + "_LAST_ASSIGNED_ID";
        String lastId = sharedPreferences.getString(lastId_Key, "0");

        long lastAssignedId = Long.parseLong(lastId) + 1;

        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(lastId_Key, Long.toString(lastAssignedId));
        prefsEditor.commit();

        return lastAssignedId;
    }

    public void clearPrefs() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }
}
