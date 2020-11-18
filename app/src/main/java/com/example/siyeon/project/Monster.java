package com.example.siyeon.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Monster extends Activity implements  MyEventListener{

    Coin coin;
    int mprogress;
    Animation animation;
    TextView tvcoin;
    ProgressBar bar;
    Button mos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        coin=new Coin();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon);


        Button shop=(Button)findViewById(R.id.shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shop shop=new Shop(Monster.this);
                shop.callFunction();
                shop.setMyEventListener(Monster.this);




            }
        });

        Intent intent=getIntent();
        tvcoin=(TextView)findViewById(R.id.coin_text);
        tvcoin.setText(String.valueOf(coin.getCoin()));
        SharedPreferences sharedPreferences=getSharedPreferences("progress",0);
        mprogress=(sharedPreferences.getInt("progress",0));
        bar=(ProgressBar)findViewById(R.id.progress);
        bar.setProgress(mprogress);
        mos=(Button)findViewById(R.id.moster_nomal);
        mos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onMyEvent(-1);
            }
        });
        animation= AnimationUtils.loadAnimation(this,R.anim.normal);
        mos.startAnimation(animation);

    }
    @Override
    public void onMyEvent(int add_coin){
        final AnimationDrawable drawable = (AnimationDrawable) mos.getBackground();
        drawable.start();
        final Button afood=(Button)findViewById(R.id.afood) ;
        AnimationDrawable afood_drawble=(AnimationDrawable)afood.getBackground();
        final Button cfood=(Button)findViewById(R.id.cfood) ;
        AnimationDrawable cfood_drawble=(AnimationDrawable)cfood.getBackground();



        if(add_coin ==0)
        {
            afood.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"Eat Apple",Toast.LENGTH_LONG).show();
            afood_drawble.start();

            //apple
            coin=new Coin();
            tvcoin.setText(String.valueOf(coin.getCoin()-200));


            mprogress=mprogress+50;
            bar.setProgress(mprogress);


        }
        else if (add_coin==1)
        {
            cfood.setVisibility(View.VISIBLE);
            cfood_drawble.start();

            //choco
            Toast.makeText(getApplicationContext(),"Choco",Toast.LENGTH_LONG).show();
            coin=new Coin();
            tvcoin.setText(String.valueOf(coin.getCoin()-100));

            mprogress=mprogress+30;
            bar.setProgress(mprogress);

        }
        else {
            drawable.stop();
            animation= AnimationUtils.loadAnimation(this,R.anim.normal);
             mos.startAnimation(animation);

             afood.setVisibility(View.INVISIBLE);
             cfood.setVisibility(View.INVISIBLE);
            if(afood_drawble.isRunning())
             afood_drawble.stop();
            if(cfood_drawble.isRunning())
                cfood_drawble.stop();

            }

        if(mprogress>100)
        {
            Levelup levelup=new Levelup(Monster.this);
            levelup.callFunction();
            mprogress=0;

        }



    }
    @Override
    protected void onDestroy(){

        SharedPreferences sharedPreferences=getSharedPreferences("progress",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("progress",mprogress);
        editor.commit();

            super.onDestroy();
    }

}
