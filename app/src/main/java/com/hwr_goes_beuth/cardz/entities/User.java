package com.hwr_goes_beuth.cardz.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class User extends Entity {

    private List<Long> collectedCardIds;
    private long currentMatchId;
    private long sharkDeckId;
    private long raptorDeckId;

    public User() {
        collectedCardIds = new ArrayList<>();
    }

    public List<Long> getCollectedCardIds() {
        return collectedCardIds;
    }

    public long getCurrentMatchId() {
        return currentMatchId;
    }

    public long getSharkDeckId() {
        return sharkDeckId;
    }

    public long getRaptorDeckId() {
        return raptorDeckId;
    }

    public void setCurrentMatchId(long currentMatchId) {
        this.currentMatchId = currentMatchId;
    }

    public void setSharkDeckId(long sharkDeckId) {
        this.sharkDeckId = sharkDeckId;
    }

    public void setRaptorDeckId(long raptorDeckId) {
        this.raptorDeckId = raptorDeckId;
    }
}
