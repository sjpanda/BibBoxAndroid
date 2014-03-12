package com.ta.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bibboxandroid.R;

/**
 * @author Jing SHU
 * @date 12/03/2014
 * @copyright TA Copyright
 * @brief La page qui permet d'avoir un menu global pour toute application
 */
public class BaseActivity extends Activity {
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_login:
			openLogin();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void openLogin(){
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
}
