package com.example.team_directory_v02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class MainActivity extends Activity {

	public boolean sound;
	public SoundPool beep1;
	private Button group, setting, exit;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frontpage);

		final SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		final int soundId = sp.load(MainActivity.this, R.raw.beep1, 1);

		final SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Editor editor = pref.edit();
		editor.commit();
		sound = pref.getBoolean("sound", false);

		group = (Button) findViewById(R.id.grp);
		setting = (Button) findViewById(R.id.set);
		exit = (Button) findViewById(R.id.ext);

		group.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Group.class));
				if (sound) {
					sp.play(soundId, 1, 1, 0, 0, 1);
				}

			}
		});

		setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Setting.class));
				if (sound) {
					sp.play(soundId, 1, 1, 0, 0, 1);
				}

			}
		});

		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

}
