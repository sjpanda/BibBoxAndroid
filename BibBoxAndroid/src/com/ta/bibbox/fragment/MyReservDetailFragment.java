package com.ta.bibbox.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.bibboxandroid.R;
import com.ta.bibbox.activity.MyReservDetailActivity;
import com.ta.bibbox.activity.MyReservListActivity;
import com.ta.bibbox.adapter.ReservAdapter;
import com.ta.bibbox.converter.DateNTimeConverter;
import com.ta.bibbox.model.AReservViewModel.ReservDetail;
import com.ta.bibbox.model.AReservViewModel.ReservList;
import com.ta.bibbox.model.MyReservsViewModel;
import com.ta.bibbox.pojo.Reservation;

/**
 * @author Jing SHU
 * @date 13/03/2014
 * A fragment representing a single MyReserv detail screen. This fragment is
 * either contained in a {@link MyReservListActivity} in two-pane mode (on
 * tablets) or a {@link MyReservDetailActivity} on handsets.
 */
public class MyReservDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The content this fragment is presenting.
	 */
	private MyReservsViewModel.Item mItem;

	private List<ReservList> header;
	private Map<ReservList, List<ReservDetail>> children;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public MyReservDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = MyReservsViewModel.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_myreserv_detail,
				container, false);

		// Show the content as text in a TextView.
		if (mItem != null) {
			TextView head = (TextView) rootView.findViewById(R.id.myreserv_detail_text);
			if((mItem.getReservs() == null) || (mItem.getReservs().size() == 0)){
				head.setText("Aucune réservation dans cette catégorie");
			} else {
				try{
					head.setText("");
					head.setVisibility(View.GONE);
					dispatchReservations(mItem.getReservs());	
					ExpandableListView expListView = ((ExpandableListView) rootView.findViewById(R.id.myreserv_detail_list));
					ReservAdapter adapter = new ReservAdapter(getActivity(), header, children);
					expListView.setAdapter(adapter);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}

		return rootView;
	}

	private void dispatchReservations(List<Reservation> reservs){
		if(reservs == null) { return; }
		header = new ArrayList<ReservList>();
		children = new HashMap<ReservList, List<ReservDetail>>();
		for(Reservation r : reservs){
			ReservList l = new ReservList(r.getMonoAllocable().getLocation().getName(), r.getMonoAllocable().getName());
			header.add(l);
			List<ReservDetail> ld = new ArrayList<ReservDetail>();
			ReservDetail d1 = new ReservDetail("Date de début", DateNTimeConverter.dateToTime(r.getBeginDate()));
			ReservDetail d2 = new ReservDetail("Date de fin", DateNTimeConverter.dateToTime(r.getEndDate()));
			ReservDetail d3 = new ReservDetail("Nombre de personne", DateNTimeConverter.dateToTime(r.getBeginDate()));
			ld.add(d1);
			ld.add(d2);
			ld.add(d3);
			children.put(l, ld);
		}
	}
}
