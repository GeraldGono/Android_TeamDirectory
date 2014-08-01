package com.example.team_directory_v02;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Setting extends Activity {

	public CheckBox ckBox;
	public boolean sound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound_settings);
		ckBox = (CheckBox) findViewById(R.id.checkBox1);
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		sound = pref.getBoolean("sound", false);
		if (sound) {
			ckBox.setChecked(true);
		}

	}

	public void sound(View v) {
		ckBox = (CheckBox) v;
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Editor editor = pref.edit();
		if (ckBox.isChecked()) {
			editor.putBoolean("sound", true);
			editor.commit();
			Toast.makeText(this, "sound on", Toast.LENGTH_SHORT).show();
		} else {
			editor.putBoolean("sound", false);
			editor.commit();

		}

	}

	public void menu(View v) {
		finish();

	}

}


