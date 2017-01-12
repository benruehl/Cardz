package com.hwr_goes_beuth.cardz.gameSetup;

import android.content.Context;

import com.hwr_goes_beuth.cardz.core.app.AppComponent;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.core.presentation.ActivityPresenter;
import com.hwr_goes_beuth.cardz.core.presentation.ViewManager;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.game.opponents.Opponent;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentManager;
import com.hwr_goes_beuth.cardz.match.MatchActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class GameSetupPresenter extends ActivityPresenter {

    @Inject
    ViewManager mViewManager;

    @Inject
    DAOFactory mDAOFactory;

    @Inject
    OpponentManager mOpponentManager;

    private Faction selectedFaction;
    private Opponent selectedOpponent;

    @Override
    public void init() {
        selectedFaction = Faction.Shark;
        selectedOpponent = mOpponentManager.getAvailableOpponents(selectedFaction).get(0);
    }

    public List<Opponent> getAvailableOpponents() {
        return mOpponentManager.getAvailableOpponents(selectedFaction);
    }

    public Opponent getSelectedOpponent() {
        return selectedOpponent;
    }

    public void setSelectedOpponent(Opponent opponent) {
        selectedOpponent = opponent;
        notifyChange();
    }

    public void startMatch(Context context) {

        Match newMatch = mDAOFactory.getMatchDAO().createMatch();

        Player matchUser = mDAOFactory.getMatchDAO().getMatchUser(newMatch);
        Deck matchUserDeck = mDAOFactory.getPlayerDAO().getDeck(matchUser);
        matchUserDeck.setFaction(selectedFaction);
        mDAOFactory.getDeckDAO().updateDeck(matchUserDeck);

        selectUserDeckForMatch(matchUserDeck);

        Player opponentPlayer = mDAOFactory.getMatchDAO().getOpponent(newMatch);
        opponentPlayer.setName(selectedOpponent.getName());
        mDAOFactory.getPlayerDAO().updatePlayer(opponentPlayer);

        Deck opponentDeck = mDAOFactory.getPlayerDAO().getDeck(opponentPlayer);
        opponentDeck.setFaction(selectedOpponent.getFaction());
        mDAOFactory.getDeckDAO().updateDeck(opponentDeck);

        mOpponentManager.createOpponentDeck(opponentPlayer);

        User currentUser = mDAOFactory.getUserDAO().getOrCreateCurrentUser();
        currentUser.setCurrentMatchId(newMatch.getId());
        mDAOFactory.getUserDAO().updateUser(currentUser);

        mViewManager.switchView(context, MatchActivity.class);
    }

    private void selectUserDeckForMatch(Deck targetMatchUserDeck) {
        User user = mDAOFactory.getUserDAO().getOrCreateCurrentUser();

        List<Deck> allUserDecks = new ArrayList<>();
        allUserDecks.add(mDAOFactory.getUserDAO().getSharkDeck(user));
        allUserDecks.add(mDAOFactory.getUserDAO().getRaptorDeck(user));

        Deck sourceDeck = null;
        for (Deck userDeck : allUserDecks) {
            if (userDeck.getFaction() == selectedFaction)
                sourceDeck = userDeck;
        }

        if (sourceDeck == null)
            throw new IllegalStateException("user has no deck of selected faction " + selectedFaction);

        for (Card sourceCard : mDAOFactory.getDeckDAO().getCards(sourceDeck)) {
            Card targetCard = mDAOFactory.getDeckDAO().createCardInDeck(targetMatchUserDeck);
            targetCard.setName(sourceCard.getName());
            targetCard.setDamage(sourceCard.getDamage());
            targetCard.setHealth(sourceCard.getHealth());
            targetCard.setCost(sourceCard.getCost());
            mDAOFactory.getCardDAO().updateCard(targetCard);
        }
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
