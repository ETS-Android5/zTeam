package model.genres;

/**
 * Interface that provide high-level access to Category
 * @author John Jia
 */
public interface ICategory {
    /**
     * Get the description of the category.
     * @return String representing the description of a category.
     */
    String getDescription();

    /**
     * Get the colour of the game for dynamic rendering.
     * @return String representing the colour.
     */
    String getGameColor();

    /**
     * Get the image location of the game in a directory.
     * @return String representing the directory location.
     */
    String getImageLocation();

    /**
     * Get the icon loaction of the game in a directory.
     * @return String representing the directory location.
     */
    String getIconLocation();

    /**
     * Get the name of the category.
     * @return String representing the name of the category.
     */
    String getName();
}
