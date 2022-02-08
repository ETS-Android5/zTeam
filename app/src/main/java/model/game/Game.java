package model.game;

import java.util.List;

/**
 * Abstract class of Game object, being the parent of DetailsGame, MainGame and ListGame, holding
 * common fields/methods.
 */
public abstract class Game {
    private String name;
    private String rating;
    private String developer;
    private List<String> genres;
    private String icon;

    /**
     * Constructor for MainGame.
     * @param name
     * @param developer
     * @param icon
     */
    public Game(String name, String developer, String icon) {
        this.name = name;
        this.developer = developer;
        this.icon = icon;
    }

    /**
     * Constructor for ListGame and DetailsGame
     * @param name name
     * @param rating rating
     * @param genres genres
     * @param icon icon
     */
    public Game(String name, String rating, List<String> genres, String icon) {
        this.name = name;
        this.rating = rating;
        this.genres = genres;
        this.icon = icon;
    }



    /**
     * Get the icon directory.
     * @return String representing the icon directory.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Get the name of the Game.
     * @return String representing the name of the game.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the rating of the game.
     * @return String representing the rating.
     */
    public String getRating() {
        return rating;
    }

    /**
     * Get the genres of the game.
     * @return List of genre names.
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Get the developer of the game
     * @return String representing the developer.
     */
    public String getDeveloper(){
        return developer;
    }
}
