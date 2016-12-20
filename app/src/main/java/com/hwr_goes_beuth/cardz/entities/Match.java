package com.hwr_goes_beuth.cardz.entities;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Match extends Entity {

    private Long matchUserId;
    private Long opponentId;

    public Long getMatchUserId() {
        return matchUserId;
    }

    public Long getOpponentId() {
        return opponentId;
    }
}
