package model.search;

import java.util.List;

import model.game.IDetailsGame;
import model.game.IListGame;
import model.game.IMainGame;

/**
 * Search interface used for all searches in the application.
 */
public interface Search {
    /**
     * Returns the most viewed items in the database.
     */
    List<IMainGame> topSearch(List<IDetailsGame> games, int numOfGames);

    /**
     * Returns a list of games that match the user input.
     */
    List<IListGame> itemSearch(List<IListGame> games, String name);
}
