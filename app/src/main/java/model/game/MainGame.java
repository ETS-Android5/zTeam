package model.game;

/**
 * Conrete implementation of the interface IMainGame to represent the information needed for
 * MainActivity in the MostViewed panel.
 */
public class MainGame  extends Game implements IMainGame{

    /**
     * Constructor to initalize object.
     * @param name Name of the game
     * @param developer Name of developer.
     */
    public MainGame(String name, String developer, String imageLocation) {
        super(name, developer, imageLocation);
    }

}
