package com.example.siyeon.project;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TodoAdapter extends BaseAdapter {

    private List<Todo> mTodo=new ArrayList<Todo>();

    private MyEventListener mListener;
    public void setMyEventListener(MyEventListener listener) { mListener = listener; }

    public TodoAdapter()
    {

    }

    @Override
    public int getCount()
    {
        return mTodo.size();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final int pos=position;
        final Context context=parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, parent, false);
        }
        TextView name=(TextView)convertView.findViewById(R.id.list_name);
        TextView date=(TextView)convertView.findViewById(R.id.list_date);
        TextView rate=(TextView)convertView.findViewById(R.id.list_rate);
        Button delete=(Button)convertView.findViewById(R.id.list_button);
        delete.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {

                AlertDialog.Builder dlg=new AlertDialog.Builder(context);
                dlg.setTitle("완료 되었습니다.");
                final  int tmp_coin=mTodo.get(position).rate*10+20;
                dlg.setMessage("coin "+tmp_coin+"개를 획득했습니다.");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onMyEvent(tmp_coin);

                    }
                });
                dlg.show();
                mTodo.remove(position);
                notifyDataSetChanged();


            }
        });


        Todo tmp=mTodo.get(position);
        name.setText(tmp.name);
        date.setText(String.format(tmp.year+"/"+tmp.month+"/"+tmp.day));


        if(tmp.rate ==0)
            rate.setText("☆☆☆");
        else if (tmp.rate ==1)
            rate.setText("★☆☆");
        else if (tmp.rate==2)
            rate.setText("★★☆");
        else
            rate.setText("★★★");

        return  convertView;
    }

    @Override
    public long getItemId(int position){return  position;}
    @Override
    public Object getItem(int position){
        return mTodo.get(position);
    }

    public void addItem(Todo add_todo)
    {
        mTodo.add(add_todo);
    }


}
