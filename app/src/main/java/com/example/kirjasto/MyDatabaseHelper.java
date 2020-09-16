// extends SQLiteOpenHelper
//Implementoi OnCreate ja OnUpgrade
// construktori SQLiteOpenHelperille
//public MyDatabaseHelper(@Nullable Context context) { POISTA kaikki context ja ) välistä
//declare name, factory, version



package com.example.kirjasto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME =  "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE ="book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES ="book_pages";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // TÄHÄN SQL schema
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_AUTHOR + " TEXT, " +
                        COLUMN_PAGES + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db); //AINA Kun Table päivitetään niin pitää kutsua myös onCreate metodia

    }
    //metodi kirjan lisäämistä varten
    void addBook(String title, String author, int pages) {
        SQLiteDatabase db = this.getWritableDatabase(); // viitataan SQLiteDatabase luokkaan ja käytetän sen ominaisuutta kirjottamiseen
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added succesfully", Toast.LENGTH_SHORT).show();
        }
    }

}
