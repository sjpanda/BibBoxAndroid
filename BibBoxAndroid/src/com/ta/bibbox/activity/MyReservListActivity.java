package com.ta.bibbox.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibboxandroid.R;
import com.ta.bibbox.fragment.MyReservDetailFragment;
import com.ta.bibbox.fragment.MyReservListFragment;
import com.ta.bibbox.model.MyReservsViewModel;
import com.ta.bibbox.pojo.Reservation;
import com.ta.bibbox.pojo.ReservationState;
import com.ta.bibbox.pojo.UserRole;
import com.ta.bibbox.service.ServiceReservation;

/**
 * @author Jing SHU
 * @date 13/03/2014
 * An activity representing a list of MyReservs. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link MyReservDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MyReservListFragment} and the item details (if present) is a
 * {@link MyReservDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link MyReservListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class MyReservListActivity extends BaseActivity implements
MyReservListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_myreserv_list);

		if (findViewById(R.id.myreserv_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((MyReservListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.myreserv_list))
					.setActivateOnItemClick(true);
		}
	}

	@Override
	public void onResume() {
		super.onResume();  // Always call the superclass method first

		SharedPreferences pref = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);   
		String login = pref.getString(LoginActivity.Login, null);
		if(login == null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		} else {
			String role = pref.getString(LoginActivity.Role, null);
			if((! role.equalsIgnoreCase(UserRole.Basic.toString()))
					&& (! role.equalsIgnoreCase(UserRole.Teacher.toString()))){
				Toast.makeText(this, "Vous n'avez pas acc�s � cette fonctionnalit�", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				return;
			}
			
			ServiceReservation reserv = new ServiceReservation();
			List<Reservation> reservations = reserv.GetAllReservationsByUser(login);
			MyReservsViewModel.addItems(reservations);
		}
	}

	public void cancelReservation(View view){
		CancelReservTask task = new CancelReservTask(this);
		task.execute((Void) null);
	}

	/**
	 * Callback method from {@link MyReservListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(MyReservDetailFragment.ARG_ITEM_ID, id);
			MyReservDetailFragment fragment = new MyReservDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.myreserv_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, MyReservDetailActivity.class);
			detailIntent.putExtra(MyReservDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
	
	public class CancelReservTask extends AsyncTask<Void, Void, Boolean> {	
		Context context;
		
		public CancelReservTask(Context context){
			this.context = context;
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				TextView tvIdReserv = (TextView)findViewById(R.id.areserv_detail_id);
				int idReserv = Integer.parseInt(tvIdReserv.getText().toString());
				
				ServiceReservation reserv = new ServiceReservation();
				Reservation canceledReserv = reserv.TerminateReservationString(idReserv, ReservationState.Canceled);
				return (canceledReserv != null);
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (success) {
				Toast.makeText(context, "Votre r�servation est bien annul�e", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(context, MyReservListActivity.class);
				context.startActivity(intent);
			} else {
				Toast.makeText(context, "Erreur : votre r�servation n'est pas annul�e", Toast.LENGTH_LONG).show();
			}
		}
	}
}
