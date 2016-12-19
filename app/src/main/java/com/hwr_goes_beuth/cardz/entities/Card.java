package com.hwr_goes_beuth.cardz.entities;

import com.hwr_goes_beuth.cardz.entities.enums.Faction;

/**
 * Created by Project0rion on 15.12.2016.
 */
public class Card extends Entity {

    private String name;
    private int damage;
    private int health;
    private int cost;
    private Faction faction;

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getCost() {
        return cost;
    }

    public Faction getFaction() {
        return faction;
    }
}
