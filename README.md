# OneCalendarView
CalendarView Personalizado para desarrolladores android
OneCalendarView es un CalendarView Personalizado que perimite a los desarrolladores android tener el mismo CalendarView en cualquier aplicacion android (API 16 o superior).

# Capturas de pantalla


| demo 9 | demo 10 | demo 11 | demo 12 | 
| --- | --- | --- | --- |
| ![](https://cloud.githubusercontent.com/assets/15864336/23593793/7fb78d10-01e1-11e7-8bf1-36c57f2f515d.png) | ![](https://cloud.githubusercontent.com/assets/15864336/23593795/7fb8c31a-01e1-11e7-837d-fa828ce0c9d4.png) | ![](https://cloud.githubusercontent.com/assets/15864336/23593794/7fb88dc8-01e1-11e7-8b23-76b4da4cb523.png) | ![](https://cloud.githubusercontent.com/assets/15864336/23593796/7fc8f348-01e1-11e7-90e0-27a9eeaf3f5d.png) |

| demo 13 | demo 14 | demo 15 | demo 16 | 
| --- | --- | --- | --- |
| ![](https://cloud.githubusercontent.com/assets/15864336/23593799/7fcdcab2-01e1-11e7-80c8-e5da3aa210a1.png) | ![](https://cloud.githubusercontent.com/assets/15864336/23593797/7fcc27b6-01e1-11e7-82b4-53f0a7d63671.png)  | ![](https://cloud.githubusercontent.com/assets/15864336/23593798/7fcdbffe-01e1-11e7-980e-443a4673d949.png) | ![](https://cloud.githubusercontent.com/assets/15864336/23593796/7fc8f348-01e1-11e7-90e0-27a9eeaf3f5d.png)  |


# Live Demo app
usted puede ver una aplicacion demo en el siguiente enlace https://appetize.io/app/cymqjzvzaybypepxhnmn4hewx0

# Instalación
en su archivo /app/build.gradle
```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
       compile 'com.github.MorochoRochaDarwin:OneCalendarView:3.1.1'
}
```

# Agregar la vista OneCalendarView a su Layout
```xml
  <com.darwindeveloper.onecalendar.views.OneCalendarView
        android:id="@+id/oneCalendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
```
# En sus Actividades o Fragments
Inicialice la vista y llame a sus 2 metodos obligatorios para capturar los eventos en el calendario (NOTA: de no llamar a estos metodos se producira un error en tiempo de ejecución).

```java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final OneCalendarView calendarView = (OneCalendarView) findViewById(R.id.oneCalendar);



        //el siguiente fragmento puede ser usado para capturar los swipes en el calendar
        calendarView.setOnCalendarChangeListener(new OneCalendarView.OnCalendarChangeListener() {

            /**
             * notifica al usuario que el calendario a cambiado al mes anterior
             */
            @Override
            public void prevMonth() {
                //hacer algo aqui
            }

            /**
             * notifica al usuario que el calendario a cambiado al mes siguiente
             */
            @Override
            public void nextMonth() {
                //hacer algo aqui
            }
        });


        //el siguiente fragmento de codigo muestra como obtener los datos de un dia en el calendario
        //ademas de realizar otras acciones
        calendarView.setOneCalendarClickListener(new OneCalendarView.OneCalendarClickListener() {

            /**
             * cuando se da click en un dia en el calendario mostrado
             *
             * @param day      un Objeto de tipo Day del cual podemos llara a su metodo getDate() para recuperar una fecha
             * @param position posicion desde 0-41, que ocupa en el calendario actual
             */
            @Override
            public void dateOnClick(Day day, int position) {
                //recuerde que en java los meses inician desde 0
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
```

También puede llamar a los dos metodos anterioes implementando las interfaces OneCalendarView.OnCalendarChangeListener y OneCalendarView.OneCalendarClickListener
```java
calendarView.setOnCalendarChangeListener(this);
calendarView.setOneCalendarClickListener(this); 
```

# Diseño completo
Usted puede agregar varios atributos a la vista en sus layouts y crear diseños unicos. A continuación un ejemplo completo
```xml
 <com.darwindeveloper.onecalendar.views.OneCalendarView
        android:id="@+id/oneCalendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:backgroundColorDaysOfAnotherMonth="@color/colorPrimary"
        app:backgroundColorDaysOfMonth="#53c0c0c1"
        app:backgroundColorSelectedDay="#d2d2d2"
        app:calendarBackgroundColor="@color/colorPrimary"
        app:currentDayBackgroundColor="#fad501"
        app:mainBackgroundColor="@color/colorPrimary"
        app:textColorDaysOfAnotherMonth="#fff"
        app:textColorDaysOfMonth="#fff"
        app:textColorMonthAndYear="@color/colorPrimary"
        app:textColorSelectedDay="#000000" />
```

# Metodos
La clase OneCalendarView dispone de los siguientes metodos para facilitar y reducir la codificacion al usuario

| Metodo | Descripción |
| --- | --- |
| void setMonthYear(int month, int year) | este metodo configura el OneCalendarView a un mes y un año especifico |
| String getStringMonth(int numMonth) | retorna un mes como un string de pendiendo del idioma establecido en el OneCalendar (en java los meses inician en 0) |
| int getCurrentMonth() | retorna el mes actual |
| int getCurrentYear() | retorna el año actual |
| int getCurrentDayMonth() | retorna el dia del mes actual |
| int getNumberOfDaysMonthYear(int year, int month) | calcula el numero de dias que tiene un mes de una año especifico |
| String getNameDay(int day, int month, int year) | retorna el nombre de un dia especifico de una año (en ingles o español segun la configuracion) |
| int getMonth() | retorna el mes visible en el calendario |
| int getYear() | retorna el año del mes visible en el calendario |
| void addDaySelected(int position) | este metodo pinta un dia en el mes visible del calendario  (posicion es un valor entre 0-41) |
| void removeDaySeleted(int position) | este metodo remueve o despinta un dia en el mes visible del calendario (posicion es un valor entre 0-41) |
| boolean isDaySelected(int position) | comprueba si un dia en el calendario del mes visible esta seleccionado |

# Interfaces
## OneCalendarClickListener(OBLIGATORIA)
implementa todos los metodos necesarios para cuando se de clic o un clic prolongado en una fecha del calendario.
```java
        /**
         * cuando se da click en un dia del mes mostrado
         *
         * @param day      un Objeto de tipo Day del cual podemos llara a su metodo getDate() para recuperar una fecha
         * @param position posicion desde 0-41, que ocupa en el calendario actual
         */
        void dateOnClick(Day day, int position);

        /**
         * cuando se da click prolongado en un dia del mes mostrado
         *
         * @param day      un Objeto de tipo Day del cual podemos llara a su metodo getDate() para recuperar una fecha
         * @param position posicion desde 0-41, que ocupa en el calendario actual
         */
        void dateOnLongClick(Day day, int position);
```
## OnCalendarChangeListener(OBLIGATORIA)
implementa todos los metodos necesarios para notificar que ha habido un cambio en el mes del calendario
```java
         /**
         * notifica al usuario que el calendario a cambiado al mes anterior
         */
        void prevMonth();

        /**
         * notifica al usuario que el calendario a cambiado al mes siguiente
         */
        void nextMonth();
```


