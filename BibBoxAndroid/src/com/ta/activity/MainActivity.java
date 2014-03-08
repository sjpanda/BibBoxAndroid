package com.ta.activity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibboxandroid.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.converter.UserConverter;
import com.ta.pojo.User;

/**
 * @author Jing SHU
 * @date 04/03/2014
 * @copyright TA Copyright
 * @brief La page d'accueil de l'application
 */
public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.hellosoap.MESSAGE";

	private static final String METHOD_NAME = "Login";

	private static final String NAMESPACE = "http://tempuri.org/";

	//private static final String URL = "http://localhost:5645/ServiceAuthentification.svc";
	private static final String URL = "http://132.227.69.163:81/ServiceAuthentification.svc";
	//private static final String URL = "http://132.227.69.4:5645/ServiceAuthentification.svc";

	final String SOAP_ACTION = "http://tempuri.org/IServiceAuthentification/Login";

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
		Toast.makeText(this.getApplicationContext(),"Hello ! XDDDDDDDDDDDD",3000).show();
		call();

		tv.setText("result of wcf : \n" + sb.toString());
		setContentView(tv);
	}

	public void call() {
		HttpTransportSE androidHttpTransport = null;
		try {
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			request.addProperty("login", "Basic1");
			request.addProperty("password", "password1");

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.debug = true;
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapObject result = (SoapObject)envelope.getResponse();

			//to get the data
			//String resultData = result.getProperty(0).toString();
			//sb.append(resultData + "\n"); 

			//User u = SoapObjectUtil.soapToPojo(User.class, (SoapObject)((SoapObject)result.getProperty(0)).getProperty(0));  
			
//			ObjectMapper mapper = new ObjectMapper();
//			String key = ((SoapObject)result.getProperty(0)).getProperty(0).toString();
//			String user = key.replace("anyType", "");
//			sb.append("user : " + user + "\n"); 
//			User u = mapper.readValue(user, User.class);
//			
//			if(u != null){
//				sb.append(u.getLogin() + "\n"); 
//			} else {
//				sb.append("Oops\n"); 
//			}
			
			sb.append(result.toString() + "\n"); 
			User u = UserConverter.instance().convertToObject(result);
			if(u != null){
				sb.append("Hello " + u.getLogin() + "\n"); 
			} else {
				sb.append("Oops\n"); 
			}

		} catch (Exception e) {
			sb.append("Error:\n" + e.getClass().getName() + "\n" + e.getMessage() + "\n\nRequest : \n" + androidHttpTransport.requestDump + "\nResponse : \n" + androidHttpTransport.responseDump + "\n");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
