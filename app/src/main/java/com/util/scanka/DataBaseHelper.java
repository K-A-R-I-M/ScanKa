package com.util.scanka;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MemoBook.db";

    /*------------------------- Table Chapitre -------------------------*/
    public static final String TABLE_CHAPITRE = "chapitre";

    public static final String TABLE_CHAPITRE_COL_1 = "id";
    public static final String TABLE_CHAPITRE_COL_2 = "name";
    public static final String TABLE_CHAPITRE_COL_3 = "nb";
    public static final String TABLE_CHAPITRE_COL_4 = "nb_pages";
    public static final String TABLE_CHAPITRE_COL_5 = "nb_pages_dl";
    public static final String TABLE_CHAPITRE_COL_6 = "dl_status";
    public static final String TABLE_CHAPITRE_COL_7 = "id_livre";

    /*------------------------- Table livre -------------------------*/
    public static final String TABLE_LIVRE = "livre";

    public static final String TABLE_LIVRE_COL_1 = "id";
    public static final String TABLE_LIVRE_COL_2 = "title";
    public static final String TABLE_LIVRE_COL_3 = "id_image";
    public static final String TABLE_LIVRE_COL_4 = "description";

    private static final String[] TABLES = {TABLE_CHAPITRE, TABLE_LIVRE};



    /*------------------------- Creation de la BD -------------------------*/
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /*------------------------- creation des table -------------------------*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_CHAPITRE+"\n" +
                "(\n" +
                "    "+TABLE_CHAPITRE_COL_1+" INTEGER PRIMARY KEY,\n" +
                "    "+TABLE_CHAPITRE_COL_2+" VARCHAR(255),\n" +
                "    "+TABLE_CHAPITRE_COL_3+" INTEGER,\n" +
                "    "+TABLE_CHAPITRE_COL_4+" INTEGER,\n" +
                "    "+TABLE_CHAPITRE_COL_5+" INTEGER,\n" +
                "    "+TABLE_CHAPITRE_COL_6+" VARCHAR(100), \n" +
                "    "+TABLE_CHAPITRE_COL_7+" INTEGER \n" +
                ");");
        db.execSQL("CREATE TABLE "+TABLE_LIVRE+"\n" +
                "(\n" +
                "    "+TABLE_LIVRE_COL_1+" INTEGER PRIMARY KEY,\n" +
                "    "+TABLE_LIVRE_COL_2+" VARCHAR(255),\n" +
                "    "+TABLE_LIVRE_COL_3+" INTEGER, \n" +
                "    "+TABLE_LIVRE_COL_4+" VARCHAR(255)\n" +
                ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        for (String table : TABLES){
            db.execSQL("drop table "+table+";");
        }
        onCreate(db);
    }
}
