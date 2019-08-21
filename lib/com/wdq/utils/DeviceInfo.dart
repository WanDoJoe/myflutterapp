import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

/**
 * 名称要和Java端一致
 */
 const  channelName = "com.mmd.flutterapp/plugin";
 const methodName = "log";

 const MethodChannel channel = MethodChannel(channelName);

 class DeviceInfoClass {
     static Future<String> info() async{

         Map<String,String> map = {"data":"初始化手机，获取设备信息"};

         String result = await channel.invokeMethod(methodName,map);
         print("futterMethod:");
         print(result);
         return result;

     }
  }
