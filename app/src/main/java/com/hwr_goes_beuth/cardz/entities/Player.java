package com.hwr_goes_beuth.cardz.entities;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Player extends Entity {

    private long deckId;
    private long handId;
    private long fieldId;

    public Long getDeckId() {
        return deckId;
    }

    public Long getHandId() {
        return handId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public void setHandId(Long handId) {
        this.handId = handId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }
}
