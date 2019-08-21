package io.flutter.plugins;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/**
 * Created by sinosoft_wan on 2019-8-21.
 */

public class DeviceInfoPlugin implements MethodChannel.MethodCallHandler {
    private static final String TAG = "FlutterPluginTest";

    /**
     * 插件标识
     */
    public static String CHANNEL = "com.mmd.flutterapp/plugin";
    private static String ACTION_LOG = "log";
    private static String LOG_ARGUMENT = "data";
    static MethodChannel channel;
    private static JSONObject jsonOb;
    public static void registerWith(PluginRegistry.Registrar registrar, JSONObject jsonObject) {
        Log.e("DeviceInfoPlugin", jsonObject.toString());
        jsonOb=jsonObject;
        channel = new MethodChannel(registrar.messenger(), CHANNEL);
        DeviceInfoPlugin instance = new DeviceInfoPlugin();
        channel.setMethodCallHandler(instance);
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        /**
         * 通过 method 判断调用方法
         */
        if (methodCall.method.equals(ACTION_LOG)) {
            /**
             * 解析参数
             */
            String text = methodCall.argument(LOG_ARGUMENT);
            if (TextUtils.isEmpty(text)) {
                /**
                 * 错误返回
                 */
                result.error("Data is Null",null,null);
            }else {
                try {
                    //接受Futter传过参数
                    Log.d(TAG, "onMethodCall: " + text);
//
                    /**
                     * 成功返回
                     */
                    result.success(jsonOb.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }else {
            result.notImplemented();
        }
    }

}
