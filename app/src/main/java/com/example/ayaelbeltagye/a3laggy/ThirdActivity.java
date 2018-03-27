package com.example.ayaelbeltagye.a3laggy;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ayaelbeltagye.a3laggy.Database.Helper;


public class ThirdActivity extends AppCompatActivity {
    ArrayList<medicine_details> list;
    MedcineAdaprt medicineAdapter;
    ListView l;
    Helper h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button b = (Button) findViewById(R.id.button);
        l = (ListView) findViewById(R.id.listView);
        EditText e = (EditText) findViewById(R.id.editText2);
        h = new Helper(this);
        list = h.get_medicine();
        medicineAdapter = new MedcineAdaprt(this, list);
        l.setAdapter(medicineAdapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent , View v, final int position, long id) {
                final medicine_details md = (medicine_details) parent.getItemAtPosition(position);
                AlertDialog.Builder adb = new AlertDialog.Builder(ThirdActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + md.getName().toString() );
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       list.remove(position);
                      boolean b =  h.delete_medicine(md );
                        Toast.makeText(ThirdActivity.this,String.valueOf(b),Toast.LENGTH_LONG).show();
                       medicineAdapter = new MedcineAdaprt(ThirdActivity.this, list);
                      l.setAdapter(medicineAdapter);
                    }});

                adb.create().show();
            }
        });
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterr(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void filterr ( String name ) {
        ArrayList<medicine_details> list2 = new ArrayList<medicine_details>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(name))
                list2.add(list.get(i));
        }
        medicineAdapter = new MedcineAdaprt(this, list2);
        l.setAdapter(medicineAdapter);

    }

    public void Add (View v){
        Intent i = new Intent(ThirdActivity.this,SecondActivity.class);
        startActivity(i);
    }
}