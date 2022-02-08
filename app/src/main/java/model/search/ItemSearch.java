package model.search;

import java.util.List;

import model.game.IDetailsGame;
import model.game.IListGame;
import model.game.IMainGame;

/**
 * Abstract class implements the search interface.
 */
public abstract class ItemSearch implements Search{

    /**
     * Throw an error if using the wrong search.
     */
    @Override
    public List<IMainGame> topSearch(List<IDetailsGame> games, int numOfGames) {
        throw new RuntimeException();
    }

    /**
     * Pass implementation to concrete class.
     */
    public abstract List<IListGame> itemSearch(List<IListGame> games, String name);

}
