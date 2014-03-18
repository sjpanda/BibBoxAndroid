package com.ta.bibbox.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bibboxandroid.R;

public class SelectMultiAllocableAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final List<String> names;
	private final Map<String, Integer> multiAllocables;
	private final int nbPerson;

	public SelectMultiAllocableAdapter(Context context, List<String> names, Map<String, Integer> multiAllocables, int nbPerson) {
		super(context, R.layout.amulti_list, names);
		this.context = context;
		this.names = names;
		this.multiAllocables = multiAllocables;
		this.nbPerson = nbPerson;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.amulti_list, parent, false);
		try{
			TextView name = (TextView) rowView.findViewById(R.id.multi_allocable_name);
			name.setText(names.get(position));

			Spinner count = (Spinner) rowView.findViewById(R.id.multi_allocable_count);
			List<Integer> possibleNum = new ArrayList<Integer>();
			int minNbMultiAllocableDispo = multiAllocables.get(names.get(position)) < nbPerson ? multiAllocables.get(names.get(position)) : nbPerson;
			for(int i=0; i<=minNbMultiAllocableDispo; i++){
				possibleNum.add(i);
			}
			ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, possibleNum);
			count.setAdapter(adapter);
		} catch(Exception e){
			e.printStackTrace();
		}

		return rowView;
	}
}
