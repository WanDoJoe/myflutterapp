package com.wdq.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;


/**
 * Created by sinosoft_wan on 2019-8-20.
 */

public class PhoneUtils {

    private static final String TAG = "DeviceParams";
    public static final String NETWORK_TYPE_NONE = "none";
    public static final String NETWORK_TYPE_WIFI = "wifi";
    public static final String NETWORK_TYPE_MOBILE = "mobile";

    /**
     * 得到设备的deviceid
     *
     * @param context
     * @return
     */
    public static String deviceid(Context context) {
        return new DeviceUuidFactory(context).getDeviceUuid().toString();
    }

    /**
     * 得到Android API Level
     *
     * @return
     */
//	public static int buildSDK() {
//		return android.os.Build.VERSION.SDK_INT;
//	}

    /**
     * 得到Android API Level
     *
     * @return 16
     */
    public static String buildSDK() {
        int sdk = Build.VERSION.SDK_INT;
        Log.i(TAG + "buildSDK", "" + String.valueOf(sdk));
        return String.valueOf(sdk);
    }

    /**
     * 获取手机的IMEI码
     */
//    @Permission(permissions = {Manifest.permission.READ_PHONE_STATE})
    public static String getIMEI(Context context) {
        String imei="";
        try {
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//        }
//        String IMEI = tm.getDeviceId();
//        Log.i(TAG+"getIMEI", ""+IMEI);
            TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            String imsi = mTelephonyMgr.getSubscriberId();  //获取IMSI号
            imei = mTelephonyMgr.getDeviceId();  //获取IMEI号
        }catch (Exception e){
            e.printStackTrace();
        }
        return imei;
    }

    /**
     * @return  4.1.1
     */
    public static String osVersion() {
        Log.i(TAG+"osVersion", ""+ Build.VERSION.RELEASE);
        return Build.VERSION.RELEASE;
    }

    /**
     * @return current Android API level.
     */
    public static int apiLevel() {
        try {
            // Although the Build.VERSION.SDK field has existed since API 1, it
            // is deprecated and could be removed
            // in the future. Therefore use reflection to retrieve it for
            // maximum forward compatibility.
            final Class<?> buildClass = Build.VERSION.class;
            final String sdkString = (String) buildClass
                    .getField("SDK").get(null); //$NON-NLS-1$
            return Integer.parseInt(sdkString);
        } catch (final Exception e) {
            Log.d(TAG, "Caught exception", e); //$NON-NLS-1$

            // Although probably not necessary, protects from the aforementioned
            // deprecation
            try {
                final Class<?> buildClass = Build.VERSION.class;
                return buildClass.getField("SDK_INT").getInt(null); //$NON-NLS-1$
            } catch (final Exception ignore) {
                Log.d(TAG, "Caught exception", ignore); //$NON-NLS-1$
            }
        }

        // Worse-case scenario, assume Cupcake
        return 3;
    }

    /**
     * Gets the device manufacturer's name. This is only available on SDK 4 or
     * greater, so on SDK 3 this method returns the constant string "unknown".
     *
     * @return A string naming the manufacturer
     */
    public static String getManufacturer() {
        String mfg = "unknown"; //$NON-NLS-1$
        if (apiLevel() > 3) {
            try {
                final Class<?> buildClass = Build.class;
                mfg = (String) buildClass.getField("MANUFACTURER").get(null); //$NON-NLS-1$
            } catch (final Exception ignore) {
                Log.d(TAG, "Caught exception", ignore); //$NON-NLS-1$
            }
        }
        return mfg;
    }

    public static String getModel() {
        String mfg = "unknown"; //$NON-NLS-1$
        if (apiLevel() > 3) {
            try {
                final Class<?> buildClass = Build.class;
                mfg = (String) buildClass.getField("MODEL").get(null); //$NON-NLS-1$
            } catch (final Exception ignore) {
                Log.d(TAG, "Caught exception", ignore); //$NON-NLS-1$
            }
        }
        return mfg;
    }

    /**
     * 获取当前数据连接类型
     *
     * @param context
     * @return
     */
    public static String networkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netWorkInfo = connectivityManager.getActiveNetworkInfo();
        if (netWorkInfo == null || !netWorkInfo.isConnected()) {
            return NETWORK_TYPE_NONE;
        } else {
            switch (netWorkInfo.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                    return NETWORK_TYPE_MOBILE;
                case ConnectivityManager.TYPE_WIFI:
                    return NETWORK_TYPE_WIFI;
            }
            return NETWORK_TYPE_MOBILE;
        }
    }

    /**
     * 获取当前网络运营商名称
     *
     * @param context
     * @return
     */
    public static String networkOperator(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getNetworkOperatorName();
    }

    /**
     * 当前设备屏幕的宽(像素)
     *
     * @param context
     * @return
     */
    public static int screenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 当前设备屏幕的高(像素)
     *
     * @param context
     * @return
     */
    public static int screenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // final int phoneScreenWidth = display.getWidth();
        return display.getHeight();
    }

    /**
     * 当前设备屏幕的密度
     *
     * @param context
     * @return
     */
    public static float density(Context context) {
        // return context.getResources().getDisplayMetrics().densityDpi;
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    /**
     * Gets the versionName of the application.
     *
     * @param context
     *            {@link Context}. Cannot be null.
     * @return The application's version
     */
    public static String appVersion(final Context context) {
        final PackageManager pm = context.getPackageManager();

        try {
            final String versionName = pm.getPackageInfo(
                    context.getPackageName(), PackageManager.GET_ACTIVITIES).versionName;

			/*
			 * If there is no versionName in the Android Manifest, the
			 * versionName will be null.
			 */
            if (versionName == null) {
                return "unknown"; //$NON-NLS-1$
            }
            return versionName;
        } catch (final PackageManager.NameNotFoundException e) {
			/*
			 * This should never occur--our own package must exist for this code
			 * to be running
			 */
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取name对应的AndroidManifest中的meta-data值
     *
     * @param context
     * @param name
     * @return
     */
    public static String metaData(Context context, String name) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (applicationInfo == null) {
                return "";
            } else {
                Bundle bundle = applicationInfo.metaData;
                if (bundle == null) {
                    return "";
                }
                String result = bundle.getString(name);
                if (result == null) {
                    return "";
                } else {
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
