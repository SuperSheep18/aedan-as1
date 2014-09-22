package com.example.aedan_as1;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	int todoDone = 0;
	int todoNotDone = 0;
	int todo_archive = 0;
	int todoDone_archive = 0;
	int todoNotDone_archive = 0;
	ArrayList<ToDoItemObject> todoList;
	ArrayList<ToDoItemObject> archiveList;
	ArrayAdapter<ToDoItemObject> listAdapter;
	ArrayAdapter<ToDoItemObject> archiveAdapter;
	Button all, archive, add;
	EditText userinput;
	boolean archiveFlag = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        userinput = (EditText) findViewById(R.id.todoField); //This is causing a crash.
        
        add = (Button) findViewById(R.id.add_button);
        archive = (Button) findViewById(R.id.archive_button);
        all = (Button) findViewById(R.id.all_button);
        
        //Set up array adapter
        ListView todoListView = (ListView) findViewById(R.id.listView);
        
        //todoList = new ArrayList<ToDoItemObject>();
        //archiveList = new ArrayList<ToDoItemObject>();
        
        listAdapter = new CustomListAdapter(this, ListSharingClass.todoList);
        archiveAdapter = new CustomListAdapter(this, ListSharingClass.archiveList);
        
        todoListView.setAdapter(listAdapter); //We want to view the To-Do List, not the archive initially.
        
        add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Add Note", Toast.LENGTH_SHORT).show();
				String inputString = userinput.getText().toString();
				ToDoItemObject newItem = new ToDoItemObject();
				newItem.todoText = inputString;
				newItem.isCompleted = false;
				newItem.currentDate = Calendar.getInstance();
				if (!inputString.equals("")) { //Make sure the text field is not empty before adding something.
					ListSharingClass.todoList.add(newItem);
				}
				listAdapter.notifyDataSetChanged(); //Do this every time you change the list.
				userinput.setText("");
			}
		});
        
        archive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				ListView todoListView = (ListView) findViewById(R.id.listView);
				if (archiveFlag == true) {
					archive.setText("View To-Do");
					todoListView.setAdapter(archiveAdapter);
					archiveFlag = false;
				}
				else {
					archive.setText("View Archive");
					todoListView.setAdapter(listAdapter);
					archiveFlag = true;
				}
			}
        });
        all.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				@SuppressWarnings("unused")
				String emailString = "E-mail All Items";
				@SuppressWarnings("unused")
				String cancelString = "Cancel";
			}
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //options = (Button) findViewById(R.id.options_button);
       // options.setOnClickListener(new View.OnClickListener() {
        listAdapter.notifyDataSetChanged();
		//	@Override
		//	public void onClick(View v) {
		//		// TODO Auto-generated method stub
		//		Toast.makeText(getApplicationContext(), "View Options", Toast.LENGTH_SHORT).show();
		//	}
		//});
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	
    
    
}