package com.example.team_directory_v02;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class RedActivity extends Activity {

	private ListView obj;
	public DBHelper mydb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_red_member);
		// call the array_list(color) in DBHelper
		mydb = new DBHelper(this);
		ArrayList<Member> array_list = mydb.getAllColorMembers("red");

		ArrayAdapter<Member> arrayAdapter = new ArrayAdapter<Member>(this,
				android.R.layout.simple_list_item_1, array_list);
		obj = (ListView) findViewById(R.id.listView1);
		obj.setAdapter(arrayAdapter);

		obj.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int id_To_Search = arg2 + 1;
				Bundle dataBundle = new Bundle();
				dataBundle.putInt("id", id_To_Search);
				Intent intent = new Intent(getApplicationContext(),
						com.example.team_directory_v02.DisplayMember.class);
				intent.putExtras(dataBundle);
				startActivity(intent);
			}
		});
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.item1:
			Bundle dataBundle = new Bundle();
			dataBundle.putInt("id", 0);
			Intent intent = new Intent(getApplicationContext(),
					com.example.team_directory_v02.DisplayMember.class);
			intent.putExtras(dataBundle);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}*/
	// for key event
	public boolean onKeyDown(int keycode, KeyEvent event) {
		if (keycode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(true);
		}
		return super.onKeyDown(keycode, event);

	}

	public void back(View v) {
//		Intent groupIntent = new Intent(this, Group.class);
//		startActivity(groupIntent);
		finish();

	}

}
