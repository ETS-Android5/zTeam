package zteam.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import zteam.R;

import java.util.ArrayList;
import java.util.List;

import data.game.GameProvider;
import data.game.IGameCallback;
import data.game.IGameProvider;
import model.game.IDetailsGame;
import model.game.IListGame;
import model.game.ListGame;
import model.search.GameItemSearch;
import model.search.Search;

/**
 * SearchActivity class which is responsible for rendering the view for the search screen.
 */
public class SearchActivity extends AppCompatActivity {

    private List<IListGame> games = new ArrayList<>();

    /**
     * This method renders the view on creation.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        fetchAllGames();
    }

    /**
     * Retrieves all the games from the database in the form of an IListGame.
     */
    private void fetchAllGames(){
        IGameProvider gameProvider = GameProvider.getInstance();
        gameProvider.fetchGames(new IGameCallback() {
            @Override
            public void onSuccess(List<IDetailsGame> gameList) {
                for (IDetailsGame detailsGame : gameList) {
                    games.add(new ListGame(detailsGame.getName(),detailsGame.getRating(),
                            detailsGame.getSize(),detailsGame.getGenres(), detailsGame.getIcon()));
                }
                setUpAllGames();
            }
        });
    }

    /**
     * Set up the key events for all games.
     */
    private void setUpAllGames(){
        EditText editText = findViewById(R.id.edittext);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (i == KeyEvent.KEYCODE_ENTER)){
                    populateSearchResult(editText.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Search for a game given user input.
     */
    private List<IListGame> searchForGame(String input) {
        Search searchAgent = new GameItemSearch();
        return searchAgent.itemSearch(games, input);
    }

    /**
     *Populate the Recycler View after the user has entered in their input.
     */
    private void populateSearchResult(String text) {
        List<IListGame> searchResult = searchForGame(text);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.search_result_recycler);
        recyclerView.setLayoutManager(layoutManager);
        SearchAdapter adapter = new SearchAdapter(this, searchResult);
        recyclerView.setAdapter(adapter);
    }
}