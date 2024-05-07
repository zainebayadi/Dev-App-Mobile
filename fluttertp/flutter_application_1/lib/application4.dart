import 'package:flutter/material.dart';

class MyClass4 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text('4émme app flutter'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
            Container(
                child:Image.asset("images/1.jpg", fit: BoxFit.fill,),
                width: 350,
            ),
            Text("hello world!")
                      ],),
        )
       
      ),
    );
  }
}