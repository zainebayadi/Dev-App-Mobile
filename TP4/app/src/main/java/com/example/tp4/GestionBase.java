package com.example.tp4;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GestionBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "base.db";
    public static final String DATABASE_TABLE = "Employes";
    public static final String col_1 = "ID";
    public static final String Col_2 = "NOM";
    public static final String col_3 = "EMAIL";
    public static final String col_4 = "PHONE";

    public GestionBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NOM TEXT,EMAIL TEXT ,PHONE TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public boolean insertData(String nom, String mail, String phone) {
        SQLiteDatabase dB = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(Col_2, nom);
        cn.put(col_3, mail);
        cn.put(col_4, phone);
        cn.put(Col_2, nom);
        long resultat = dB.insert(DATABASE_TABLE, null, cn);
        if (resultat == -1)
            return false;
        else
            return true;
    }

    public Cursor getALLData() {
        SQLiteDatabase dB = this.getWritableDatabase();
        Cursor resultat = dB.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
        return resultat;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, col_1 + " = ?", new String[]{String.valueOf(id)});
        db.close();

    }

    public void update(String nom, String mail, String phone, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(Col_2, nom);
        cVals.put(col_3, mail);
        cVals.put(col_4, phone);
        int count = db.update(DATABASE_TABLE, cVals, col_1 + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public class MainActivity extends AppCompatActivity {
        private Button b1, b2;
        private EditText nom, mail, phone;
        GestionBase dbb;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            b1 = findViewById(R.id.save);
            b2 = findViewById(R.id.base);
            nom = findViewById(R.id.editTextText);
            mail = findViewById(R.id.editTextTextEmailAddress);
            phone = findViewById(R.id.phone);
            dbb = new GestionBase(this);

            b1.setOnClickListener(v -> {
                if (!nom.getText().toString().isEmpty() && !mail.getText().toString().isEmpty() && !phone.getText().toString().isEmpty()) {
                    boolean inserted = dbb.insertData(nom.getText().toString(), mail.getText().toString(), phone.getText().toString());
                    if (inserted)
                        Toast.makeText(MainActivity.this, "Insertion avec succès", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Échec d'insertion", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Tous les champs doivent être remplis", Toast.LENGTH_SHORT).show();
                }
            });

            b2.setOnClickListener(v -> {
                Intent intl = new Intent(MainActivity.this, ManagingDB.class);
                startActivity(intl);
            });
        }
    }

    public class ManagingDB extends AppCompatActivity {
        private Button b;
        private ListView lv;
        GestionBase dataBase;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            b = findViewById(R.id.Retour);
            lv = findViewById(R.id.listView);
            dataBase = new GestionBase(this);
            viewData();

            Intent int2 = new Intent(ManagingDB.this, MainActivity.class);
            startActivity(int2);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    String[] items = {"Modifier", "Supprimer"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(ManagingDB.this);
                    builder.setTitle("Action");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0)
                                showUpdate(ManagingDB.this, lv, position);
                            else if (which == 1)
                                delete(lv, position);
                        }
                    });
                    builder.show();
                }
            });
        }

        private void showUpdate(Activity ac, ListView lv, int p) {
            Dialog dialog = new Dialog(ac);
            dialog.setContentView(R.layout.update_db);
            dialog.setTitle("Update");

            final EditText name = dialog.findViewById(R.id.editTextText2);
            final EditText mail = dialog.findViewById(R.id.editTextTextEmailAddress2);
            final EditText phone = dialog.findViewById(R.id.editTextPhone2);
            Button bt = dialog.findViewById(R.id.button2);

            final String[] chaine = lv.getAdapter().getItem(p).toString().split("_");
            name.setText(chaine[1]);
            mail.setText(chaine[2]);
            phone.setText(chaine[3]);

            int width = (int) (ac.getResources().getDisplayMetrics().widthPixels * 0.9);
            int height = (int) (ac.getResources().getDisplayMetrics().heightPixels * 0.7);
            dialog.getWindow().setLayout(width, height);
            dialog.show();

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = Integer.parseInt(chaine[0]);
                    dataBase.update(name.getText().toString(), mail.getText().toString(), phone.getText().toString(), i);
                    Toast.makeText(ManagingDB.this, "Mise à jour avec succès", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ManagingDB.this, ManagingDB.class);
                    startActivity(intent);
                    viewData();
                }
            });

        }

        private void delete(ListView lv, int p) {
            String[] chaine = lv.getAdapter().getItem(p).toString().split(" ");
            int i = Integer.parseInt(chaine[0]);
            dataBase.delete(i);
            Toast.makeText(ManagingDB.this, "Suppression avec succès", Toast.LENGTH_SHORT).show();
            viewData();
        }
        public void viewData(){
            Cursor c = dataBase.getALLData();
            ArrayList<String> list = new ArrayList<>();

            if (c.getCount() == 0){
                Toast.makeText(ManagingDB.this, "La base est vide", Toast.LENGTH_SHORT).show();
            }
            else {
                while(c.moveToNext()){
                    list.add(c.getString(0)+" "+c.getString(1)+ " " + c.getString(2)+ " " + c.getString(3));
                    ListAdapter listAdapter = new ArrayAdapter<>(ManagingDB.this,
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                    lv.setAdapter(listAdapter);
                }
            }
        }
    }
}
