package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import com.hwr_goes_beuth.cardz.core.dataAccess.DeckDAO;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsDeckDAO implements DeckDAO {

    @Override
    public Deck createRaptorDeck() {
        Deck newDeck = new Deck();
        newDeck.setFaction(Faction.Raptor);

        return newDeck;
    }

    @Override
    public Deck createSharkDeck() {
        Deck newDeck = new Deck();
        newDeck.setFaction(Faction.Shark);

        return newDeck;
    }
}
