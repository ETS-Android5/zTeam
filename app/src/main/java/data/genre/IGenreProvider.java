package data.genre;

/**
 * Interface for activity classes to use to retrieve the list of games.
 */
public interface IGenreProvider {
    void fetchGenres(IGenreCallback genreListCallback);
}
