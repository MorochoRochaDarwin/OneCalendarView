package com.darwindeveloper.onecalendar.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darwindeveloper.onecalendar.R;
import com.darwindeveloper.onecalendar.clases.Day;
import com.darwindeveloper.onecalendar.fragments.MonthFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by DARWIN on 3/3/2017.
 */

public class OneCalendarView extends LinearLayout {

    AppCompatActivity mActivity;
    MonthFragment fragment;

    private LinearLayout mainContent;
    private OneFrameLayout fragment_container;

    private ImageButton buttonUp, buttonDown;
    private TextView textViewMY;
    private TextView textViewD, textViewL, textViewM, textViewX, textViewJ, textViewV, textViewS;

    public static final int SPANISH = 0, ENGLISH = 1;//si el calendario estara en español o ingles


    private int iday, imonth, iyear, month, year;
    //meses por defecto en español
    private String enero = "Enero";
    private String febrero = "Febrero";
    private String marzo = "Marzo";
    private String abril = "Abril";
    private String mayo = "Mayo";
    private String junio = "Junio";
    private String julio = "Julio";
    private String agosto = "Agosto";
    private String septiembre = "Septiembre";
    private String octubre = "Octubre";
    private String noviembre = "Noviembre";
    private String diciembre = "Deciembre";


    //para el estilo del calendar
    int mainBackgroundColor = Color.parseColor("#0239a9");
    int calendarBackgroundColor = Color.parseColor("#FFF5F5F5");
    int currentDayBackgroundColor = Color.parseColor("#0099cc");
    int backgroundColorDaysOfMonth = Color.parseColor("#FFF5F5F5");
    int backgroundColorDaysOfAnotherMonth = Color.parseColor("#FFF5F5F5");
    int textColorDaysOfMonth = Color.parseColor("#0099cc");
    int textColorDaysOfAnotherMonth = Color.parseColor("#d2d2d2");
    int textColorMonthAndYear = Color.parseColor("#0099cc");
    int textColorSelectedDay = Color.parseColor("#000000");
    int textColorCurrentDayDay = Color.parseColor("#000000");
    int backgroundColorSelectedDay = Color.parseColor("#d2d2d2");
    int calendarLanguage = 0;

    public OneCalendarView(Context context) {
        super(context);
        mActivity = (AppCompatActivity) context;
    }

    public OneCalendarView(Context context, AttributeSet attrs) throws RuntimeException {
        super(context, attrs);
        mActivity = (AppCompatActivity) context;


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OneCalendarView, 0, 0);
        try {
            mainBackgroundColor = Color.parseColor(a.getString(R.styleable.OneCalendarView_mainBackgroundColor));
        } catch (NullPointerException e) {
        }

        try {
            calendarBackgroundColor = Color.parseColor(a.getString(R.styleable.OneCalendarView_calendarBackgroundColor));
        } catch (NullPointerException e) {
        }

        try {
            currentDayBackgroundColor = Color.parseColor(a.getString(R.styleable.OneCalendarView_currentDayBackgroundColor));
        } catch (NullPointerException e) {
        }

        try {
            backgroundColorDaysOfMonth = Color.parseColor(a.getString(R.styleable.OneCalendarView_backgroundColorDaysOfMonth));
        } catch (NullPointerException e) {
        }

        try {
            backgroundColorDaysOfAnotherMonth = Color.parseColor(a.getString(R.styleable.OneCalendarView_backgroundColorDaysOfAnotherMonth));
        } catch (NullPointerException e) {
        }

        try {
            textColorDaysOfMonth = Color.parseColor(a.getString(R.styleable.OneCalendarView_textColorDaysOfMonth));
        } catch (NullPointerException e) {
        }

        try {
            textColorDaysOfAnotherMonth = Color.parseColor(a.getString(R.styleable.OneCalendarView_textColorDaysOfAnotherMonth));
        } catch (NullPointerException e) {
        }

        try {
            textColorMonthAndYear = Color.parseColor(a.getString(R.styleable.OneCalendarView_textColorMonthAndYear));
        } catch (NullPointerException e) {
        }

        try {
            textColorSelectedDay = Color.parseColor(a.getString(R.styleable.OneCalendarView_textColorSelectedDay));
        } catch (NullPointerException e) {
        }

        try {
            textColorCurrentDayDay = Color.parseColor(a.getString(R.styleable.OneCalendarView_textColorCurrentDayDay));
        } catch (NullPointerException e) {
        }

        try {
            backgroundColorSelectedDay = Color.parseColor(a.getString(R.styleable.OneCalendarView_backgroundColorSelectedDay));
        } catch (NullPointerException e) {
        }

        calendarLanguage = a.getInt(R.styleable.OneCalendarView_calendarLanguage, 0);

