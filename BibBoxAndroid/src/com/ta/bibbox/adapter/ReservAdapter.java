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

/**
 * @author Jing SHU
 * @date 13/03/2014
 * @copyright TA Copyright
 * @brief L'adaptateur personalisé pour ExpandableListView des réservations
 */
public class ReservAdapter extends BaseExpandableListAdapter {
	private Context context;
    private List<AReservViewModel.ReservList> listDataHeader; 
    private Map<AReservViewModel.ReservList, List<AReservViewModel.ReservDetail>> listDataChild;

    public ReservAdapter(Context context, List<AReservViewModel.ReservList> listDataHeader, 
    		Map<AReservViewModel.ReservList, List<AReservViewModel.ReservDetail>> listDataChild){
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
		System.out.println("didi1");
		final AReservViewModel.ReservDetail child = (AReservViewModel.ReservDetail) getChild(groupPosition, childPosition);
		System.out.println("didi2");
        if (convertView == null) {
        	System.out.println("didi3");
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            System.out.println("didi4");
            convertView = infalInflater.inflate(R.layout.areserv_detail, null);
            System.out.println("didi5");
        }
        System.out.println("didi6");
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.areserv_detail);
        System.out.println("didi7");
        txtListChild.setText(child.getName() + " : " + child.getValue());
        System.out.println("didi8");
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
		 System.out.println("data1");
		 AReservViewModel.ReservList header = (AReservViewModel.ReservList) getGroup(groupPosition);
		 System.out.println("data2");
	        if (convertView == null) {
	        	System.out.println("data3");
	            LayoutInflater infalInflater = (LayoutInflater) this.context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            System.out.println("data4");
	            convertView = infalInflater.inflate(R.layout.areserv_list, null);
	            System.out.println("data5");
	        }
	        System.out.println("data6");
	        TextView listHeader = (TextView) convertView
	                .findViewById(R.id.areserv_list);
	        System.out.println("data7");
	        listHeader.setTypeface(null, Typeface.BOLD);
	        System.out.println("data8");
	        listHeader.setText(header.getMonoAllocableName() + " (" + header.getLcoation() + ")");
	        System.out.println("data9");
	 
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
