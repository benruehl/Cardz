package com.hwr_goes_beuth.cardz.entities;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Match extends Entity {

    private MatchUser matchUser;
    private Opponent opponent;

    public MatchUser getMatchUser() {
        return matchUser;
    }

    public Opponent getOpponent() {
        return opponent;
    }
}
