package com.example.pone.medcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medicareDB.db";
    private static final int DATABASE_VERSION = 1;
    public MySQLiteOpenHelper (Context context) {
        super(context, "/mnt/sdcard/" + DATABASE_NAME, null, DATABASE_VERSION);
        //super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  User " +
                "(" +
                UserTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserTable.FNAME + "  VARCHAR(255)," +
                UserTable.LNAME + "  VARCHAR(255), " +
                UserTable.BIRTH + "  VARCHAR(255), " +
                UserTable.GENDER + " INTEGER, " +
                UserTable.HEIGHT + " DOUBLE, " +
                UserTable.WEIGHT + " DOUBLE " +
                ")");

        db.execSQL("CREATE TABLE  Medicine " +
                "(" +
                MedicineTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MedicineTable.NAME + "  VARCHAR(255)," +
                MedicineTable.DESCRIPTION + "  VARCHAR(255), " +
                MedicineTable.HOW_TO_USE+ "  VARCHAR(255), " +
                MedicineTable.MISS_DOSE + "  VARCHAR(255), " +
                MedicineTable.OVERDOSE + "  VARCHAR(255), " +
                MedicineTable.AVOID + "  VARCHAR(255), " +
                MedicineTable.SIDE_EFFECT + "  VARCHAR(255) " +
                ")");
        /*
         String sql = "INSERT INTO " + MedicineTable.TABLE + "("
                + MedicineTable.NAME + ","
                + MedicineTable.DESCRIPTION + ","
                + MedicineTable.HOW_TO_USE + ","
                + MedicineTable.MISS_DOSE + ","
                + MedicineTable.OVERDOSE + ","
                + MedicineTable.AVOID + ","
                + MedicineTable.SIDE_EFFECT
                +") VALUES('Parasetamal', '', '', '', '', '', '')";
        db.execSQL(sql);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Medicine");
        onCreate(db);
    }
}
