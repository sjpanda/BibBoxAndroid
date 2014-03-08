package com.ta.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibboxandroid.R;
import com.ta.pojo.User;
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
