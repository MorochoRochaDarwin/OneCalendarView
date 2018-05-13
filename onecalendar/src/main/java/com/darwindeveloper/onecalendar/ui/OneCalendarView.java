package com.darwindeveloper.onecalendar.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darwindeveloper.onecalendar.R;
import com.darwindeveloper.onecalendar.domain.OnCalendarChangeListener;
import com.darwindeveloper.onecalendar.domain.OneCalendarClickListener;
import com.darwindeveloper.onecalendar.fragments.MonthFragment;
import com.darwindeveloper.onecalendar.model.Day;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by DARWIN on 3/3/2017.
 */

public class OneCalendarView extends LinearLayout {

    private static final Calendar calendarForComputeData = Calendar.getInstance(Locale.getDefault());
    private static final DateFormatSymbols dfs = new DateFormatSymbols();
    private static final String[] months = dfs.getShortMonths();

    AppCompatActivity mActivity;
    MonthFragment fragment;

    // Colors
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

    // Views
    private LinearLayout linearLayout;
    private OneFrameLayout oneFrameLayout;
    private TextView textViewMY;
    private TextView textViewD;
    private TextView textViewL;
    private TextView textViewM;
    private TextView textViewX;
    private TextView textViewJ;
    private TextView textViewV;
    private TextView textViewS;

    private int monthVisibleOnCalendar;
    private int yearVisibleOnCalendar;

    // Listeners
    private OnCalendarChangeListener onCalendarChangeListener;
    private OneCalendarClickListener oneCalendarClickListener;

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

        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ui_one_calendar_view, this, true);
    }

    private void init() {
        yearVisibleOnCalendar = getCurrentYear();
        monthVisibleOnCalendar = getCurrentMonth();

        //inicializamos las vistas
        linearLayout = findViewById(R.id.mainContent);
        oneFrameLayout = findViewById(R.id.fragment_container);
        textViewMY = findViewById(R.id.textViewMY);

        textViewD = findViewById(R.id.textViewD);
        textViewL = findViewById(R.id.textViewL);
        textViewM = findViewById(R.id.textViewM);
        textViewX = findViewById(R.id.textViewX);
        textViewJ = findViewById(R.id.textViewJ);
        textViewV = findViewById(R.id.textViewV);
        textViewS = findViewById(R.id.textViewS);

        textViewMY.setTextColor(textColorMonthAndYear);

        setLanguage();

        oneFrameLayout.setOnSwipeListener(new OneFrameLayout.OnSwipeListener() {
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


        (findViewById(R.id.imageButtonDown)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                prevMonth();
                onCalendarChangeListener.prevMonth();
            }
        });

        (findViewById(R.id.imageButtonUp)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                nextMoth();
                onCalendarChangeListener.nextMonth();
            }
        });

    }


    //MIS METODOS
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();

        linearLayout.setBackgroundColor(mainBackgroundColor);
        oneFrameLayout.setBackgroundColor(calendarBackgroundColor);

        showMonth();
    }

    private void showMonth() {
        showMonth(monthVisibleOnCalendar, yearVisibleOnCalendar);
    }

    @SuppressLint("DefaultLocale")
    private void showMonth(final int month, int year) {
        textViewMY.setText(String.format("%s %d", getStringMonth(month), year));

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

        // Replace whatever is in the oneFrameLayout view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, fragment);
        //transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    /**
     * este metodo configura el calendar a un mes y un año especifico
     *
     * @param month mes para el calendar
     * @param year  año del mes
     */
    public void setMonthYear(int month, int year) {
        showMonth(month, year);
    }

    @SuppressLint("DefaultLocale")
    public void setLanguage() {
        textViewL.setText(getInitialWeekday(Calendar.MONDAY));
        textViewM.setText(getInitialWeekday(Calendar.TUESDAY));
        textViewX.setText(getInitialWeekday(Calendar.WEDNESDAY));
        textViewJ.setText(getInitialWeekday(Calendar.THURSDAY));
        textViewV.setText(getInitialWeekday(Calendar.FRIDAY));
        textViewS.setText(getInitialWeekday(Calendar.SATURDAY));
        textViewD.setText(getInitialWeekday(Calendar.SUNDAY));
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
        if (monthVisibleOnCalendar == 11) {
            monthVisibleOnCalendar = 0;
            yearVisibleOnCalendar++;
        } else {
            monthVisibleOnCalendar++;
        }
        showMonth();
    }

    private void prevMonth() {
        if (monthVisibleOnCalendar == 0) {
            monthVisibleOnCalendar = 11;
            yearVisibleOnCalendar--;
        } else {
            monthVisibleOnCalendar--;
        }

        showMonth();
    }

    public void setOnCalendarChangeListener(OnCalendarChangeListener onCalendarChangeListener) {
        this.onCalendarChangeListener = onCalendarChangeListener;
    }

    public void setOneCalendarClickListener(OneCalendarClickListener oneCalendarClickListener) {
        this.oneCalendarClickListener = oneCalendarClickListener;
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

    private String getInitialWeekday(int weekday) {
        calendarForComputeData.set(Calendar.DAY_OF_WEEK, weekday);
        String dayOfWeek = calendarForComputeData.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
        return dayOfWeek.substring(0, 1);
    }

    public String getStringMonth(int month) {
        calendarForComputeData.set(Calendar.MONTH, month);
        return months[calendarForComputeData.get(Calendar.MONTH)];
    }

    public int getMonthVisibleOnCalendar() {
        return monthVisibleOnCalendar;
    }

    public int getYearVisibleOnCalendar() {
        return yearVisibleOnCalendar;
    }
}
