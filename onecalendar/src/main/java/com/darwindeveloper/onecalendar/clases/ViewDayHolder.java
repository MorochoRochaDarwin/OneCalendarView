package com.darwindeveloper.onecalendar.clases;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.darwindeveloper.onecalendar.R;

public class ViewDayHolder extends RecyclerView.ViewHolder {

    Button dia;

    public ViewDayHolder(View itemView) {
        super(itemView);
        dia = (Button) itemView.findViewById(R.id.textViewDay);
    }
}
