package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.User;

/**
 * Created by Project0rion on 19.12.2016.
 */
public interface UserDAO {

    User getOrCreateRecentUser();
}
