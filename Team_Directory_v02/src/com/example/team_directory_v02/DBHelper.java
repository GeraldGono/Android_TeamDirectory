package com.example.team_directory_v02;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "MyDBName.db";
	public static final String MEMBERS_TABLE_NAME = "members";
	public static final String MEMBERS_COLUMN_ID = "id";
	public static final String MEMBERS_COLUMN_NAME = "name";
	public static final String MEMBERS_COLUMN_DEPT = "dept";
	public static final String MEMBERS_COLUMN_COLOR = "color";

	// @SuppressWarnings("rawtypes")
	// private HashMap Hp;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(" create table members "
				+ "(id integer primary key,name TEXT,dept TEXT,color TEXT)");
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

	public Cursor getData(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from members where id=" + id + "",
				null);
		return res;
	}

	public int numberOfRows() {
		SQLiteDatabase db = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(db,
				MEMBERS_TABLE_NAME);
		return numRows;

	}

	public boolean updateMembers(Integer id, String name, String dept,
			String color) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("dept", dept);
		contentValues.put("color", color);
		db.update("members", contentValues, "id = ? ",
				new String[] { Integer.toString(id) });
		return true;
	}

	public Integer deleteMembers(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("members", "id = ? ",
				new String[] { Integer.toString(id) });
	}

	// retrieve colors
	public ArrayList<HashMap<String, Object>> loadAllEmployees;
	HashMap<String, Object> employeeInfo;

	public ArrayList<HashMap<String, Object>> loadColors(String color) {

		loadAllEmployees = new ArrayList<HashMap<String, Object>>();
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = ("SELECT * FROM " + MEMBERS_TABLE_NAME + " WHERE "
				+ MEMBERS_COLUMN_COLOR + " = ?");

		Cursor res = db.rawQuery(selectQuery, new String[] { color });

		int getName = res.getColumnIndex(MEMBERS_COLUMN_NAME);
		int getID = res.getColumnIndex(MEMBERS_COLUMN_ID);

		for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {

			employeeInfo = new HashMap<String, Object>();
			employeeInfo.put("name", res.getString(getName));
			employeeInfo.put("ID", res.getString(getID));
			loadAllEmployees.add(employeeInfo);
		}

		return loadAllEmployees;
	}

}