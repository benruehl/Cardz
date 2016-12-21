package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.FieldDAO;
import com.hwr_goes_beuth.cardz.entities.Field;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsFieldDAO implements FieldDAO {

    private SharedPrefsDAOContext context;

    public SharedPrefsFieldDAO(SharedPrefsDAOContext context) {
        this.context = context;
    }

    @Override
    public Field getField(long id) {
        return context.loadFromPrefs(Field.class, id);
    }

    @Override
    public Field createField() {
        Field newField = new Field();
        newField.setId(context.getNextId());

        context.saveToPrefs(newField);

        return newField;
    }
}
