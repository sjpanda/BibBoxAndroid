package com.ta.bibbox.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.ta.bibbox.adapter.ReservDetailAdapter;
import com.ta.bibbox.converter.DateNTimeConverter;
import com.ta.bibbox.model.AReservViewModel.ReservDetail;
import com.ta.bibbox.model.AReservViewModel.ReservList;
import com.ta.bibbox.model.MyReservsViewModel;
import com.ta.bibbox.pojo.MultiAllocable;
import com.ta.bibbox.pojo.Reservation;
import com.ta.bibbox.pojo.ReservationState;

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
	public static final String ID_RESERV = "ID_RESERV";
	public static final String CANCEL_BTN = "CANCEL_BTN";

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
					ReservDetailAdapter adapter = new ReservDetailAdapter(getActivity(), header, children);
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
			ReservDetail idReserv = new ReservDetail(ID_RESERV, String.valueOf(r.getId()));
			ReservDetail location = new ReservDetail("Bibliothèque", r.getMonoAllocable().getLocation().getName());
			ReservDetail nameMonoAlloc = new ReservDetail("Nom de box", r.getMonoAllocable().getName());
			ReservDetail beginDate = new ReservDetail("Date de début", DateNTimeConverter.dateToString(r.getBeginDate()));
			ReservDetail endDate = new ReservDetail("Date de fin", DateNTimeConverter.dateToString(r.getEndDate()));
			ReservDetail nbPerson = new ReservDetail("Nombre de personne", String.valueOf(r.getNbUser()));
			ld.add(idReserv);
			ld.add(location);
			ld.add(nameMonoAlloc);
			ld.add(beginDate);
			ld.add(endDate);
			ld.add(nbPerson);

			if(r.getMultiAllocables() != null){
				Map<String, Integer> multiAllocables = new HashMap<String, Integer>();
				for(MultiAllocable m : r.getMultiAllocables()){
					if(multiAllocables.containsKey(m.getName())){
						multiAllocables.put(m.getName(), multiAllocables.get(m.getName()) + 1);
					} else {
						multiAllocables.put(m.getName(), 1);
					}
				}

				for(Entry<String, Integer> entry : multiAllocables.entrySet()){
					ReservDetail multiAlloc = new ReservDetail(entry.getKey(), String.valueOf(entry.getValue()));
					ld.add(multiAlloc);
				}
			}

			if(r.getState() == ReservationState.Waiting){
				ReservDetail toDisplayACancelButton = new ReservDetail(CANCEL_BTN, "");
				ld.add(toDisplayACancelButton);
			}

			children.put(l, ld);
		}
	}
}
