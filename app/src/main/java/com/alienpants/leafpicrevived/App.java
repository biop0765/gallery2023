package com.alienpants.leafpicrevived;

import androidx.multidex.MultiDexApplication;

import com.alienpants.leafpicrevived.util.ApplicationUtils;
import com.alienpants.leafpicrevived.util.preferences.Prefs;
import com.orhanobut.hawk.Hawk;

/**
 * Created by dnld on 28/04/16.
 */
public class App extends MultiDexApplication {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        ApplicationUtils.init(this);

        initialiseStorage();
    }

    public static App getInstance() {
        return mInstance;
    }

    private void initialiseStorage() {
        Prefs.init(this);
        Hawk.init(this).build();
    }
}
