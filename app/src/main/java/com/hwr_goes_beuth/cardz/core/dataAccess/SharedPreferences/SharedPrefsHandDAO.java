package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.HandDAO;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsHandDAO extends AbstractSharedPrefsDAO implements HandDAO {

    CardDAO cardDAO;

    public SharedPrefsHandDAO(SharedPreferences sharedPreferences, Gson gson, CardDAO cardDAO) {
        super(sharedPreferences, gson);

        this.cardDAO = cardDAO;
    }

    @Override
    public List<Card> getCards(Hand hand) {
        List<Card> cards = new ArrayList<>();

        for (long cardId : hand.getCardIds()) {
            Card card = cardDAO.getCard(cardId);
            cards.add(card);
        }

        return cards;
    }
}
