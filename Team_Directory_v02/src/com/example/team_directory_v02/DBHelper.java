package com.example.team_directory_v02;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// for code review revision (Change data fetching to model class)
public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "MyDBName.db",
			MEMBERS_TABLE_NAME = "members", MEMBERS_COLUMN_ID = "id",
			MEMBERS_COLUMN_NAME = "name", MEMBERS_COLUMN_DEPT = "dept",
			MEMBERS_COLUMN_COLOR = "color";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(" create table members "
				+ "(id integer primary key,name TEXT NOT NULL,dept TEXT NOT NULL,color TEXT NOT NULL)");
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
		// insert new members in database
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

	public ArrayList<ArrayList<String>> loadColors(String color) {

		ArrayList<ArrayList<String>> loadAllEmployees;
		// ArrayList<String> employeeInfo = new ArrayList<String>();

		loadAllEmployees = new ArrayList<ArrayList<String>>();
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = ("SELECT * FROM " + MEMBERS_TABLE_NAME + " WHERE "
				+ MEMBERS_COLUMN_COLOR + " = ?");

		Cursor res = db.rawQuery(selectQuery, new String[] { color });

		int getName = res.getColumnIndex(MEMBERS_COLUMN_NAME);
		int getId = res.getColumnIndex(MEMBERS_COLUMN_ID);

		for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {

			ArrayList<String> employeeInfo = new ArrayList<String>();
			employeeInfo.add(res.getString(getName));
			employeeInfo.add(res.getString(getId));
			loadAllEmployees.add(employeeInfo);
		}

		return loadAllEmployees;
	}
}
