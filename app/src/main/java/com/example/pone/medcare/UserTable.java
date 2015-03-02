package com.example.pone.medcare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTable {
    public List<User> users;
    public static final String TABLE = "User";
    public static final String ID = "_id";
    public static final String FNAME = "fname";
    public static final String LNAME = "lname";
    public static final String BIRTH = "birth";
    public static final String GENDER = "gender";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";

    public static String error = "Null";
    MySQLiteOpenHelper dbHelper;
    SQLiteDatabase db;


    public UserTable(Context context){
        dbHelper = new MySQLiteOpenHelper (context);
        users = new ArrayList<>();
    }

    public  boolean insert(User user){
        boolean success = false;
        try{
            db = dbHelper.getWritableDatabase();
            String sql = "INSERT INTO " + TABLE + "("
                         + FNAME + ","
                         + LNAME + ","
                         + BIRTH + ","
                         + GENDER + ","
                         + HEIGHT + ","
                         + WEIGHT
                         +") VALUES(?, ?, ?, ?, ?, ?)";
            SQLiteStatement stm =  db.compileStatement(sql);
            stm.bindString(1, user.getFname());
            stm.bindString(2, user.getLname());
            stm.bindString(3, user.getBirth().toString());
            stm.bindLong(4, user.getGender());
            stm.bindDouble(5, user.getHeight());
            stm.bindDouble(6, user.getWeight());
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
    public  int delete(int id){
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
    public List<User> getAll(){
        String sql = "SELECT  * FROM " + TABLE;
        db = dbHelper.getReadableDatabase();
        users.clear();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            User user = cursorToUser(cursor);
            users.add(user);
        }
        cursor.close();
        db.close();
        return  users;
    }
    public User cursorToUser(Cursor cursor){
        User user = null;
        int id =       cursor.getInt(cursor.getColumnIndex(ID));
        String fname = cursor.getString(cursor.getColumnIndex(FNAME));
        String lname = cursor.getString(cursor.getColumnIndex(LNAME));
        String date = cursor.getString(cursor.getColumnIndex(BIRTH));
        Date birth = getBirth(date);
        int gender = cursor.getInt(cursor.getColumnIndex(GENDER));
        double height = cursor.getDouble(cursor.getColumnIndex(HEIGHT));
        double weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
        user = new User(id, fname, lname, birth, gender, height, weight );

        return user;
    }
    public Date getBirth(String d){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            date = dateFormat.parse(d);
        }
        catch(ParseException e){
            System.out.println(e.getMessage());
        }
        return date;
    }

    public User getById(int id){
        String sql = "SELECT  * FROM  " + TABLE + " WHERE " + ID + " =?";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        User user = null;
        while (cursor.moveToNext()) {
            user = cursorToUser(cursor);
        }
        cursor.close();
        db.close();
        return  user;
    }
    public  int update(User user){
        int effect = 0;
        try{
            db = dbHelper.getWritableDatabase();
            String sql = " UPDATE " + TABLE +
                        " SET "
                        + FNAME + "=?, "
                        + LNAME + "=?, "
                        + BIRTH + "=?, "
                        + GENDER + "=?, "
                        + HEIGHT + "=?, "
                        + WEIGHT + "=?, "
                        + " WHERE "+ ID +" =?";
            SQLiteStatement stm =  db.compileStatement(sql);
            stm.bindString(1, user.getFname());
            stm.bindString(2, user.getLname());
            stm.bindString(3, user.getBirth().toString());
            stm.bindLong(4, user.getGender());
            stm.bindDouble(5, user.getHeight());
            stm.bindDouble(6, user.getWeight());

            stm.bindLong(3, user.getId());
            effect = stm.executeUpdateDelete();
        }
        catch (SQLiteException e){
            error = "Update Error: " + e.getMessage();
        }
        finally {
            db.close();
        }
        return effect;
    }

}
