package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchUserDAO;
import com.hwr_goes_beuth.cardz.entities.MatchUser;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsMatchUserDAO extends AbstractSharedPrefsDAO implements MatchUserDAO {

    public SharedPrefsMatchUserDAO(SharedPreferences sharedPreferences, Gson gson) {
        super(sharedPreferences, gson);
    }

    @Override
    public MatchUser getMatchUser(long id) {
        return loadFromPrefs(MatchUser.class, id);
    }
}
