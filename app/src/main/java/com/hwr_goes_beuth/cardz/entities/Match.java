package com.hwr_goes_beuth.cardz.entities;

import com.hwr_goes_beuth.cardz.entities.enums.MatchPhase;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Match extends Entity {

    private MatchPhase matchPhase;
    private long matchUserId;
    private long opponentId;

    public MatchPhase getMatchPhase() {
        return matchPhase;
    }

    public void setMatchPhase(MatchPhase matchPhase) {
        this.matchPhase = matchPhase;
    }

    public long getMatchUserId() {
        return matchUserId;
    }

    public void setMatchUserId(long matchUserId) {
        this.matchUserId = matchUserId;
    }

    public long getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(long opponentId) {
        this.opponentId = opponentId;
    }
}
