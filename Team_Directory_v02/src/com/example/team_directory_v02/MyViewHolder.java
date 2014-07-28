package com.example.team_directory_v02;

import android.view.View;
import android.widget.TextView;

class MyViewHolder {
	public TextView tvname, tvID, tvcolor;

	public MyViewHolder(View row) {
		tvname = (TextView) row.findViewById(R.id.tvName);
		tvID = (TextView) row.findViewById(R.id.tvID);

	}

}