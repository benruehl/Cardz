package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.core.dataAccess.CardDAO;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.entities.enums.MatchPhase;
import java.util.List;
import java.util.ArrayList;

public class SharedPrefsCardDAO implements CardDAO {

    private SharedPrefsDAOContext context;

    public SharedPrefsCardDAO(SharedPrefsDAOContext context) {
        this.context = context;
    }

    @Override
    public Card getCard(long id) {
        return context.loadFromPrefs(Card.class, id);
    }

    @Override
    public Card createCard() {
        Card newCard = new Card();
        newCard.setId(context.getNextId());
        context.saveToPrefs(newCard);
        return newCard;
    }

    @Override
    public void updateCard(Card card) {
        context.saveToPrefs(card);
    }

    @Override
    public void deleteCard(long cardId) {
        Card card = getCard(cardId);
        context.deleteFromPrefs(card);
    }
}
