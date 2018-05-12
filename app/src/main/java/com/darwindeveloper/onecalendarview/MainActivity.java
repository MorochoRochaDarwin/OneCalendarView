package com.darwindeveloper.onecalendarview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.darwindeveloper.onecalendar.domain.OneCalendarClickListener;
import com.darwindeveloper.onecalendar.model.Day;
import com.darwindeveloper.onecalendar.ui.OneCalendarView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private OneCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (OneCalendarView) findViewById(R.id.oneCalendar);

        //los metodos son obigatorios
        //calendarView.setOnCalendarChangeListener(params)
        //setOneCalendarClickListener(params)

        calendarView.setOneCalendarClickListener(new OneCalendarClickListener() {
            @Override
            public void dateOnClick(Day day, int position) {

            }

            @Override
            public void dateOnLongClick(Day day, int position) {

            }
        });


        calendarView.setOnCalendarChangeListener(new OneCalendarView.OnCalendarChangeListener() {
            @Override
            public void prevMonth() {

            }

            @Override
            public void nextMonth() {

            }
        });

        //initCalendar();


    }


    private void initCalendar() {
        //el siguiente fragmento puede ser usado para capturar los swipes en el calendar
        calendarView.setOnCalendarChangeListener(new OneCalendarView.OnCalendarChangeListener() {

            /**
             * notifica al usuario que el calendario a cambiado al mes anterior
             */
            @Override
            public void prevMonth() {
                Toast.makeText(MainActivity.this, calendarView.getStringMonth(calendarView.getMonth()) + " " + calendarView.getYear(), Toast.LENGTH_SHORT).show();
            }

            /**
             * notifica al usuario que el calendario a cambiado al mes siguiente
             */
            @Override
            public void nextMonth() {
                Toast.makeText(MainActivity.this, calendarView.getStringMonth(calendarView.getMonth()) + " " + calendarView.getYear(), Toast.LENGTH_SHORT).show();
            }
        });


        //el siguiente fragmento de codigo muestra como obtener los datos de un dia en el calendario
        //ademas de realizar otras acciones
        calendarView.setOneCalendarClickListener(new OneCalendarClickListener() {

            /**
             * cuando se da click en un dia en el calendario mostrado
             *
             * @param day      un Objeto de tipo Day del cual podemos llara a su metodo getDate() para recuperar una fecha
             * @param position posicion desde 0-41, que ocupa en el calendario actual
             */
            @Override
            public void dateOnClick(Day day, int position) {
                //recuerde que en java los meses inician desde 0
                Date date = day.getDate();
                int year = date.getYear();
                int month = date.getMonth();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int numDay = cal.get(Calendar.DAY_OF_MONTH);
                Toast.makeText(MainActivity.this, numDay + " " + calendarView.getStringMonth(month) + " " + year, Toast.LENGTH_SHORT).show();

                //el siguiente fragemento de codigo comprueba si un dia en el calendario esta o no seleccionado
                //sientase libre de comentar o descomentar este codigo para observar los cambios
                if (calendarView.isDaySelected(position)) {
                    calendarView.removeDaySeleted(position);
                } else {
                    calendarView.addDaySelected(position);
                }


            }

            /**
             * cuando se da click prolongado en un dia en el calendario mostrado
             *
             * @param day      un Objeto de tipo Day del cual podemos llara a su metodo getDate() para recuperar una fecha
             * @param position posicion desde 0-41, que ocupa en el calendario actual
             */
            @Override
            public void dateOnLongClick(Day day, int position) {

            }
        });
    }
}
