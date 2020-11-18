package com.example.siyeon.project;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;




public class Sched extends Activity implements  MyEventListener{

    Intent intent;
    Intent add_intent;
    File storeDir;
    File file_todo;
    TodoAdapter adapter;
    TextView tvcoin;
    Coin coin;







    protected Todo MakeTodo(String line) {

        Todo todo = new Todo();
        String[] tmp = line.split("/");
        todo.name = tmp[0];
        todo.year = Integer.parseInt(tmp[1]);
        todo.month = Integer.parseInt(tmp[2]);
        todo.day = Integer.parseInt(tmp[3]);
        todo.rate = Integer.parseInt(tmp[4]);

        return todo;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sche);
        add_intent = getIntent();

        ListView list = (ListView) findViewById(R.id.list_to_do);
        adapter = new TodoAdapter();
        tvcoin=(TextView)findViewById(R.id.coin_text);
        list.setAdapter(adapter);
        adapter.setMyEventListener(this);
        storeDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "store");
        file_todo = new File(storeDir.getPath() + File.separator + "todo.txt");
        if (file_todo.exists()) {
            try {
                FileReader fileReader = new FileReader(file_todo);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    Todo tmp_todo = MakeTodo(line);
                    adapter.addItem(tmp_todo);
                    adapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
            }
        }
        coin=new Coin();
        tvcoin.setText(String.valueOf(coin.getCoin()));

    }
    @Override
    public void onMyEvent(int add_coin){
        coin.Addcoin(add_coin);
        tvcoin.setText(String.valueOf(coin.getCoin()));
    }
    @Override
    protected void onDestroy() {


        file_todo = new File(storeDir.getPath() + File.separator + "todo.txt");
        if(file_todo.exists())
        {
            file_todo.delete();
            file_todo = new File(storeDir.getPath() + File.separator + "todo.txt");

        }
        try {

            BufferedWriter writer=new BufferedWriter(new FileWriter(file_todo.getAbsoluteFile()));

            for (int i=0;i<adapter.getCount();i++) {
                Todo item=(Todo)adapter.getItem(i);
                writer.write(String.format(item.name +"/" + item.year +"/"+ item.month + "/" + item.day + "/" + item.rate + "\n"));
            }
            writer.close();
        } catch (Exception e) { Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        }


        super.onDestroy();

    }

    public void onClickAdd(View v) {

        intent= new Intent(Sched.this, Add.class);
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode==RESULT_OK){

            Todo tmp=new Todo();
            tmp.name=data.getStringExtra("name");
            tmp.year= data.getIntExtra("year",0);
            tmp.month=data.getIntExtra("month",0);
            tmp.day=data.getIntExtra("day",0);
            tmp.rate=data.getIntExtra("rate",0);
            adapter.addItem(tmp);
            adapter.notifyDataSetChanged();

        }
    }
}