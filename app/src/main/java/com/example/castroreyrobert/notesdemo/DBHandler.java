package com.example.castroreyrobert.notesdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "notes.db";
    private static final int DB_VERSION = 1;
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "NOTE";
    public static final String COL_4 = "DATE";
    public static final String TB_NAME = "note_table";
    private static final String CREATE_QUERY = "CREATE TABLE " + TB_NAME +
            "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_2+ " TEXT," + COL_3 + " TEXT,"
            + COL_4 + " TEXT);";

    public DBHandler(Context context) {
        super(context,DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TB_NAME);
        onCreate(db);
    }

    public void addNotes(SQLiteDatabase db, NoteModel noteModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, noteModel.getTitle());
        contentValues.put(COL_3, noteModel.getNote());
        contentValues.put(COL_4, noteModel.getDate());

        db.insert(TB_NAME, null, contentValues);
    }

    public Cursor viewNotes(SQLiteDatabase db){
        String [] notes = {COL_1, COL_2, COL_3
        , COL_4};
        Cursor res = db.query(TB_NAME, notes,null, null, null, null, null);
        return res;
    }
}
