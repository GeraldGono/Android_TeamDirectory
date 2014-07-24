package com.example.team_directory_v02;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "MyDBName.db";
	public static final String MEMBERS_TABLE_NAME = "members";
	public static final String MEMBERS_COLUMN_ID = "id";
	public static final String MEMBERS_COLUMN_NAME = "name";
	public static final String MEMBERS_COLUMN_DEPT = "dept";
	public static final String MEMBERS_COLUMN_COLOR = "color";
	public Cursor res;

	@SuppressWarnings("rawtypes")
	private HashMap hp;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table members " + "(id integer primary key, "
				+ "name text," + "dept text" + "color text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS members");
		onCreate(db);
	}

	public boolean insertMembers(String name, String dept, String color) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("name", name);
		contentValues.put("dept", dept);
		contentValues.put("color", color);

		db.insert("members", null, contentValues);
		return true;
	}

}
