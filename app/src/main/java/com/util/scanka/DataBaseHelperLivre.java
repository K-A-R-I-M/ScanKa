package com.util.scanka;

import static com.util.scanka.DataBaseHelper.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelperLivre {
    SQLiteOpenHelper dbhelper;

    public DataBaseHelperLivre(Context context) {
        dbhelper = new DataBaseHelper(context);
    }

    public void insertLivre(Livre livre){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_LIVRE_COL_2, livre.getTitle());
        contentValues.put(TABLE_LIVRE_COL_3, livre.getId_image());
        contentValues.put(TABLE_LIVRE_COL_4, livre.getDescription());
        db.insert(TABLE_LIVRE, null, contentValues);

    }

    public ArrayList<Livre> recupLivre(){
        ArrayList<Livre> listeLivre = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor allEvent = db.rawQuery("Select * from "+TABLE_LIVRE+";", null);

        while (allEvent.moveToNext()) {
            int id = allEvent.getInt(allEvent.getColumnIndex(TABLE_LIVRE_COL_1));
            String title = allEvent.getString(allEvent.getColumnIndex(TABLE_LIVRE_COL_2));
            int id_image = allEvent.getInt(allEvent.getColumnIndex(TABLE_LIVRE_COL_3));
            String description = allEvent.getString(allEvent.getColumnIndex(TABLE_LIVRE_COL_4));
            //System.out.println("|"+id+"|"+title+"|"+id_image+"|"+description);
            listeLivre.add(new Livre(id, title, id_image, description));
        }
        return listeLivre;
    }

    public void modifyLivre(Livre currentItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_LIVRE_COL_2, currentItem.getTitle());
        contentValues.put(TABLE_LIVRE_COL_3, currentItem.getId_image());
        contentValues.put(TABLE_LIVRE_COL_4, currentItem.getDescription());
        String id = String.valueOf(currentItem.getId()+1);
        dbhelper.getWritableDatabase().update(TABLE_LIVRE, contentValues, TABLE_LIVRE_COL_1+" = ?", new String[]{id});
    }


}
