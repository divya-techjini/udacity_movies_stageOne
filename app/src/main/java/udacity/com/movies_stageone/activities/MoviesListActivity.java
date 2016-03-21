package udacity.com.movies_stageone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Response;
import udacity.com.movies_stageone.R;
import udacity.com.movies_stageone.adapters.MoviesListAdapter;
import udacity.com.movies_stageone.datamodel.MoviesItem;
import udacity.com.movies_stageone.datamodel.MoviesResponse;
import udacity.com.movies_stageone.network.MoviesApi;
import udacity.com.movies_stageone.network.MoviesManager;

public class MoviesListActivity extends BaseActivity implements MoviesManager.OnGetListListener, MoviesListAdapter.OnMovieClickListener {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtEmpty)
    TextView txtEmpty;
    private MoviesManager mMoviesManager;
    private MoviesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        mMoviesManager = new MoviesManager();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        showLoadingDialog(R.string.msg_loading);
        mMoviesManager.getMoviesList(MoviesListActivity.this, MoviesApi.SORT_BY_POPULAR);
        setTitle(R.string.title_movie_list_pop);

    }

    private void setListView(MoviesResponse response) {
        if (response.getResults() == null || response.getResults().size() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
        } else {
            txtEmpty.setVisibility(View.GONE);
            adapter = new MoviesListAdapter(response.getResults(), MoviesListActivity.this);
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
            showLoadingDialog(R.string.msg_loading);
            setTitle(R.string.title_movie_list_pop);
            mMoviesManager.getMoviesList(MoviesListActivity.this, MoviesApi.SORT_BY_POPULAR);
            return true;
        } else if (id == R.id.action_rating) {
            showLoadingDialog(R.string.msg_loading);
            setTitle(R.string.title_movie_list_rate);
            mMoviesManager.getMoviesList(MoviesListActivity.this, MoviesApi.SORT_BY_RATING);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetMoviesListSuccess(MoviesResponse response) {
        hideLoadingDialog();
        setListView(response);

    }

    @Override
    public void onGetMoviesListFailure(String url) {

    }

    @Override
    public void onGetMoviesListException(String url) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onMovieSelected(MoviesItem movieItem) {
        Intent intent = new Intent(MoviesListActivity.this, MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTR_MOVIE, movieItem);
        startActivity(intent);

    }
}
