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
    public void updateField(Field field) {
        context.saveToPrefs(field);
    }

    @Override
    public void deleteField(long fieldId) {
        Field field = getField(fieldId);

        cardDAO.deleteCard(field.getCenterCardId());
        cardDAO.deleteCard(field.getLeftCardId());
        cardDAO.deleteCard(field.getRightCardId());
        cardDAO.deleteCard(field.getCenterLeftCardId());
        cardDAO.deleteCard(field.getCenterRightCardId());

        context.deleteFromPrefs(field);
    }

    @Override
    public Card getLeftCard(Field field) {
        return cardDAO.getCard(field.getLeftCardId());
    }

    @Override
    public Card getCenterLeftCard(Field field) {
        return cardDAO.getCard(field.getCenterLeftCardId());
    }

    @Override
    public Card getCenterCard(Field field) {
        return cardDAO.getCard(field.getCenterCardId());
    }

    @Override
    public Card getCenterRightCard(Field field) {
        return cardDAO.getCard(field.getCenterRightCardId());
    }

    @Override
    public Card getRightCard(Field field) {
        return cardDAO.getCard(field.getRightCardId());
    }
}
