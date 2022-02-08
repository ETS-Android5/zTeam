package zteam.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zteam.list.ListActivity;
import zteam.R;

/**
 * Class to populate the list of categories in the MainActivity, done through a Recycler View.
 */
public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.ViewHolder>{

    /**
     * Inner ViewHolder class that only generates the data as required on the screen for memory
     * purposes.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView label;
        TextView description;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            label = itemView.findViewById(R.id.category_label);
            description = itemView.findViewById(R.id.category_desc);
            card = itemView.findViewById(R.id.category_card);
        }
    }

    private List<String> labels;
    private List<String> descriptions;
    private List<String> images;
    private Context mContext;

    public CategoryViewAdapter(List<String> labels, List<String> descriptions, List<String> images, Context context) {
        this.labels = labels;
        this.descriptions = descriptions;
        this.images = images;
        this.mContext = context;
    }

    /**
     * Create the ViewHolder fetching the genre card.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_genre_card,parent,false);
        return new ViewHolder(view);
    }

    /**
     * Set the image, labels and texts of the card when seen on screen.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int j = mContext.getResources().getIdentifier(images.get(position), "drawable", mContext.getPackageName());
        holder.image.setImageResource(j);
        holder.label.setText(labels.get(position));
        holder.description.setText(descriptions.get(position));
        holder.card.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ListActivity.class);
            intent.putExtra("label", labels.get(holder.getAdapterPosition()));
            mContext.startActivity(intent);
        });

    }

    /**
     * Get the amount of categories that are to be displayed.
     */
    @Override
    public int getItemCount() {
        return labels.size();
    }

}
