package com.hwr_goes_beuth.cardz.game.opponents;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.match.MatchHelper;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Created by Project0rion on 23.12.2016.
 */
public class Opponent {

    public enum CardSlotPosition {
        Left, CenterLeft, Center, CenterRight, Right
    }

    private String name;
    private Faction faction;
    private OpponentBehavior behavior;

    public Opponent(String name, Faction faction, OpponentBehavior behavior) {
        this.name = name;
        this.faction = faction;
        this.behavior = behavior;
    }

    public void performMove(DAOFactory daoFactory, Player opponentPlayer, Field matchUserField) {
        Field field = daoFactory.getPlayerDAO().getField(opponentPlayer);
        Hand hand = daoFactory.getPlayerDAO().getHand(opponentPlayer);
        Collection<Card> cardsOnHand = daoFactory.getHandDAO().getCards(hand);

        PriorityQueue<Card> playableCards = behavior.getCardsToPlay(cardsOnHand);
        PriorityQueue<CardSlotPosition> targetCardSlots = behavior.prioritizeAvailableCardSlots(field, matchUserField);

        for (int i = 0; i < playableCards.size(); i++) {
            Card card = playableCards.poll();

            switch (targetCardSlots.poll()) {
                case Left:
                    MatchHelper.playCardToLeft(daoFactory, opponentPlayer, card);
                    break;
                case CenterLeft:
                    MatchHelper.playCardToCenterLeft(daoFactory, opponentPlayer, card);
                    break;
                case Center:
                    MatchHelper.playCardToCenter(daoFactory, opponentPlayer, card);
                    break;
                case CenterRight:
                    MatchHelper.playCardToCenterRight(daoFactory, opponentPlayer, card);
                    break;
                case Right:
                    MatchHelper.playCardToRight(daoFactory, opponentPlayer, card);
                    break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public Faction getFaction() {
        return faction;
    }
}
