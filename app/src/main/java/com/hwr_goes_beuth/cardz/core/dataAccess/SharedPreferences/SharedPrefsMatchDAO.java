package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;


import com.hwr_goes_beuth.cardz.core.dataAccess.MatchDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchUserDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.OpponentDAO;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.MatchUser;
import com.hwr_goes_beuth.cardz.entities.Opponent;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsMatchDAO implements MatchDAO {

    private SharedPrefsDAOContext context;
    private MatchUserDAO matchUserDAO;
    private OpponentDAO opponentDAO;

    public SharedPrefsMatchDAO(SharedPrefsDAOContext context, MatchUserDAO matchUserDAO, OpponentDAO opponentDAO) {
        this.context = context;
        this.matchUserDAO = matchUserDAO;
        this.opponentDAO = opponentDAO;
    }

    @Override
    public Match getMatch(long id) {
        return context.loadFromPrefs(Match.class, id);
    }

    @Override
    public Match createMatch() {
        Match newMatch = new Match();
        newMatch.setId(context.getNextId());

        MatchUser newMatchUser = matchUserDAO.createSharkMatchUser();
        Opponent newOpponent = opponentDAO.createRaptorOpponent();

        newMatch.setMatchUserId(newMatchUser.getId());
        newMatch.setOpponentId(newOpponent.getId());

        context.saveToPrefs(newMatch);

        return newMatch;
    }

    @Override
    public MatchUser getMatchUser(Match match) {
        return matchUserDAO.getMatchUser(match.getMatchUserId());
    }

    @Override
    public Opponent getOpponent(Match match) {
        return opponentDAO.getOpponent(match.getOpponentId());
    }
}
