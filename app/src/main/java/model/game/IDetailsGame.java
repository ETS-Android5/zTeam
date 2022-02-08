package model.game;

import java.util.List;

/**
 * Interface that provide high-level access to DetailsGame
 * @author John Jia
 */
public interface IDetailsGame {

    /**
     * Return the description of the game.
     * @return String representing the description.
     */
    String getDescription();

    /**
     * Return the developer of the game.
     * @return String representing the developer.
     */
    String getDevelopers();

    /**
     * Return the list of pictures
     * @return List of strings representing the picture locations.
     */
    List<String> getMainPicturesLocation();

    /**
     * Return the size of the game.
     * @return String representing the size of the game.
     */
    String getSize();

    /**
     * Return the name of the game.
     * @return String representing the name of the game.
     */
    String getName();

    /**
     * Get the rating of the game.
     * @return String representing the rating of a game.
     */
    String getRating();

    /**
     * Get the genres of a game.
     * @return List of strings containing the genre name.
     */
    List<String> getGenres();

    /**
     * Get the icon of a game.
     * @return String representing the location of the icon of the game.
     */
    String getIcon();

    /**
     * Get the number of times a game has been viewed.
     * @return int value representing the amount of times a game has been viewed.
     */
    int getViews();

    /**
     *  Get the number of reviews of a game.
     * @return String representing the amount of times a game has been reviewed.
     */
    String getReviews();

}
