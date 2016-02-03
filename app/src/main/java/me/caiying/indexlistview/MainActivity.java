package me.caiying.indexlistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import me.caiying.indexlistview.database.DatabaseHelper;
import me.caiying.library.indexlistview.IndexListView;

/**
 * Created by caiying on 03/02/2016.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    IndexListView listView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
//        DatabaseHelper.getHelper(getApplicationContext()).getWritableDatabase();
        DatabaseHelper.getHelper(getApplicationContext()).prepareTestData();
        initViews();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add("cursor adapter");
        listView.setAdapter(adapter);

    }

    private void initViews() {
        listView = (IndexListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(MainActivity.this, DemoCursorActivity.class));
                break;
        }
    }
}
