package com.darwindeveloper.onecalendar.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.darwindeveloper.onecalendar.R;

public class ViewHolderDay extends RecyclerView.ViewHolder {

    Button dia;

    public ViewHolderDay(View itemView) {
        super(itemView);
        dia = (Button) itemView.findViewById(R.id.textViewDay);
    }
}
