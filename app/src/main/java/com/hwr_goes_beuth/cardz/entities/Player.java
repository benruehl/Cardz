package com.hwr_goes_beuth.cardz.entities;

import java.util.List;
import java.util.ArrayList;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.entities.enums.MatchPhase;

public class Player extends Entity {

    private String name;
    private int health;
    private int money;
    private long deckId;
    private long handId;
    private long fieldId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public long getDeckId() {
        return deckId;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }

    public long getHandId() {
        return handId;
    }

    public void setHandId(long handId) {
        this.handId = handId;
    }

    public long getFieldId() {
        return fieldId;
    }

    public void setFieldId(long fieldId) {
        this.fieldId = fieldId;
    }

}
