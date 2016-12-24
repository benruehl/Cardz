package com.hwr_goes_beuth.cardz.entities;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Player extends Entity {

    private String name;
    private long deckId;
    private long handId;
    private long fieldId;

    public String getName() {
        return name;
    }

    public long getDeckId() {
        return deckId;
    }

    public long getHandId() {
        return handId;
    }

    public long getFieldId() {
        return fieldId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }

    public void setHandId(long handId) {
        this.handId = handId;
    }

    public void setFieldId(long fieldId) {
        this.fieldId = fieldId;
    }
}
