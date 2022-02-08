package zteam.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import zteam.R;
import zteam.details.DetailsActivity;

import java.util.List;

/**
 * Class to get the most viewed games and populated the Most Viewed RecyclerView in the Home Page.
 */
public class MostViewedIGameAdapter extends RecyclerView.Adapter<MostViewedIGameAdapter.ViewHolder>{

    /**
     * Inner ViewHolder class that only generates the data as required on the screen for memory
     * purposes.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView developer;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            developer = itemView.findViewById(R.id.developer);
            card = itemView.findViewById(R.id.most_viewed_card);
        }
    }

    private List<String> names;
    private List<String> developers;
    private List<String> icons;
    Context mContext;
    ProgressBar mProgressBar;


    public MostViewedIGameAdapter(@NonNull Context context, ProgressBar progressBar,
                                  List<String> names, List<String> developers, List<String> icons) {
        mContext = context;
        this.names = names;
        this.developers = developers;
        this.icons = icons;
        mProgressBar = progressBar;
    }

    /**
     * Create the ViewHolder class by inflating the most viewed card.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card,
                parent,false);
        return new MostViewedIGameAdapter.ViewHolder(view);
    }

    /**
     * Bind the images, name, developer and set up listener when the card is on the screen.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int img = mContext.getResources().getIdentifier(icons.get(position), "drawable",
                mContext.getPackageName());
        holder.image.setImageResource(img);
        holder.name.setText(names.get(position));
        holder.developer.setText(developers.get(position));
        holder.card.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("name", names.get(holder.getAdapterPosition()));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((
                    MainActivity)mContext, holder.image, ViewCompat.getTransitionName(holder.image));
            mContext.startActivity(intent,options.toBundle());
        });

    }

    /**
     * Return the amount of most viewed cards.
     */
    @Override
    public int getItemCount() {
        return names.size();
    }


}
