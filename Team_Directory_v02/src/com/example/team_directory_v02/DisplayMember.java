package com.example.team_directory_v02;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMember extends Activity implements OnItemSelectedListener {

	private DBHelper mydb;
	public int id_To_Update = 0;
	public TextView name, dept, colorChosen, color, chooseColor;
	public Spinner colorChoices;
	public int sound, mPos, value;
	public String mSelection;
	public Bundle extras;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_member);
		name = (TextView) findViewById(R.id.editTextName);
		dept = (TextView) findViewById(R.id.editTextDept);
		color = (TextView) findViewById(R.id.color_chosen);
		chooseColor = (TextView) findViewById(R.id.textView4);
		colorChoices = (Spinner) findViewById(R.id.color_group);

		colorChoices.setOnItemSelectedListener(this);

		List<String> categories = new ArrayList<String>();
		categories.add("blue");
		categories.add("red");
		categories.add("green");
		categories.add("yellow");
		// spinner array adapter
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, categories);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		colorChoices.setAdapter(dataAdapter);

		mydb = new DBHelper(this);
		extras = getIntent().getExtras();
		if (extras != null) {
			value = extras.getInt("id");
			if (value > 0) {
				// for view of member data from DBHelper
				Cursor rs = mydb.getData(value);
				id_To_Update = value;
				rs.moveToFirst();
				String nam = rs.getString(rs
						.getColumnIndex(DBHelper.MEMBERS_COLUMN_NAME));
				String dep = rs.getString(rs
						.getColumnIndex(DBHelper.MEMBERS_COLUMN_DEPT));
				String col = rs.getString(rs
						.getColumnIndex(DBHelper.MEMBERS_COLUMN_COLOR));
				if (!rs.isClosed()) {
					rs.close();
				}

				Button b = (Button) findViewById(R.id.button1);
				b.setVisibility(View.INVISIBLE);
				colorChoices.setVisibility(View.INVISIBLE);
				chooseColor.setVisibility(View.INVISIBLE);

				name.setText((CharSequence) nam);
				name.setFocusable(false);
				name.setClickable(false);

				dept.setText((CharSequence) dep);
				dept.setFocusable(false);
				dept.setClickable(false);

				color.setText((CharSequence) col);

			}
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// String item = colorChoices.getSelectedItem().toString();

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		extras = getIntent().getExtras();
		if (extras != null) {
			value = extras.getInt("id");
			if (value > 0) {
				getMenuInflater().inflate(R.menu.activity_manipulate_member,
						menu);
			} else {
				getMenuInflater().inflate(R.menu.activity_main_menu, menu);
			}
		}
		return true;
	}

	public boolean editMember() {

		Button b = (Button) findViewById(R.id.button1);
		b.setVisibility(View.VISIBLE);
		colorChoices.setVisibility(View.VISIBLE);
		chooseColor.setVisibility(View.VISIBLE);

		name.setEnabled(true);
		name.setFocusableInTouchMode(true);
		name.setClickable(true);

		dept.setEnabled(true);
		dept.setFocusableInTouchMode(true);
		dept.setClickable(true);

		return true;
	}

	public boolean deleteMember() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.delete)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								mydb.deleteMembers(id_To_Update);
								Toast.makeText(getApplicationContext(),
										"Deleted Successfully",
										Toast.LENGTH_SHORT).show();
								startActivity(new Intent(
										getApplicationContext(), Group.class));
								finish();
							}
						})

				.setNegativeButton(R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
							}
						});
		AlertDialog d = builder.create();
		d.setTitle("Are you sure");
		d.show();

		return true;
	}

	// for code revision(Edit()/Delete() function)
	// for menu selection EDIT/DELETE members
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.Edit_Member: // edit member process
			editMember();
			break;

		// return true;
		case R.id.Delete_Member: // delete member process
			deleteMember();
			break;
		// return true;
		default:
			return super.onOptionsItemSelected(item);

		}
		return true;
	}

	// saving new member process
	public void run(View view) {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int Value = extras.getInt("id");
			if (Value > 0) {
				if (mydb.updateMembers(id_To_Update, name.getText().toString(),
						dept.getText().toString(), colorChoices
								.getSelectedItem().toString())) {
					Toast.makeText(getApplicationContext(), "Updated",
							Toast.LENGTH_SHORT).show();
					startActivity(new Intent(getApplicationContext(),
							Group.class));
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "not Updated",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				if (mydb.insertMembers(name.getText().toString(), dept
						.getText().toString(), colorChoices.getSelectedItem()
						.toString())) {
					Toast.makeText(getApplicationContext(), "done",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "not done",
							Toast.LENGTH_SHORT).show();
				}
				startActivity(new Intent(getApplicationContext(), Group.class));
				finish();
			}
		}

	}

	public void back(View v) {
		finish();
	}

}
