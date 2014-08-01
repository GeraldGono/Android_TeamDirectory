package com.example.team_directory_v02;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

// for code revision (Prevent using Hashmap for smooth access Operation)
public class GetAllWorkerAdapter extends ArrayAdapter<ArrayList<?>> {

	private final ArrayList<ArrayList<String>> dataList;
	public Context context;

	public GetAllWorkerAdapter(Context context,
			ArrayList<ArrayList<String>> getList) {
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
		Member holder = null;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.testview, parent, false);
			holder = new Member(row);
			row.setTag(holder);
		} else {
			// recycling
			holder = (Member) row.getTag();
		}

		if (holder.tvName != null) {
			holder.tvName.setText(dataList.get(position).get(0).toString());
			holder.tvId.setText(dataList.get(position).get(1).toString());

		}

		return row;

	}

	public class Member {
		public TextView tvName, tvId, tvColor;

		public Member(View row) {
			tvName = (TextView) row.findViewById(R.id.tvName);
			tvId = (TextView) row.findViewById(R.id.tvId);

		}
	}

}

