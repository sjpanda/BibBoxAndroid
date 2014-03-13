package com.ta.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.bibboxandroid.R;
import com.ta.pojo.MonoAllocable;
import com.ta.service.ServiceAllocable;

/**
 * @author Jing SHU
 * @date 12/03/2014
 * @copyright TA Copyright
 * @brief La page contenant le résultat de recherche des mono-allouables
 */
public class MonoAllocableActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);   
		String login = pref.getString(LoginActivity.Login, null);
		if(login == null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		} else {
			// Get information from the intent
			Intent intent = getIntent();
			int nbPerson = intent.getIntExtra(NewReservActivity.NB_PERSON, 0);
			String equip = intent.getStringExtra(NewReservActivity.EQUIP);
			String location = intent.getStringExtra(NewReservActivity.LOCATION);
			String date = intent.getStringExtra(NewReservActivity.DATE);
			String beginTime = intent.getStringExtra(NewReservActivity.BEGIN_TIME);
			String endTime = intent.getStringExtra(NewReservActivity.END_TIME);
			
//			System.out.println("fifi nbPerson : " + nbPerson);
//			System.out.println("fifi equip : " + equip);
//			System.out.println("fifi location : " + location);
//			System.out.println("fifi date : " + date);
//			System.out.println("fifi beginTime : " + beginTime);
//			System.out.println("fifi endTime : " + endTime);

			ServiceAllocable alloc = new ServiceAllocable();
			List<MonoAllocable> monoAllocables = alloc.SearchMonoAllocables(nbPerson, equip, location, date, beginTime, endTime);
			if((monoAllocables != null) && (monoAllocables.size() > 0)){
				List<String> names = new ArrayList<String>();
				for(MonoAllocable m : monoAllocables){
					names.add(m.getName());
				}
//				BaseExpandableListAdapter adapter = new BaseExpandableListAdapter(this, names, null);
//				ExpandableListView lvMono = (ExpandableListView) findViewById(R.id.listView_mono);
//				lvMono.setAdapter(adapter);
				setContentView(R.layout.activity_mono_allocable);
			} else {
				TextView tv = new TextView(this);
				tv.setText("Aucun résultat");
				setContentView(tv);
			}
		}
	}
}
