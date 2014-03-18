package com.ta.bibbox.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bibboxandroid.R;
import com.ta.bibbox.adapter.SummaryMultiAllocableAdapter;
import com.ta.bibbox.converter.CollectionConverter;
import com.ta.bibbox.service.ServiceReservation;

/**
 * @author Jing SHU
 * @date 18/03/2014
 * @copyright TA Copyright
 * @brief La page contenant le récapitulatif d'une réservation juste avant la validation de l'utilisateur
 */
public class SummaryActivity extends BaseActivity {
	private Map<String, Integer> selectedMultiAllocables = new HashMap<String, Integer>();

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
			
			Intent intent = getIntent();
			selectedMultiAllocables = (HashMap<String, Integer>)intent.getSerializableExtra(MultiAllocableActivity.MULTI_ALLOC);
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
		ValidateReservTask task = new ValidateReservTask(this);
		task.execute((Void) null);
	}

	public class ValidateReservTask extends AsyncTask<Void, Void, Boolean> {	
		Context context;
		
		public ValidateReservTask(Context context){
			this.context = context;
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);  
				String login  = pref.getString(LoginActivity.Login, null);
				int idMonoAllocable = pref.getInt(MonoAllocableActivity.NUM_MONO, 0);
				int nbPerson = pref.getInt(NewReservActivity.NB_PERSON, 0);
				String date = pref.getString(NewReservActivity.DATE, null);
				String beginTime = pref.getString(NewReservActivity.BEGIN_TIME, null);
				String endTime = pref.getString(NewReservActivity.END_TIME, null);
				String multiAllocables = CollectionConverter.instance().mapStringIntToCustomString(selectedMultiAllocables);
				
				ServiceReservation reserv = new ServiceReservation();
				boolean validated = reserv.ValidateReservationString(login, idMonoAllocable, nbPerson, date, beginTime, endTime, multiAllocables);
				return validated;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (success) {
				Toast.makeText(context, "Votre réservation est bien enregistrée", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(context, MyReservListActivity.class);
				context.startActivity(intent);
			} else {
				Toast.makeText(context, "Erreur : votre réservation n'est pas enregistrée", Toast.LENGTH_LONG).show();
			}
		}
	}
}
