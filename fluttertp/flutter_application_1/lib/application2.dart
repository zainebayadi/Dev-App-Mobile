import 'package:flutter/material.dart';

class MyClass2 extends StatelessWidget {
 

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: Text('first app'),
        ),
        body: Center(
          child: Container(
            decoration: BoxDecoration(
              border: Border.all(
                color: Colors.black,
                width: 8,
              ),
              borderRadius: BorderRadius.circular(20),

            ),
            child: Text('hello world' ,style: TextStyle(color: Colors.deepPurpleAccent),),

          ),
        ),
      ),
    );
  }
}