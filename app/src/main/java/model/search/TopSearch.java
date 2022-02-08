package model.search;

import java.util.List;

import model.game.IDetailsGame;
import model.game.IListGame;
import model.game.IMainGame;

/**
 * Abstract class that implements the search interface.
 */
public abstract class TopSearch implements Search{

    /**
     *Pass implementation to concrete class.
     */
    public abstract List<IMainGame> topSearch(List<IDetailsGame> games, int numOfGames);

    /**
     * Throw an error if the wrong search is used.
     */
    public List<IListGame> itemSearch(List<IListGame> games, String name){
        throw new RuntimeException();
    }

}
