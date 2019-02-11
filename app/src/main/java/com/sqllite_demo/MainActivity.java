package com.sqllite_demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	EditText name, email, address;
	Button save, show, delete;
	ListView show_data_list;

	Database_Helper database;

	// Variable for database table fields size
	int data_size = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Calling init method
		init();
	}

	void init() {
		name = (EditText) findViewById(R.id.name);
		email = (EditText) findViewById(R.id.email);
		address = (EditText) findViewById(R.id.address);

		save = (Button) findViewById(R.id.save);
		show = (Button) findViewById(R.id.show);
		delete = (Button) findViewById(R.id.delete);

		show_data_list = (ListView) findViewById(R.id.show_saved_data);

		// Initializing database
		database = new Database_Helper(MainActivity.this);

		// Implementing click listener to all buttons
		save.setOnClickListener(this);
		show.setOnClickListener(this);
		delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.save:

			// Getting edittext fields texts into string
			String getName = name.getText().toString();
			String getEmail = email.getText().toString();
			String getAddress = address.getText().toString();

			// Check if all edit text is filled or not
			if (TextUtils.isEmpty(getName) || TextUtils.isEmpty(getEmail)
					|| TextUtils.isEmpty(getAddress)) {

				// Showing a toast if any of them fields empty
				Toast.makeText(MainActivity.this, "All fields are necessary.",
						Toast.LENGTH_SHORT).show();
			} else {

				// Insert data into database
				database.insertData(new Data_Model(getName, getEmail,
						getAddress));

				// Toast for successfully saved data
				Toast.makeText(MainActivity.this, "Data saved successfully.",
						Toast.LENGTH_SHORT).show();

			}

			break;

		case R.id.show:

			// Getting stored data from database in list
			List<Data_Model> list = database.getAllData();

			// Arraylist for storing data to show over listview
			ArrayList<Data_Model> new_list = new ArrayList<Data_Model>();

			// getting list size and stored in data_size
			data_size = list.size();

			// Checked if database contains data or not
			if (data_size == 0) {

				// If data is not contained means 0 then show a toast
				Toast.makeText(MainActivity.this, "There is no data in table.",
						Toast.LENGTH_SHORT).show();

				// Check if listview is shown or not
				if (show_data_list.isShown()) {

					// Hide listview
					show_data_list.setVisibility(View.GONE);
				}
			} else {

				// For each for all data in database
				for (Data_Model data : list) {

					// Getting data from database
					String stored_name = data.getName();
					String stored_email = data.getEmail();
					String stored_address = data.getAddress();

					// Adding data to array list
					new_list.add(new Data_Model(stored_name, stored_email,
							stored_address));

				}

				// Custom adapter for setting array list
				Custom_Adapter adapter = new Custom_Adapter(MainActivity.this,
						new_list);

				// setting adapter over listview
				show_data_list.setAdapter(adapter);

				// Notifying adapter
				adapter.notifyDataSetChanged();

				// Showing listview
				show_data_list.setVisibility(View.VISIBLE);
			}

			break;

		case R.id.delete:

			// Check if database contains data or not
			if (data_size == 0) {

				// If there is no data toast is shown
				Toast.makeText(MainActivity.this,
						"There is nothing to delete.", Toast.LENGTH_SHORT)
						.show();
			} else {

				// else data is deleted and hide the listview
				Toast.makeText(MainActivity.this, "Data deleted Successfully.",
						Toast.LENGTH_SHORT).show();
				database.deleteTable();
				show_data_list.setVisibility(View.GONE);
			}

			break;
		}

	}

}
