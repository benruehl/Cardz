package com.hwr_goes_beuth.cardz.game.opponents;

import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.game.opponents.behaviors.AggressiveOpponentBehavior;
import com.hwr_goes_beuth.cardz.game.opponents.behaviors.PassiveOpponentBehavior;
import java.util.ArrayList;
import java.util.List;

public class OpponentRepository {

    public List<Opponent> getKnownOpponents() {
        List<Opponent> knownOpponents = new ArrayList<>();
        knownOpponents.add(new Opponent("Detlef", Faction.Raptor, new AggressiveOpponentBehavior()));
        knownOpponents.add(new Opponent("Karl", Faction.Shark, new PassiveOpponentBehavior()));
        knownOpponents.add(new Opponent("Klaus", Faction.Raptor, new PassiveOpponentBehavior()));
        knownOpponents.add(new Opponent("Carsten", Faction.Shark, new AggressiveOpponentBehavior()));
        return knownOpponents;
    }
}
