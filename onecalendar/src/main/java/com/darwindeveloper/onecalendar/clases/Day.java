package com.darwindeveloper.onecalendar.clases;

import android.graphics.Color;

import java.util.Date;

/**
 * Created by DARWIN on 1/3/2017.
 */

public class Day {

    private Date date;
    private boolean valid, selected;
    private int textColor = Color.parseColor("#0099cc");
    private int textColorNV = Color.parseColor("#d2d2d2");
    private int backgroundColor = Color.parseColor("#FFF5F5F5");
    private int backgroundColorNV = Color.parseColor("#FFF5F5F5");


    public Day(Date date, int textColor, int backgroundColor) {
        this.date = date;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        valid = true;
    }

    public Day(Date date, boolean valid, int textColorNV, int backgroundColorNV) {
        this.date = date;
        this.valid = valid;
        this.textColorNV = textColorNV;
        this.backgroundColorNV = backgroundColorNV;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColorNV() {
        return textColorNV;
    }

    public void setTextColorNV(int textColorNV) {
        this.textColorNV = textColorNV;
    }

    public int getBackgroundColorNV() {
        return backgroundColorNV;
    }

    public void setBackgroundColorNV(int backgroundColorNV) {
        this.backgroundColorNV = backgroundColorNV;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
