package com.sqllite_demo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_Adapter extends BaseAdapter {

	ArrayList<Data_Model> arrayList;
	LayoutInflater inflater;
	ViewHolder holder = null;

	public Custom_Adapter(Context context, ArrayList<Data_Model> arrayList) {
		this.arrayList = arrayList;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return arrayList.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int pos, View view, ViewGroup root) {

		if (view == null) {
			view = inflater.inflate(R.layout.customview_for_listview, root,
					false);
			holder = new ViewHolder();

			holder.text = (TextView) view.findViewById(R.id.all_data);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		// Getting all fields from array list and stored in strings
		String name = arrayList.get(pos).getName();
		String email = arrayList.get(pos).getEmail();
		String address = arrayList.get(pos).getAddress();

		// Data is displayed over textView
		holder.text.setText("Name : " + name + "\n" + "Email : " + email + "\n"
				+ "Address : " + address);

		return view;
	}

	// View holder for holding view
	public class ViewHolder {

		TextView text;

	}

}
