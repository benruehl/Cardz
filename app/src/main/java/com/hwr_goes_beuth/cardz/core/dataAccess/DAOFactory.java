package com.hwr_goes_beuth.cardz.core.dataAccess;

/**
 * Created by Project0rion on 19.12.2016.
 * Follows the concepts described in the article found here: http://www.oracle.com/technetwork/java/dataaccessobject-138824.html
 */
public abstract class DAOFactory {

    public abstract UserDAO getUserDAO();
    public abstract MatchDAO getMatchDAO();
    public abstract MatchUserDAO getMatchUserDAO();
    public abstract OpponentDAO getOpponentDAO();
    public abstract DeckDAO getDeckDAO();
    public abstract HandDAO getHandDAO();
    public abstract FieldDAO getFieldDAO();
    public abstract CardDAO getCardDAO();
}
