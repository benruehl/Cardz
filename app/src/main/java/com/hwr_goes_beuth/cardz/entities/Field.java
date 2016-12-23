package com.hwr_goes_beuth.cardz.entities;

import java.util.List;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Field extends Entity {

    private long leftCardId;
    private long centerLeftCardId;
    private long centerCardId;
    private long centerRightCardId;
    private long rightCardId;

    public long getLeftCardId() {
        return leftCardId;
    }

    public void setLeftCardId(long leftCardId) {
        this.leftCardId = leftCardId;
    }

    public long getCenterLeftCardId() {
        return centerLeftCardId;
    }

    public void setCenterLeftCardId(long centerLeftCardId) {
        this.centerLeftCardId = centerLeftCardId;
    }

    public long getCenterCardId() {
        return centerCardId;
    }

    public void setCenterCardId(long centerCardId) {
        this.centerCardId = centerCardId;
    }

    public long getCenterRightCardId() {
        return centerRightCardId;
    }

    public void setCenterRightCardId(long centerRightCardId) {
        this.centerRightCardId = centerRightCardId;
    }

    public long getRightCardId() {
        return rightCardId;
    }

    public void setRightCardId(long rightCardId) {
        this.rightCardId = rightCardId;
    }
}
