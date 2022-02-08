package data.game;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.genre.IGenreProvider;
import data.genre.GenreProvider;
import model.game.DetailsGame;
import model.game.IDetailsGame;

/**
 * Singleton class to fetch the game data in the database.
 */
public class GameProvider implements IGameProvider {

    //Store instance of GameProvider to maintain one object.
    private static GameProvider gameProvider;
    private List<IDetailsGame> games = new ArrayList<>();

    /**
     * Private constructor to prevent instantiation.
     */
    private GameProvider() {}

    /**
     * Method to get the single instance of GameProvider.
     * @return The instance of GameProvider.
     */
    public static GameProvider getInstance() {
        if (gameProvider == null) {
            gameProvider = new GameProvider();
        }
        return gameProvider;
    }


    /**
     * Method to return games from the database.
     * @param gameCallback Callback to synchronously return items to the caller.
     */
    public void fetchGames(IGameCallback gameCallback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        IGenreProvider gp = GenreProvider.getInstance();
        db.collection("Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            games.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map map = document.getData();
                                String name = (String)map.get("Name");
                                String rating = (String)map.get("Rating");
                                String size = (String)map.get("Size");
                                String description = (String)map.get("Description");
                                List<String> mainPics = (List<String>)map.get("MainPicturesLocation");
                                String developer = (String)map.get("Developer");
                                int views = ((Long)map.get("Views")).intValue();
                                List<String> genres = (List<String>)map.get("Genres");
                                String icon = (String)map.get("Icon");
                                String review_num = (String)map.get("Reviews") + " reviews";
                                games.add(new DetailsGame(name, rating, size, genres, icon, description, developer,
                                        mainPics, views, review_num));

                            }
                            gameCallback.onSuccess(games);
                        }
                    }
                });
    }

}
