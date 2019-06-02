package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuakeAdapter extends ArrayAdapter<Earthquake> {

    public QuakeAdapter(Context context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Earthquake currentQuakeFlavor = getItem(position);

        DecimalFormat formatter = new DecimalFormat("0.0");
        //format()输入为Double类型
        String outputMag = formatter.format(currentQuakeFlavor.getMag());

        TextView mMag = (TextView)listItemView.findViewById(R.id.mag_view);
        mMag.setText(outputMag);

        // 从 TextView 获取背景，该背景是一个 GradientDrawable。
        GradientDrawable magnitudeCircle = (GradientDrawable) mMag.getBackground();

        // 为震级圆圈设置正确的背景颜色。
        int magnitudeColor = getMagnitudeColor(currentQuakeFlavor.getMag());
        // 设置震级圆圈的颜色
        magnitudeCircle.setColor(magnitudeColor);

        String part1Location;
        String part2Location;

        String totalLocation = currentQuakeFlavor.getLocation();
        if(totalLocation.contains("of")){
            String[] parts = totalLocation.split("(?<=of)",2);
            part1Location = parts[0];
            part2Location = parts[1];
        }
        else {
            part1Location = "Near the";
            part2Location = totalLocation;
        }

        TextView mPart1Location = (TextView)listItemView.findViewById(R.id.first_location_view);
        mPart1Location.setText(part1Location);

        TextView mPart2Location = (TextView)listItemView.findViewById(R.id.second_location_view);
        mPart2Location.setText(part2Location);

        Date date = new Date(currentQuakeFlavor.getDate());

        String formattedDate = formatDate(date);

        TextView mDate = (TextView)listItemView.findViewById(R.id.date_view);

        mDate.setText(formattedDate);


        String formattedTime = formatTime(date);

        TextView mTime = (TextView)listItemView.findViewById(R.id.time_view);

        mTime.setText(formattedTime);

        return listItemView;
    }

    public String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return simpleDateFormat.format(date);
    }

    public String formatTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        return simpleDateFormat.format(date);
    }

    private int getMagnitudeColor(double mag){
        int magnitudeColor = 0;
        int magnitudeColorID = (int)Math.floor(mag);
        switch (magnitudeColorID){
            case 0:
                magnitudeColor = R.color.magnitude1;
                break;
            case 1:
                magnitudeColor = R.color.magnitude2;
                break;
            case 2:
                magnitudeColor = R.color.magnitude3;
                break;
            case 3:
                magnitudeColor = R.color.magnitude4;
                break;
            case 4:
                magnitudeColor = R.color.magnitude5;
                break;
            case 5:
                magnitudeColor = R.color.magnitude6;
                break;
            case 6:
                magnitudeColor = R.color.magnitude7;
                break;
            case 7:
                magnitudeColor = R.color.magnitude8;
                break;
            case 8:
                magnitudeColor = R.color.magnitude9;
                break;
            case 9:
                magnitudeColor = R.color.magnitude10plus;
                break;
       }
       //调用 ContextCompat getColor() 以将颜色资源 ID 转换为 实际整数颜色值
        return ContextCompat.getColor(getContext(), magnitudeColor);
    }

}
