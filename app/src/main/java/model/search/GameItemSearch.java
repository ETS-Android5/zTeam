package model.search;

import java.util.ArrayList;
import java.util.List;
import model.game.IListGame;

/**
 * Concrete class to search for a game given a string from the user.
 */
public class GameItemSearch extends ItemSearch {

    /**
     * Concrete implementation of the method to return a list of games that has the user inputted
     * string.
     * @return List of IListGame search results.
     */
    @Override
    public List<IListGame> itemSearch(List<IListGame> games, String name) {
        List<IListGame> searchResultGames = new ArrayList<>();
        for (IListGame game : games){
            if (game.getName().toLowerCase().contains(name.toLowerCase())){
                searchResultGames.add(game);
            }
        }
        return searchResultGames;
    }
}
