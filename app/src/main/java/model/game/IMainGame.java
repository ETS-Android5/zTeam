package model.game;

/**
 * Interface for access to game details for MainActivity
 */
public interface IMainGame {
    /**
     * String to return the game name.
     * @return String representing the name.
     */
    String getName();

    /**
     * Return the developer of the game.
     * @return String representing the developer.
     */
    String getDeveloper();

    /**
     * Return the image location of the game.
     * @return String representing the image location.
     */
    String getIcon();
}
