package com.darwindeveloper.onecalendar.clases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darwindeveloper.onecalendar.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by DARWIN on 1/3/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<ViewDayHolder> {

    private Context context;
    private ArrayList<Day> dias;
    private int textColorSelectedDay, backgroundColorSelectedDay;

    public CalendarAdapter(Context context, ArrayList<Day> dias, int textColorSelectedDay, int backgroundColorSelectedDay) {
        this.context = context;
        this.dias = dias;
        this.textColorSelectedDay = textColorSelectedDay;
        this.backgroundColorSelectedDay = backgroundColorSelectedDay;
    }

    @NonNull
    @Override
    public ViewDayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false);
        return new ViewDayHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewDayHolder holder, int positionAdapter) {
        final int position = positionAdapter;

        final Day dia = dias.get(position);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dia.getDate());
        int nday = cal.get(Calendar.DAY_OF_MONTH);
        holder.dia.setText(String.format("%d", nday));

        if (dia.isSelected()) {
            holder.dia.setTextColor(textColorSelectedDay);
            holder.itemView.setBackgroundColor(backgroundColorSelectedDay);
        } else if (dia.isValid()) {
            holder.dia.setTextColor(dia.getTextColor());
            holder.itemView.setBackgroundColor(dia.getBackgroundColor());
        } else {
            holder.dia.setTextColor(dia.getTextColorNV());
            holder.itemView.setBackgroundColor(dia.getBackgroundColorNV());
        }

        holder.dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dayOnClickListener.dayOnClick(dia, position);
            }
        });


        holder.dia.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dayOnClickListener.dayOnLongClik(dia, position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dias.size();
    }


    private DayOnClickListener dayOnClickListener;

    public void setDayOnClickListener(DayOnClickListener dayOnClickListener) {
        this.dayOnClickListener = dayOnClickListener;
    }

}
