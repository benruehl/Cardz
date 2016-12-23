package com.hwr_goes_beuth.cardz.game.cards;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project0rion on 23.12.2016.
 */
public class CardRepository {

    private List<Card> knownCards;

    public CardRepository() {
        knownCards = new ArrayList<>();
        knownCards.add(createCard(Faction.Raptor, "Evil Raptor King", 6, 8, 10));
        knownCards.add(createCard(Faction.Raptor, "Evil Raptor King", 6, 8, 10));
        knownCards.add(createCard(Faction.Raptor, "Evil Raptor King", 6, 8, 10));
        knownCards.add(createCard(Faction.Raptor, "Evil Raptor King", 6, 8, 10));
        knownCards.add(createCard(Faction.Raptor, "Evil Raptor King", 6, 8, 10));
    }


    private Card createCard(Faction faction, String name, int damage, int health, int cost) {
        Card newCard = new Card();
        newCard.setName(name);
        newCard.setFaction(faction);
        newCard.setDamage(damage);
        newCard.setHealth(health);
        newCard.setCost(cost);

        return newCard;
    }

    public List<Card> getKnownCards() {
        return knownCards;
    }
}
