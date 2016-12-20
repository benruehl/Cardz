package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.OpponentDAO;
import com.hwr_goes_beuth.cardz.entities.Opponent;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsOpponentDAO extends AbstractSharedPrefsDAO implements OpponentDAO {

    public SharedPrefsOpponentDAO(SharedPreferences sharedPreferences, Gson gson) {
        super(sharedPreferences, gson);
    }

    @Override
    public Opponent getOpponent(long id) {
        return loadFromPrefs(Opponent.class, id);
    }
}
