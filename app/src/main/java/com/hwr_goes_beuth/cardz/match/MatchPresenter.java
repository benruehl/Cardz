package com.hwr_goes_beuth.cardz.match;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.game.cards.CardRepository;
import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.game.opponents.Opponent;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentManager;
import com.hwr_goes_beuth.cardz.match.phases.EndPhase;
import com.hwr_goes_beuth.cardz.match.phases.InitialPhase;
import com.hwr_goes_beuth.cardz.match.phases.MatchUsersTurnPhase;
import com.hwr_goes_beuth.cardz.match.phases.OpponentsTurnPhase;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by Project0rion on 11.12.2016.
 */
public class MatchPresenter extends ActivityPresenter {

    @Inject
    DAOFactory mDAOFactory;

    @Inject
    OpponentManager mOpponentManager;

    @Inject
    CardRepository mCardRepository;

    private MatchPhase currentPhase;

    @Override
    public void init() {
        loadMatchPhase();

        Player opponentPlayer = getCurrentMatchOpponent();
        Opponent opponent = mOpponentManager.getOpponent(opponentPlayer);
    }

    private void loadMatchPhase() {
        Collection<MatchPhase> knownMatchPhases = new ArrayList<>();
        knownMatchPhases.add(new InitialPhase(mDAOFactory));
        knownMatchPhases.add(new MatchUsersTurnPhase(mDAOFactory));
        knownMatchPhases.add(new OpponentsTurnPhase(mDAOFactory));
        knownMatchPhases.add(new EndPhase(mDAOFactory));

        for (MatchPhase phase : knownMatchPhases) {
            if (phase.getMappedPhase() == getCurrentMatch().getMatchPhase())
                currentPhase = phase;
        }
    }

    public void runMatchPhase() {
        while (currentPhase.canGoToNextPhase()) {
            currentPhase = currentPhase.getNextPhase();
            Match currentMatch = getCurrentMatch();
            currentMatch.setMatchPhase(currentPhase.getMappedPhase());
            mDAOFactory.getMatchDAO().updateMatch(currentMatch);
        }

        currentPhase.run();
        notifyChange();
    }

    public String getCurrentPhaseTitle() {
        return currentPhase.getPhaseTitle();
    }

    public Collection<Card> getMatchUserHand() {
        Hand matchUserHand = mDAOFactory.getPlayerDAO().getHand(getCurrentMatchUser());
        return mDAOFactory.getHandDAO().getCards(matchUserHand);
    }

    public Field getMatchUserField() {
        return mDAOFactory.getPlayerDAO().getField(getCurrentMatchUser());
    }

    public Field getOpponentField() {
        return mDAOFactory.getPlayerDAO().getField(getCurrentMatchOpponent());
    }

    public int getMatchUserDeckCount() {
        Deck matchUserDeck = mDAOFactory.getPlayerDAO().getDeck(getCurrentMatchUser());
        return matchUserDeck.getCardIds().size();
    }

    public Card getCardById(long cardId) {
        return mDAOFactory.getCardDAO().getCard(cardId);
    }

    private Player getCurrentMatchUser() {
        Match currentMatch = getCurrentMatch();
        Player matchUser = mDAOFactory.getMatchDAO().getMatchUser(currentMatch);
        return matchUser;
    }

    private Player getCurrentMatchOpponent() {
        Match currentMatch = getCurrentMatch();
        Player opponentPlayer = mDAOFactory.getMatchDAO().getOpponent(currentMatch);
        return opponentPlayer;
    }

    private Match getCurrentMatch() {
        User currentUser = mDAOFactory.getUserDAO().getOrCreateCurrentUser();
        Match currentMatch = mDAOFactory.getUserDAO().getCurrentMatch(currentUser);
        return currentMatch;
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
