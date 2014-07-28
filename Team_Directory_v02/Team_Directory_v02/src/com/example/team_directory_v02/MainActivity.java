package com.example.team_directory_v02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	public int volume = 1, sound;
	public MediaPlayer beep1;
	private Button Group, Setting;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frontpage);

		beep1 = MediaPlayer.create(MainActivity.this, R.raw.beep1);

		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"higher", MODE_PRIVATE);
		Editor editor = pref.edit();
		sound = pref.getInt("sound", 0);
		if (sound == 0) {
			volume = 0;
		}

		Group = (Button) findViewById(R.id.grp);
		Setting = (Button) findViewById(R.id.set);

		Group.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent groupIntent = new Intent(MainActivity.this, Group.class);
				startActivity(groupIntent);
				finish();
				if (sound == 1) {
					beep1.start();
				}

			}
		});

		Setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent settingIntent = new Intent(MainActivity.this,
						Setting.class);
				startActivity(settingIntent);
				finish();
				if (sound == 1) {
					beep1.start();
				}

			}
		});

	}

	public void Exit(View v) {
		System.exit(0);
	}

}
