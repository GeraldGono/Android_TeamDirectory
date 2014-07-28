package com.example.team_directory_v02;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	public MediaPlayer beep1;
	public Setting set;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frontpage);

		beep1 = MediaPlayer.create(MainActivity.this, R.raw.beep1);

	}

	// go to settings activity
	public void Setting(View v) {
		Intent settingIntent = new Intent(this, Setting.class);
		startActivity(settingIntent);
		beep1.start();

	}

	// go to groups activity
	public void Group(View v) {
		Intent groupIntent = new Intent(this, Group.class);
		startActivity(groupIntent);
		beep1.start();

	}

	// terminate the application
	public void Exit(View v) {
		System.exit(0);
	}

}
