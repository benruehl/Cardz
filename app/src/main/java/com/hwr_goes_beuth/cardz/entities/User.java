package com.hwr_goes_beuth.cardz.entities;

import java.util.List;
import java.util.ArrayList;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.entities.enums.MatchPhase;

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

    public void setCurrentMatchId(long currentMatchId) {
        this.currentMatchId = currentMatchId;
    }

    public long getSharkDeckId() {
        return sharkDeckId;
    }

    public void setSharkDeckId(long sharkDeckId) {
        this.sharkDeckId = sharkDeckId;
    }

    public long getRaptorDeckId() {
        return raptorDeckId;
    }

    public void setRaptorDeckId(long raptorDeckId) {
        this.raptorDeckId = raptorDeckId;
    }

}
