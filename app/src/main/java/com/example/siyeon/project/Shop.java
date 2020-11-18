package com.example.siyeon.project;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Shop{

    private Context context;
    private MyEventListener mListener;
    public void setMyEventListener(MyEventListener listener) { mListener = listener; }
    Coin coin;
    public Shop(Context context)
    {
        this.context=context;
    }

    public void callFunction( ){
        final Dialog diglog=new Dialog(context);
        diglog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diglog.setContentView(R.layout.activity_shop);
        diglog.show();
        coin=new Coin();

        TextView textView=(TextView)diglog.findViewById(R.id.coin_text);
        textView.setText(String.valueOf(coin.getCoin()));
        Button apple=(Button)diglog.findViewById(R.id.give_apple);
        Button choco=(Button)diglog.findViewById(R.id.give_choco);
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(coin.getCoin()>200)
                {

                    mListener.onMyEvent(0);
                    coin.Subcoin(200);
                    diglog.dismiss();

                }
                else
                {
                    Toast.makeText(context,"포인트가 부족합니다.",Toast.LENGTH_LONG).show();
                }

            }
        });

        choco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(coin.getCoin()>100)
                {

                    mListener.onMyEvent(1);
                    coin.Subcoin(100);
                    diglog.dismiss();

                }
                else
                {
                    Toast.makeText(context,"포인트가 부족합니다.",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
