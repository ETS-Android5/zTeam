package zteam.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import model.game.IListGame;
import zteam.R;
import zteam.details.DetailsActivity;

/**
 * Adapter class to populate the RecyclerView with the resulting games.
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * Inner ViewHolder class that only generates the data as required on the screen for memory
     * purposes.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView image;
        TextView name;
        TextView rating;
        TextView size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.search_name);
            rating = itemView.findViewById(R.id.rating);
            card = itemView.findViewById(R.id.search_card);
            size = itemView.findViewById(R.id.size);
        }

        /**
         * Set the view of the card.
         */
        public void setView(String name, String rating, String size, String image){
            this.name.setText(name);
            this.rating.setText(rating);
            this.size.setText(size);
            int img = mContext.getResources().getIdentifier(image, "drawable", mContext.getPackageName());
            this.image.setImageResource(img);
            this.card.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("name", name);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((SearchActivity)mContext, this.image, ViewCompat.getTransitionName(this.image));
                mContext.startActivity(intent,options.toBundle());
            });
        }
    }

    /**
     * Inner class for when no search results are found.
     */
    public class NotFoundViewholder extends RecyclerView.ViewHolder{
        public NotFoundViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private List<String> names;
    private List<String> ratings;
    private List<String> sizes;
    private List<String> images;
    private List<IListGame> games;
    Context mContext;

    public SearchAdapter(Context mContext, List<IListGame> games) {
        this.mContext = mContext;
        this.images = new ArrayList<>();
        this.sizes = new ArrayList<>();
        this.names = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.games = games;
    }

    /**
     * Create either the Search activity card or the not found card depending on the search result.
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType != 0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                    search_activity_card, parent, false);
            return new SearchAdapter.ViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.not_found,
                    parent, false);
            return new NotFoundViewholder(view);
        }
    }

    /**
     * Set the details of the card when on screen.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != 0){
            ((ViewHolder)holder).setView(this.games.get(position).getName(), this.games.get(
                    position).getRating(), this.games.get(position).getSize(),
                    this.games.get(position).getIcon());
        }
    }

    /**
     * Return the amount of items to be displayed in the recycler view.
     */
    @Override
    public int getItemCount() {
        if (this.games.size() != 0){
            return this.games.size();
        }
        // We must return 1 to display the card view of the no results image.
        else{
            return 1;
        }
    }

    /**
     * Returns which item view is being returned.
     */
    @Override
    public int getItemViewType(int position) {
        return this.games.size();
    }

}
