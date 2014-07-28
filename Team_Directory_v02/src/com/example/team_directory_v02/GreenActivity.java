package com.example.team_directory_v02;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GreenActivity extends Activity {

	private ListView obj;
	public DBHelper mydb;
	public ArrayList<HashMap<String, Object>> loadAllColorEmployee;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color_member);

		mydb = new DBHelper(this);

		loadAllColorEmployee = mydb.loadColors("green");

		obj = (ListView) findViewById(R.id.listView1);
		obj.setAdapter(new GetAllWorkerAdapter(GreenActivity.this,
				loadAllColorEmployee));

		obj.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				int getID = Integer.parseInt((String) ((TextView) arg1
						.findViewById(R.id.tvID)).getText());

				Bundle dataBundle = new Bundle();
				dataBundle.putInt("id", getID);
				Intent intent = new Intent(getApplicationContext(),
						com.example.team_directory_v02.DisplayMember.class);
				intent.putExtras(dataBundle);
				startActivity(intent);
				finish();
			}
		});
	}

	public boolean onKeyDown(int keycode, KeyEvent event) {
		if (keycode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(true);
		}
		return super.onKeyDown(keycode, event);

	}

	public void back(View v) {
		Intent intent = new Intent(getApplicationContext(),
				com.example.team_directory_v02.Group.class);
		startActivity(intent);
		finish();

	}

}
