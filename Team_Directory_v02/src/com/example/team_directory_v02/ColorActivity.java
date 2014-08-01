package com.example.team_directory_v02;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ColorActivity extends Activity {

	private ListView myColorMembers;
	public DBHelper mydb;
	public ArrayList<ArrayList<String>> loadAllColorEmployee;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color_member);

		mydb = new DBHelper(this);

		Bundle extras = getIntent().getExtras();
		String value = extras.getString("color");
		loadAllColorEmployee = mydb.loadColors(value);

		myColorMembers = (ListView) findViewById(R.id.listView1);
		myColorMembers.setAdapter(new GetAllWorkerAdapter(ColorActivity.this,
				loadAllColorEmployee));

		// for data Info
		myColorMembers.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				int getId = Integer.parseInt((String) ((TextView) arg1
						.findViewById(R.id.tvId)).getText());

				Bundle dataBundle = new Bundle();
				dataBundle.putInt("id", getId);
				Intent intent = new Intent(getApplicationContext(),
						DisplayMember.class);
				intent.putExtras(dataBundle);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), Group.class));
		super.onBackPressed();
	}

	public void back(View v) {

		onBackPressed();

	}
}
