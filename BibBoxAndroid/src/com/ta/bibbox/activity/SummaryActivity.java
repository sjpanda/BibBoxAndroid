package com.ta.bibbox.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bibboxandroid.R;
import com.ta.bibbox.adapter.SummaryMultiAllocableAdapter;

/**
 * @author Jing SHU
 * @date 18/03/2014
 * @copyright TA Copyright
 * @brief La page contenant le récapitulatif d'une réservation juste avant la validation de l'utilisateur
 */
public class SummaryActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onResume() {
		super.onResume();  // Always call the superclass method first

		SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);   
		String login = pref.getString(LoginActivity.Login, null);
		if(login == null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		} else {
			String nameMonoAllocable = pref.getString(MonoAllocableActivity.NAME_MONO, null);
			String location = pref.getString(NewReservActivity.LOCATION, null);
			
			List<String> names = new ArrayList<String>();
			names.add("Nom de box");
			names.add("Bibliothèque");
			
			Map<String, String> summary = new HashMap<String, String>();
			summary.put("Nom de box", nameMonoAllocable);
			summary.put("Bibliothèque", location);
			
			
//			TextView tvNameMono = (TextView)findViewById(R.id.value_name_mono);
//			tvNameMono.setText(nameMonoAllocable);
//			TextView tvLocation = (TextView)findViewById(R.id.value_location);
//			tvLocation.setText(location);
			
			Intent intent = getIntent();
			Map<String, Integer> selectedMultiAllocables = (HashMap<String, Integer>)intent.getSerializableExtra(MultiAllocableActivity.MULTI_ALLOC);
			if(selectedMultiAllocables != null){
				for(Entry<String, Integer> entry : selectedMultiAllocables.entrySet()){
					names.add(entry.getKey());
					summary.put(entry.getKey(), String.valueOf(entry.getValue()));
				}
			}
			
			ListView lvSummary = (ListView)findViewById(R.id.summary);
			lvSummary.setAdapter(new SummaryMultiAllocableAdapter(this, names, summary));
		}
	}

	public void validateReservation(View view){

	}

}
