package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.Player;

/**
 * Created by Project0rion on 19.12.2016.
 */
public interface MatchDAO {

    Match getMatch(long id);
    Match createMatch();
    void deleteMatch(long matchId);

    Player getMatchUser(Match match);
    Player getOpponent(Match match);
}
