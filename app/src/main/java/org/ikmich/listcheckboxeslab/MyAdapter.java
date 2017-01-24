package org.ikmich.listcheckboxeslab;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ikmich on 1/24/17.
 */
public class MyAdapter extends ArrayAdapter<String> implements CompoundButton.OnCheckedChangeListener {

    private List<String> data = new LinkedList<>();
    private int resource;
    private boolean selectAll = false;
    private SparseBooleanArray checkedStates;

    public MyAdapter(Context context, int resource, List<String> data, boolean selectAll) {
        super(context, resource, data);
        this.resource = resource;
        this.data = data;
        this.selectAll = selectAll;

        checkedStates = new SparseBooleanArray(this.data.size());
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(this.resource, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.cb = (CheckBox) convertView.findViewById(R.id.chk);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.label);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cb.setTag(position);
        viewHolder.cb.setChecked(this.checkedStates.get(position));
        viewHolder.cb.setOnCheckedChangeListener(this);

        viewHolder.tv.setText(getItem(position));

        return convertView;
    }

    public void selectAll() {
        for (int i = 0; i < data.size(); i++) {
            checkedStates.put(i, true);
        }
        notifyDataSetChanged();
    }

    public void unselectAll() {
        for (int i = 0; i < data.size(); i++) {
            checkedStates.put(i, false);
        }
        notifyDataSetChanged();
    }

    public void setChecked(int position, boolean isChecked) {
        checkedStates.put(position, isChecked);
    }

    public boolean isChecked(int position) {
        return checkedStates.get(position);
    }

    public List<String> getSelected() {
        List<String> items = new LinkedList<>();
        for (int i = 0; i < data.size(); i++) {
            if (isChecked(i)) {
                items.add(data.get(i));
            }
        }
        return items;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int pos = (Integer) compoundButton.getTag();
        setChecked(pos, b);
    }

    class ViewHolder {
        CheckBox cb;
        TextView tv;
    }
}
