package com.example.android.anafyp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class dataAdapter extends BaseAdapter {

    private Context mContext;
    private List<Product> mProuctList;

    public dataAdapter(Context mContext, List<Product> mProuctList) {
        this.mContext = mContext;
        this.mProuctList = mProuctList;
    }

    @Override
    public int getCount() {
        return mProuctList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProuctList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =View.inflate(mContext,R.layout.r_layout,null);
        TextView Info=(TextView)v.findViewById(R.id.info);
        TextView Time=(TextView)v.findViewById(R.id.time);
        TextView Speed=(TextView)v.findViewById(R.id.speed);

        Info.setText(mProuctList.get(position).getName());
        Speed.setText(mProuctList.get(position).getDescription());

        v.setTag(mProuctList.get(position).getId());

        return v;
    }
}

