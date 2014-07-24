package com.example.team_directory_v02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frontpage);
		
	}
	//go to settings activity
	public void Setting(View v) {
		Intent settingIntent = new Intent(this, Setting.class);
		startActivity(settingIntent);

	}
	//go to groups activity
	public void Group(View v) {
		Intent groupIntent = new Intent(this, Group.class);
		startActivity(groupIntent);

	}
	//terminate the application
	public void Exit(View v) {
		System.exit(0);
	}

}
