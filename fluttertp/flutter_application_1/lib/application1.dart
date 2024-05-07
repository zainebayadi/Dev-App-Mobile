import 'package:flutter/material.dart';

class MyClass1 extends StatelessWidget {
 

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text('first app'),
        ),
        body: Center(
          child: Text('hello world'),
        ),
       
      ),
    );
  }
}