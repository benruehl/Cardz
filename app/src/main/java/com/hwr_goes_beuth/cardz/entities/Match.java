package com.hwr_goes_beuth.cardz.entities;

import java.util.List;
import java.util.ArrayList;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.entities.enums.MatchPhase;

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
