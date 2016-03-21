package udacity.com.movies_stageone.network;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import udacity.com.movies_stageone.datamodel.MoviesResponse;

/**
 * Created by techjini on 14/1/16.
 */
public class MoviesManager {
    public String API_KEY = "";

    public MoviesManager() {

    }


    public void getMoviesList(final OnGetListListener listListener, String sortBy) {
        MovieApiClient apiClient = MovieApiClient.getInstance();
        MoviesService service = apiClient.getService(MoviesService.class);
        Call<MoviesResponse> model = service.getMoviesList(sortBy, API_KEY);
        try {
            model.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Response<MoviesResponse> response, Retrofit retrofit) {
                    listListener.onGetMoviesListSuccess(response.body());
                }

                @Override
                public void onFailure(Throwable t) {
                    listListener.onGetMoviesListFailure(t.toString());
                }
            });
        } catch (Exception e) {
            listListener.onGetMoviesListException(e.toString());
        }

    }


    public interface OnGetListListener {
        void onGetMoviesListSuccess(MoviesResponse response);

        void onGetMoviesListFailure(String msg);

        void onGetMoviesListException(String msg);
    }


}
