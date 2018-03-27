package com.example.ayaelbeltagye.a3laggy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;
import android.widget.Toast;

import com.example.ayaelbeltagye.a3laggy.medicine_details;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.*;

/**
 * Created by Aya Elbeltagye on 01-Jan-18.
 */
public class Helper extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String DATABASE="medicine";

    public Helper(Context context) {
        super(context, DATABASE, null, VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contract.medicines.CREATE_TABLE_MEDICINE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.medicines.TABLE_NAME);
        onCreate(db);
    }
    public void add_medicine(medicine_details medicineDetails){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(Contract.medicines.COLUMN_MEDICINE_IMAGE, medicineDetails.getImage());
        value.put(Contract.medicines.COLUMN_MEDICINE_NAME,medicineDetails.getName());
        value.put(Contract.medicines.COLUMN_MEDICINE_DOSE,medicineDetails.getDose());
        value.put(Contract.medicines.COLUMN_MEDICINE_TIME,medicineDetails.getTime());
        db.insert(Contract.medicines.TABLE_NAME,null,value);
    }
    public boolean delete_medicine(medicine_details m ){
//       String s = String.valueOf(id);
      //  String s = id+"";
       String  [] args = {String.valueOf(m) };
       SQLiteDatabase db=getWritableDatabase();
      // db.delete(Contract.medicines.TABLE_NAME, Contract.medicines.COLUMN_ID + " =?", new String[]{String.valueOf(id)});
      // db.delete(Contract.medicines.TABLE_NAME, Contract.medicines.COLUMN_ID + "=" + s, null) ;
       // db.close();
     //  return db.delete(Contract.medicines.TABLE_NAME, Contract.medicines.COLUMN_ID + "= ?" ,args) > 0;
       return db.delete(Contract.medicines.TABLE_NAME ,Contract.medicines.COLUMN_ID+"=?", args ) >0 ;

    }

    public ArrayList<medicine_details> get_medicine(){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<medicine_details> List=new ArrayList<>();
        Cursor cursor=db.query(Contract.medicines.TABLE_NAME, null, null, null, null, null, null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                medicine_details m = new medicine_details();
                m.setImage(cursor.getString(1));
                m.setTime(cursor.getString(2));
                m.setName(cursor.getString(3));
                m.setDose(cursor.getString(4));
                List.add(m);

            }
        }
        for (int i =0 ; i< List.size() ; i ++){
            Log.e ("tset values" , List.get(i).toString());
        }
        return List;

    }
}

