package com.ta.bibbox.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibboxandroid.R;
import com.ta.bibbox.adapter.MonoAllocableAdapter;
import com.ta.bibbox.converter.ScreenConverter;
import com.ta.bibbox.model.AMonoAllocableViewModel.MonoAllocableDetail;
import com.ta.bibbox.model.AMonoAllocableViewModel.MonoAllocableList;
import com.ta.bibbox.pojo.BoxType;
import com.ta.bibbox.pojo.MonoAllocable;
import com.ta.bibbox.pojo.UserRole;
import com.ta.bibbox.service.ServiceAllocable;

/**
 * @author Jing SHU
 * @date 12/03/2014
 * @copyright TA Copyright
 * @brief La page contenant le r�sultat de recherche des mono-allouables
 */
public class MonoAllocableActivity extends BaseActivity {
	private List<MonoAllocableList> header;
	private Map<MonoAllocableList, List<MonoAllocableDetail>> children;

	public final static String NUM_MONO = "com.ta.bibbox.NUM_MONO";
	public final static String NAME_MONO = "com.ta.bibbox.NAME_MONO";

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
			int nbPerson = pref.getInt(NewReservActivity.NB_PERSON, 0);
			String equip = pref.getString(NewReservActivity.EQUIP, null);
			String location = pref.getString(NewReservActivity.LOCATION, null);
			String date = pref.getString(NewReservActivity.DATE, null);
			String beginTime = pref.getString(NewReservActivity.BEGIN_TIME, null);
			String endTime = pref.getString(NewReservActivity.END_TIME, null);

			ServiceAllocable alloc = new ServiceAllocable();
			List<MonoAllocable> monoAllocables = alloc.SearchMonoAllocables(nbPerson, equip, location, date, beginTime, endTime);
			Button launcheSearchForTeacher = (Button) findViewById(R.id.launch_search_teacher);
			ExpandableListView lvMono = (ExpandableListView) findViewById(R.id.listView_mono);
			if((monoAllocables != null) && (monoAllocables.size() > 0)){
				try{
					launcheSearchForTeacher.setVisibility(View.GONE);
					lvMono.setVelocityScale(View.VISIBLE);

					dispatchMonoAllocables(monoAllocables);
					MonoAllocableAdapter adapter = new MonoAllocableAdapter(this, header, children);
					lvMono.setAdapter(adapter);					
				} catch (Exception e){
					e.printStackTrace();
				}
			} else {
				String role = pref.getString(LoginActivity.Role, null);
				if(role.equalsIgnoreCase(UserRole.Teacher.toString())){
					launcheSearchForTeacher.setVisibility(View.VISIBLE);
					lvMono.setVelocityScale(View.GONE);
				} else {
					Toast.makeText(this, "Ancun r�sultat", Toast.LENGTH_LONG).show();
					launcheSearchForTeacher.setVisibility(View.GONE);
					lvMono.setVelocityScale(View.GONE);
					Intent intent = new Intent(this, NewReservActivity.class);
					startActivity(intent);
				}
			}
		}
	}

	/** Called when the user clicks the Reservation button */
	public void openSearchMultiAllocables(View view) {
		TextView tvNumMonoAllocable = (TextView) findViewById(R.id.value_num_mono);
		TextView tvNameMonoAllocable = (TextView) findViewById(R.id.value_name_mono);
		TextView tvLocation = (TextView) findViewById(R.id.value_location);
		int numMonoAllocable = Integer.parseInt(tvNumMonoAllocable.getText().toString());
		String nameMonoAllocable = tvNameMonoAllocable.getText().toString();
		String location = tvLocation.getText().toString();

		Editor editor = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE).edit();
		editor.putInt(NUM_MONO, numMonoAllocable);
		editor.putString(NAME_MONO, nameMonoAllocable);
		editor.putString(NewReservActivity.LOCATION, location);
		editor.commit();

		Intent intent = new Intent(this, MultiAllocableActivity.class);
		startActivity(intent);
	}

	public void openSearchForTeacher(View view){
		Intent intent = new Intent(this, MonoAllocableTeacherActivity.class);
		startActivity(intent);
		
//		Button launcheSearchForTeacher = (Button) findViewById(R.id.launch_search_teacher);
//		launcheSearchForTeacher.setVisibility(View.GONE);
//
//		List<MonoAllocable> monoAllocables = searchMonoAllocables(true);
//		ExpandableListView lvMono = (ExpandableListView) findViewById(R.id.listView_mono);
//		if((monoAllocables != null) && (monoAllocables.size() > 0)){
//			try{
//				lvMono.setVelocityScale(View.VISIBLE);
//				dispatchMonoAllocables(monoAllocables);
//				MonoAllocableAdapter adapter = new MonoAllocableAdapter(this, header, children);
//				lvMono.setAdapter(adapter);					
//			} catch (Exception e){
//				e.printStackTrace();
//			}
//		} else {
//			Toast.makeText(this, "Ancun r�sultat", Toast.LENGTH_LONG).show();
//			launcheSearchForTeacher.setVisibility(View.GONE);
//			lvMono.setVelocityScale(View.GONE);
//			Intent intent = new Intent(this, NewReservActivity.class);
//			startActivity(intent);
//		}
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
					m.getName(),
					m.getLocation().getName(),
					String.valueOf(((BoxType)m.getType()).getNbSeat()),
					ScreenConverter.instance().convertToString(((BoxType)m.getType()).getScreen()),
					m.getDescription());
			ld.add(d);
			children.put(l, ld);
		}
	}
}
