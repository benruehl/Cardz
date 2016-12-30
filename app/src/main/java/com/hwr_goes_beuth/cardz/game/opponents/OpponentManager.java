package com.hwr_goes_beuth.cardz.game.opponents;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.game.cards.DeckRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Project0rion on 23.12.2016.
 */
public class OpponentManager {

    private DAOFactory daoFactory;
    private List<Opponent> knownOpponents;
    private DeckRepository deckRepository;

    public OpponentManager(DAOFactory daoFactory, DeckRepository deckRepository, OpponentRepository opponentRepository) {
        this.daoFactory = daoFactory;
        this.deckRepository = deckRepository;
        knownOpponents = opponentRepository.getKnownOpponents();
    }

    public List<Opponent> getAvailableOpponents(Faction matchPlayerFaction) {
        List<Opponent> availableOpponents = new ArrayList<>();

        for (Opponent opponent : knownOpponents) {
            if (opponent.getFaction() != matchPlayerFaction)
                availableOpponents.add(opponent);
        }

        return availableOpponents;
    }

    public Opponent getOpponent(Player opponentPlayer) {
        for (Opponent opponent : knownOpponents) {
            if (opponent.getFaction() == daoFactory.getPlayerDAO().getDeck(opponentPlayer).getFaction()
                    && opponent.getName().equals(opponentPlayer.getName()))
                return opponent;
        }

        throw new NoSuchElementException("there is no opponent known which matches the name and faction of the player");
    }

    public void createOpponentDeck(Player opponentPlayer) {
        deckRepository.createOpponentDeck(opponentPlayer);
    }
}
