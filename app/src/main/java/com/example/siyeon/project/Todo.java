package com.example.siyeon.project;

import android.provider.BaseColumns;

import java.io.Serializable;

public final class Todo implements  Serializable{
    String name;
    int day;
    int year;
    int month;
    int rate;
}