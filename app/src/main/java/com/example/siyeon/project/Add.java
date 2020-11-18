package com.example.siyeon.project;



import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.content.Intent;

import android.app.Activity;
import android.app.DatePickerDialog;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;


public class Add extends Activity {

    int mYear, mMonth, mDay;
    int mrate;
    TextView mTxtDate;
    TextView mTxtim;
    EditText input ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_sched);
        mTxtDate = (TextView) findViewById(R.id.date);
        mTxtim = (TextView) findViewById(R.id.imtv);
        input = (EditText) findViewById(R.id.todoname);

        final RatingBar ratbar = (RatingBar) findViewById(R.id.importance);

        ratbar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {

                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating,
                                                boolean fromUser) {
                        mrate = (int) rating;
                        if (mrate == 1)
                            mTxtim.setText("하면 좋다.");
                        else if (mrate == 2)
                            mTxtim.setText("되도록이면 꼭 하자!");
                        else if (mrate == 3)
                            mTxtim.setText("안하면 세계가 멸망한다.");
                    }

                });


        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();


    }


    public void complete_OnClick(View v) {

        String name = input.getText().toString();
        if (input.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "할 일의 이름을 입력하세요", Toast.LENGTH_LONG).show();

        } else {

            Intent intent=getIntent();
            intent.putExtra("name",name);
            intent.putExtra("year",mYear);
            intent.putExtra("month",mMonth+1);

            intent.putExtra("day",mDay);
            intent.putExtra("rate",mrate);
            setResult(RESULT_OK,intent);
            finish();
        }
    }


    public void mOnClick(View v) {
        new DatePickerDialog(Add.this, mDateSetListener, mYear, mMonth, mDay).show();
    }


    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {


        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            UpdateNow();

        }

    };

    //텍스트뷰의 값을 업데이트 하는 메소드

    void UpdateNow() {
        mTxtDate.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay));

    }
}