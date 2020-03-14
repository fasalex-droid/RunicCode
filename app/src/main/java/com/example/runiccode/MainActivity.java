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
    TextView currentDateTime;
    Calendar dateAndTime=Calendar.getInstance();
    ImageView imageView;
    LinearLayout linearLayout;//Для видимости области с рунами
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDateTime= findViewById(R.id.currentDateTime);
        setInitialDateTime();
        imageView = findViewById(R.id.imageView6);
        linearLayout = findViewById(R.id.linearLayout); //Для видимости области с рунами
    }

    public void onClick(View v)
    {
        linearLayout.setVisibility(View.VISIBLE);   //делаем видимой область с результатами рун после нажатия на кнопку "Узнать"

        if (flag)
            imageView.setImageResource(R.drawable.gebo);
        else
            // возвращаем первую картинку
            imageView.setImageResource(R.drawable.hagalaz);
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
