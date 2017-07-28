package com.tops.sqlitedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Android on 03/04/2017.
 */

public class Adpt extends BaseAdapter {

    ArrayList<StudBean> arrayList;
    Context context;

    public Adpt(Context context, ArrayList<StudBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.dispaly_layout, null);

        TextView txtId = (TextView) convertView.findViewById(R.id.txtId);
        TextView txtFName = (TextView) convertView.findViewById(R.id.txtFName);
        TextView txtLName = (TextView) convertView.findViewById(R.id.txtLName);

        StudBean studBean = arrayList.get(position);
        txtId.setText("ID = " + studBean.getId());
        txtFName.setText("First Name = " + studBean.getfName());
        txtLName.setText("Last Name = " + studBean.getlName());

        return convertView;
    }
}
