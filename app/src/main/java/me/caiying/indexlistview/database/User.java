package me.caiying.indexlistview.database;

import android.database.Cursor;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by caiying on 03/02/2016.
 */
@DatabaseTable(tableName = "user")
public class User {
    @DatabaseField(columnName = "_id", generatedId = true)
    public long id;
    @DatabaseField(columnName = "name")
    public String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public static User objectFromCursor(Cursor cursor) {
        User user = new User();
        user.id = cursor.getLong(cursor.getColumnIndex("_id"));
        user.name = cursor.getString(cursor.getColumnIndex("name"));
        return user;
    }
}