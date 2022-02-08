package model.game;

import java.util.List;

/**
 * DetailsGame Class that holds all the information needed for a Detail Game View
 */
public class DetailsGame extends Game implements IDetailsGame{

    private String description;
    private String developer;
    private List<String> mainPictureLocation;
    private int views;
    private String review_num;
    private String size;

    /**
     * Constructor
     * @param name name
     * @param rating rating
     * @param size size
     * @param genres genres
     * @param icon icon
     * @param description description
     * @param developer developer
     * @param mainPictureLocation mainPictureLocation
     * @param review_num review_num
     */
    public DetailsGame(String name, String rating, String size, List<String> genres, String icon,
                       String description, String developer,
                       List<String> mainPictureLocation,int views,
                       String review_num) {
        super(name, rating, genres, icon);
        this.description = description;
        this.developer = developer;
        this.mainPictureLocation = mainPictureLocation;
        this.views = views;
        this.review_num = review_num;
        this.size = size;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDevelopers() {
        return developer;
    }

    @Override
    public List<String> getMainPicturesLocation() {
        return mainPictureLocation;
    }

    @Override
    public String getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public List<String> getGenres(){
        return super.getGenres();
    }

    @Override
    public String getIcon(){
        return super.getIcon();
    }

    public int getViews() {
        return views;
    }

    public String getReviews() {
        return review_num;
    }

}
