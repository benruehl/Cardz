package com.hwr_goes_beuth.cardz.entities;

import java.util.List;
import java.util.ArrayList;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.entities.enums.MatchPhase;

public class Hand extends Entity {

    private List<Long> cardIds;

    public Hand() {
        cardIds = new ArrayList<>();
    }

    public List<Long> getCardIds() {
        return cardIds;
    }

}
