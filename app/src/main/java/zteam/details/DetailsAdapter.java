package zteam.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import zteam.R;

import java.util.List;

/**
 * Adapter class used to populate the RecyclerView of genres.
 */
public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    /**
     * Inner ViewHolder class that only generates the data as required on the screen for memory
     * purposes.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView genre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            genre = itemView.findViewById(R.id.genres);
        }
    }


    List<String> genres;
    Context detailContext;

    public DetailsAdapter(@NonNull Context context, @NonNull List<String> object){
        genres = object;
        detailContext = context;
    }

    /**
     * Create the card by inflating the genre card.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_genre, parent, false);
        return new ViewHolder(view);
    }

    /**
     * When the genre card is on the screen set the text details.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.genre.setText(genres.get(position));
    }

    /**
     * Return the amount of genres of a game.
     */
    @Override
    public int getItemCount() {
        return genres.size();
    }


}