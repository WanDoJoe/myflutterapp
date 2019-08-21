import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'dart:convert';

const URL_BASE="http://39.107.91.159:18085/sinosoftmobile";

class Protocol {

  static dynamic  loginReq(dynamic username,dynamic password,dynamic mobileinfo){
//    print(username+","+password+","+mobileinfo);
    return _ReturnMapToJson(
        { "biz": {"username": username, "password": password},
          "mobileinfo": mobileinfo
        }
    );
  }



  static dynamic  _ReturnMapToJson(dynamic mapData){

    var jsondate={
      "udid":"asdasdaksdhak",
      "jsondata":mapData
    };
    return json.encode(jsondate);

  }
}


//    var _HttpClint=new HttpClient();

  Future<Response> post(url,body) async{
    dynamic posturl=URL_BASE+url;
    print(posturl);
    print(body);
    print("开始请求数据");
//      Dio mDio=new Dio();
    Response response;
    response=await  Dio().post(posturl,data: body)
//          .catchError((){
//        print("出现异常了");
//      })
        ;
//          .then((result) {
//        print("接口返回的数据是:${result}");
//      }).whenComplete((){
//        print("异步任务处理完成");
//      }).catchError((){
//        print("出现异常了");
//      });
    print("请求完成");
    print(response.data.toString());
    if(response.statusCode==200) {
      print("请求完成");
      return response;
    }else{
      print("请求完成="+response.statusCode.toString());
    }
    return response;
//              print(response.toString());
    // ignore: return_of_invalid_type
//      return response;

//      var uri = new Uri.http(Protocol.URL_BASE, url,body);
//      var request =await _HttpClint.postUrl(uri);
//      var response = await request.close();
//      var responseBody = await response.toString();
//
//        print(responseBody);
  }


//并发多个 请求
//       response= await Future.wait([dio.post("/info"),dio.get("/token")]);
  class responseHttp {
//  int statusCode;
    Future post(url, body) async {
      Dio dio = new Dio();
      Response response;
      response = await dio.post(URL_BASE + url, data: body);
      print(response);
//    var response = request.
      if (response.statusCode == 200) {
//      print(response.statusMessage+"=111111111111111="+ response.statusCode.toString());
////      print(response.data);
//      return  response.toString();
      } else {
//      _ErrorMessage(response. statusCode,response.statusMessage);
//      return response.toString();
      }
    }

    downloadFile() async {
      //下载文件
      Dio dio = new Dio();
      Response response;
      response = await dio.download("https://www.google.com/", "./xx.html");
    }


    _ErrorMessage(int statusCode, String errorMsg) {

    }
  }
