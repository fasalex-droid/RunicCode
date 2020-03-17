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

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //+++блок с датой и временем
    String dataString, familiyaString, imyaString, otchestvoSTring;
EditText editText1,editText2,editText3;
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
        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText1.setText("Фасолько");
        editText2.setText("Александр");
        editText3.setText("Вадимович");

    }

    public void onClick(View v) {
        linearLayout.setVisibility(View.VISIBLE);   //делаем видимой область с результатами рун после нажатия на кнопку "Узнать"

        EditText fString = (EditText) findViewById(R.id.editText);
        EditText iString = (EditText) findViewById(R.id.editText2);
        EditText oString = (EditText) findViewById(R.id.editText3);

        familiyaString = String.valueOf(fString.getText());
        imyaString = String.valueOf(iString.getText());
        otchestvoSTring = String.valueOf(oString.getText());

        //--------ПАРСИНГ даты рождения---------/
        //ДАТА
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

        //ФИО
        int name = 0;
        int nameString = 0;  //имя
        int secondName = 0;
        int secondNameString = 0; //Фамилия
        int otchestvo = 0;
        int otchestvoString = 0; //Отчество
        int chisloLichnosti = 0;
        int chisloLichnostiString = 0; //Число Личности
        int att1_FIO = 0;
        int att2_FIO = 0;
        int att3_FIO = 0;
        int attK_FIO = 0;

        //ЗОЛОТАЯ РУНА

        int goldRunic = 0;
        int nameDay = 0;
        int secondNameMonth = 0;
        int otchestvoYear = 0;
        int att1_GOLD = 0;
        int att2_GOLD = 0;
        int att3_GOLD = 0;
        int attK_GOLD=0;

        String str = dataString;
        StringBuffer sb = new StringBuffer(dataString);
        sb.delete(2, 3);
        sb.delete(4, 5);
        char[] strToArray = toString().valueOf(sb).toCharArray(); // Преобразуем строку str в массив символов (char)

        for (int i = 0; i < strToArray.length; i++) {
            if (i == 0 || i == 1) {day = day + Integer.parseInt(toString().valueOf(strToArray[i]));}
            if (i == 2 || i == 3) {month = month + Integer.parseInt(toString().valueOf(strToArray[i]));}
            if (i == 4 || i == 5 || i == 6 || i == 7) {year = year + Integer.parseInt(toString().valueOf(strToArray[i]));}
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
        attK = searchATT(dayString,monthString,yearString,  att1, att2, att3, attK,chisloSuchnostiString);

        //Выбираем необходимую Руну Сущности
        imageSet(chisloSuchnostiString,attK,imageViewSushnost);
        //------------ПАРСИНГ даты рождения---------/


        //------------ПАРСИНГ ФИО---------/
        //Находим Число имени
        char[] strNameToArray = toString().valueOf(imyaString).toCharArray(); // Преобразуем строку с Именем в массив символов (char)
        for (int i = 0; i < strNameToArray.length; i++)
        {name =chislo(toString().valueOf(strNameToArray[i]),name);}

        //Находим Число фамилии
        char[] strSecondNameToArray = toString().valueOf(familiyaString).toCharArray(); // Преобразуем строку с Именем в массив символов (char)
        for (int y = 0; y < strSecondNameToArray.length; y++)
        {secondName =chislo(toString().valueOf(strSecondNameToArray[y]),secondName);}

        //Находим Число отчества
        char[] strotchestvoToArray = toString().valueOf(otchestvoSTring).toCharArray(); // Преобразуем строку с Именем в массив символов (char)
        for (int y = 0; y < strotchestvoToArray.length; y++)
        {otchestvo =chislo(toString().valueOf(strotchestvoToArray[y]),otchestvo);}

        chisloLichnosti = name + secondName + otchestvo;

        //Считаем сумму цифр Числа Личности
        chisloLichnostiString = chisloK(chisloLichnosti,chisloLichnostiString);
        //Считаем сумму цифр Имени
        nameString = chisloK(name,nameString);
        //Считаем сумму цифр Фамилии
        secondNameString = chisloK(secondName,secondNameString);
        //Считаем сумму цифр Фамилии
        otchestvoString = chisloK(otchestvo,otchestvoString);

        //создаём два массива, attDMY - массив с днём месяцем и годом, att - массив именно с циврами 1,2 или 3
        attK_FIO = searchATT(nameString,secondNameString,otchestvoString,att1_FIO,att2_FIO,att3_FIO,attK_FIO,chisloLichnostiString);

        //Выбираем необходимую Руну Личности
        imageSet(chisloLichnostiString,attK_FIO,imageViewLichnost);
        //------------ПАРСИНГ ФИО---------/

        //------------ПАРСИНГ Золотая Руна---------/
        nameDay = chisloK(nameString+dayString,nameDay);
        otchestvoYear = chisloK(otchestvoString+yearString,otchestvoYear);
        secondNameMonth = chisloK(secondNameString+monthString,secondNameMonth);


        goldRunic = chisloK(chisloLichnostiString+chisloSuchnostiString,goldRunic);
        attK_GOLD = searchATT(nameDay,secondNameMonth,otchestvoYear,att1_GOLD,att2_GOLD,att3_GOLD,attK_GOLD,chisloLichnostiString);

        //Выбираем необходимую Золотую руну
        imageSet(goldRunic,attK_GOLD,imageViewGold);
        //------------ПАРСИНГ Золотая Руна---------/


       // if (flag)
       //     imageViewSushnost.setImageResource(R.drawable.gebo);
       // else
       //     // возвращаем первую картинку
       //     imageViewSushnost.setImageResource(R.drawable.hagalaz);
       // flag = !flag;
    }

    public int searchATT(int a1,int a2,int a3, int att1,int att2,int att3,int attK,int chisloString){
        Integer[] attDMY = {a1, a2, a3};
        Integer[] att = new Integer[3];
        for (int y = 0; y < 3; y++) {
            if (attDMY[y] == 1 || attDMY[y] == 4 || attDMY[y] == 7) {att[y] = 1;}
            if (attDMY[y] == 2 || attDMY[y] == 5 || attDMY[y] == 8) {att[y] = 2;}
            if (attDMY[y] == 3 || attDMY[y] == 6 || attDMY[y] == 9) {att[y] = 3;}
        }

        //считаем сколько в массиве значений 1,2 или 3 чтобы определить конечный att
        for (int z = 0; z < 3; z++) {
            if (att[z] == 1) {att1++;}
            if (att[z] == 2) {att2++;}
            if (att[z] == 3) {att3++;}
        }

        //Определяем конечный атт? сравниваем все между собой чтобы вычислить итоговый
        if (att1 > att2 && att1 > att3) {attK = 1;}
        if (att2 > att1 && att2 > att3) {attK = 2;}
        if (att3 > att1 && att3 > att2) {attK = 3;}
        if (attK==0){
            if (chisloString == 1 || chisloString == 4 || chisloString == 7) {attK = 1;}
            if (chisloString == 2 || chisloString == 5 || chisloString == 8) {attK = 2;}
            if (chisloString == 3 || chisloString == 6 || chisloString == 9) {attK = 3;}
        }
        return attK;
    }

    public void imageSet(int chisloString, int attK,ImageView image){
        switch (chisloString){
            case 1:
                if (attK==1) {image.setImageResource(R.drawable.fehu);}
                else if(attK==2){image.setImageResource(R.drawable.hagalaz);}
                else if(attK==3){image.setImageResource(R.drawable.tiwaz);}
                break;
            case 2:
                if (attK==1) {image.setImageResource(R.drawable.uruz);}
                else if(attK==2){image.setImageResource(R.drawable.naudiz);}
                else if(attK==3){image.setImageResource(R.drawable.berkano);}
                break;
            case 3:
                if (attK==1) {image.setImageResource(R.drawable.turisaz);}
                else if(attK==2){image.setImageResource(R.drawable.isa);}
                else if(attK==3){image.setImageResource(R.drawable.ehwaz);}
                break;
            case 4:
                if (attK==1) {image.setImageResource(R.drawable.ansuz);}
                else if(attK==2){image.setImageResource(R.drawable.jera);}
                else if(attK==3){image.setImageResource(R.drawable.mannaz);}
                break;
            case 5:
                if (attK==1) {image.setImageResource(R.drawable.raidho);}
                else if(attK==2){image.setImageResource(R.drawable.iwaz);}
                else if(attK==3){image.setImageResource(R.drawable.laguz);}
                break;
            case 6:
                if (attK==1) {image.setImageResource(R.drawable.kenaz);}
                else if(attK==2){image.setImageResource(R.drawable.pert);}
                else if(attK==3){image.setImageResource(R.drawable.inguz);}
                break;
            case 7:
                if (attK==1) {image.setImageResource(R.drawable.gebo);}
                else if(attK==2){image.setImageResource(R.drawable.algiz);}
                else if(attK==3){image.setImageResource(R.drawable.othala);}
                break;
            case 8:
                if (attK==1) {image.setImageResource(R.drawable.wunjo);}
                else if(attK==2){image.setImageResource(R.drawable.soulo);}
                else if(attK==3){image.setImageResource(R.drawable.dagaz);}
                break;
            default:
                image.setImageResource(R.drawable.gebo);
                break;
        }
    }

    public int chisloK(int name, int nameString){
        char[] chisloNameToArray = toString().valueOf(name).toCharArray();
        //char[] chisloNameToArray1;
        int chisloNew=0;
        do {
            for (int i = 0; i < chisloNameToArray.length; i++) {
                nameString = nameString + Integer.parseInt(toString().valueOf(chisloNameToArray[i]));
                chisloNew = nameString;
            }
            chisloNameToArray = toString().valueOf(nameString).toCharArray();
            nameString=0;
        }
        while (chisloNameToArray.length > 1);

        return chisloNew;
    }

    public int chislo(String strToArray, int secondName){
            if (strToArray.equals("А")||strToArray.equals("И")||strToArray.equals("С")||strToArray.equals("Ъ")||
                    strToArray.equals("а")||strToArray.equals("и")||strToArray.equals("с")||strToArray.equals("ъ")){secondName=secondName+1;}
            if (strToArray.equals("Б")||strToArray.equals("Й")||strToArray.equals("Т")||strToArray.equals("Ы")||
                    strToArray.equals("б")||strToArray.equals("й")||strToArray.equals("т")||strToArray.equals("ы")){secondName=secondName+2;}
            if (strToArray.equals("В")||strToArray.equals("К")||strToArray.equals("У")||strToArray.equals("Ь")||
                    strToArray.equals("в")||strToArray.equals("к")||strToArray.equals("у")||strToArray.equals("ь")){secondName=secondName+3;}
            if (strToArray.equals("Г")||strToArray.equals("Л")||strToArray.equals("Ф")||strToArray.equals("Э")||
                    strToArray.equals("г")||strToArray.equals("л")||strToArray.equals("ф")||strToArray.equals("э")){secondName=secondName+4;}
            if (strToArray.equals("Д")||strToArray.equals("М")||strToArray.equals("Х")||strToArray.equals("Ю")||
                    strToArray.equals("д")||strToArray.equals("м")||strToArray.equals("х")||strToArray.equals("ю")){secondName=secondName+5;}
            if (strToArray.equals("Е")||strToArray.equals("Н")||strToArray.equals("Ц")||strToArray.equals("Я")||
                    strToArray.equals("е")||strToArray.equals("н")||strToArray.equals("ц")||strToArray.equals("я")){secondName=secondName+6;}
            if (strToArray.equals("Ё")||strToArray.equals("О")||strToArray.equals("Ч")||
                    strToArray.equals("ё")||strToArray.equals("о")||strToArray.equals("ч")){secondName=secondName+7;}
            if (strToArray.equals("Ж")||strToArray.equals("П")||strToArray.equals("Ш")||
                    strToArray.equals("ж")||strToArray.equals("п")||strToArray.equals("ш")){secondName=secondName+8;}
            if (strToArray.equals("З")||strToArray.equals("Р")||strToArray.equals("Щ")||
                    strToArray.equals("з")||strToArray.equals("р")||strToArray.equals("щ")){secondName=secondName+9;}
        return secondName;
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
