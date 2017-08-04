package com.example.weilun.eventcountdown;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.weilun.eventcountdown.ID";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor)parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ViewEventActivity.class);
                intent.putExtra(EXTRA_ID, cursor.getLong(cursor.getColumnIndex(EventContract.EventEntry._ID)));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_all) {
            EventDBQueries dbQueries = new EventDBQueries(new EventDBHelper(getApplicationContext()));
            dbQueries.deleteAll();
            Toast.makeText(this, getString(R.string.delete_success), Toast.LENGTH_SHORT).show();
            onResume();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventDBQueries dbQuery = new EventDBQueries(new EventDBHelper(getApplicationContext()));
        String [] columns = {
                EventContract.EventEntry._ID,
                EventContract.EventEntry.COLUMN_NAME_TITLE,
                EventContract.EventEntry.COLUMN_NAME_DATE
        };
        Cursor cursor = dbQuery.query(columns, null, null, null, null
                , EventContract.EventEntry.COLUMN_NAME_DATE + " ASC");
        EventCursorAdapter adapter = new EventCursorAdapter(this, cursor, 0);
        listView.setAdapter(adapter);
    }
}
