package com.hwr_goes_beuth.cardz.game.cards;;

import java.util.List;
import java.util.ArrayList;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.entities.Card;

public class CardRepository {

    private List<Card> knownCards;
    
    public CardRepository() {
        knownCards = new ArrayList<>();
        knownCards.add(createCard(Faction.Shark, "Tigerhai", 100, 30, 2));
        knownCards.add(createCard(Faction.Shark, "Sandhai", 80, 40, 1));
        knownCards.add(createCard(Faction.Shark, "Hammerhai", 100, 50, 4));
        knownCards.add(createCard(Faction.Raptor, "Grossraptor", 120, 60, 4));
        knownCards.add(createCard(Faction.Raptor, "Assassinator", 60, 30, 2));
        knownCards.add(createCard(Faction.Raptor, "Killerraptor", 100, 50, 5));
    }
    
    public List<Card> getKnownCards() { return knownCards; }
    
    private Card createCard(Faction faction, String name, int damage, int health, int cost) {
        Card newCard = new Card();
        newCard.setName(name);
        newCard.setFaction(faction);
        newCard.setDamage(damage);
        newCard.setHealth(health);
        newCard.setCost(cost);
        
        return newCard;
    }
    
    public Card getCardByName(String cardName, Faction faction) {
        for (Card card : knownCards) {
            if (card.getFaction() == faction && card.getName() == cardName)
                return card;
            }
        return null;
    }
}
