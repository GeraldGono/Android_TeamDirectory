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

public class BlueActivity extends Activity {

	private ListView myBlueMembers;
	public DBHelper mydb;
	public ArrayList<ArrayList<String>> loadAllColorEmployee;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color_member);

		mydb = new DBHelper(this);

		loadAllColorEmployee = mydb.loadColors("blue");

		myBlueMembers = (ListView) findViewById(R.id.listView1);
		myBlueMembers.setAdapter(new GetAllWorkerAdapter(BlueActivity.this,
				loadAllColorEmployee));

		myBlueMembers.setOnItemClickListener(new OnItemClickListener() {
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

	// Intent i = new Intent(getApplicationContext(), ColorActivity.class);
	// i.putExtra("color", "red"); // to start red
	// startActivity(i); pass the data as a Bundle

}

