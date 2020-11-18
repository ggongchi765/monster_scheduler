package com.example.siyeon.project;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;


public class Levelup{

    private Context context;
    public Levelup(Context context)
    {
        this.context=context;
    }

    public void callFunction( ) {
        final Dialog diglog = new Dialog(context);
        diglog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diglog.setContentView(R.layout.level_up);
        diglog.show();
    }
}
