package wingstud.com.bookingmaster.activitys.Others;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import wingstud.com.bookingmaster.activitys.utils.AppString;

public class DatabaseHandler extends SQLiteOpenHelper {

    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "BOOKINGMASTER";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table " + AppString.TABEL_NAME + "(auto_id  INTEGER PRIMARY KEY,h_id text,h_slug text,h_name text,h_price text, h_image text,h_address text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + AppString.TABEL_NAME);
        onCreate(db);
    }

    public String Delete_table2(String table_name) {
        String dlt_msg = "Delete_table";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + table_name);
        db.close();
        return dlt_msg;
    }

    //////////////////      saved    ////////////////////////////
    public boolean insert_Saved(String h_id, String h_slug, String h_name, String h_price, String h_image, String h_address) {
        boolean login_msg = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("h_id", h_id);
        contentValues.put("h_slug", h_slug);
        contentValues.put("h_name", h_name);
        contentValues.put("h_price", h_price);
        contentValues.put("h_image", h_image);
        contentValues.put("h_address", h_address);
        Cursor cursor = db.query(AppString.TABEL_NAME, null, " h_id=?",
                new String[]{h_id}, null, null, null);
        if (cursor.getCount() > 0) {

        } else {
            db.insert(AppString.TABEL_NAME, null, contentValues);
            login_msg = true;
        }
        db.close();
        return login_msg;
    }

    public ArrayList<HashMap<String, String>> get_saved() {
        ArrayList<HashMap<String, String>> allUser_name = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
        Cursor cursor = usersUser_name.rawQuery("select * from " + AppString.TABEL_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> User_name_table = new HashMap<String, String>();
                User_name_table.put("h_id",
                        cursor.getString(cursor.getColumnIndex("h_id")));
                User_name_table.put("h_slug",
                        cursor.getString(cursor.getColumnIndex("h_slug")));
                User_name_table.put("h_name",
                        cursor.getString(cursor.getColumnIndex("h_name")));
                User_name_table.put("h_price",
                        cursor.getString(cursor.getColumnIndex("h_price")));
                User_name_table.put("h_image",
                        cursor.getString(cursor.getColumnIndex("h_image")));
                User_name_table.put("h_address",
                        cursor.getString(cursor.getColumnIndex("h_address")));
                allUser_name.add(User_name_table);
            } while (cursor.moveToNext());

        }

        return allUser_name;
    }

    public String getCityid(String Citye_name) {
        String id = "0";
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
        Cursor cursor = usersUser_name.rawQuery("SELECT * FROM City WHERE cityname =" + "'" + Citye_name + "'", null);
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getString(cursor.getColumnIndex("city_id"));
            } while (cursor.moveToNext());
        }
        return id;
    }




    public ArrayList<HashMap<String, String>> get_UserCartfromid(int _id) {
        ArrayList<HashMap<String, String>> allUser_name = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
        Cursor cursor = usersUser_name.rawQuery("SELECT * FROM UserCart WHERE _id =" + "'" + _id + "'", null);
        //  Cursor cursor = usersUser_name.rawQuery("select * from UserCart ", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> User_name_table = new HashMap<String, String>();
                User_name_table.put("p_name",
                        cursor.getString(cursor.getColumnIndex("p_name")));
                User_name_table.put("p_url",
                        cursor.getString(cursor.getColumnIndex("p_url")));
                User_name_table.put("p_price",
                        cursor.getString(cursor.getColumnIndex("p_price")));
                User_name_table.put("p_id",
                        cursor.getString(cursor.getColumnIndex("p_id")));
                User_name_table.put("p_item",
                        cursor.getString(cursor.getColumnIndex("p_item")));
                User_name_table.put("p_weight",
                        cursor.getString(cursor.getColumnIndex("p_weight")));
                User_name_table.put("_id",
                        cursor.getString(cursor.getColumnIndex("_id")));
                allUser_name.add(User_name_table);
            } while (cursor.moveToNext());
        }
        return allUser_name;
    }

    public String delete_byID(String id) {
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
        usersUser_name.delete(AppString.TABEL_NAME, "h_id=" + id, null);
        return "Remove";
    }





    ///////////////////////////////////////////////////
    ////////// update /////////
    public void update_byID(String id, String value) {
        Log.d("update_byID", id + " " + value);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("p_item", value);
        db.update("UserCart", values, "p_id = ?",
                new String[]{id});
    }

    public boolean getAvelable(String h_id) {
        boolean login_msg = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(AppString.TABEL_NAME, null, " h_id=?",
                new String[]{h_id}, null, null, null);
        if (cursor.getCount() > 0) {
            login_msg = false;
        } else {
            login_msg = true;

        }
        db.close();
        return login_msg;
    }

}
