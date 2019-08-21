import 'package:dio/dio.dart';
import 'package:myflutterapp/com/wdq/httpapi/Protocol.dart' as Protocol;
class HttpApis {
  static Future<Response> Login(String url,dynamic username,dynamic password,dynamic mobileinfo){
    dynamic params=Protocol.Protocol.loginReq(username,password,mobileinfo);
    print(params);
    return  Protocol.post(url,params.toString());
  }

}
