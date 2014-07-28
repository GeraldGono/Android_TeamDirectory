package com.example.team_directory_v02;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class GetAllWorkerAdapter extends ArrayAdapter<HashMap<String, Object>> {

	private final ArrayList<HashMap<String, Object>> dataList;
	public Context context;

	public GetAllWorkerAdapter(Context context,
			ArrayList<HashMap<String, Object>> getList) {
		super(context, R.layout.testview);
		this.context = context;
		this.dataList = getList;

	}

	@Override
	public int getCount() {

		return dataList.size();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		MyViewHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.testview, parent, false);
			holder = new MyViewHolder(row);
			row.setTag(holder);
		} else {
			// recycling
			holder = (MyViewHolder) row.getTag();
		}

		if (holder.tvname != null) {
			holder.tvname
					.setText(dataList.get(position).get("name").toString());
			holder.tvID.setText(dataList.get(position).get("ID").toString());

		}

		return row;

	}



}
