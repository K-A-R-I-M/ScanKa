package com.util.scanka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.util.scanka.DataBaseHelper.*;

public class DataBaseHelperChapitre {
    SQLiteOpenHelper dbhelper;

    public DataBaseHelperChapitre(Context context) {
        dbhelper = new DataBaseHelper(context);

    }

    public void insertChapitre(Chapitre chapitre){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_CHAPITRE_COL_2, chapitre.getName());
        contentValues.put(TABLE_CHAPITRE_COL_3, chapitre.getNb());
        contentValues.put(TABLE_CHAPITRE_COL_4, chapitre.getNb_pages());
        contentValues.put(TABLE_CHAPITRE_COL_5, chapitre.getNb_pages_dl());
        contentValues.put(TABLE_CHAPITRE_COL_6, String.valueOf(chapitre.isDl_status()));
        contentValues.put(TABLE_CHAPITRE_COL_7, chapitre.getId_livre());
        db.insert(TABLE_CHAPITRE, null, contentValues);

    }

    public ArrayList<Chapitre> recupChapitre(){
        ArrayList<Chapitre> listeChapitre = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor allEvent = db.rawQuery("Select * from "+TABLE_CHAPITRE+";", null);

        while (allEvent.moveToNext()) {
            int id = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_1));
            String name = allEvent.getString(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_2));
            int nb = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_3));
            int nb_pages = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_4));
            int nb_pages_dl = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_5));
            boolean dl_status = Boolean.parseBoolean(allEvent.getString(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_6)));
            int id_livre = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_7));
            System.out.println(id+"|"+name+"|"+nb+"|"+nb_pages+"|"+nb_pages_dl+"|"+dl_status+"|");
            listeChapitre.add(new Chapitre(id, name, nb, nb_pages, nb_pages_dl, dl_status, id_livre));
        }

        return listeChapitre;
    }

    public ArrayList<Chapitre> recupChapitresLivre(int id_livre_recup){
        ArrayList<Chapitre> listeChapitre = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor allEvent = db.rawQuery("Select * from "+TABLE_CHAPITRE+" where "+TABLE_CHAPITRE_COL_7+" = "+id_livre_recup+";", null);

        while (allEvent.moveToNext()) {

            int id = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_1));
            String name = allEvent.getString(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_2));
            int nb = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_3));
            int nb_pages = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_4));
            int nb_pages_dl = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_5));
            boolean dl_status = Boolean.parseBoolean(allEvent.getString(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_6)));
            int id_livre = allEvent.getInt(allEvent.getColumnIndex(TABLE_CHAPITRE_COL_7));
            System.out.println(id+"|"+name+"|"+nb+"|"+nb_pages+"|"+nb_pages_dl+"|"+dl_status+"|");
            listeChapitre.add(new Chapitre(id, name, nb, nb_pages, nb_pages_dl, dl_status, id_livre));
        }

        return listeChapitre;
    }

    public void modifyChapitre(Chapitre currentItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_CHAPITRE_COL_2, currentItem.getName());
        contentValues.put(TABLE_CHAPITRE_COL_3, currentItem.getNb());
        contentValues.put(TABLE_CHAPITRE_COL_4, currentItem.getNb_pages());
        contentValues.put(TABLE_CHAPITRE_COL_5, currentItem.getNb_pages_dl());
        contentValues.put(TABLE_CHAPITRE_COL_6, String.valueOf(currentItem.isDl_status()));
        contentValues.put(TABLE_CHAPITRE_COL_7, currentItem.getId_livre());
        System.out.println(String.valueOf(currentItem.isDl_status()));
        String id = String.valueOf(currentItem.getId()+1);
        dbhelper.getWritableDatabase().update(TABLE_CHAPITRE, contentValues, TABLE_CHAPITRE_COL_1+" = ?", new String[]{id});
    }
}
