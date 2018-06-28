package com.wadexhong.chocolabs.mainpage;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wadexhong.chocolabs.Chocolabs;
import com.wadexhong.chocolabs.R;
import com.wadexhong.chocolabs.helper.SharedPreferenceHelper;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private final String SEARCH_CACHE = "SEARCH_CACHE";

    private Toolbar mToolbar;
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);

        mRecyclerView = findViewById(R.id.activity_main_recyclerview);
        mMainAdapter = new MainAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mMainAdapter);

        mToolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setQueryHint("請輸入文字");

        EditText editText = mSearchView.findViewById(R.id.search_src_text);
        editText.setHintTextColor(getResources().getColor(R.color.not_so_white));
        editText.setTextColor(getResources().getColor(R.color.white));
        editText.setText(SharedPreferenceHelper.read(SEARCH_CACHE, ""));

        ImageView imageView = mSearchView.findViewById(R.id.search_close_btn);
        imageView.setColorFilter(R.color.white);
        ImageView hintIcon = mSearchView.findViewById(R.id.search_button);
        hintIcon.setColorFilter(R.color.white);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAndCache(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAndCache(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void searchAndCache(String query) {
        mPresenter.searchDrama(query);
        SharedPreferenceHelper.write(SEARCH_CACHE, query);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void refreshUi(Cursor cursor) {
        mMainAdapter.setCursor(cursor);
    }
}
