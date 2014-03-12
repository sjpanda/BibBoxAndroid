package com.ta.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibboxandroid.R;

/**
 * @author Jing SHU
 * @date 04/03/2014
 * @copyright TA Copyright
 * @brief La page d'accueil de l'application
 */
public class MainActivity extends Activity {
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}


		// Get the message from the intent
		Intent intent = getIntent();
		String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
		if((message != null) &&(! message.isEmpty())){
			// Create the text view
			TextView textView = new TextView(this);
			textView.setTextSize(40);
			textView.setText(message);

			// Set the text view as the activity layout
			setContentView(textView);
		}

		/* DO NOT DELETE  */
		//		tv = new TextView(this);
		//		Toast.makeText(this.getApplicationContext(),"Hello ! XDDDDDDDDDDDD", Toast.LENGTH_LONG).show();
		//
		//		tv.setText("result of wcf : \n" + ExamplesWCFCall.testWCFCall());
		//		setContentView(tv);
		/* END */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