        a.recycle();


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.one_calendar_view, this, true);

    }


    private void init() {
        imonth = getCurrentMonth();
        iyear = getCurrentYear();
        iday = getCurrentDayMonth();

        month = imonth;
        year = iyear;

        //inicializamos las vistas
        mainContent = (LinearLayout) findViewById(R.id.mainContent);
        fragment_container = (OneFrameLayout) findViewById(R.id.fragment_container);
        buttonDown = (ImageButton) findViewById(R.id.imageButtonDown);
        buttonUp = (ImageButton) findViewById(R.id.imageButtonUp);
        textViewMY = (TextView) findViewById(R.id.textViewMY);

        textViewD = (TextView) findViewById(R.id.textViewD);
        textViewL = (TextView) findViewById(R.id.textViewL);
        textViewM = (TextView) findViewById(R.id.textViewM);
        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewJ = (TextView) findViewById(R.id.textViewJ);
        textViewV = (TextView) findViewById(R.id.textViewV);
        textViewS = (TextView) findViewById(R.id.textViewS);


        textViewMY.setTextColor(textColorMonthAndYear);

        setLanguage(calendarLanguage);


        fragment_container.setOnSwipeListener(new OneFrameLayout.OnSwipeListener() {

            @Override
            public void rightSwipe() {
                prevMonth();
                onCalendarChangeListener.prevMonth();
            }

            @Override
            public void leftSwipe() {
                nextMoth();
                onCalendarChangeListener.nextMonth();
            }
        });


        buttonDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                prevMonth();
                onCalendarChangeListener.prevMonth();
            }
        });

        buttonUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                nextMoth();
                onCalendarChangeListener.nextMonth();
            }
        });

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();


        mainContent.setBackgroundColor(mainBackgroundColor);
        fragment_container.setBackgroundColor(calendarBackgroundColor);


        showMonth(month, year);


    }


    private void showMonth(final int month, int year) {

        textViewMY.setText(getStringMonth(month) + " " + year);

        fragment = new MonthFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(MonthFragment.YEAR, year);
        bundle.putInt(MonthFragment.MONTH, month);
        bundle.putInt(MonthFragment.TCDAYS, textColorDaysOfMonth);
        bundle.putInt(MonthFragment.BCCDay, currentDayBackgroundColor);
        bundle.putInt(MonthFragment.BCDays, backgroundColorDaysOfMonth);
        bundle.putInt(MonthFragment.BCDaysNV, backgroundColorDaysOfAnotherMonth);
        bundle.putInt(MonthFragment.TCDAYSNV, textColorDaysOfAnotherMonth);
        bundle.putInt(MonthFragment.TCSDAY, textColorSelectedDay);
        bundle.putInt(MonthFragment.TCSDAY, textColorCurrentDayDay);
        bundle.putInt(MonthFragment.BCSDAY, backgroundColorSelectedDay);
        fragment.setArguments(bundle);


        fragment.setOnDayClickListener(new MonthFragment.OnDayClickListener() {

            /**
             * un objeto de tipo day para obtener la fecha (año,mes,dia) con un objeto calendar
             * <p>
             * otros metodos como setBackgroundColor(int backgroundColor) y getBackgroundColor() color del fondo del numero de dia del mes
             * setTextColor(int textColor) y getTextColor() color del texto numero de dia del mes
             *
             * @param day
             * @param position
             */
            @Override
            public void dayOnClick(Day day, int position) {
                oneCalendarClickListener.dateOnClick(day, position);
            }

            /**
             * similar a dayOnClick solo que este se ejecuta cuando haya un clic prolongado
             *
             * @param day
             * @param position
             */
            @Override
            public void dayOnLongClik(Day day, int position) {
                oneCalendarClickListener.dateOnLongClick(day, position);
            }
        });


        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, fragment);
        //transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();

    }


    //MIS METODOS


    /**
     * este metodo configura el calendar a un mes y un año especifico
     *
     * @param month mes para el calendar
     * @param year  año del mes
     */
    public void setMonthYear(int month, int year) {
        showMonth(month, year);
    }


    /**
     * permite cambiar el lenguaje de como se visualizan los meses y dias del calendario
     *
     * @param language SPANISH=0, ENGLISH=1;
     */
    public void setLanguage(int language) {
        if (language == 1) {//si el idioma es el ingles
            textViewL.setText("M");
            textViewM.setText("T");
            textViewX.setText("W");
            textViewJ.setText("T");
            textViewV.setText("F");
            textViewS.setText("S");
            textViewD.setText("S");

            enero = "January";
            febrero = "February";
            marzo = "March";
            abril = "April";
            mayo = "May";
            junio = "June";
            julio = "July";
            agosto = "August";
            septiembre = "September";
            octubre = "October";
            noviembre = "November";
            diciembre = "December";

        } if (language == 2) {//si el idioma es el ingles
        textViewL.setText("S");
        textViewM.setText("T");
        textViewX.setText("Q");
        textViewJ.setText("Q");
        textViewV.setText("S");
        textViewS.setText("S");
        textViewD.setText("D");

        enero = "Janeiro";
        febrero = "Fevereiro";
        marzo = "Março";
        abril = "Abril";
        mayo = "Maio";
        junio = "Junho";
        julio = "Julho";
        agosto = "Agosto";
        septiembre = "Setembro";
        octubre = "Outubro";
        noviembre = "Novembro";
        diciembre = "Dezembro";

    } else {

            textViewL.setText("L");
            textViewM.setText("M");
            textViewX.setText("X");
            textViewJ.setText("J");
            textViewV.setText("V");
            textViewS.setText("S");
            textViewD.setText("D");

            enero = "Enero";
            febrero = "Febrero";
            marzo = "Marzo";
            abril = "Abril";
            mayo = "Mayo";
            junio = "Junio";
            julio = "Julio";
            agosto = "Agosto";
            septiembre = "Septiembre";
            octubre = "Octubre";
            noviembre = "Noviembre";
            diciembre = "Deciembre";
        }

        textViewMY.setText(getStringMonth(month) + " " + year);


    }

    /**
     * retorna un mes como un string de pendiendo del idioma establecido en el OneCalendar
     *
     * @param numMonth numero del mes iniciando desde 0,1,2...
     * @return mes en texto segun el idioma elegido
     */
    public String getStringMonth(int numMonth) {
        switch (numMonth) {
            case 0:
                return enero;

            case 1:
                return febrero;

            case 2:
                return marzo;

            case 3:
                return abril;

            case 4:
                return mayo;

            case 5:
                return junio;

            case 6:
                return julio;

            case 7:
                return agosto;

            case 8:
                return septiembre;

            case 9:
                return octubre;

            case 10:
                return noviembre;

            case 11:
                return diciembre;

        }
        return enero;
    }


    /**
     * retorna el mes actual iniciando desde 0=enero
     *
     * @return
     */
    public int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }


    /**
     * retorna el año actual
     *
     * @return
     */
    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * retorna el dia del mes actual
     *
     * @return
     */
    public int getCurrentDayMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * calcula el numero de dias que tiene un mes de una año especifico
     *
     * @param year
     * @param month
     * @return
     */
    public int getNumberOfDaysMonthYear(int year, int month) {
        Calendar mycal = new GregorianCalendar(year, month, 1);
        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    /**
     * nos retorna el nombre de un dia especifico de una año (en ingles o español segun la configuracion)
     *
     * @param day
     * @param month
     * @param year
     * @return nombre del dia
     */
    public String getNameDay(int day, int month, int year) {
        Date date1 = (new GregorianCalendar(year, month, day)).getTime();
        // Then get the day of week from the Date based on specific locale.
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date1);
    }

    private void nextMoth() {
        if (month == 11) {
            month = 0;
            year++;
        } else {
            month++;
        }
        showMonth(month, year);
    }

    private void prevMonth() {
        if (month == 0) {
            month = 11;
            year--;
        } else {
            month--;
        }

        showMonth(month, year);
    }


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


    private OnCalendarChangeListener onCalendarChangeListener;

    public void setOnCalendarChangeListener(OnCalendarChangeListener onCalendarChangeListener) {
        this.onCalendarChangeListener = onCalendarChangeListener;
    }

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

    private OneCalendarClickListener oneCalendarClickListener;

    public void setOneCalendarClickListener(OneCalendarClickListener oneCalendarClickListener) {
        this.oneCalendarClickListener = oneCalendarClickListener;
    }

    /**
     * retorna el mes visible en el calendario
     * @return
     */
    public int getMonth() {
        return month;
    }

    /**
     * retorna el año del mes visible en el calendario
     * @return
     */
    public int getYear() {
        return year;
    }


    /**
     * este metodo pinta un dia en el calendario actual como seleccionado
     *
     * @param position posicion (0-41) que ocupa en el calendario el dia seleccionado
     */
    public void addDaySelected(int position) {
        fragment.addItemSelected(position);
    }

    /**
     * este metodo remueve o despinta el seleccionado de un dia en el calendario actual
     *
     * @param position posicion (0-41) que ocupa en el calendario el dia seleccionado
     */
    public void removeDaySeleted(int position) {
        fragment.removeItemSelected(position);
    }


    /**
     * comprueba si un dia en el calendario actual esta seleccionado
     *
     * @param position posicion (0-41) que ocupa en el calendario el dia seleccionado
     * @return true o false
     */
    public boolean isDaySelected(int position) {
        return fragment.getDays().get(position).isSelected();
    }


}
