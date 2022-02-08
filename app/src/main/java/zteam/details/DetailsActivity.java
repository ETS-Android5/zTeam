package zteam.details;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import zteam.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import data.game.GameProvider;
import data.game.IGameProvider;
import model.game.IDetailsGame;

/**
 * DetailsActivity class which is responsible for rendering the view for a detailed view of the
 * game.
 */
public class DetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DetailsAdapter mainAdapter;
    private String gameName;
    private ViewPager mViewPager;
    private SlideImgAdapter mViewPagerAdapter;
    private List<String> MainPicturesLocation;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        gameName = this.getIntent().getExtras().getString("name");
        fetchDetailViewGame(gameName, this);
    }

    /**
     * Fetch data of the game that needs to be displayed on the detail view
     * @param name Name of the game that needs to be displayed
     * @param context pass in setup in order to use context in adapter
     */
    public void fetchDetailViewGame(String name, Context context){
        IGameProvider gameProvider = GameProvider.getInstance();
        gameProvider.fetchGames(games -> {
            for (IDetailsGame detailsGame : games) {
                if (detailsGame.getName().equals(name)) {
                    setup(detailsGame,context);
                    break;
            }
        }
        });
    }

    /**
     * Set up the views in DetailsActivity.
     * @param game IDetailsGame object that stores all the information needed
     * @param context Android Context to be set up
     */
    public void setup(IDetailsGame game,Context context) {
        ExpandableTextView expTv = findViewById(R.id.expand_text_view);
        TextView game_developer =  findViewById(R.id.developer);
        TextView game_views = findViewById(R.id.views);
        TextView game_size = findViewById(R.id.size);
        TextView game_name = findViewById(R.id.name);
        TextView game_review = findViewById(R.id.star);
        TextView game_review_count = findViewById(R.id.viewText2);
        ImageView game_icon = findViewById(R.id.game_icon);

        expTv.setText(game.getDescription());
        game_developer.setText(game.getDevelopers());
        game_name.setText(game.getName());
        game_size.setText(game.getSize());
        game_views.setText(Integer.toString(game.getViews()));
        game_review.setText(game.getRating());
        game_review_count.setText(game.getReviews());
        game_icon.setImageResource(context.getResources().getIdentifier(game.getIcon(), "drawable", context.getPackageName()));
        MainPicturesLocation = new ArrayList<>();
        MainPicturesLocation = game.getMainPicturesLocation();
        setUpImageSwitcher();
        List<String> genreNames = game.getGenres();
        initItemAdapter(genreNames);
    }

    /**
     * Set up the adapter and set it to the recycler view to dynamically display the genre
     * @param genres Genres to be set up.
     */
    private void initItemAdapter(List<String> genres) {
        recyclerView = findViewById(R.id.genres_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                DetailsActivity.this, LinearLayoutManager.HORIZONTAL, false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainAdapter = new DetailsAdapter(this, genres);
        recyclerView.setAdapter(mainAdapter);
    }

    /**
     * Set up the animation of the viewpager.
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setUpImageSwitcher(){
        mViewPager = findViewById(R.id.main_switcher);
        final float MIN_SCALE = 0.85f;
        final float MIN_ALPHA = 0.5f;
        mViewPager.setPageTransformer(false, (page, position) -> {
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();
            if ( position < -1 ) {
                page.setAlpha( 0 );
            }
            else if ( position <= 1 ) {
                float scaleFactor = Math.max( MIN_SCALE, 1 - Math.abs( position ) );
                float vertMargin = pageHeight * ( 1 - scaleFactor ) / 2;
                float horzMargin = pageWidth * ( 1 - scaleFactor ) / 2;
                if ( position < 0 ) {
                    page.setTranslationX( horzMargin - vertMargin / 2 );
                } else {
                    page.setTranslationX( -horzMargin + vertMargin / 2 );
                }
                page.setScaleX( scaleFactor );
                page.setScaleY( scaleFactor );
                page.setAlpha( MIN_ALPHA +
                        ( scaleFactor - MIN_SCALE ) /
                                ( 1 - MIN_SCALE ) * ( 1 - MIN_ALPHA ));
            } else {
                page.setAlpha( 0 );
            }
        });
        // Set the number to 3 images.
        int[] images = new int[3];
        int i = 0;
        for (String img : MainPicturesLocation){
            images[i] = this.getResources().getIdentifier(img, "drawable", this.getPackageName());
            i++;
        }
        mViewPagerAdapter = new SlideImgAdapter(this, images);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

}