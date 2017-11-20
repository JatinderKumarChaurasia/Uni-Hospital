package com.example.jatinderkumar.spla;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Jatinder Kumar on 31-10-2017.
 */

public class GridImageAdapter extends BaseAdapter{
    Context context;

    public GridImageAdapter(Context context) {
        this.context = context;
    }

    private Integer[] Facilities = {
            R.drawable.book_checkup, R.drawable.consultation,
            R.drawable.family_doctor, R.drawable.emergency,
            R.drawable.specialist, R.drawable.query,
    };


    @Override
    public int getCount() {
        return Facilities.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return Facilities[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null)
        {
            imageView =new ImageView( context);
            imageView.setLayoutParams( new GridView.LayoutParams( 85,85 ) );
            imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
            imageView.setPadding( 40,40,40,40 );

        }
        else
        {
            imageView =(ImageView) convertView;

        }
        imageView.setImageResource(Facilities[position] );
        return  imageView;
    }
}
