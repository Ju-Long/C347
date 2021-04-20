package com.myapplicationdev.android.demolistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {
    private ArrayList<Food> food;
    private Context context;
    private TextView tvFoodName;
    private ImageView ivStar;

    public FoodAdapter(Context context, int resources, ArrayList<Food> objects) {
        super(context, resources, objects);
        food = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvFoodName = (TextView) rowView.findViewById(R.id.tvFoodName);
        ivStar = (ImageView) rowView.findViewById(R.id.ivStar);

        Food currentFood = food.get(position);
        tvFoodName.setText(currentFood.getName());

        if (currentFood.isStar())
            ivStar.setImageResource(R.drawable.star);
        else
            ivStar.setImageResource(R.drawable.nostar);

        return rowView;
    }
}

