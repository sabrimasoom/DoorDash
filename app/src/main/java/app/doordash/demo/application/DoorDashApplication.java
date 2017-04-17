package app.doordash.demo.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Masoom on 4/16/17.
 */

public class DoorDashApplication extends Application {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);//multi dex support for api 14
    }

}
