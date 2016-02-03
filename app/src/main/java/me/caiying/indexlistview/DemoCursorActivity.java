package me.caiying.indexlistview;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import me.caiying.indexlistview.adapter.DemoCursorAdapter;
import me.caiying.indexlistview.database.DatabaseHelper;
import me.caiying.indexlistview.database.User;
import me.caiying.library.indexlistview.IndexListView;
import me.caiying.library.indexlistview.IndexScroller;

/**
 * Created by caiying on 03/02/2016.
 */
public class DemoCursorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private IndexListView listView;
    private DemoCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        initViews();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        getSupportLoaderManager().initLoader(1, null, this);
    }

    private void initViews() {
        adapter = new DemoCursorAdapter(this);
        listView = (IndexListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.bind(new IndexScroller(this));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this) {
            @Override
            public Cursor loadInBackground() {
                Cursor cursor = null;
                try {
                    Dao<User, Long> userLongDao = DatabaseHelper.getHelper(DemoCursorActivity.this).getUserDao();
                    CloseableIterator<User> iterator = userLongDao.iterator(userLongDao.queryBuilder().orderBy("name", true).prepare());
                    AndroidDatabaseResults results = (AndroidDatabaseResults) iterator.getRawResults();
                    cursor = results.getRawCursor();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (cursor == null) {
                    return super.loadInBackground();
                } else {
                    return cursor;
                }
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
