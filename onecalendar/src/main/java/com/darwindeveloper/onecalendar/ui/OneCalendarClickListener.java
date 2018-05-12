package com.darwindeveloper.onecalendar.ui;

import com.darwindeveloper.onecalendar.model.Day;

public interface OneCalendarClickListener {
    /**
     * cuando se da click en un dia en el calendario mostrado
     *
     * @param day      un Objeto de tipo Day del cual podemos llara a su metodo getDate() para recuperar una fecha
     * @param position posicion desde 0-41, que ocupa en el calendario actual
     */
    void dateOnClick(Day day, int position);

    /**
     * cuando se da click prolongado en un dia en el calendario mostrado
     *
     * @param day      un Objeto de tipo Day del cual podemos llara a su metodo getDate() para recuperar una fecha
     * @param position posicion desde 0-41, que ocupa en el calendario actual
     */
    void dateOnLongClick(Day day, int position);
}
