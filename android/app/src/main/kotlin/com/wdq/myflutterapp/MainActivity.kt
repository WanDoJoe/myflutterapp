package com.wdq.myflutterapp

import android.os.Bundle
import com.wdq.utils.PhoneUtils

import io.flutter.app.FlutterActivity
import io.flutter.plugins.DeviceInfoPlugin
import io.flutter.plugins.GeneratedPluginRegistrant
import org.json.JSONObject

class MainActivity: FlutterActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)
    registerWithPlugin()
}

  fun registerWithPlugin(){
    DeviceInfoPlugin.registerWith(this.registrarFor(DeviceInfoPlugin.CHANNEL),getDeviceInfoJson());
  }



  fun   getDeviceInfoJson(): JSONObject {
    var deviceInfoJO =  JSONObject();
    deviceInfoJO.put("UDID", PhoneUtils.deviceid(this));
    deviceInfoJO.put("OStype", "Android");
    deviceInfoJO.put("Osversion", PhoneUtils.osVersion());
    deviceInfoJO.put("token", "");
    deviceInfoJO.put("imei", PhoneUtils.getIMEI(this));
    deviceInfoJO.put("DeviceMode", PhoneUtils.getModel());
    return deviceInfoJO;

  }
}
