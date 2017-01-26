package com.hwr_goes_beuth.cardz.game.opponents.behaviors;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.game.opponents.Opponent;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentBehavior;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Project0rion on 23.12.2016.
 */
public class PassiveOpponentBehavior implements OpponentBehavior {

    @Override
    public PriorityQueue<Card> getCardsToPlay(Collection<Card> playableCards) {
        List<Card> playableCardsSorted = new ArrayList<>(playableCards);
        Collections.sort(playableCardsSorted, cardComparator);

        final int defaultQueueSize = 11;
        PriorityQueue<Card> cardsToPlay = new PriorityQueue<>(defaultQueueSize, cardComparator);

        cardsToPlay.add(playableCardsSorted.get(0));

        return cardsToPlay;
    }

    private final Comparator<Card> cardComparator = new Comparator<Card>() {
        @Override
        public int compare(Card o1, Card o2) {
            if (o1 == null || o2 == null)
                return 0;

            if (o1.getHealth() > o2.getHealth())
                return 1;
            else if (o1.getHealth() < o2.getHealth())
                return -1;

            return 0;
        }
    };

    @Override
    public Collection<Opponent.CardSlotPosition> prioritizeAvailableCardSlots(Field field, Field matchUserField) {
        Collection<Opponent.CardSlotPosition> prioritizedCardSlots = new ArrayList<>();
        prioritizedCardSlots.add(Opponent.CardSlotPosition.Right);
        prioritizedCardSlots.add(Opponent.CardSlotPosition.Left);
        prioritizedCardSlots.add(Opponent.CardSlotPosition.CenterRight);
        prioritizedCardSlots.add(Opponent.CardSlotPosition.CenterLeft);
        prioritizedCardSlots.add(Opponent.CardSlotPosition.Center);

        return prioritizedCardSlots;
    }
}
