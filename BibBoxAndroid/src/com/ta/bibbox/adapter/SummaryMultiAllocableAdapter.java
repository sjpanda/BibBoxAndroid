package com.ta.bibbox.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bibboxandroid.R;

public class SummaryMultiAllocableAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final List<String> names;
	private final Map<String, String> summary;

	public SummaryMultiAllocableAdapter(Context context, List<String> names, Map<String, String> summary) {
		super(context, R.layout.summary_multi_list, names);
		this.context = context;
		this.names = names;
		this.summary = summary;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.summary_multi_list, parent, false);
		try{
			TextView name = (TextView) rowView.findViewById(R.id.summary_name);
			name.setText(names.get(position));

			TextView value = (TextView) rowView.findViewById(R.id.summary_value);
			value.setText(summary.get(names.get(position)));
		} catch(Exception e){
			e.printStackTrace();
		}

		return rowView;
	}
}
