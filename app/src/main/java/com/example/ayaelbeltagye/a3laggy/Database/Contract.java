package com.example.ayaelbeltagye.a3laggy.Database;

import android.provider.BaseColumns;

/**
 * Created by Aya Elbeltagye on 01-Jan-18.
 */
public class Contract {
    public static class medicines implements BaseColumns {
        public static final String TABLE_NAME="medicine";
        public static final String COLUMN_ID="id";
        public static final String COLUMN_MEDICINE_IMAGE="medicine_image";
        public static final String COLUMN_MEDICINE_TIME = "medicine_time";
        public static final String COLUMN_MEDICINE_NAME="medicine_name";
        public static final String COLUMN_MEDICINE_DOSE="medicine_dose";
        public static final String CREATE_TABLE_MEDICINE= "CREATE TABLE "+TABLE_NAME+"("
                +COLUMN_ID+" INTEGER PRIMARYKEY AUTOINCREAMENT, "
                +COLUMN_MEDICINE_IMAGE +" TEXT, "
                +COLUMN_MEDICINE_TIME +" TEXT, "
                +COLUMN_MEDICINE_NAME+" TEXT, "
                +COLUMN_MEDICINE_DOSE+" TEXT "
                +")";
    }
}
