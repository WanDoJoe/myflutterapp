import 'package:flutter/material.dart';
import 'package:toast/toast.dart';

import 'package:myflutterapp/com/wdq/utils/DeviceInfo.dart' as DeviceInfo;
import 'package:myflutterapp/com/wdq/httpapi/HttpApi.dart'as HttpApi;
import 'package:myflutterapp/com/wdq/pages/Launcher.dart' as Launcher;

class Login extends StatefulWidget{
  String userinfo="";
  @override
  State<StatefulWidget> createState(){
    return _LoginState();
  }
}

class _LoginState extends State<Login>{
  final _formKey = GlobalKey<FormState>();
  String _username, _password;
  bool _isObscure = true;
  Color _eyeColor;


  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Form(
            key: _formKey,
            child: ListView(
              padding: EdgeInsets.symmetric(horizontal: 22.0),
              children: <Widget>[
                SizedBox(
                  height: kToolbarHeight,
                ),
                buildTitle(),
                buildTitleLine(),
                SizedBox(height: 70.0),
                buildUserNameTextField(),
                SizedBox(height: 30.0),
                buildPasswordTextField(context),
//                buildForgetPasswordText(context),
                SizedBox(height: 30.0),
                buildLoginButton(context),
              ],
            )));
  }


  Align buildLoginButton(BuildContext context) {
    return Align(
      child: SizedBox(
        height: 45.0,
        width: 270.0,
        child: RaisedButton(
          child: Text(
            'Login',
            style: Theme.of(context).primaryTextTheme.headline,
          ),
          color: Colors.blue,
          onPressed: () {
            if (_formKey.currentState.validate()) {
              ///只有输入的内容符合要求通过才会到达此处
              _formKey.currentState.save();
              //TODO 执行登录方法
              print('username:$_username , assword:$_password');
              DeviceInfo.DeviceInfoClass.info().then((info){
                print(info);
                print("info=");
                print(info.toString());

                  HttpApi.HttpApis.Login("/api/v1/mobile/loginApp",
                      _username, _password, info)
                      .then((result) {
//                    result.data.toString();
                    if (result != null || result != "") {
                      print("接口返回的数据是:\n${result.data.toString()}");
//                  spUtil.SPUtils.put("userinfo", result);
                    } else {
                      print("接口返回的数据是:null");
//                  spUtil.SPUtils.put("userinfo","");
                    }

                    Navigator.push(context,
                        new MaterialPageRoute(builder: (BuildContext context) {
                          return new Launcher.Launcher();
                        }));
//               Navigator.pop(context);
                  }).catchError((errorMsg) {
                    print("异常" + errorMsg.toString());
//                spUtil.SPUtils.put("userinfo","");
                  });

              });



            }else{
//              print("输入用户名密码有误");
//                Toast.show("输入用户名密码有误", context,Toast.LENGTH_SHORT,);

            }
          },
          shape: StadiumBorder(side: BorderSide(color: Colors.white,),),
        ),
      ),
    );
  }

  Padding buildForgetPasswordText(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 8.0),
      child: Align(
        alignment: Alignment.centerRight,
        child: FlatButton(
          child: Text(
            '忘记密码？',
            style: TextStyle(fontSize: 14.0, color: Colors.grey),
          ),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
    );
  }

  TextFormField buildPasswordTextField(BuildContext context) {
    return TextFormField(
      onSaved: (String value) => _password = value,
      obscureText: _isObscure,
      validator: (String value) {
        if (value.isEmpty) {
          return '请输入密码';
        }
      },
      decoration: InputDecoration(
          labelText: '密码',
          labelStyle: TextStyle(fontSize: 20.0,color: Colors.grey),
          suffixIcon: IconButton(
              icon: Icon(
                Icons.remove_red_eye,
                color: _eyeColor,
              ),
              onPressed: () {
                setState(() {
                  _isObscure = !_isObscure;
                  _eyeColor = _isObscure
                      ? Colors.grey
                      : Theme.of(context).iconTheme.color;
                });
              })),
    );
  }

  TextFormField buildUserNameTextField() {
    return TextFormField(
      decoration: InputDecoration(
        labelText: '用户名',
        labelStyle: TextStyle(fontSize: 20.0,color: Colors.grey),
      ),
      validator: (String value) {
        if(value.isEmpty){
          return '请输入正确的用户名';
        }
      },
      onSaved: (String value) => _username = value,
    );
  }

  Padding buildTitleLine() {
    return Padding(
      padding: EdgeInsets.only(left: 12.0, top: 4.0),
      child: Align(
        alignment: Alignment.bottomLeft,
        child: Container(
          color: Colors.blue,
          width: 40.0,
          height: 2.0,
        ),
      ),
    );
  }

  Padding buildTitle() {
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: Text(
        '移动办公',
        style: TextStyle(fontSize: 24.0, color: Colors.blue,),
      ),
    );
  }

}