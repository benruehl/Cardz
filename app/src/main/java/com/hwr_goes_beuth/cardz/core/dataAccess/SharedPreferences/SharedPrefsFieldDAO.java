package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.FieldDAO;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsFieldDAO extends AbstractSharedPrefsDAO implements FieldDAO {

    public SharedPrefsFieldDAO(SharedPreferences sharedPreferences, Gson gson) {
        super(sharedPreferences, gson);
    }
}
