package com.hwr_goes_beuth.cardz.entities;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Match extends Entity {

    private long matchUserId;
    private long opponentId;

    public long getMatchUserId() {
        return matchUserId;
    }

    public long getOpponentId() {
        return opponentId;
    }

    public void setMatchUserId(long matchUserId) {
        this.matchUserId = matchUserId;
    }

    public void setOpponentId(long opponentId) {
        this.opponentId = opponentId;
    }
}
