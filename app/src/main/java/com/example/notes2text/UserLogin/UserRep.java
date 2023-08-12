package com.example.notes2text.UserLogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class UserRep extends SQLiteOpenHelper implements UserRepository {

    //Name of the User repository storing the email, username and password of all registered Users
    public static final String DBNAME = "Notes2TextUsers";


    /**
     * Creates a User repository called DBNAME
     * @param context The Android context that uses the database
     */
    public UserRep(Context context) {
        super(context, DBNAME, null, 1);
    }


    /**
     * Creates a database for the first time.
     * @param MyDB The SQLDatabase database being created.
     */
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table user(name Text Primary key, email Text, password Text)");

    }

    /**
     * Upgrade the database
     * @param MyDB The SQLDatabase database being upgraded
     * @param i Older version of the database
     * @param i1 New version of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists user");

    }

    /**
     * Add user info to a User Repository
     * @param username username of a user
     * @param email email of a user
     * @param password password of a user
     * @return true if user was added successfully
     */
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

    /**
     * Check whether a username exists in the user repository database
     * @param username username of a user
     * @return true if username exists
     */
    public boolean uniqueUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where name = ?", new String[]{username});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    /*
    Returns true if a username and password combination exists in one of
    the rows in DBNAME database, false otherwise
     */

    /**
     *  Check if a username and password combination exists in one of
     *  the rows in DBNAME database, false otherwise
     * @param username username of a possible user
     * @param password password of a possible user
     * @return true if username and password exists
     */
    public boolean checkUserPassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where name = ? and password = ?", new String[]{username, password});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    /**
     * Check if email exists in the user database
     * @param email email of a user
     * @return true if email exists in user database
     */
    public boolean uniqueEmail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM user WHERE email = ?", new String[]{email});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }


    /**
     * Get the email corresponding to the username
     * @param username username of a user
     * @return the email corresponding to the username
     */
    public String getEmail(String username){
        String email = null;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select email from user WHERE name =?", new String[]{username});
        int i = cursor.getColumnIndex("email");
        if (cursor.moveToFirst() && i !=-1){
            email = cursor.getString(i);
        }
        cursor.close();
        return email;
    }

    public String getPassword(String username){
        String password = null;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select password from user WHERE name =?", new String[]{username});
        int i = cursor.getColumnIndex("password");
        if (cursor.moveToFirst() && i !=-1){
            password = cursor.getString(i);
        }
        cursor.close();
        return password;

    }

    public void updateUsername(String username, String newUsername){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newUsername);
        MyDB.update("user", values, "name = ?", new String[]{username});
    }

    public void updatePassword(String password, String newPassword){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);
        MyDB.update("user", values, "password = ?", new String[]{password});
    }

    public void updateEmail(String email, String newEmail){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", newEmail);
        MyDB.update("user", values, "email = ?", new String[]{email});
    }

}