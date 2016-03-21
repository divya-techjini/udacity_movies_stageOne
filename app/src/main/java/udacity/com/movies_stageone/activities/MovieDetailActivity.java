package udacity.com.movies_stageone.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import udacity.com.movies_stageone.R;
import udacity.com.movies_stageone.datamodel.MoviesItem;
import udacity.com.movies_stageone.network.MoviesApi;

public class MovieDetailActivity extends BaseActivity {

    public static final String EXTR_MOVIE = "com.udacity.movie.item";
    @Bind(R.id.img_movie)
    ImageView imgMovie;
    @Bind(R.id.txt_title)
    TextView txtTitle;
    @Bind(R.id.txt_release)
    TextView txtRelease;
    @Bind(R.id.txt_rating)
    TextView txtRating;
    @Bind(R.id.txt_overview)
    TextView txtOverview;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fillDetailsToViews();

    }

    private void fillDetailsToViews() {
        MoviesItem item = getIntent().getParcelableExtra(EXTR_MOVIE);
        if (item == null) return;
        if (item.getPoster_path() != null && !item.getPoster_path().isEmpty()) {
            Picasso.with(MovieDetailActivity.this).load(MoviesApi.IMG_URL.concat(item.getPoster_path())).
                    placeholder(R.drawable.default_pos).into(imgMovie);
        }
        txtTitle.setText(item.getOriginal_title());
        txtRelease.setText(item.getRelease_date());
        txtRating.setText(String.valueOf(item.getVote_average()).concat("/").concat(getString(R.string.lbl_rating)));
        txtOverview.setText(item.getOverview());
        setTitle(item.getTitle());
        setBackEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
