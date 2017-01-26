package com.hwr_goes_beuth.cardz.game.opponents;

import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.game.opponents.behaviors.AggressiveOpponentBehavior;
import com.hwr_goes_beuth.cardz.game.opponents.behaviors.PassiveOpponentBehavior;
import java.util.ArrayList;
import java.util.List;

public class OpponentRepository {

    public List<Opponent> getKnownOpponents() {
        List<Opponent> knownOpponents = new ArrayList<>();
        knownOpponents.add(new Opponent("Diablo", Faction.Raptor, new AggressiveOpponentBehavior()));
        knownOpponents.add(new Opponent("TRex", Faction.Raptor, new PassiveOpponentBehavior()));
        knownOpponents.add(new Opponent("Garth", Faction.Raptor, new AggressiveOpponentBehavior()));
        knownOpponents.add(new Opponent("Kreacher", Faction.Raptor, new PassiveOpponentBehavior()));
        knownOpponents.add(new Opponent("Whipper", Faction.Shark, new PassiveOpponentBehavior()));
        knownOpponents.add(new Opponent("MrBubbles", Faction.Shark, new PassiveOpponentBehavior()));
        knownOpponents.add(new Opponent("GreatWhite", Faction.Shark, new AggressiveOpponentBehavior()));
        knownOpponents.add(new Opponent("Blade", Faction.Shark, new AggressiveOpponentBehavior()));
        return knownOpponents;
    }
}
