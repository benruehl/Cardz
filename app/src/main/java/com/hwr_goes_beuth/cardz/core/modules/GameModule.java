package com.hwr_goes_beuth.cardz.core.modules;

import com.hwr_goes_beuth.cardz.game.cards.CardRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Project0rion on 04.12.2016.
 */
@Module
public class GameModule {

    @Provides
    @Singleton
    CardRepository providesCardRepository() {
        return new CardRepository();
    }
}
