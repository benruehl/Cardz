package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Card;
import com.hwr_goes_beuth.cardz.entities.Match;
import com.hwr_goes_beuth.cardz.entities.Deck;
import com.hwr_goes_beuth.cardz.entities.User;
import java.util.List;

public interface UserDAO {

    User getOrCreateCurrentUser();
    void updateUser(User user);
    void deleteCurrentUser();

    List<Card> getCollectedCards(User user);
    Match getCurrentMatch(User user);
    Deck getRaptorDeck(User user);
    Deck getSharkDeck(User user);
    void deleteCurrentMatch(User user);
}
