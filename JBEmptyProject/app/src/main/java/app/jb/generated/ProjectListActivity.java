package app.jb.generated;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import app.jb.generated.*;
import app.jb.generated.ProjectListFragment;

public class ProjectListActivity extends AppCompatActivity implements ProjectListFragmentDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

    	FragmentManager fm = getFragmentManager();
    	if (fm.findFragmentById(android.R.id.content) == null) { 
    		ProjectListFragment list = new ProjectListFragment(); 
    		list.onAttach(this); 
    		fm.beginTransaction().add(android.R.id.content, list).commit(); 
    	}

    	createDatabase();
    }

    public void openActivity(Class<?> activity) {
    	Intent intent = new Intent(this, activity);
    	startActivity(intent);
    }

    public void createDatabase() {

    }


}