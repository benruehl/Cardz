package com.hwr_goes_beuth.cardz.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class User extends Entity {

    private List<Long> collectedCardIds;
    private Long currentMatchId;
    private Long sharkDeckId;
    private Long raptorDeckId;

    public User() {
        collectedCardIds = new ArrayList<>();
    }

    public List<Long> getCollectedCardIds() {
        return collectedCardIds;
    }

    public Long getCurrentMatchId() {
        return currentMatchId;
    }

    public Long getSharkDeckId() {
        return sharkDeckId;
    }

    public Long getRaptorDeckId() {
        return raptorDeckId;
    }

    public void setCurrentMatchId(Long currentMatchId) {
        this.currentMatchId = currentMatchId;
    }

    public void setSharkDeckId(Long sharkDeckId) {
        this.sharkDeckId = sharkDeckId;
    }

    public void setRaptorDeckId(Long raptorDeckId) {
        this.raptorDeckId = raptorDeckId;
    }
}
