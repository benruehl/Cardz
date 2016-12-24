package com.hwr_goes_beuth.cardz.core.presentation;

import com.hwr_goes_beuth.cardz.core.app.App;
import com.hwr_goes_beuth.cardz.core.app.AppComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class ActivityPresenter {

    private List<PresentedActivity> mObservingViews = new ArrayList<>();

    public void registerObserver(PresentedActivity activity) {
        mObservingViews.add(activity);
    }

    protected void notifyChange() {
        for (PresentedActivity view : mObservingViews) {
            view.update();
        }
    }

    public void init() {

    }

    public void inject(AppComponent appComponent) {

    }
}
