package com.example.notes2text.UserLogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class UserRepImpl extends SQLiteOpenHelper implements UserRepository {

    //Name of the User repository storing the email, username and password of all registered Users
    public static final String DBNAME = "Notes2TextUsers.db";


    //Creates a User repository called DBNAME
    public UserRepImpl(Context context) {
        super(context, DBNAME, null, 1);
    }

    //Creates a table in database with name, email and password columns
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table user(name Text Primary key, email Text, password Text)");

    }

    //Upgrades table constructed
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists user");

    }

    //Add a user's info to the database
    public boolean addUser(String username, String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDB.insert("user", null, contentValues);
        return result != -1;
    }

    //Returns true if username exists in the DBNAME data, false otherwise
    public boolean uniqueUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where name = ?", new String[]{username});
        int count = cursor.getCount();
        cursor.close();
        return count < 1;
    }

    /*
    Returns true if a username and password combination exists in one of
    the rows in DBNAME database, false otherwise
     */
    public boolean checkUserPassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where name = ? and password = ?", new String[]{username, password});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    //Returns false if an email exists in the DBNAME database, true otherwise
    public boolean uniqueEmail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM user WHERE email = ?", new String[]{email});
        int count = cursor.getCount();
        cursor.close();
        return count < 1;
    }

    //Returns the email address that is in the same row of a username in the DBNAME database
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

