package com.hwr_goes_beuth.cardz.core.modules;

import com.hwr_goes_beuth.cardz.core.dataAccess.DAOFactory;
import com.hwr_goes_beuth.cardz.game.cards.CardRepository;
import com.hwr_goes_beuth.cardz.game.opponents.DeckRepository;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentManager;
import com.hwr_goes_beuth.cardz.game.opponents.OpponentRepository;

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

    @Provides
    @Singleton
    DeckRepository providesDeckRepository(DAOFactory daoFactory, CardRepository cardRepository) {
        return new DeckRepository(daoFactory, cardRepository);
    }

    @Provides
    @Singleton
    OpponentRepository providesOpponentRepository() {
        return new OpponentRepository();
    }

    @Provides
    @Singleton
    OpponentManager providesOpponentManager(DAOFactory daoFactory, DeckRepository deckRepository, OpponentRepository opponentRepository) {
        return new OpponentManager(daoFactory, deckRepository, opponentRepository);
    }
}
