package com.hwr_goes_beuth.cardz.game.opponents;

import com.hwr_goes_beuth.cardz.entities.enums.Faction;

/**
 * Created by Project0rion on 23.12.2016.
 */
public class Opponent {

    private String name;
    private Faction faction;
    private OpponentBehavior behavior;

    public Opponent(String name, Faction faction, OpponentBehavior behavior) {
        this.name = name;
        this.faction = faction;
        this.behavior = behavior;
    }

    public String getName() {
        return name;
    }

    public Faction getFaction() {
        return faction;
    }
}
