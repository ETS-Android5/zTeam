package model.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.game.IDetailsGame;
import model.game.IMainGame;
import model.game.MainGame;

/**
 * Concrete class that implements the most viewed games.
 */
public class TopGenreSearch extends TopSearch{

    /**
     * Method to return the most viewed games.
     * @param games All the games in the database.
     * @param numOfGames The number of games to fetch.
     * @return The top number of games in an IMainGame object.
     */
    @Override
    public List<IMainGame> topSearch(List<IDetailsGame> games, int numOfGames) {
        List<IMainGame> results = new ArrayList<>();
        Collections.sort(games, Comparator.comparingInt(g -> -g.getViews()));
        for(int i = 0; i < numOfGames; i++){
            IMainGame mainGame = new MainGame(games.get(i).getName(), games.get(i).getDevelopers(),
                    games.get(i).getIcon());
            results.add(mainGame);
        }

        return results;
    }
}
