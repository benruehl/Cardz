package com.hwr_goes_beuth.cardz.core.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hwr_goes_beuth.cardz.core.app.App;

import javax.inject.Inject;

/**
 * Created by Project0rion on 04.12.2016.
 */
public abstract class PresentedActivity<TPresenter extends ActivityPresenter> extends AppCompatActivity {

    private TPresenter mPresenter;
    PresenterCacheInjectionWrapper mPresenterCacheWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterCacheWrapper = new PresenterCacheInjectionWrapper();
        ((App) getApplication()).getAppComponent().inject(mPresenterCacheWrapper);

        TPresenter newPresenter = createPresenter();
        PresenterCache presenterCache = mPresenterCacheWrapper.getPresenterCache();
        TPresenter presenterFromCache = (TPresenter)presenterCache.get(newPresenter.getClass());
        mPresenter = presenterFromCache != null ? presenterFromCache : newPresenter;

        if (mPresenter != presenterFromCache)
            presenterCache.put(mPresenter);

        mPresenter.inject(((App) getApplication()).getAppComponent());
        mPresenter.registerObserver(this);
        mPresenter.init();
    }

    protected abstract TPresenter createPresenter();

    protected abstract void update();

    protected TPresenter getPresenter() {
        return mPresenter;
    }
}
