package zteam.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.genres.ICategory;
import zteam.R;
import zteam.details.DetailsActivity;

/**
 * Adapter class responsible for populating the list of games within the RecyclerView of the
 * ListActivity.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView rating;
        TextView size;
        View gameBackgroundView;

        /**
         * Inner ViewHolder class that only generates the data as required on the screen for memory
         * purposes.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.category_label);
            rating = itemView.findViewById(R.id.rating);
            size = itemView.findViewById(R.id.size);
            gameBackgroundView = itemView.findViewById(R.id.game_background);
        }
    }

    private List<String> images;
    private List<String> names;
    private List<String> ratings;
    private List<String> sizes;
    private ICategory mCategory;
    private Context mContext;

    public ListAdapter(@NonNull Context context, List<String> images, List<String> names,
                       List<String> ratings, List<String> sizes, ICategory category) {
        this.images = images;
        this.names = names;
        this.ratings = ratings;
        this.sizes = sizes;
        this.mCategory = category;
        this.mContext = context;
    }

    /**
     * Create the card by inflating the List card layout.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card,parent,
                false);
        return new ViewHolder(view);
    }

    /**
     * Define game information when on screen and set up a listener to enter the DetailsActivity.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int img = mContext.getResources().getIdentifier(images.get(position), "drawable",
                mContext.getPackageName());
        holder.image.setImageResource(img);
        holder.name.setText(names.get(position));
        holder.rating.setText(ratings.get(position));
        holder.size.setText(sizes.get(position));
        int i = mContext.getResources().getIdentifier(this.mCategory.getGameColor(),
                "drawable", mContext.getPackageName());
        holder.gameBackgroundView.setBackgroundResource(i);
        holder.gameBackgroundView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("name", names.get(holder.getAdapterPosition()));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((
                    ListActivity)mContext, holder.image, ViewCompat.getTransitionName(holder.image));
            mContext.startActivity(intent,options.toBundle());
        });

    }

    /**
     * Return the number of games to be populated in the RecyclerView.
     */
    @Override
    public int getItemCount() {
        return names.size();
    }

}
