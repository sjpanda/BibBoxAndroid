package com.ta.activity;

import com.example.bibboxandroid.R;
import com.example.bibboxandroid.R.layout;
import com.example.bibboxandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

/**
 * @author Jing SHU
 * @date 04/03/2014
 * @copyright TA Copyright
 * @brief La page qui permet d'annuler une réservation
 */
public class CancelReservActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);   
		String login = pref.getString(LoginActivity.Login, null);
		if(login == null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		} else {
			setContentView(R.layout.activity_cancel_reserv);
		}
	}

}
