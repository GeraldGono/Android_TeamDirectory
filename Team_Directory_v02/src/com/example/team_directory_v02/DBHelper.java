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

	@SuppressWarnings("rawtypes")
	private HashMap hp;

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

	// for Color member
	public ArrayList<Member> getAllColorMembers(String color) {
		ArrayList<Member> array_list_color = new ArrayList<Member>();
		Log.d("sana gumana", "Color array list created");
		// hp = new HashMap();
		SQLiteDatabase db = this.getReadableDatabase();

		/*
		 * Cursor res = db.rawQuery( MEMBERS_TABLE_NAME, new String[] {
		 * MEMBERS_COLUMN_NAME, MEMBERS_COLUMN_DEPT, MEMBERS_COLUMN_COLOR },
		 * MEMBERS_COLUMN_COLOR + "=" + color, //new String[] {color}, );
		 */

		/*
		 * Cursor res = db .query(MEMBERS_TABLE_NAME, new String[] {
		 * MEMBERS_COLUMN_ID, MEMBERS_COLUMN_NAME, MEMBERS_COLUMN_DEPT,
		 * MEMBERS_COLUMN_COLOR }, MEMBERS_COLUMN_COLOR + " like " + color,
		 * null, null, null, null);
		 */

		String selectQuery = ("SELECT * FROM " + MEMBERS_TABLE_NAME + " WHERE "
				+ MEMBERS_COLUMN_COLOR + " = ?");
		// Cursor res = db.rawQuery(selectQuery, new String[] { color });
		Cursor res = db.rawQuery(selectQuery,
				new String[] { color });

		if (res.moveToFirst()) {
			Log.d("res.moveToFirst()", "res.moveToFirst()");
			while (res.isAfterLast() == false) {
				Log.d("gagana yan", res.getString(res
						.getColumnIndexOrThrow(MEMBERS_COLUMN_NAME)));
				Member member = new Member(res.getString(res
						.getColumnIndexOrThrow(MEMBERS_COLUMN_NAME)),
						res.getString(res
								.getColumnIndexOrThrow(MEMBERS_COLUMN_DEPT)),
						res.getString(res
								.getColumnIndexOrThrow(MEMBERS_COLUMN_COLOR)));

				array_list_color.add(member);
				/*
				 * array_list.add(res.getString(res
				 * .getColumnIndex(MEMBERS_COLUMN_COLOR)));
				 */
				res.moveToNext();
			}
		}
		return array_list_color;
	}
	/*
	 * // for Blue member public ArrayList getAllBlueMembers(String color) {
	 * ArrayList array_list = new ArrayList(); // hp = new HashMap();
	 * SQLiteDatabase db = this.getReadableDatabase(); Cursor res =
	 * db.rawQuery("select * from members where color like blue" + color, null);
	 * res.moveToFirst(); while (res.isAfterLast() == false) {
	 * array_list.add(res.getString(res .getColumnIndex(MEMBERS_COLUMN_NAME)));
	 * array_list.add(res.getString(res .getColumnIndex(MEMBERS_COLUMN_COLOR)));
	 * res.moveToNext(); } return array_list; }
	 * 
	 * // for Green member public ArrayList getAllGreenMembers(String color) {
	 * ArrayList array_list = new ArrayList(); // hp = new HashMap();
	 * SQLiteDatabase db = this.getReadableDatabase(); Cursor res =
	 * db.rawQuery("select * from members where color like green" + color,
	 * null); res.moveToFirst(); while (res.isAfterLast() == false) {
	 * array_list.add(res.getString(res .getColumnIndex(MEMBERS_COLUMN_NAME)));
	 * array_list.add(res.getString(res .getColumnIndex(MEMBERS_COLUMN_COLOR)));
	 * res.moveToNext(); } return array_list; }
	 * 
	 * // for Yellow member public ArrayList getAllYellowMembers(String color) {
	 * ArrayList array_list = new ArrayList(); // hp = new HashMap();
	 * SQLiteDatabase db = this.getReadableDatabase(); Cursor res =
	 * db.rawQuery("select * from members where color like yellow" + color,
	 * null); res.moveToFirst(); while (res.isAfterLast() == false) {
	 * array_list.add(res.getString(res .getColumnIndex(MEMBERS_COLUMN_NAME)));
	 * array_list.add(res.getString(res .getColumnIndex(MEMBERS_COLUMN_COLOR)));
	 * res.moveToNext(); } return array_list; }
	 */

}
