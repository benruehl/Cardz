package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.entities.Player;
import java.util.List;

public interface PlayerDAO {

    Player getPlayer(long id);
    Player createSharkPlayer();
    Player createRaptorPlayer();
    void updatePlayer(Player player);
    void deletePlayer(long playerId);

    Deck getDeck(Player player);
    Hand getHand(Player player);
    Field getField(Player player);
}
