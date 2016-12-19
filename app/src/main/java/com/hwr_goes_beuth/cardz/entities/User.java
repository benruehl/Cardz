package com.hwr_goes_beuth.cardz.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class User extends Entity {

    private List<Card> collectedCards;
    private Match currentMatch;
    private Deck sharkDeck;
    private Deck raptorDeck;

    public User() {
        collectedCards = new ArrayList<>();
    }

    public List<Card> getCollectedCards() {
        return collectedCards;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public Deck getSharkDeck() {
        return sharkDeck;
    }

    public Deck getRaptorDeck() {
        return raptorDeck;
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public void setSharkDeck(Deck sharkDeck) {
        this.sharkDeck = sharkDeck;
    }

    public void setRaptorDeck(Deck raptorDeck) {
        this.raptorDeck = raptorDeck;
    }
}
