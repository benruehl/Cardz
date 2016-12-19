package com.hwr_goes_beuth.cardz.entities;

/**
 * Created by Project0rion on 18.12.2016.
 */
public class Entity {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id != 0)
            throw new IllegalStateException("id of entity cannot be modified");

        this.id = id;
    }
}
