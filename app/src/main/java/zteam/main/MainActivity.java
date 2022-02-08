package zteam.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import data.game.GameProvider;
import data.genre.GenreProvider;
import data.genre.IGenreProvider;
import data.game.IGameCallback;
import data.game.IGameProvider;
import model.game.IDetailsGame;
import model.game.IMainGame;
import model.genres.ICategory;
import model.search.Search;
import model.search.TopGenreSearch;
import zteam.R;
import zteam.search.SearchActivity;

/**
 * Create and display Main Screen
 */
public class MainActivity extends AppCompatActivity{
    private List<String> labels = new ArrayList<>();
    private List<String> descriptions = new ArrayList<>();
    private List<String> images = new ArrayList<>();

    private List<String> names = new ArrayList<>();
    private List<String> developers = new ArrayList<>();
    private List<String> icons = new ArrayList<>();

    private int numGames = 10;

    private ProgressBar progressBar;

    /**
     * Create MainActivity Screen and display it
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createProgressBar();

        ImageView search = findViewById(R.id.search_icon);
        search.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            this.startActivity(intent);
        });

        fetchCategoryData();
    }

    /**
     * Fetch all genre data. On completion, initialize Recycler View for
     * category section.
     */
    private void fetchCategoryData() {
        IGenreProvider provider = GenreProvider.getInstance();
        //Receive results from the fetch and set the fields accordingly.
        provider.fetchGenres(categoryList -> {
            for(ICategory category: categoryList){
                labels.add(category.getName());
                descriptions.add(category.getDescription());
                images.add(category.getImageLocation());
            }
            fetchMostViewedGames();
            populateCategories();
        });
    }

    /**
     * Fetch the most viewed games. On completetion initialize the Recycler View for the Most
     * Viewed games.
     */
    public void fetchMostViewedGames() {
        IGameProvider gameProvider = GameProvider.getInstance();
        gameProvider.fetchGames(new IGameCallback() {
            @Override
            public void onSuccess(List<IDetailsGame> gameList) {
                names.clear();
                developers.clear();
                icons.clear();
                Search searchAgent = new TopGenreSearch();
                List<IMainGame> mainGames = searchAgent.topSearch(gameList, numGames);
                for(int i = 0; i < numGames; i++){
                    names.add(mainGames.get(i).getName());
                    developers.add(mainGames.get(i).getDeveloper());
                    icons.add(mainGames.get(i).getIcon());

                }
                populateMostViewed();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Initialize a horizontal recycler view for most viewed games cards
     */
    private void populateMostViewed() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.
                HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.top_items);
        recyclerView.setLayoutManager(layoutManager);
        MostViewedIGameAdapter adapter = new MostViewedIGameAdapter(this, progressBar,names,
                developers, icons);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Initialize a horizontal recycler view for categories cards
     */
    private void populateCategories() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(R.id.category_recycler);
        recyclerView.setLayoutManager(layoutManager);
        CategoryViewAdapter adapter = new CategoryViewAdapter(labels,descriptions,images,this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Initialize and display a progress bar
     */
    private void createProgressBar(){
        RelativeLayout layout= findViewById(R.id.main_root);

        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar, params);

        progressBar.setVisibility(View.VISIBLE);
    }
}