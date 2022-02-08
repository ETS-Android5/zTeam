package data.genre;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.genres.ICategory;
import model.genres.Genre;

/**
 * Singleton classes to get the genres from the database.
 */
public class GenreProvider implements IGenreProvider {

    private static GenreProvider genreProvider;

    private GenreProvider() {}

    public static GenreProvider getInstance() {
        if (genreProvider == null) {
            genreProvider = new GenreProvider();
        }
        return genreProvider;
    }

    /**
     * Fetch genres from the database and return synchronously.
     * @param genreListCallback
     */
    public void fetchGenres(IGenreCallback genreListCallback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<ICategory> genres = new ArrayList<>();
        db.collection("Genres")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map map = document.getData();
                            String img = (String)map.get("ImageLocation");
                            String name = (String)map.get("Name");
                            String description = (String)map.get("Description");
                            String icon = (String)map.get("IconLocation");
                            String gameColour = (String)map.get("GameColor");
                            Genre genre = new Genre(img, name, description, gameColour, icon);
                            genres.add(genre);
                        }
                        genreListCallback.onSuccess(genres);
                    }
                });
    }


}
