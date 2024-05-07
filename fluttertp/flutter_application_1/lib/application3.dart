import 'package:flutter/material.dart';

class MyClass3 extends StatelessWidget {
 

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text('troisiemme app flutter'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
            Image.asset("images/1.jpg"),
            Text("hello world!")
          ],),
        )
       
      ),
    );
  }
}