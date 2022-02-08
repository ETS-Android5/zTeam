package model.game;

import java.util.List;

/**
 * ListGame Class that holds all the information needed for a List Game View
 * @author John Jia
 */
public class ListGame extends Game implements IListGame{
    private String size;

    /**
     * Constructor for ListGame
     * @param name name
     * @param rating rating
     * @param size size
     * @param genres genres
     * @param icon icon
     */
    public ListGame(String name, String rating, String size, List<String> genres, String icon){
        super(name, rating, genres, icon);
        this.size = size;
    }

    @Override
    public String getSize(){
        return this.size;
    }

    @Override
    public String getIcon(){
        return super.getIcon();
    }
}