package com.hwr_goes_beuth.cardz.entities;

import com.hwr_goes_beuth.cardz.entities.enums.Faction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project0rion on 15.12.2016.
 */
public class Deck extends Entity{

    private Faction faction;
    private List<Long> cardIds;

    public Deck() {
        cardIds = new ArrayList<>();
    }

    public Faction getFaction() {
        return faction;
    }

    public List<Long> getCardIds() {
        return cardIds;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}
