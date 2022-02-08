package model.game;

import java.util.List;

/**
 * Interface that provide high-level access to ListGame
 * @author John Jia
 */
public interface IListGame {

    /**
     * String to return the game name.
     * @return String representing the name.
     */
    String getName();

    /**
     * Retutn the size of the game.
     * @return String representing the size of the game.
     */
    String getSize();

    /**
     * String representing the rating of a game.
     * @return String representing the rating of the game.
     */
    String getRating();

    /**
     * Return the genres of a game.
     * @return List of strings representing the genre names.
     */
    List<String> getGenres();


    /**
     * Return the icon of a game.
     * @return String representing the directory location of the icon.
     */
    String getIcon();
}