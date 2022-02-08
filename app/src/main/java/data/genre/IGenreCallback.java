package data.genre;

import java.util.List;

import model.genres.ICategory;

/**
 * Callback interface to return a list of genres synchronously.
 */
public interface IGenreCallback {
    void onSuccess(List<ICategory> categoryList);
}
