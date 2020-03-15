package com.example.runiccode;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //+++блок с датой и временем
    String dataString, multiTextStr;

    TextView multiText;
    TextView currentDateTime;
    Calendar dateAndTime=Calendar.getInstance();
    ImageView imageViewSushnost,imageViewLichnost,imageViewGold;
    LinearLayout linearLayout;//Для видимости области с рунами
    boolean flag = true;
    private Object then;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDateTime= findViewById(R.id.currentDateTime);
        setInitialDateTime();

        imageViewSushnost = findViewById(R.id.imageView6);  //Руна сущности
        imageViewLichnost = findViewById(R.id.imageView7);  //Руна Личности
        imageViewGold = findViewById(R.id.imageView8);      //Золотая руна

        linearLayout = findViewById(R.id.linearLayout); //Для видимости области с рунами

        multiTextStr="";
    }

    public void onClick(View v) {
        linearLayout.setVisibility(View.VISIBLE);   //делаем видимой область с результатами рун после нажатия на кнопку "Узнать"

        //Разбивка даты рождения
        int day = 0;
        int dayString = 0;  //День рождения
        int month = 0;
        int monthString = 0; //Месяц рождения
        int year = 0;
        int yearString = 0; //Год рождения
        int chisloSuchnosti = 0;
        int chisloSuchnostiString = 0; //Число сущности
        int att1 = 0;
        int att2 = 0;
        int att3 = 0;

        int attK = 0;

        String str = dataString;
        StringBuffer sb = new StringBuffer(dataString);
        sb.delete(2, 3);
        sb.delete(4, 5);

        char[] strToArray = toString().valueOf(sb).toCharArray(); // Преобразуем строку str в массив символов (char)

        for (int i = 0; i < strToArray.length; i++) {
            if (i == 0 || i == 1) {
                day = day + Integer.parseInt(toString().valueOf(strToArray[i]));
            }

            if (i == 2 || i == 3) {
                month = month + Integer.parseInt(toString().valueOf(strToArray[i]));
            }

            if (i == 4 || i == 5 || i == 6 || i == 7) {
                year = year + Integer.parseInt(toString().valueOf(strToArray[i]));
            }

        }
        chisloSuchnosti = day + month + year;

        //Считаем сумму цифр Числа сущности
        char[] chisloSuchnostiToArray = toString().valueOf(chisloSuchnosti).toCharArray();

        char[] chisloSuchnostiToArray1;
        do {
            for (int i = 0; i < chisloSuchnostiToArray.length; i++) {
                chisloSuchnostiString = chisloSuchnostiString + Integer.parseInt(toString().valueOf(chisloSuchnostiToArray[i]));
            }
            chisloSuchnostiToArray1 = toString().valueOf(chisloSuchnostiString).toCharArray();
        }
        while (chisloSuchnostiToArray1.length > 1);


        //Считаем сумму цифр дня рождения
        char[] dayToArray = toString().valueOf(day).toCharArray();
        for (int i = 0; i < dayToArray.length; i++) {
            dayString = dayString + Integer.parseInt(toString().valueOf(dayToArray[i]));
        }

        //Считаем сумму цифр месяца рождения
        char[] monthToArray = toString().valueOf(month).toCharArray();
        for (int i = 0; i < monthToArray.length; i++) {
            monthString = monthString + Integer.parseInt(toString().valueOf(monthToArray[i]));
        }

        //Считаем сумму цифр года рождения
        char[] yearToArray = toString().valueOf(year).toCharArray();
        for (int i = 0; i < yearToArray.length; i++) {
            yearString = yearString + Integer.parseInt(toString().valueOf(yearToArray[i]));
        }

        //создаём два массива, attDMY - массив с днём месяцем и годом, att - массив именно с циврами 1,2 или 3
        Integer[] attDMY = {dayString, monthString, yearString};
        Integer[] att = new Integer[3];
        for (int y = 0; y < 3; y++) {
            if (attDMY[y] == 1 || attDMY[y] == 4 || attDMY[y] == 7) {
                att[y] = 1;
            }
            if (attDMY[y] == 2 || attDMY[y] == 5 || attDMY[y] == 8) {
                att[y] = 2;
            }
            if (attDMY[y] == 3 || attDMY[y] == 6 || attDMY[y] == 9) {
                att[y] = 3;
            }
        }


        //считаем сколько в массиве значений 1,2 или 3 чтобы определить конечный att
        for (int z = 0; z < 3; z++) {
            if (att[z] == 1) {
                att1++;
            }
            if (att[z] == 2) {
                att2++;
            }
            if (att[z] == 3) {
                att3++;
            }
        }


        //Определяем конечный атт? сравниваем все между собой чтобы вычислить итоговый
        if (att1 > att2 && att1 > att3) {
            attK = att1;
        }
        if (att2 > att1 && att2 > att3) {
            attK = att2;
        }
        if (att3 > att1 && att3 > att2) {
            attK = att3;
        }

        if (flag)
            imageViewSushnost.setImageResource(R.drawable.gebo);
        else
            // возвращаем первую картинку
            imageViewSushnost.setImageResource(R.drawable.hagalaz);
        flag = !flag;
    }
    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(MainActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }
    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(MainActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }
    // установка начальных даты и времени
    private void setInitialDateTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(dateAndTime.getTime());
        currentDateTime.setText(date);
        dataString = date;
        //currentDateTime.setText(DateUtils.formatDateTime(this,
        //        dateAndTime.getTimeInMillis(),
        //        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };
    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
    //---блок с датой и временем


}
