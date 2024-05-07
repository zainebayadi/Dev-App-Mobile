import 'package:flutter/material.dart';
void main()=>runApp(MyHomePage());
class MyHomePage extends StatelessWidget {
  final List Liste = List.generate(1000, (index){
    return{
      "id":index,
      "title":"this is the title $index",
      "subtitle":"this is the subtitle $index"
    };
  },
  );
  @override
  Widget build(BuildContext context){
    return MaterialApp(
      home: Scaffold(appBar: AppBar(
        title: Text('app'),
      ),
      body: ListView.builder(
        itemCount:Liste.length,
        itemBuilder: (context,index)=>Card(
        elevation: 6,
        margin: EdgeInsets.all(10),
        child: ListTile(
          leading: CircleAvatar(
            backgroundColor: Colors.amber,
            child: Text(Liste[index]["id"].toString()),
            ),
          title: Text(Liste[index]["title"]),
          subtitle: Text(Liste[index]["subtitle"]),
          trailing: Icon(Icons.add_link),            
          ),
        ),
        ),
      ),
    );

  }
}