package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.Match;
import java.util.List;

public interface MatchDAO {

    Match getMatch(long id);
    Match createMatch();
    void updateMatch(Match match);
    void deleteMatch(long id);

    Player getMatchUser(Match match);
    Player getOpponent(Match match);
}
