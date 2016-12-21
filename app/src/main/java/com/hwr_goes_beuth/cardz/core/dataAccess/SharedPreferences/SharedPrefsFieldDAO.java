package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.FieldDAO;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsFieldDAO implements FieldDAO {

    private SharedPrefsDAOContext context;
    private CardDAO cardDAO;

    public SharedPrefsFieldDAO(SharedPrefsDAOContext context, CardDAO cardDAO) {
        this.context = context;
        this.cardDAO = cardDAO;
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

    @Override
    public void deleteField(long fieldId) {
        Field field = getField(fieldId);

        //for (long cardId : field.getCardIds()) {
        //    cardDAO.deleteCard(cardId);
        //}

        context.deleteFromPrefs(field);
    }
}
