package com.example.team_directory_v02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Group extends Activity {

	private ImageView imgR, imgG, imgB, imgY;
	public SoundPool beep1;
	public Button menu;
	public int sound;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);

		imgR = (ImageView) findViewById(R.id.imageView6);
		imgG = (ImageView) findViewById(R.id.imageView5);
		imgY = (ImageView) findViewById(R.id.imageView1);
		imgB = (ImageView) findViewById(R.id.imageView2);

		final SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		final int soundId = sp.load(Group.this, R.raw.beep1, 1);

		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"higher", MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.commit();
		sound = pref.getInt("sound", 0);
		// open Red Group
		imgR.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						RedActivity.class));
				finish();
				if (sound == 1) {
					sp.play(soundId, 1, 1, 0, 0, 1);
				}

			}
		});
		// open Green Group
		imgG.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						GreenActivity.class));
				finish();
				if (sound == 1) {
					sp.play(soundId, 1, 1, 0, 0, 1);
				}

			}
		});
		// open Yellow Group
		imgY.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						YellowActivity.class));
				finish();
				if (sound == 1) {
					sp.play(soundId, 1, 1, 0, 0, 1);
				}

			}
		});
		// open Blue Group
		imgB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						BlueActivity.class));
				finish();
				if (sound == 1) {
					sp.play(soundId, 1, 1, 0, 0, 1);
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
					DisplayMember.class);
			intent.putExtras(dataBundle);
			startActivity(intent);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}

	// back to Main menu
	public void menu(View v) {
		startActivity(new Intent(Group.this, MainActivity.class));
		finish();

	}
}

