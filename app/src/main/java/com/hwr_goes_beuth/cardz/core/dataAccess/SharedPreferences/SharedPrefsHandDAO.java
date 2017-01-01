package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;


import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.HandDAO;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsHandDAO implements HandDAO {

    private SharedPrefsDAOContext context;
    private CardDAO cardDAO;

    public SharedPrefsHandDAO(SharedPrefsDAOContext context, CardDAO cardDAO) {
        this.context = context;
        this.cardDAO = cardDAO;
    }

    @Override
    public Hand getHand(long id) {
        return context.loadFromPrefs(Hand.class, id);
    }

    @Override
    public Hand createHand() {
        Hand newHand = new Hand();
        newHand.setId(context.getNextId());

        context.saveToPrefs(newHand);

        return newHand;
    }

    @Override
    public void updateHand(Hand playerHand) {
        context.saveToPrefs(playerHand);
    }

    @Override
    public void deleteHand(long handId) {
        Hand hand = getHand(handId);

        for (long cardId : hand.getCardIds()) {
            cardDAO.deleteCard(cardId);
        }

        context.deleteFromPrefs(hand);
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
