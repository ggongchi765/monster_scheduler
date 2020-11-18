package com.example.siyeon.project;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class MainActivity extends AppCompatActivity {
    File storeDir;

    Coin coin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        coin=new Coin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeDir=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"store");
        if (!storeDir.exists()) {

            storeDir.mkdirs();
            if(!storeDir.exists())
                Toast.makeText(this, "DIR ERROR", Toast.LENGTH_SHORT).show();

        }

       SharedPreferences sharedPreferences=getSharedPreferences("coin",0);
        coin.setCoin(sharedPreferences.getInt("coin",0));
    }

    public void onClickMon(View view){
        Intent intent=new Intent(this, Monster.class);
        startActivityForResult(intent,0);

}

    public void onClickSched(View view){
        Intent intent=new Intent(getApplicationContext(), Sched.class);
        startActivityForResult(intent,10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
    }
    @Override
    protected void onDestroy(){

        try{

            SharedPreferences sharedPreferences=getSharedPreferences("coin",0);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("coin",coin.getCoin());
            editor.commit();
        }catch (Exception e){   Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();}


        super.onDestroy();
    }
}
