package com.example.cw2_app31;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "contact_details";
    private static final String ID_COLUMN_NAME = "person_id";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String DOB_COLUMN_NAME = "dob";
    private static final String EMAIL_COLUMN_NAME = "email";
    private static final String IMAGE_COLUMN_NAME = "image_resource_id";

    private static final String DATABASE_CREATE_QUERY = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT," +
                    "%s TEXT," +
                    "%s TEXT," +
                    "%s INTEGER)",
            DATABASE_NAME, ID_COLUMN_NAME, NAME_COLUMN_NAME, DOB_COLUMN_NAME, EMAIL_COLUMN_NAME, IMAGE_COLUMN_NAME);

    private SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase(); // hoặc getReadableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_QUERY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        Log.w(this.getClass().getName(), DATABASE_NAME + " database upgrade to version"
                + newVersion + "- old data lost");
        onCreate(sqLiteDatabase);
    }

    public long insertDetail(String name, String dob, String email, int imageResourceId) {
        ContentValues rowValues = new ContentValues();
        rowValues.put(NAME_COLUMN_NAME, name);
        rowValues.put(DOB_COLUMN_NAME, dob);
        rowValues.put(EMAIL_COLUMN_NAME, email);
        rowValues.put(IMAGE_COLUMN_NAME, imageResourceId);
        return database.insertOrThrow(DATABASE_NAME, null, rowValues);
    }

    public List<DataModel> getDetails() {
        List<DataModel> dataList = new ArrayList<>();

        Cursor results = database.query(DATABASE_NAME,
                new String[]{ID_COLUMN_NAME, NAME_COLUMN_NAME, DOB_COLUMN_NAME, EMAIL_COLUMN_NAME, IMAGE_COLUMN_NAME},
                null, null, null, null, "name");

        results.moveToFirst();
        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            String dob = results.getString(2);
            String email = results.getString(3);
            int imageResourceId = results.getInt(4);

            DataModel data = new DataModel(name, dob, email, imageResourceId);
            dataList.add(data);

            results.moveToNext();
        }

        results.close();
        return dataList;
    }

    public void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
