package com.hwr_goes_beuth.cardz.match.phases;

import com.hwr_goes_beuth.cardz.R;
import com.hwr_goes_beuth.cardz.core.app.App;
import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.Field;
import com.hwr_goes_beuth.cardz.entities.Hand;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.User;
import com.hwr_goes_beuth.cardz.match.MatchHelper;
import com.hwr_goes_beuth.cardz.match.MatchPhase;

/**
 * Created by Project0rion on 30.12.2016.
 */
public class MatchUsersTurnPhase extends MatchPhase {

    private boolean drewCard;

    public MatchUsersTurnPhase(DAOFactory daoFactory) {
        super(daoFactory);
        drewCard = false;
    }

    @Override
    public String getPhaseTitle() {
        return App.getContext().getString(R.string.match_phase_title_matchUsersTurn);
    }

    @Override
    public void run() {
        User currentUser = getDaoFactory().getUserDAO().getOrCreateCurrentUser();
        Match currentMatch = getDaoFactory().getUserDAO().getCurrentMatch(currentUser);
        Player opponent = getDaoFactory().getMatchDAO().getOpponent(currentMatch);
        Player matchUser = getDaoFactory().getMatchDAO().getMatchUser(currentMatch);

        MatchHelper.letPlayerDrawCards(getDaoFactory(), matchUser, 1);
        drewCard = true;

        makeMatchUserPlayCard(matchUser);
    }

    private void makeMatchUserPlayCard(Player matchUser) {
        Hand matchUserHand = getDaoFactory().getPlayerDAO().getHand(matchUser);
        Field matchUserField = getDaoFactory().getPlayerDAO().getField(matchUser);

        long cardId = matchUserHand.getCardIds().get(0);
        matchUserField.setCenterLeftCardId(cardId);
        matchUserHand.getCardIds().remove(cardId);
        getDaoFactory().getFieldDAO().updateField(matchUserField);
        getDaoFactory().getHandDAO().updateHand(matchUserHand);
    }

    @Override
    public boolean canGoToNextPhase() {
        return drewCard;
    }

    @Override
    public MatchPhase getNextPhase() {
        return new OpponentsTurnPhase(getDaoFactory());
    }

    @Override
    public boolean canUserPerformAction() {
        return drewCard;
    }

    @Override
    public com.hwr_goes_beuth.cardz.entities.enums.MatchPhase getMappedPhase() {
        return com.hwr_goes_beuth.cardz.entities.enums.MatchPhase.MatchUsersTurn;
    }
}
