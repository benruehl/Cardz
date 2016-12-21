package com.hwr_goes_beuth.cardz.entities;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Match extends Entity {

    private long matchUserId;
    private long opponentId;

    public Match() {
        matchUserId = 0L;
        opponentId = 0L;
    }

    public Long getMatchUserId() {
        return matchUserId;
    }

    public Long getOpponentId() {
        return opponentId;
    }

    public void setMatchUserId(Long matchUserId) {
        this.matchUserId = matchUserId;
    }

    public void setOpponentId(Long opponentId) {
        this.opponentId = opponentId;
    }
}
