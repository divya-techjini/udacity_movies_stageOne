package udacity.com.movies_stageone.utils;

import java.util.Comparator;

import udacity.com.movies_stageone.datamodel.MoviesItem;

/**
 * Created by techjini on 21/3/16.
 */
public class PopularComparator implements Comparator<MoviesItem> {


    @Override
    public int compare(MoviesItem lhs, MoviesItem rhs) {
        if (lhs.getPopularity() == rhs.getPopularity())
            return 0;
        else if (lhs.getPopularity() > rhs.getPopularity())
            return 1;
        else
            return -1;
    }
}
