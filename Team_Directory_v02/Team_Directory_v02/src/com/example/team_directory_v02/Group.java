package com.example.team_directory_v02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Group extends Activity {

	private ImageView imgR, imgG, imgB, imgY;
	public MediaPlayer beep1;
	public Button menu;
	public int sound, volume = 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);

		imgR = (ImageView) findViewById(R.id.imageView6);
		imgG = (ImageView) findViewById(R.id.imageView5);
		imgY = (ImageView) findViewById(R.id.imageView1);
		imgB = (ImageView) findViewById(R.id.imageView2);

		beep1 = MediaPlayer.create(Group.this, R.raw.beep1);

		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"higher", MODE_PRIVATE);
		Editor editor = pref.edit();
		sound = pref.getInt("sound", 0);
		if (sound == 0) {
			volume = 0;
		}

		imgR.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent RedIntent = new Intent(Group.this, RedActivity.class);
				startActivity(RedIntent);
				finish();
				if (sound == 1) {
					beep1.start();
				}

			}
		});

		imgG.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent GreenIntent = new Intent(Group.this, GreenActivity.class);
				startActivity(GreenIntent);
				finish();
				if (sound == 1) {
					beep1.start();
				}

			}
		});

		imgY.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent YellowIntent = new Intent(Group.this,
						YellowActivity.class);
				startActivity(YellowIntent);
				finish();
				if (sound == 1) {
					beep1.start();
				}

			}
		});

		imgB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent BlueIntent = new Intent(Group.this, BlueActivity.class);
				startActivity(BlueIntent);
				finish();
				if (sound == 1) {
					beep1.start();
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}

	public void menu(View v) {
		Intent intent = new Intent(Group.this, MainActivity.class);
		startActivity(intent);
		finish();
		if (sound == 1) {
			beep1.start();
		}
		

	}
}
