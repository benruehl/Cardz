package com.hwr_goes_beuth.cardz.core.presentation;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class PresenterCacheInjectionWrapper {

    @Inject
    Lazy<PresenterCache> presenterCache;

    public PresenterCache getPresenterCache() {
        return presenterCache.get();
    }
}
