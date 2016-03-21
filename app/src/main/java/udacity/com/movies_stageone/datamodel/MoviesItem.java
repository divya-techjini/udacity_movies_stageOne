package udacity.com.movies_stageone.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by techjini on 21/3/16.
 */
public class MoviesItem implements Parcelable {



    private int id;
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private List<Integer> genre_ids;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private float popularity;
    private int vote_count;
    private boolean video;
    private float vote_average;


    public MoviesItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(id);
        dest.writeString(poster_path);
        dest.writeInt(adult ? 1 : 0);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeList(genre_ids);
        dest.writeString(original_title);
        dest.writeString(original_language);
        dest.writeString(title);
        dest.writeString(backdrop_path);
        dest.writeFloat(popularity);
        dest.writeInt(vote_count);
        dest.writeInt(video ? 1 : 0);
        dest.writeFloat(vote_average);
    }

    public MoviesItem(Parcel in) {

        this.id = in.readInt();
        this.poster_path = in.readString();
        this.adult = (in.readInt() != 0);
        this.overview = in.readString();
        this.release_date = in.readString();
        this.genre_ids = in.readArrayList(Integer.class.getClassLoader());
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.popularity = in.readFloat();
        this.vote_count = in.readInt();
        this.video = in.readInt() != 0;
        this.vote_average = in.readFloat();
    }

    public static final Parcelable.Creator<MoviesItem> CREATOR = new Parcelable.Creator<MoviesItem>() {

        @Override
        public MoviesItem createFromParcel(Parcel source) {
            return new MoviesItem(source);
        }

        @Override
        public MoviesItem[] newArray(int size) {
            return new MoviesItem[size];
        }
    };


}
