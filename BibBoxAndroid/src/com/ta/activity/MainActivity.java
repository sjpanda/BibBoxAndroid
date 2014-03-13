package com.ta.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bibboxandroid.R;
import com.ta.converter.DateNTimeConverter;
import com.ta.pojo.User;
import com.ta.service.ServiceAllocable;
import com.ta.service.ServiceAuthentification;
import com.ta.service.ServiceSystemParameter;

/**
 * @author Jing SHU
 * @date 04/03/2014
 * @copyright TA Copyright
 * @brief La page d'accueil de l'application
 */
public class MainActivity extends BaseActivity {
	public final static String NB_PERSON = "com.ta.bibbox.NBPERSON";
	public final static String EQUIP = "com.ta.bibbox.EQUIP";
	public final static String LOCATION = "com.ta.bibbox.LOCATION";
	public final static String DATE = "com.ta.bibbox.DATE";
	public final static String BEGIN_TIME = "com.ta.bibbox.BEGIN_TIME";
	public final static String END_TIME = "com.ta.bibbox.END_TIME";
	public final static long ONE_MINUTE_MILLIS = 60 * 1000;
	
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		fillSpinners();
		
		/* DO NOT DELETE  */
		//		tv = new TextView(this);
		//		Toast.makeText(this.getApplicationContext(),"Hello ! XDDDDDDDDDDDD", Toast.LENGTH_LONG).show();
		//
		//		tv.setText("result of wcf : \n" + ExamplesWCFCall.testWCFCall());
		//		setContentView(tv);
		/* END */
	}
	
	/** Called when the user clicks the Search button */
	public void openSearchMonoAllocables(View view) {
		Intent intent = new Intent(this, MonoAllocableActivity.class);
	    Spinner spnNbPerson = (Spinner) findViewById(R.id.spinner_nbPerson);
	    Spinner spnEquips = (Spinner) findViewById(R.id.spinner_equips);
	    Spinner spnLocation = (Spinner) findViewById(R.id.spinner_location);
	    DatePicker dpDate = (DatePicker) findViewById(R.id.dp_date);
	    Spinner spnBeginTime = (Spinner) findViewById(R.id.spinner_beginTime);
	    Spinner spnEndTime = (Spinner) findViewById(R.id.spinner_endTime);
	    
	    int nbPerson = (Integer)spnNbPerson.getSelectedItem();
	    String equip = spnEquips.getSelectedItem().toString();
	    String location = spnLocation.getSelectedItem().toString();
	    String date = dpDate.getYear() + "-" + dpDate.getMonth() + "-" + dpDate.getDayOfMonth() + " 00:00:00";
	    String beginTime = spnBeginTime.getSelectedItem().toString();
	    String endTime = spnEndTime.getSelectedItem().toString();
	    
		intent.putExtra(NB_PERSON, nbPerson);
		intent.putExtra(EQUIP, equip);
		intent.putExtra(LOCATION, location);
		intent.putExtra(DATE, date);
		intent.putExtra(BEGIN_TIME, beginTime);
		intent.putExtra(END_TIME, endTime);
		
		startActivity(intent);
	}
	
	private void fillSpinners(){
		ServiceAllocable alloc = new ServiceAllocable();
		ServiceSystemParameter sysParam = new ServiceSystemParameter();

		List<Integer> possibleNbSeat = alloc.GetMonoAllocablePossibleNbSeat();
		ManageSpinner<Integer> msInteger = new ManageSpinner<Integer>();
		msInteger.manageSpinner(this, fillList(possibleNbSeat), R.id.spinner_nbPerson);
		
		List<String> equips = new ArrayList<String>();
		equips.add("Indifferent");
		equips.addAll(alloc.GetMonoAllocableEquips());
		ManageSpinner<String> msString = new ManageSpinner<String>();
		msString.manageSpinner(this, equips, R.id.spinner_equips);
		
		List<String> locations = new ArrayList<String>();
		locations.add("Indifferent");
		locations.addAll(alloc.GetAllLocations());
		msString.manageSpinner(this, locations, R.id.spinner_location);
		
		int maxReservDays = sysParam.GetMaxReservDays();
		initDate(maxReservDays);
		
		int quantum = sysParam.GetReservationMinInterval();
		if(quantum < 0){
			// 30 min
			quantum = 30;
		}
        Date beginTime = alloc.GetBeginReservTime();
        Date endTime = alloc.GetEndReservTime();
        
        List<String> beginTimes = reservTimes(beginTime, endTime, quantum, true);
        msString.manageSpinner(this, beginTimes, R.id.spinner_beginTime);
        
        List<String> endTimes = reservTimes(beginTime, endTime, quantum, false);
        msString.manageSpinner(this, endTimes, R.id.spinner_endTime);
	}
	
	private List<Integer> fillList(List<Integer> list){
		List<Integer> result = new ArrayList<Integer>();
		if((list != null) && (! list.isEmpty())){
			int min = list.get(0);
			int max = list.get(0);
			for(int i : list){
				if(i < min){ min = i; }
				if(i > max) { max = i; }
			}
			for(int i = min; i <= max; i++){
				System.out.println("nbperson : " + i);
				result.add(i);
			}
		}
		return result;
	}
	
	private void initDate(int maxReservDays){
		DatePicker dpDate = (DatePicker) findViewById(R.id.dp_date);
		dpDate.setCalendarViewShown(false);
		long now = (new Date()).getTime();
		dpDate.setMinDate(now - 2000);
		if(maxReservDays > 0){
			dpDate.setMaxDate((new Date(now + maxReservDays * 24 * 60 * ONE_MINUTE_MILLIS)).getTime());
		}
	}
	
	private List<String> reservTimes(Date beginTime, Date endTime, int quantum, boolean begin){
		List<String> result = new ArrayList<String>();
		if(begin){
			result.add(DateNTimeConverter.dateToTime(beginTime));
		}
		long quantumInMillis = quantum * ONE_MINUTE_MILLIS;
		Date tmp = new Date(beginTime.getTime() + quantumInMillis);
		while(tmp.before(endTime)){
			result.add(DateNTimeConverter.dateToTime(tmp));
			tmp = new Date(tmp.getTime() + quantumInMillis);
		}
		if(! begin){
			result.add(DateNTimeConverter.dateToTime(endTime));
		}
		return result;
	}
	
	private class ManageSpinner<T>{
		public void manageSpinner(Context context, List<T> contents, int spinnerId){
			ArrayAdapter<T> adapter = new ArrayAdapter<T>(context, android.R.layout.simple_spinner_item, contents);
			Spinner spinner = (Spinner) findViewById(spinnerId);
			spinner.setAdapter(adapter);
		}
	}
}
