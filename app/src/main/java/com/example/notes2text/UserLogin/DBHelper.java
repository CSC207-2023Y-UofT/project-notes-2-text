package com.example.notes2text.UserLogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login3.db";

    public DBHelper(Context context) {
        super(context, "Login3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table user(name Text Primary key, email Text, password Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists user");

    }

    public boolean insertData(String username, String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDB.insert("user", null, contentValues);
        return result != -1;
    }

    public boolean uniqueUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where name = ?", new String[]{username});
        cursor.close();
        return false;
    }

    public boolean checkUserPassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where name = ? and password = ?", new String[]{username, password});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean uniqueEmail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM user WHERE email = ?", new String[]{email});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public String getEmail(String username){
        String email = null;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select email from user WHERE name =?", new String[]{username});
        int i = cursor.getColumnIndex("email");
        if (cursor.moveToFirst() && i !=-1){
            email = cursor.getString(i);
        }
        return email;




    }
}


//    public void addUser(User user) {
//
//    }
//
//    @Override
//    public User getUser(String username) {
//        return null;
//    }
//
//    @Override
//    public boolean validUser(User user, String password) {
//        return false;
//    }
