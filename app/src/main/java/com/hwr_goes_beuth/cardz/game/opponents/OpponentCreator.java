package com.hwr_goes_beuth.cardz.game.opponents;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.game.opponents.behaviors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.inject.Inject;

/**
 * Created by Project0rion on 23.12.2016.
 */
public class OpponentCreator {

    private DAOFactory daoFactory;
    private List<Opponent> knownOpponents;

    public OpponentCreator(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        knownOpponents = new ArrayList<>();

        knownOpponents.add(new Opponent("Sharky McSharkface", Faction.Shark, new PassiveOpponentBehavior()));
        knownOpponents.add(new Opponent("Sharkinator", Faction.Shark, new AggressiveOpponentBehavior()));

        knownOpponents.add(new Opponent("Raptorion", Faction.Raptor, new AggressiveOpponentBehavior()));
        knownOpponents.add(new Opponent("Toothloosy", Faction.Raptor, new PassiveOpponentBehavior()));
    }

    public List<Opponent> getAvailableOpponents(Faction matchPlayerFaction) {
        List<Opponent> availableOpponents = new ArrayList<>();

        for (Opponent opponent : knownOpponents) {
            if (opponent.getFaction() != matchPlayerFaction)
                availableOpponents.add(opponent);
        }

        return availableOpponents;
    }

    public Opponent getOpponentForOpponentPlayer(Player opponentPlayer) {
        for (Opponent opponent : knownOpponents) {
            if (opponent.getFaction() == daoFactory.getPlayerDAO().getDeck(opponentPlayer).getFaction()
                    && opponent.getName().equals(opponentPlayer.getName()))
                return opponent;
        }

        throw new NoSuchElementException("there is no opponent known which matches the name and faction of the player");
    }
}
