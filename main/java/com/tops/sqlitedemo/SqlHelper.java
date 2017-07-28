package com.tops.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by Android on 03/04/2017.
 */

public class SqlHelper extends SQLiteOpenHelper {

    String tbName = "Stud", id = "ID", fName = "FName", lName = "LName";
    SQLiteDatabase db;

    public SqlHelper(Context context) {
        super(context, "Students", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + tbName + " ("+id+" integer primary key autoincrement, "+fName+" text, "+lName+" text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(StudBean studBean) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(fName, studBean.getfName());
        contentValues.put(lName, studBean.getlName());
        db.insert(tbName, null, contentValues);
        db.close();
    }

    public ArrayList<StudBean> displayData() {
        ArrayList<StudBean> arrayList = new ArrayList<>();
        db = getReadableDatabase();
        String sql = "select * from " + tbName;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            StudBean studBean = new StudBean();
            studBean.setId(cursor.getInt(0) + "");
            studBean.setfName(cursor.getString(1));
            studBean.setlName(cursor.getString(2));
            arrayList.add(studBean);
        }
        db.close();
        return arrayList;
    }

    public void updateData(StudBean studBean) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(fName, studBean.getfName());
        contentValues.put(lName, studBean.getlName());
        db.update(tbName, contentValues, id +"="+studBean.getId(), null);
        db.close();
    }

    public void deleteData(String delId) {
        db = getWritableDatabase();
        db.delete(tbName, id + "=" + delId, null);
        db.close();
    }
}
