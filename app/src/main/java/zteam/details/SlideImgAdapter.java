package zteam.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.Objects;

import zteam.R;


/**
 * Adapter for a Details view to dynamically show a list of slideable images
 */
public class SlideImgAdapter extends PagerAdapter {
    // Context object
    Context context;
    // Array of images
    int[] images;
    // Layout Inflater
    LayoutInflater mLayoutInflater;
    // Viewpager Constructor

    public SlideImgAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Overridden method to get the number of items to be added
     * @return num items
     */
    @Override
    public int getCount() {
        // return the number of images
        return images.length;
    }

    /**
     * Overridden method to Check whether the input view is the same as the context/layout
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    /**
     * Set up the items
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.slide_img, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);
        imageView.setImageResource(images[position]);
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }

    /**
     * Remove an item
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}