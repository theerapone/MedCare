package com.example.pone.medcare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicineTable {
    public List<Medicine> medicines;
    public static final String TABLE = "medicine";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String HOW_TO_USE = "how_to_use";
    public static final String MISS_DOSE = "miss_dose";
    public static final String OVERDOSE = "overdose";
    public static final String AVOID = "avoid";
    public static final String SIDE_EFFECT = "side_effect";

    public static String error = "Null";
    MySQLiteOpenHelper dbHelper;
    SQLiteDatabase db;

    public MedicineTable(Context context){
        dbHelper = new MySQLiteOpenHelper (context);
        medicines = new ArrayList<>();
    }
    public List<Medicine> all(){
        String sql = "SELECT  * FROM " + TABLE;
        db = dbHelper.getReadableDatabase();
        medicines.clear();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Medicine medicine = cursorToMedicine(cursor);
            medicines.add(medicine);
        }
        cursor.close();
        db.close();
        return medicines;
    }
    public Cursor allName() throws SQLiteException{
        String sql = "SELECT " +ID + "," + NAME + " FROM " + TABLE;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
    public Medicine cursorToMedicine(Cursor cursor){
        User user = null;
        int id =       cursor.getInt(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
        String how_to_use = cursor.getString(cursor.getColumnIndex(HOW_TO_USE));
        String miss_dose = cursor.getString(cursor.getColumnIndex(MISS_DOSE));
        String avoid = cursor.getString(cursor.getColumnIndex(AVOID));
        String side_effect = cursor.getString(cursor.getColumnIndex(OVERDOSE));

        Medicine medicine = new Medicine()
                                .setId(id)
                                .setName(name)
                                .setDescription(description)
                                .setHow_to_use(how_to_use)
                                .setMiss_dose(miss_dose)
                                .setAvoid(avoid)
                                .setSide_effect(side_effect);

        return medicine;
    }
    public  boolean insert(Medicine medicine){
        boolean success = false;
        try{
            db = dbHelper.getWritableDatabase();
            String sql = "INSERT INTO " + TABLE + "("
                    + NAME + ","
                    + DESCRIPTION + ","
                    + HOW_TO_USE + ","
                    + MISS_DOSE + ","
                    + OVERDOSE + ","
                    + AVOID + ","
                    + SIDE_EFFECT
                    +") VALUES(?, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement stm =  db.compileStatement(sql);
            stm.bindString(1, medicine.getName());
            stm.bindString(2, medicine.getDescription());
            stm.bindString(3, medicine.getHow_to_use());
            stm.bindString(4, medicine.getMiss_dose());
            stm.bindString(5, medicine.getOverdose());
            stm.bindString(6, medicine.getAvoid());
            stm.bindString(7, medicine.getSide_effect());
            stm.executeInsert();
            db.close();
            success = true;
        }
        catch (SQLiteException e){
            error = "Insert Error: " + e.getMessage();
        }
        return success;
    }
}
