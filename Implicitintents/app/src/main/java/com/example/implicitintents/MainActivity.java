package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText zone1;
    private EditText zone2;
    private EditText zone3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zone1=findViewById(R.id.website_edittext);
    }

    public void openWebsite(View view) {
        String texte = zone1.getText().toString();
        Uri webpage= Uri.parse(texte);
        Intent intent=new Intent(Intent.ACTION_VIEW,webpage);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Log.d("ImplicitIntents", "Can't handle this intent!");        }

    }

    public void openLocation(View view) {
        String texte1 = zone1.getText().toString();
        Uri addressUri=Uri.parse("geo:0,0?q="+texte1);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if(getIntent().resolveActivity(getPackageManager())!=null){
            startActivity(intent);

        }else {
            Log.d("ImplicitIntents", "Can't handle this intent!");        }

    }

    public void shareText(View view) {
        String texte2 = zone1.getText().toString();
        String mimeType="text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.app_name)
                .setText(texte2)
                .startChooser();
    }
}