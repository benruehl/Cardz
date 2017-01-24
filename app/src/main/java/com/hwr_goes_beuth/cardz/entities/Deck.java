package com.hwr_goes_beuth.cardz.entities;

import java.util.List;
import java.util.ArrayList;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.entities.enums.MatchPhase;

public class Deck extends Entity {

    private Faction faction;
    private List<Long> cardIds;

    public Deck() {
        cardIds = new ArrayList<>();
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public List<Long> getCardIds() {
        return cardIds;
    }

}
