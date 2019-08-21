import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'package:myflutterapp/com/wdq/utils/DeviceInfo.dart' as DeviceInfo;
import 'package:myflutterapp/com/wdq/pages/Login.dart' as Login;
import 'package:myflutterapp/com/wdq/pages/Launcher.dart' as Launcher;

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  var user="";
  @override
  Widget build(BuildContext context) {
    print("启动 MyApp ===dart==="+user.toString());

//    DeviceInfo.DeviceInfoClass.info();

    if(user.isEmpty){
      return MaterialApp(
        home: Login.Login(),
      );
    }else{
      return MaterialApp(
        home: Launcher.Launcher(),
      );
    }
  }
}

