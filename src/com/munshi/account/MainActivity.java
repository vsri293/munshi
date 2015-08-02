package com.munshi.account;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private EditText editTxt;
	private Button btn;
	private ListView list;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> arrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    editTxt = (EditText) findViewById(R.id.etAdd);
	    btn = (Button) findViewById(R.id.btAdd);
	    list = (ListView) findViewById(R.id.list);
	    arrayList = new ArrayList<String>();

	    // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
	    // and the array that contains the data
	    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

	    // Here, you set the data in your ListView
	    list.setAdapter(adapter);

	    btn.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {

	            // this line adds the data of your EditText and puts in your array
	            arrayList.add(editTxt.getText().toString());
	            // next thing you have to do is check if your adapter has changed
	            adapter.notifyDataSetChanged();
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}