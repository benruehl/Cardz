package com.hwr_goes_beuth.cardz.match;

import com.hwr_goes_beuth.cardz.game.cards.CardRepository;
import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.Opponent;
import com.hwr_goes_beuth.cardz.entities.User;

import javax.inject.Inject;

/**
 * Created by Project0rion on 11.12.2016.
 */
public class MatchPresenter extends ActivityPresenter {

    @Inject
    DAOFactory mDAOFactory;

    @Inject
    CardRepository mCardRepository;

    public void initMatch() {
        /*
        Card kingCard = mCardRepository.getKnownCards().get(0);

        User currentUser = mDAOFactory.getUserDAO().getOrCreateCurrentUser();
        Match currentMatch = mDAOFactory.getUserDAO().getCurrentMatch(currentUser);
        Opponent matchOpponent = mDAOFactory.getMatchDAO().getOpponent(currentMatch);
        Deck opponentDeck = mDAOFactory.getOpponentDAO().getDeck(matchOpponent);

        Card opponentCard = mDAOFactory.getDeckDAO().createCardInDeck(opponentDeck);
        opponentCard.setName(kingCard.getName());
        opponentCard.setFaction(kingCard.getFaction());
        opponentCard.setDamage(kingCard.getDamage());
        opponentCard.setHealth(kingCard.getHealth());
        opponentCard.setCost(kingCard.getCost());

        mDAOFactory.getCardDAO().updateCard(opponentCard);
        */
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
