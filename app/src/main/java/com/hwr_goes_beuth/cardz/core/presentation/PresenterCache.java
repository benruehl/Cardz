package com.hwr_goes_beuth.cardz.core.presentation;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class PresenterCache {

    private Dictionary<Class, ActivityPresenter> cachedPresenters = new Hashtable<>();

    public void put(ActivityPresenter presenter) {
        cachedPresenters.put(presenter.getClass(), presenter);
    }

    public <T extends ActivityPresenter> T get(Class<T> presenterType) {
        return (T)cachedPresenters.get(presenterType.getClass());
    }
}
