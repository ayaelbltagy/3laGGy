package com.example.ayaelbeltagye.a3laggy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1= (Button) findViewById(R.id.new_medicine);
        Button b2 = (Button)findViewById(R.id.all_medecines);

    }
    public void add (View v){
        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(i);
    }
    public void show (View v ){
        Intent j = new Intent(MainActivity.this,ThirdActivity.class);
        startActivity(j);
    }
    }


