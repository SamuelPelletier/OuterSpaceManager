package com.example.pellesam.outerspacemanager.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by mac14 on 20/03/2017.
 */

public class AttackDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDB.db";
    public static final String ATTACK_TABLE_NAME = "Attack";
    public static final String TIME = "timestamp";
    public static final String FLEET_SEND = "fleet";
    private static final String HOMME_TABLE_CREATE = "CREATE TABLE " + ATTACK_TABLE_NAME + " (" + TIME + " REAL, " +
            FLEET_SEND + " TEXT);";
    public AttackDB(Context context) {
        super(context, Environment.getExternalStorageDirectory()+"/"+DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HOMME_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {db.execSQL("DROP TABLE IF EXISTS " +
            ATTACK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ATTACK_TABLE_NAME);
        onCreate(db);
    }
}