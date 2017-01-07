package com.hwr_goes_beuth.cardz.match;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Field;
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

    public static void playCardToLeft(DAOFactory daoFactory, Player player, Card card) {
        Field playerField = daoFactory.getPlayerDAO().getField(player);
        playerField.setLeftCardId(card.getId());

        boolean removedSuccessful = removeCardFromHandOrDeck(daoFactory, player, card);

        if (removedSuccessful)
            daoFactory.getFieldDAO().updateField(playerField);
        else
            throw new IllegalStateException("a card which is neither on the hand nor on the deck cannot be played");
    }

    public static void playCardToCenterLeft(DAOFactory daoFactory, Player player, Card card) {
        Field playerField = daoFactory.getPlayerDAO().getField(player);
        playerField.setCenterLeftCardId(card.getId());

        boolean removedSuccessful = removeCardFromHandOrDeck(daoFactory, player, card);

        if (removedSuccessful)
            daoFactory.getFieldDAO().updateField(playerField);
        else
            throw new IllegalStateException("a card which is neither on the hand nor on the deck cannot be played");
    }

    public static void playCardToCenter(DAOFactory daoFactory, Player player, Card card) {
        Field playerField = daoFactory.getPlayerDAO().getField(player);
        playerField.setCenterCardId(card.getId());

        boolean removedSuccessful = removeCardFromHandOrDeck(daoFactory, player, card);

        if (removedSuccessful)
            daoFactory.getFieldDAO().updateField(playerField);
        else
            throw new IllegalStateException("a card which is neither on the hand nor on the deck cannot be played");
    }

    public static void playCardToCenterRight(DAOFactory daoFactory, Player player, Card card) {
        Field playerField = daoFactory.getPlayerDAO().getField(player);
        playerField.setCenterRightCardId(card.getId());

        boolean removedSuccessful = removeCardFromHandOrDeck(daoFactory, player, card);

        if (removedSuccessful)
            daoFactory.getFieldDAO().updateField(playerField);
        else
            throw new IllegalStateException("a card which is neither on the hand nor on the deck cannot be played");
    }

    public static void playCardToRight(DAOFactory daoFactory, Player player, Card card) {
        Field playerField = daoFactory.getPlayerDAO().getField(player);
        playerField.setRightCardId(card.getId());

        boolean removedSuccessful = removeCardFromHandOrDeck(daoFactory, player, card);

        if (removedSuccessful)
            daoFactory.getFieldDAO().updateField(playerField);
        else
            throw new IllegalStateException("a card which is neither on the hand nor on the deck cannot be played");
    }

    private static boolean removeCardFromHandOrDeck(DAOFactory daoFactory, Player player, Card card) {
        Hand playerHand = daoFactory.getPlayerDAO().getHand(player);
        boolean removedFromHand = playerHand.getCardIds().remove(card.getId());

        if (removedFromHand)
            daoFactory.getHandDAO().updateHand(playerHand);

        boolean removedFromDeck = false;

        if (!removedFromHand) {
            Deck playerDeck = daoFactory.getPlayerDAO().getDeck(player);
            removedFromDeck = playerDeck.getCardIds().remove(card.getId());

            if (removedFromDeck)
                daoFactory.getDeckDAO().updateDeck(playerDeck);
        }

        return removedFromHand || removedFromDeck;
    }
}
