package com.example.pone.medcare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class VitaminTable {
    public static final String TABLE = "vitamin";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String FOOD = "food";
    public static final String EFFECT = "effect";

    public List<Vitamin> entities;
    public Vitamin entitiy;

    public String error = "Null";
    private MySQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    public VitaminTable(Context context){
        dbHelper = new MySQLiteOpenHelper (context);
        entities = new ArrayList<>();
    }

    public  boolean insert(Vitamin vitamin){
        boolean success = false;
        try{
            db = dbHelper.getWritableDatabase();
            String sql = "INSERT INTO " + TABLE + "("
                            + NAME + ","
                            + FOOD + ","
                            + EFFECT
                            +") VALUES(?, ?, ?)";
            SQLiteStatement stm =  db.compileStatement(sql);
            stm.bindString(1, vitamin.getName());
            stm.bindString(2, vitamin.getFood());
            stm.bindString(3, vitamin.getEffect());
            stm.executeInsert();
            db.close();
            success = true;
        }
        catch (SQLiteException e){
            error = "Insert Error: " + e.getMessage();
        }
        finally {
            db.close();
        }
        return success;
    }
    public int delete(int id){
        int effect = 0;
        try{
            db = dbHelper.getWritableDatabase();
            String sql = "DELETE FROM " + TABLE + " WHERE "+ ID +" =?";
            SQLiteStatement stm =  db.compileStatement(sql);
            stm.bindLong(1, id);
            effect = stm.executeUpdateDelete();
        }
        catch (SQLiteException e){
            error = "Delete Error: " + e.getMessage();
        }
        finally {
            db.close();
        }
        return effect;
    }
    public List<Vitamin> getAll(){
        String sql = "SELECT  * FROM " + TABLE;
        db = dbHelper.getReadableDatabase();
        entities.clear();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            entitiy = cursorToEntity(cursor);
            entities.add(entitiy);
        }
        cursor.close();
        db.close();
        return  entities;
    }
    public Vitamin cursorToEntity(Cursor cursor){
        User user = null;
        int id = cursor.getInt(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        String food = cursor.getString(cursor.getColumnIndex(FOOD));
        String effect = cursor.getString(cursor.getColumnIndex(EFFECT));
        entitiy = new Vitamin().setFood(food).setName(name).setEffect(effect);
        return entitiy;
    }

    public Vitamin getById(int id){
        String sql = "SELECT  * FROM  " + TABLE + " WHERE " + ID + " =?";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        entitiy = new Vitamin()  ;
        while (cursor.moveToNext()) {
            entitiy = cursorToEntity(cursor);
        }
        cursor.close();
        db.close();
        return  entitiy;
    }

}
