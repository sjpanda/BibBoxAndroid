package com.ta.activity;

import com.example.bibboxandroid.R;
import com.example.bibboxandroid.R.layout;
import com.example.bibboxandroid.R.menu;
import com.ta.pojo.User;
import com.ta.service.ServiceAuthentification;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * @author Jing SHU
 * @date 12/03/2014
 * @copyright TA Copyright
 * @brief La page de login
 */
public class LoginActivity extends BaseActivity {
	public final static String Login = "com.ta.bibbox.Login";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	/** Called when the user clicks the Login button */
	public void login(View view) {
		Intent intent = new Intent(this, MainActivity.class);
	    EditText loginEditText = (EditText) findViewById(R.id.edit_login);
	    EditText passwordEditText = (EditText) findViewById(R.id.edit_password);
	    String login = loginEditText.getText().toString();
	    String password = passwordEditText.getText().toString();
	    ServiceAuthentification auth = new ServiceAuthentification();
		User u = auth.login(login, password);
		if(u != null){
			intent.putExtra(Login, login);
			startActivity(intent);
		}
	}
}
