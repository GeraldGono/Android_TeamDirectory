package com.example.team_directory_v02;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class YellowActivity extends Activity {

	private ListView myYellowMembers;
	public DBHelper mydb;
	public ArrayList<HashMap<String, Object>> loadAllColorEmployee;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color_member);

		mydb = new DBHelper(this);

		loadAllColorEmployee = mydb.loadColors("yellow");

		myYellowMembers = (ListView) findViewById(R.id.listView1);
		myYellowMembers.setAdapter(new GetAllWorkerAdapter(YellowActivity.this,
				loadAllColorEmployee));

		myYellowMembers.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				int getID = Integer.parseInt((String) ((TextView) arg1
						.findViewById(R.id.tvID)).getText());

				Bundle dataBundle = new Bundle();
				dataBundle.putInt("id", getID);
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
