package com.hwr_goes_beuth.cardz.core.dataAccess.SharedPreferences;

import com.hwr_goes_beuth.cardz.entities.Player;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.core.dataAccess.PlayerDAO;
import com.hwr_goes_beuth.cardz.core.dataAccess.MatchDAO;
import com.hwr_goes_beuth.cardz.entities.enums.Faction;
import com.hwr_goes_beuth.cardz.entities.enums.MatchPhase;
import java.util.List;
import java.util.ArrayList;

public class SharedPrefsMatchDAO implements MatchDAO {

    private SharedPrefsDAOContext context;
    private PlayerDAO playerDAO;

    public SharedPrefsMatchDAO(SharedPrefsDAOContext context, PlayerDAO playerDAO) {
        this.context = context;
        this.playerDAO = playerDAO;
    }

    @Override
    public Match getMatch(long id) {
        return context.loadFromPrefs(Match.class, id);
    }

    @Override
    public Match createMatch() {
        Match newMatch = new Match();
        newMatch.setId(context.getNextId());
        newMatch.setMatchPhase(MatchPhase.Initial);

        Player newMatchUser = playerDAO.createSharkPlayer();
        Player newOpponent = playerDAO.createRaptorPlayer();

        newMatch.setMatchUserId(newMatchUser.getId());
        newMatch.setOpponentId(newOpponent.getId());

        context.saveToPrefs(newMatch);

        return newMatch;
    }

    @Override
    public void updateMatch(Match match) {
        context.saveToPrefs(match);
    }

    @Override
    public void deleteMatch(long matchId) {
        Match match = getMatch(matchId);

        playerDAO.deletePlayer(match.getMatchUserId());
        playerDAO.deletePlayer(match.getOpponentId());

        context.deleteFromPrefs(match);
    }

    @Override
    public Player getMatchUser(Match match) {
        return playerDAO.getPlayer(match.getMatchUserId());
    }

    @Override
    public Player getOpponent(Match match) {
        return playerDAO.getPlayer(match.getOpponentId());
    }
}
