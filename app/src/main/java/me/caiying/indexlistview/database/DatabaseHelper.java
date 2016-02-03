package me.caiying.indexlistview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caiying on 03/02/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static DatabaseHelper helper;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private Dao<User, Long> userDao = null;

    private DatabaseHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (helper == null) {
            helper = new DatabaseHelper(context);
        }
        usageCounter.incrementAndGet();
        return helper;
    }


    public Dao<User, Long> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = DaoManager.createDao(getConnectionSource(), User.class);
        }
        return userDao;
    }

    public void prepareTestData() {
        try {
            TableUtils.clearTable(getConnectionSource(), User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            userDao = getUserDao();
            try {
                userDao.callBatchTasks(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        for (String name : UserNames.getUserNames()) {
                            userDao.create(new User(name));
                        }
                        return null;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (usageCounter.decrementAndGet() == 0) {
            super.close();
            userDao = null;
            helper = null;
        }
    }
}
