package com.ta.bibbox.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bibboxandroid.R;
import com.ta.bibbox.adapter.MultiAllocableAdapter;
import com.ta.bibbox.service.ServiceAllocable;

/**
 * @author Jing SHU
 * @date 17/03/2014
 * @copyright TA Copyright
 * @brief La page contenant le résultat de recherche des multi-allouables
 */
public class MultiAllocableActivity extends BaseActivity  {
	private Map<String, Integer> multiAllocables;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_allocable);
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
			GetMultiAllocablesTask task = new GetMultiAllocablesTask(this);
			task.execute((Void) null);
		}
	}
	
	public void openSummaryWithoutMultiAllocables(View view){
		Intent intent = new Intent(this, SummaryActivity.class);
		startActivity(intent);
	}
	
	public void openSummary(View view){
		Intent intent = new Intent(this, SummaryActivity.class);
		startActivity(intent);
	}

	/**
	 * Represents an asynchronous task used to get available multi-allocables for a date and time specified.
	 */
	public class GetMultiAllocablesTask extends AsyncTask<Void, Void, Boolean> {
		private Context context;
		
		public GetMultiAllocablesTask(Context context){
			super();
			this.context = context;
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);  
				int numMonoAllocable = pref.getInt(MonoAllocableActivity.NUM_MONO, 0);
				String date = pref.getString(NewReservActivity.DATE, null);
				String beginTime = pref.getString(NewReservActivity.BEGIN_TIME, null);
				String endTime = pref.getString(NewReservActivity.END_TIME, null);

				ServiceAllocable alloc = new ServiceAllocable();
				multiAllocables = alloc.GetMultiAllocablesByMonoAllocable(numMonoAllocable, date, beginTime, endTime);
				return (multiAllocables != null);
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (success) {
				SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);
				int nbPerson = pref.getInt(NewReservActivity.NB_PERSON, 0);
				if((multiAllocables != null) && (multiAllocables.size() > 0)){
					TextView message = (TextView)findViewById(R.id.message);
					message.setText("");
					List<String> names = new ArrayList<String>();
					for(String n : multiAllocables.keySet()){
						names.add(n);
					}
					ListView multiAllocableListe = (ListView)findViewById(R.id.multi_allocable_liste);
					multiAllocableListe.setAdapter(new MultiAllocableAdapter(context, names, multiAllocables, nbPerson));
				} else {
					TextView message = (TextView)findViewById(R.id.message);
					message.setText("Pas de matériel disponible");
				}
			} else {
				TextView message = (TextView)findViewById(R.id.message);
				message.setText("Pas de matériel disponible");
			}
		}
	}
}
