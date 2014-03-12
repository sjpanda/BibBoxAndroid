package com.ta.activity;

import com.example.bibboxandroid.R;
import com.example.bibboxandroid.R.layout;
import com.example.bibboxandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
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
		setContentView(R.layout.activity_cancel_reserv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cancel_reserv, menu);
		return true;
	}

}
