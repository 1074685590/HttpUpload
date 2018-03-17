package com.example.myapplication.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class ThreadUtils {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void execute(Runnable runnable){
        executorService.execute(runnable);
    }
}
