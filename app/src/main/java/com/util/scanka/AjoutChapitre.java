package com.util.scanka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AjoutChapitre extends AppCompatActivity {
    private ImageButton btn_retour_chapitre;
    private ImageView iv_ajout_chapitre;
    private EditText edt_titre_ajout_chapitre, edt_nb_suiv_chap;
    private Button btn_valid_ajout_chapitre;
    private Spinner sp_livre;
    private DataBaseHelperLivre dbhl;
    public static final String NB_CHAP = "nb chap";
    public static final String NB_CHAP_SUIV = "nb chap suiv";
    public static final int response_ajt_chapitre = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_chapitre);

        btn_retour_chapitre = findViewById(R.id.btn_retour_chapitre);
        iv_ajout_chapitre = findViewById(R.id.iv_ajout_chapitre);
        edt_titre_ajout_chapitre = findViewById(R.id.edt_titre_ajout_chapitre);
        btn_valid_ajout_chapitre = findViewById(R.id.btn_valid_ajout_chapitre);
        edt_nb_suiv_chap = findViewById(R.id.edt_nb_suiv_chap);
        sp_livre = findViewById(R.id.sp_livre);


        dbhl = new DataBaseHelperLivre(getApplicationContext());

        ArrayList<Livre> listeLivre = dbhl.recupLivre();
        ArrayList<String> titreLivre = new ArrayList<>();
        for(Livre livre : listeLivre){
            titreLivre.add(livre.getTitle());
        }

        ArrayAdapter<String> listeLivreAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, titreLivre);
        listeLivreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_livre.setAdapter(listeLivreAdapter);

        btn_valid_ajout_chapitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titre = edt_titre_ajout_chapitre.getText().toString();
                int nb_chap_suiv = Integer.parseInt(edt_nb_suiv_chap.getText().toString());
                Intent intent = new Intent();
                intent.putExtra(NB_CHAP, new Chapitre(-1,titre,Integer.parseInt(titre), 30, 0, false, listeLivre.get(sp_livre.getSelectedItemPosition()).getId()));
                intent.putExtra(NB_CHAP_SUIV, nb_chap_suiv);
                setResult(response_ajt_chapitre, intent);
                finish();

            }
        });

        btn_retour_chapitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}