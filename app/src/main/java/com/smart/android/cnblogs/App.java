package com.smart.android.cnblogs;

import android.app.Application;

import timber.log.Timber;

/**
 * Copyright © 2015 Phoenix New Media Limited All Rights Reserved.
 * Created by fengjh on 2015/7/1.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
