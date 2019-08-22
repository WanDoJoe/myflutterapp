import 'package:flutter/material.dart';

class Launcher extends StatefulWidget{
 
 @override
 State<StatefulWidget> createState(){
 
    return _LauncherState();
 }
}

class _LauncherState extends State<Launcher>{
 
 @override
 Widget  build(BuildContext context){
 
    return  Scaffold(appBar: AppBar(
      title: new Text("CustomMultiRenderDemoPage"),
    ),
      body: new Center(
        child: new Text("aaaaaaaa"),
      ),
      persistentFooterButtons: <Widget>[
        new FlatButton(
          color: Colors.amberAccent,
          onPressed: (){
                setState(() {
              print("按钮11111");
                });
          },
          child: new Text("按钮1",style: TextStyle(color: Colors.white),),
        ),
        new FlatButton(
          color: Colors.amberAccent,

          onPressed: (){
            setState(() {
              print("按钮22222");
            });
          },
          child: new Text("按钮2",style: TextStyle(color: Colors.white),),
        )
      ],
    );
 }
}
