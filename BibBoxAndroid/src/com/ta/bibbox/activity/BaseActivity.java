package com.ta.bibbox.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bibboxandroid.R;

/**
 * @author Jing SHU
 * @date 12/03/2014
 * @copyright TA Copyright
 * @brief La page qui permet d'avoir un menu global pour toute application
 */
public class BaseActivity extends FragmentActivity {
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu){
	    /* afficher ou non "Déconnexion" dans le menu */
		//MenuItem loginItem = menu.findItem(R.id.action_login);
		MenuItem logoutItem = menu.findItem(R.id.action_logout);
		SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);   
		String login = pref.getString(LoginActivity.Login, null);
		if(login == null){
			//loginItem.setVisible(true);
			logoutItem.setVisible(false);
		} else {
			logoutItem.setVisible(true);
			//loginItem.setVisible(false);
		}
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_home:
			openView(MainActivity.class);
			return true;
		case R.id.action_logout:
			logOut();
			return true;
		case R.id.action_new_reserv:
			openView(NewReservActivity.class);
			return true;
		case R.id.action_my_reservs:
			openView(MyReservListActivity.class);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void openView(Class<?> claz){
		Intent intent = new Intent(this, claz);
		startActivity(intent);
	}
	
	private void logOut(){
		getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE).edit()
    		.putString(LoginActivity.Login, null).commit();
		openView(LoginActivity.class);
	}
}
