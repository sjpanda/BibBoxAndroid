package com.ta.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibboxandroid.R;
import com.ta.pojo.Allocable;
import com.ta.pojo.Location;
import com.ta.pojo.MonoAllocable;
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
public class MainActivity extends Activity {
	TextView tv;
	StringBuilder sb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		tv = new TextView(this);
		sb = new StringBuilder();
		Toast.makeText(this.getApplicationContext(),"Hello ! XDDDDDDDDDDDD", Toast.LENGTH_LONG).show();
		
		ServiceAuthentification auth = new ServiceAuthentification();
		User u = auth.login("Basic1", "password1");
		if(u != null){
			sb.append("Hello " + u.getLogin() + "\n"); 
		} else {
			sb.append("Oops\n"); 
		}
		
		ServiceSystemParameter sysParam = new ServiceSystemParameter();
		int reservationMinInterval = sysParam.GetReservationMinInterval();
		sb.append("reservationMinInterval : " + reservationMinInterval + "\n"); 
		int maxReservDays = sysParam.GetMaxReservDays();
		sb.append("maxReservDays : " + maxReservDays + "\n"); 
		
		ServiceAllocable alloc = new ServiceAllocable();
		List<Integer> monoAllocablePossibleNbSeat = alloc.GetMonoAllocablePossibleNbSeat();
		for(int nbSeat : monoAllocablePossibleNbSeat){
			sb.append("nbSeat : " + nbSeat + "\n"); 
		}
		
		List<String> monoAllocableEquips = alloc.GetMonoAllocableEquips();
		for(String equip : monoAllocableEquips){
			sb.append("equip : " + equip + "\n"); 
		}
		
		List<Location> locations = alloc.GetAllLocations();
		for(Location location : locations){
			sb.append("location : " + location.getName() + ", begin reserv time : " + location.getBeginReservTime() + "\n"); 
		}
		
//		boolean inserted = alloc.InsertAllocable("", "Box Android", "", 1, 1);
//		sb.append("Box Android inserted ? " + inserted + "\n"); 
		
//		List<Type> types = alloc.GetAllTypesAllocable();
//		for(Type type : types){
//			sb.append("type : " + type.getClass().getName() + "\n"); 
//		}
		
//		List<Allocable> allocables = alloc.GetAllAllocable();
//		for(Allocable a : allocables){
//			sb.append("allocable : " + a.getName() + "\n"); 
//		}
		
		sb.append("For students : \n");
		List<MonoAllocable> monoAllocables = alloc.SearchMonoAllocables(1, "Indifferent", "Indifferent", "2014-05-12 00:00:00", "10:00:00", "13:00:00");
		for(MonoAllocable m : monoAllocables){
			sb.append("mono allocable : " + m.getName() + "\n");
		}
		
		sb.append("For teachers : \n");
		List<MonoAllocable> monoAllocablesT = alloc.SearchMonoAllocablesForTeacher(1, "Indifferent", "Indifferent", "2014-05-12 00:00:00", "10:00:00", "13:00:00");
		for(MonoAllocable m : monoAllocablesT){
			sb.append("mono allocable : " + m.getName() + "\n");
		}
		
		tv.setText("result of wcf : \n" + sb.toString());
		setContentView(tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
