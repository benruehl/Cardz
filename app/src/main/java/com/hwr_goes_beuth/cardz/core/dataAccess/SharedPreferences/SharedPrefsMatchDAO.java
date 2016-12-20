package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchUserDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.OpponentDAO;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.MatchUser;
import com.hwr_goes_beuth.cardz.entities.Opponent;

/**
 * Created by Project0rion on 19.12.2016.
 */
public class SharedPrefsMatchDAO extends AbstractSharedPrefsDAO implements MatchDAO {

    MatchUserDAO matchUserDAO;
    OpponentDAO opponentDAO;

    public SharedPrefsMatchDAO(SharedPreferences sharedPreferences, Gson gson, MatchUserDAO matchUserDAO, OpponentDAO opponentDAO) {
        super(sharedPreferences, gson);

        this.matchUserDAO = matchUserDAO;
        this.opponentDAO = opponentDAO;
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
