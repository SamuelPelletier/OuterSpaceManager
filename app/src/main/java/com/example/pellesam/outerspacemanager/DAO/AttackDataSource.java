package com.example.pellesam.outerspacemanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.pellesam.outerspacemanager.DataBase.AttackDB;
import com.example.pellesam.outerspacemanager.Entity.Attack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac14 on 20/03/2017.
 */

public class AttackDataSource {
    // Database fields
    private SQLiteDatabase database;
    private AttackDB dbHelper;
    private String[] allColumns = { AttackDB.TIME,AttackDB.FLEET_SEND};
    public AttackDataSource(Context context) {
        dbHelper = new AttackDB(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public Attack createAttack(String time, String fleetSend) {
        ContentValues values = new ContentValues();
        values.put(AttackDB.TIME, time);
        values.put(AttackDB.FLEET_SEND, fleetSend);
        database.insert(AttackDB.ATTACK_TABLE_NAME, null,
                values);
        Cursor cursor = database.query(AttackDB.ATTACK_TABLE_NAME,
                allColumns, AttackDB.TIME + " = \"" +time+"\"", null,
                null, null, null);
        cursor.moveToFirst();
        Attack newAttack = cursorToAttack(cursor);
        cursor.close();
        return newAttack;
    }

    public List<Attack> getAllAttacks() {
        List<Attack> attacks = new ArrayList<Attack>();
        Cursor cursor = database.query(AttackDB.ATTACK_TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Attack attack = cursorToAttack(cursor);
            attacks.add(attack);
            cursor.moveToNext();
        }
        cursor.close();
        return attacks;
    }
    private Attack cursorToAttack(Cursor cursor) {
        Attack comment = new Attack();
        comment.setTime(cursor.getLong(0));
        comment.setFleetSend(cursor.getString(1));
        return comment;
    }

    public void deleteAttack(Attack attack) {
        database.delete(AttackDB.ATTACK_TABLE_NAME, AttackDB.TIME
                + " = \"" + String.valueOf(attack.getTime())+"\"", null);
    }
}