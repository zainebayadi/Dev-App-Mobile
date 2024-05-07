import 'package:flutter/material.dart';

class MyClass3 extends StatelessWidget {
 

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text('first app'),
        ),
        body: Center(
          child: Column(
            children: [
              Image.asset('images/1.jpg'),
              Text('hello world'),
            ],
          ),
        ),
       
      ),
    );
  }
}