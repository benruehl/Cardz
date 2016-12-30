package com.hwr_goes_beuth.cardz.game.opponents;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.game.cards.CardRepository;

import javax.annotation.Generated;

/**
 * Created by Project0rion on 29.12.2016.
 */
@Generated("generated by antlr")
public class DeckRepository {

    private DAOFactory daoFactory;
    private CardRepository cardRepository;

    public DeckRepository(DAOFactory daoFactory, CardRepository cardRepository) {
        this.daoFactory = daoFactory;
        this.cardRepository = cardRepository;
    }

    public void createOpponentDeck(Player opponentPlayer) {
        if (opponentPlayer.getName() == "Sharky McSharkface")
            createSharkyMcSharkfaceDeck(opponentPlayer);
        else if (opponentPlayer.getName() == "Sharkinator")
            createSharkinatorDeck(opponentPlayer);
        else if (opponentPlayer.getName() == "Raptorion")
            createRaptorionDeck(opponentPlayer);
        else if (opponentPlayer.getName() == "Toothloosy")
            createToothloosyDeck(opponentPlayer);
        else
            throw new UnsupportedOperationException("there is no deck registered for opponent called " + opponentPlayer.getName());
    }

    private void createSharkyMcSharkfaceDeck(Player opponentPlayer) {
        giveCardToOpponent(opponentPlayer, "Sharkowski");
        giveCardToOpponent(opponentPlayer, "Sharkowski");
        giveCardToOpponent(opponentPlayer, "Sharkowski");
        giveCardToOpponent(opponentPlayer, "Sharkowski");
        giveCardToOpponent(opponentPlayer, "Gunner Shark");
        giveCardToOpponent(opponentPlayer, "Gunner Shark");
        giveCardToOpponent(opponentPlayer, "Gunner Shark");
        giveCardToOpponent(opponentPlayer, "Gunner Shark");
    }

    private void createSharkinatorDeck(Player opponentPlayer) {
        giveCardToOpponent(opponentPlayer, "Sharkowski");
        giveCardToOpponent(opponentPlayer, "Sharkowski");
        giveCardToOpponent(opponentPlayer, "Terror Shark");
        giveCardToOpponent(opponentPlayer, "Terror Shark");
        giveCardToOpponent(opponentPlayer, "Terror Shark");
        giveCardToOpponent(opponentPlayer, "Terror Shark");
        giveCardToOpponent(opponentPlayer, "Gunner Shark");
        giveCardToOpponent(opponentPlayer, "Gunner Shark");
    }

    private void createRaptorionDeck(Player opponentPlayer) {
        giveCardToOpponent(opponentPlayer, "Evil Raptor King");
        giveCardToOpponent(opponentPlayer, "Evil Raptor King");
        giveCardToOpponent(opponentPlayer, "Mecha Mage");
        giveCardToOpponent(opponentPlayer, "Mecha Mage");
        giveCardToOpponent(opponentPlayer, "Mecha Mage");
        giveCardToOpponent(opponentPlayer, "Mecha Mage");
        giveCardToOpponent(opponentPlayer, "Mecha Mage");
        giveCardToOpponent(opponentPlayer, "Mecha Mage");
    }

    private void createToothloosyDeck(Player opponentPlayer) {
        giveCardToOpponent(opponentPlayer, "Raptor Archer");
        giveCardToOpponent(opponentPlayer, "Raptor Archer");
        giveCardToOpponent(opponentPlayer, "Raptor Archer");
        giveCardToOpponent(opponentPlayer, "Raptor Archer");
        giveCardToOpponent(opponentPlayer, "Raptor Archer");
        giveCardToOpponent(opponentPlayer, "Evil Raptor King");
        giveCardToOpponent(opponentPlayer, "Mecha Mage");
        giveCardToOpponent(opponentPlayer, "Mecha Mage");
    }

    private void giveCardToOpponent(Player opponentPlayer, String cardName) {
        Deck opponentDeck = daoFactory.getDeckDAO().getDeck(opponentPlayer.getDeckId());
        Card newCard = daoFactory.getDeckDAO().createCardInDeck(opponentDeck);
        Card repoCard = cardRepository.getCardByName(cardName, opponentDeck.getFaction());

        newCard.setName(repoCard.getName());
        newCard.setFaction(repoCard.getFaction());
        newCard.setDamage(repoCard.getDamage());
        newCard.setHealth(repoCard.getHealth());
        newCard.setCost(repoCard.getCost());

        daoFactory.getCardDAO().updateCard(newCard);
    }
}