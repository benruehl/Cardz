package com.hwr_goes_beuth.cardz.match;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.Player;

/**
 * Created by Project0rion on 06.01.2017.
 */

public class MatchHelper {

    public static void letPlayerDrawCards(DAOFactory daoFactory, Player player, int amount) {
        Deck playerDeck = daoFactory.getPlayerDAO().getDeck(player);
        Hand playerHand = daoFactory.getPlayerDAO().getHand(player);

        for (int i = 0; i < amount; i++) {
            long drawnCardId = playerDeck.getCardIds().get(0);
            playerHand.getCardIds().add(drawnCardId);
            playerDeck.getCardIds().remove(drawnCardId);
            daoFactory.getDeckDAO().updateDeck(playerDeck);
            daoFactory.getHandDAO().updateHand(playerHand);
        }
    }
}
