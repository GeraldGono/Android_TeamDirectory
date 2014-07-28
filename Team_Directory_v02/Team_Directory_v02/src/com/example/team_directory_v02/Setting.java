package com.example.team_directory_v02;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Setting extends Activity {

	public CheckBox ckBox;
	public int sound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound_settings);
		ckBox = (CheckBox) findViewById(R.id.checkBox1);

		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"higher", MODE_PRIVATE);
		sound = pref.getInt("sound", 0);

		if (sound == 1) {
			ckBox.setChecked(true);
		}

	}

	public void sound(View v) {
		ckBox = (CheckBox) v;
		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"higher", MODE_PRIVATE);
		Editor editor = pref.edit();
		if (ckBox.isChecked()) {
			editor.putInt("sound", 1);
			editor.commit();
			Toast.makeText(this, "sound on", Toast.LENGTH_SHORT).show();
		} else {
			editor.putInt("sound", 0);
			editor.commit();

		}

	}

	public void menu(View v) {
		Intent intent = new Intent(getApplicationContext(),
				com.example.team_directory_v02.MainActivity.class);
		startActivity(intent);
		finish();

	}

}
