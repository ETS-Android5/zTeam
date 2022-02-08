package zteam.list;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import data.game.GameProvider;
import data.game.IGameCallback;
import data.game.IGameProvider;
import data.genre.GenreProvider;
import data.genre.IGenreProvider;
import model.game.IDetailsGame;
import model.genres.ICategory;
import zteam.R;

/**
 * Responsible for the Category screen, entered from the home screen when selecting a category.
 */
public class ListActivity extends AppCompatActivity {

    List<String> images = new ArrayList<>();
    List<String> names = new ArrayList<>();
    List<String> ratings = new ArrayList<>();
    List<String> sizes = new ArrayList<>();
    String currentGenre;
    ICategory genre;
    ProgressBar progressBar;

    /**
     * Create List view and display it
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setupProgressBar();
        currentGenre = this.getIntent().getExtras().getString("label");
        fetchListViewGames();
    }

    /**
     *  Fetch data of games that belong to the current genre list
     */
    private void fetchListViewGames() {
        progressBar.setVisibility(View.VISIBLE);


        IGameProvider gameProvider = GameProvider.getInstance();
        gameProvider.fetchGames(new IGameCallback() {
            @Override
            public void onSuccess(List<IDetailsGame> gameList) {
                for(IDetailsGame game:gameList){
                    if(game.getGenres().contains(currentGenre)){
                        names.add(game.getName());
                        ratings.add(game.getRating());
                        sizes.add(game.getSize());
                        images.add(game.getIcon());
                    }
                }
                fetchListGenre();
            }
        });
    }

    /**
     * Fetch the layout information of current list
     */
    private void fetchListGenre(){
        IGenreProvider genreProvider = GenreProvider.getInstance();
        genreProvider.fetchGenres(categoryList -> {
            for(ICategory category: categoryList){
                if(category.getName().equals(currentGenre)){
                    genre = category;
                    initItemAdapter();
                }
            }
        });
    }

    /**
     * Initialize item adapter
     */
    private void initItemAdapter() {
        initLayout();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(R.id.item_recycler);
        recyclerView.setLayoutManager(layoutManager);
        ListAdapter adapter = new ListAdapter(this, images,names,ratings,sizes, genre);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Initialize view layout by dynamically setting Views
     */
    private void initLayout() {
        TextView labelView = findViewById(R.id.textView);
        labelView.setText(this.getIntent().getExtras().getString("label"));
        ImageView iconView = findViewById(R.id.imageView6);
        int j = this.getResources().getIdentifier(this.genre.getIconLocation(), "drawable", this.getPackageName());
        iconView.setImageResource(j);
    }

    /**
     * Set up a progress bar to show loading progress
     */
    private void setupProgressBar(){
        RelativeLayout layout= findViewById(R.id.list_root);
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(progressBar, params);
    }

}
