package com.hwr_goes_beuth.cardz.core.presentation;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

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

        setupFullscreen();
    }

    private void setupFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    protected abstract TPresenter createPresenter();

    protected abstract void update();

    protected TPresenter getPresenter() {
        return mPresenter;
    }
}
