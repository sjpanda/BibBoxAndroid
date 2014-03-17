package com.ta.bibbox.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.bibboxandroid.R;
import com.ta.bibbox.model.AReservViewModel;
import com.ta.bibbox.model.AReservViewModel.ReservDetail;
import com.ta.bibbox.model.AReservViewModel.ReservList;

/**
 * @author Jing SHU
 * @date 13/03/2014
 * @copyright TA Copyright
 * @brief L'adaptateur personalisé pour ExpandableListView des réservations
 */
public class ReservAdapter extends BaseExpandableListAdapter {
	private Context context;
	private List<ReservList> listDataHeader; 
	private Map<ReservList, List<ReservDetail>> listDataChild;

	public ReservAdapter(Context context, List<ReservList> listDataHeader, 
			Map<ReservList, List<ReservDetail>> listDataChild){
		this.context = context;
		this.listDataHeader = listDataHeader;
		this.listDataChild = listDataChild;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.listDataChild.get(this.listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final ReservDetail child = (ReservDetail) getChild(groupPosition, childPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.areserv_detail, null);
		}
		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.areserv_detail);
		txtListChild.setText(child.getName() + " : " + child.getValue());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if(this.listDataChild == null) return 0;
		return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		if(this.listDataHeader == null) return 0;
		return this.listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		AReservViewModel.ReservList header = (AReservViewModel.ReservList) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.areserv_list, null);
		}
		TextView listHeader = (TextView) convertView
				.findViewById(R.id.areserv_list);
		listHeader.setTypeface(null, Typeface.BOLD);
		listHeader.setText(header.getMonoAllocableName() + " (" + header.getLcoation() + ")");

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
