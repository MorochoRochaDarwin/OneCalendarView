package com.darwindeveloper.onecalendar.domain;

public interface OnCalendarChangeListener {
    /**
     * notifica al usuario que el calendario a cambiado al mes anterior
     */
    void prevMonth();

    /**
     * notifica al usuario que el calendario a cambiado al mes siguiente
     */
    void nextMonth();
}
