package com.darwindeveloper.onecalendar.domain;

import com.darwindeveloper.onecalendar.model.Day;

public interface OnClickDayListener {
    /**
     * un objeto de tipo day para obtener la fecha (año,mes,dia) con un objeto calendar
     * <p>
     * otros metodos como setBackgroundColor(int backgroundColor) y getBackgroundColor() color del fondo del numero de dia del mes
     * setTextColor(int textColor) y getTextColor() color del texto numero de dia del mes
     *
     * @param day
     */
    void dayOnClick(Day day, int position);

    /**
     * similar a dayOnClick solo que este se ejecuta cuando haya un clic prolongado
     *
     * @param day
     */
    void dayOnLongClik(Day day, int position);
}
