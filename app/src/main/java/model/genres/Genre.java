package model.genres;

/**
 * Genre Class that holds all the information needed for a Genre view
 */
public class Genre implements ICategory {
    private final String ImageLocation;
    private final String Name;
    private final String Description;
    private final String gameColour;
    private final String iconLocation;

    public Genre(String imageLocation, String name, String description, String gameColour, String iconLocation) {
        this.gameColour = gameColour;
        this.iconLocation = iconLocation;
        this.ImageLocation = imageLocation;
        this.Name = name;
        this.Description = description;
    }



    @Override
    public String getGameColor() {
        return gameColour;
    }

    @Override
    public String getIconLocation() {
        return iconLocation;
    }

    @Override
    public String getImageLocation() {
        return ImageLocation;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getDescription() {
        return Description;
    }
}

