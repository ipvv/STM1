package com.vsahin.moneycim;

import android.app.Application;

import com.vsahin.moneycim.Di.AppComponent;
import com.vsahin.moneycim.Di.AppModule;
import com.vsahin.moneycim.Di.DaggerAppComponent;


public class MoneycimApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
