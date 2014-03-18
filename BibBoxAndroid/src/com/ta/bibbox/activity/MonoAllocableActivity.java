package com.ta.bibbox.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.bibboxandroid.R;
import com.ta.bibbox.adapter.MonoAllocableAdapter;
import com.ta.bibbox.converter.ScreenConverter;
import com.ta.bibbox.model.AMonoAllocableViewModel.MonoAllocableDetail;
import com.ta.bibbox.model.AMonoAllocableViewModel.MonoAllocableList;
import com.ta.bibbox.pojo.BoxType;
import com.ta.bibbox.pojo.MonoAllocable;
import com.ta.bibbox.service.ServiceAllocable;

/**
 * @author Jing SHU
 * @date 12/03/2014
 * @copyright TA Copyright
 * @brief La page contenant le résultat de recherche des mono-allouables
 */
public class MonoAllocableActivity extends BaseActivity {
	private List<MonoAllocableList> header;
	private Map<MonoAllocableList, List<MonoAllocableDetail>> children;
	
	public final static String NUM_MONO = "com.ta.bibbox.NUM_MONO";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mono_allocable);
	}

	@Override
	public void onResume() {
		super.onResume();  // Always call the superclass method first

		SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);   
		String login = pref.getString(LoginActivity.Login, null);
		if(login == null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		} else {
			// Get information from the intent
//			Intent intent = getIntent();
//			int nbPerson = intent.getIntExtra(NewReservActivity.NB_PERSON, 0);
//			String equip = intent.getStringExtra(NewReservActivity.EQUIP);
//			String location = intent.getStringExtra(NewReservActivity.LOCATION);
//			String date = intent.getStringExtra(NewReservActivity.DATE);
//			String beginTime = intent.getStringExtra(NewReservActivity.BEGIN_TIME);
//			String endTime = intent.getStringExtra(NewReservActivity.END_TIME);
			
			int nbPerson = pref.getInt(NewReservActivity.NB_PERSON, 0);
			String equip = pref.getString(NewReservActivity.EQUIP, null);
			String location = pref.getString(NewReservActivity.LOCATION, null);
			String date = pref.getString(NewReservActivity.DATE, null);
			String beginTime = pref.getString(NewReservActivity.BEGIN_TIME, null);
			String endTime = pref.getString(NewReservActivity.END_TIME, null);
			


//							System.out.println("fifi nbPerson : " + nbPerson);
//							System.out.println("fifi equip : " + equip);
//							System.out.println("fifi location : " + location);
//							System.out.println("fifi date : " + date);
//							System.out.println("fifi beginTime : " + beginTime);
//							System.out.println("fifi endTime : " + endTime);

			ServiceAllocable alloc = new ServiceAllocable();
			List<MonoAllocable> monoAllocables = alloc.SearchMonoAllocables(nbPerson, equip, location, date, beginTime, endTime);
			System.out.println("vivi : " + monoAllocables.toString());
			if((monoAllocables != null) && (monoAllocables.size() > 0)){
				try{
					dispatchMonoAllocables(monoAllocables);
					System.out.println("vivi 1 : " + header.toString());
					System.out.println("vivi 2 : " + children.toString());
					MonoAllocableAdapter adapter = new MonoAllocableAdapter(this, header, children);
					ExpandableListView lvMono = (ExpandableListView) findViewById(R.id.listView_mono);
					lvMono.setAdapter(adapter);					
				} catch (Exception e){
					e.printStackTrace();
				}
			} else {
				TextView tv = new TextView(this);
				tv.setText("Aucun résultat");
				setContentView(tv);
			}
		}
	}
	
	/** Called when the user clicks the Reservation button */
	public void openSearchMultiAllocables(View view) {
		TextView tvNumMonoAllocable = (TextView) findViewById(R.id.value_num_mono);
		int numMonoAllocable = Integer.parseInt(tvNumMonoAllocable.getText().toString());
		getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE).edit().putInt(NUM_MONO, numMonoAllocable).commit();
		
		Intent intent = new Intent(this, MultiAllocableActivity.class);
		startActivity(intent);
	}

	private void dispatchMonoAllocables(List<MonoAllocable> monoAllocables){
		if(monoAllocables == null) { return; }
		header = new ArrayList<MonoAllocableList>();
		children = new HashMap<MonoAllocableList, List<MonoAllocableDetail>>();
		for(MonoAllocable m : monoAllocables){
			MonoAllocableList l = new MonoAllocableList(m.getLocation().getName(), m.getName());
			header.add(l);
			List<MonoAllocableDetail> ld = new ArrayList<MonoAllocableDetail>();
			MonoAllocableDetail d = new MonoAllocableDetail(
							String.valueOf(m.getId()), 
							String.valueOf(((BoxType)m.getType()).getNbSeat()),
							ScreenConverter.instance().convertToString(((BoxType)m.getType()).getScreen()),
							m.getDescription());
			ld.add(d);
			children.put(l, ld);
		}
	}
}
