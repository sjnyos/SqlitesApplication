package com.machamasisuraj.sqlitesapplication.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String databaseName="DictionaryDB";
    private static final int dbVersion=1;

    private static final String tblWord="tblWord";
    private static final String wordId ="WordId";
    private static final String word = "word";
    private static final String meaning="meaning";

    public DbHelper(@Nullable Context context) {
        super(context, databaseName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE "+tblWord+ "("+
                wordId + " INTEGER  PRIMARY KEY AUTOINCREMENT ," +
                word + " TEXT, "+
                meaning + " TEXT "
                +")";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String word,String meaning, SQLiteDatabase db){
        try {
            String query="insert into  "
                    +tblWord
                    +"(word,meaning) values ('"
                    +word
                    + ","
                    +meaning + ""
                    +"')";
            db.execSQL(query);
            return  true;
        }catch (Exception ex){
            Log.d("sqlerror",ex.toString());
            return false;
        }
    }
    //sqllite query error

}
