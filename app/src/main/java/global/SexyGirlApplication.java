package global;

import android.app.Application;

import network.NetworkManager;

/**
 * 创建者:   Leon
 * 创建时间:  2016/10/11 10:20
 * 描述：    TODO
 */
public class SexyGirlApplication extends Application {
    public static final String TAG = "SexyGirlApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.init(getApplicationContext());
    }
}
