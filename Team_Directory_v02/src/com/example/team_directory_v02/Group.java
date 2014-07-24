package com.example.team_directory_v02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Group extends Activity {

	private ImageView imgR, imgG, imgB, imgY;
	public Button menu;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);

		imgR = (ImageView) findViewById(R.id.imageView6);
		imgG = (ImageView) findViewById(R.id.imageView5);
		imgY = (ImageView) findViewById(R.id.imageView1);
		imgB = (ImageView) findViewById(R.id.imageView2);

		imgR.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent RedIntent = new Intent(Group.this, RedActivity.class);
				startActivity(RedIntent);
			}
		});

		imgG.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent GreenIntent = new Intent(Group.this, GreenActivity.class);
				startActivity(GreenIntent);
			}
		});

		imgY.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent YellowIntent = new Intent(Group.this,
						YellowActivity.class);
				startActivity(YellowIntent);
			}
		});

		imgB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent BlueIntent = new Intent(Group.this, BlueActivity.class);
				startActivity(BlueIntent);
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
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}

	public void menu(View v) {
		Intent mainIntent = new Intent(this, MainActivity.class);
		startActivity(mainIntent);

	}

}
