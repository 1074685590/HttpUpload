package com.example.myapplication.util;

import android.util.Log;

import java.util.Objects;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class Logger {

    public static final String TAG = "gejun";
    public static final boolean DEBUG = true;

    public static String getMessage(Object o){
        return o ==null? "null":o.toString();
    }

    public static void e(Object o){
        if(DEBUG) {
            Log.e(TAG, getMessage(o));
        }
    }

    public static void i(Object objects){
        if(DEBUG) {
            Log.i(TAG, getMessage(objects));
        }
    }

    public static void w(Object objects){
        if(DEBUG) {
            Log.w(TAG, getMessage(objects));
        }
    }

    public static void d(Object objects){
        if(DEBUG) {
            Log.d(TAG, getMessage(objects));
        }
    }

}




