package udacity.com.movies_stageone.network;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import udacity.com.movies_stageone.datamodel.MoviesResponse;

/**
 * Created by techjini on 14/1/16.
 */
public interface MoviesService {

    @GET("/3/discover/movie")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<MoviesResponse> getMoviesList(@Query("sort_by") String sortBy, @Query("api_key") String api_key);


}
