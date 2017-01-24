package com.hwr_goes_beuth.cardz.game.cards;;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.User;
import java.util.NoSuchElementException;

public class DeckRepository {

    private DAOFactory daoFactory;
    private CardRepository cardRepository;
    
    public DeckRepository(DAOFactory daoFactory, CardRepository cardRepository) {
        this.daoFactory = daoFactory;
        this.cardRepository = cardRepository;
    }
    
    public void createOpponentDeck(Player opponentPlayer) {
        if (opponentPlayer.getName() == "Detlef")
            createDetlefDeck(opponentPlayer);
        else if (opponentPlayer.getName() == "Karl")
            createKarlDeck(opponentPlayer);
        else if (opponentPlayer.getName() == "Klaus")
            createKlausDeck(opponentPlayer);
        else if (opponentPlayer.getName() == "Carsten")
            createCarstenDeck(opponentPlayer);
        else
            throw new UnsupportedOperationException("there is no deck registered for opponent called" + opponentPlayer.getName());
    }
    
    public void createInitialSharkDeck (User user){
        Deck sharkDeck = daoFactory.getUserDAO().getSharkDeck(user);
        addCardToDeck(sharkDeck,"Sandhai");
        addCardToDeck(sharkDeck,"Sandhai");
        addCardToDeck(sharkDeck,"Hammerhai");
        addCardToDeck(sharkDeck,"Tigerhai");
        addCardToDeck(sharkDeck,"Tigerhai");
    }
    
    public void createInitialRaptorDeck (User user){
        Deck raptorDeck = daoFactory.getUserDAO().getRaptorDeck(user);
        addCardToDeck(raptorDeck,"Assassinator");
        addCardToDeck(raptorDeck,"Assassinator");
        addCardToDeck(raptorDeck,"Grossraptor");
        addCardToDeck(raptorDeck,"Killerraptor");
        addCardToDeck(raptorDeck,"Killerraptor");
    }
    
    private void createDetlefDeck (Player opponentPlayer){
        Deck opponentDeck = daoFactory.getDeckDAO().getDeck(opponentPlayer.getDeckId());
        addCardToDeck(opponentDeck,"Killerraptor");
        addCardToDeck(opponentDeck,"Killerraptor");
        addCardToDeck(opponentDeck,"Killerraptor");
        addCardToDeck(opponentDeck,"Grossraptor");
        addCardToDeck(opponentDeck,"Grossraptor");
    }
    
    private void createKarlDeck (Player opponentPlayer){
        Deck opponentDeck = daoFactory.getDeckDAO().getDeck(opponentPlayer.getDeckId());
        addCardToDeck(opponentDeck,"Hammerhai");
        addCardToDeck(opponentDeck,"Hammerhai");
        addCardToDeck(opponentDeck,"Hammerhai");
        addCardToDeck(opponentDeck,"Hammerhai");
        addCardToDeck(opponentDeck,"Tigerhai");
    }
    
    private void createKlausDeck (Player opponentPlayer){
        Deck opponentDeck = daoFactory.getDeckDAO().getDeck(opponentPlayer.getDeckId());
        addCardToDeck(opponentDeck,"Assassinator");
        addCardToDeck(opponentDeck,"Assassinator");
        addCardToDeck(opponentDeck,"Assassinator");
        addCardToDeck(opponentDeck,"Assassinator");
        addCardToDeck(opponentDeck,"Grossraptor");
    }
    
    private void createCarstenDeck (Player opponentPlayer){
        Deck opponentDeck = daoFactory.getDeckDAO().getDeck(opponentPlayer.getDeckId());
        addCardToDeck(opponentDeck,"Hammerhai");
        addCardToDeck(opponentDeck,"Hammerhai");
        addCardToDeck(opponentDeck,"Tigerhai");
        addCardToDeck(opponentDeck,"Tigerhai");
        addCardToDeck(opponentDeck,"Tigerhai");
    }
    
    
    private void addCardToDeck(Deck playerDeck, String cardName) {
        Card newCard = daoFactory.getDeckDAO().createCardInDeck(playerDeck);
        Card repoCard = cardRepository.getCardByName(cardName, playerDeck.getFaction());
        if (repoCard == null)
            throw new NoSuchElementException("there is no known card called " + cardName);
        newCard.setName(repoCard.getName());
        newCard.setFaction(repoCard.getFaction());
        newCard.setDamage(repoCard.getDamage());
        newCard.setHealth(repoCard.getHealth());
        newCard.setCost(repoCard.getCost());
        
        daoFactory.getCardDAO().updateCard(newCard);
    }
}
