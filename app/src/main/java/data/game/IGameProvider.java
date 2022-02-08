package data.game;

/**
 * Interface for the Activity class to call to retrieve a list of games.
 */
public interface IGameProvider {
    void fetchGames(IGameCallback gameCallback);
}
