package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.MatchUser;

/**
 * Created by Project0rion on 19.12.2016.
 */
public interface MatchUserDAO {

    MatchUser getMatchUser(long id);
    MatchUser createSharkMatchUser();
    MatchUser createRaptorMatchUser();
    void deleteMatchUser(long matchUserId);
}
