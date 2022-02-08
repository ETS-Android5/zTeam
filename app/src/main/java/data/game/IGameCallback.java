package data.game;

import java.util.List;

import model.game.IDetailsGame;

/**
 * Callback interface to synchronously retrieve the list of games.
 */
public interface IGameCallback {
    void onSuccess(List<IDetailsGame> gameList);
}
