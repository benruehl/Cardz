package com.hwr_goes_beuth.cardz.core.dataAccess;

import com.hwr_goes_beuth.cardz.entities.Field;

/**
 * Created by Project0rion on 19.12.2016.
 */
public interface FieldDAO {

    Field getField(long id);
    Field createField();
}
