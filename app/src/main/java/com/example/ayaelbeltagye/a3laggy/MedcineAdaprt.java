package com.example.ayaelbeltagye.a3laggy;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MedcineAdaprt extends BaseAdapter {
    ArrayList<medicine_details> list;
    Context context;

    public MedcineAdaprt(Context context, ArrayList<medicine_details> l) {
        this.context = context;
        this.list = l;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public medicine_details getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View ConvertView, ViewGroup Parent) {
        ViewHolder holder = null;
        if (ConvertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ConvertView = layoutInflater.inflate(R.layout.medicine_item, Parent, false);
            holder = new ViewHolder(ConvertView);
            ConvertView.setTag(holder);
        } else {
            holder = (ViewHolder) ConvertView.getTag();
        }
        medicine_details m = getItem(position);
        Picasso.with(context).load(m.getImage()).error(R.mipmap.ic_launcher).into(holder.im);
        holder.tx.setText(m.getName());
        holder.txx.setText(m.getDose());
        holder.txxm.setText(m.getTime());
        return ConvertView;

    }
}

class ViewHolder {
    TextView tx;
    ImageView im;
    TextView txx;
    TextView txxm;


    public ViewHolder(View v) {
        tx = (TextView) v.findViewById(R.id.textView12);

        im = (ImageView) v.findViewById(R.id.imageButton);
        txx = (TextView) v.findViewById(R.id.textView21);
        txxm = (TextView) v.findViewById(R.id.textView);

    }
}



