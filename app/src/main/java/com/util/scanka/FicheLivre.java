package com.util.scanka;

import static com.util.scanka.FragmentVisualisation.CURRENT_LIVRE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class FicheLivre extends AppCompatActivity {

    private DataBaseHelperChapitre dbhc;
    private RecyclerView rv_livre_chapitre;
    private ChapitreAdapter chapitreAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView tv_toolbar_livre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_livre);

        Livre livre = (Livre) getIntent().getExtras().get(CURRENT_LIVRE);

        tv_toolbar_livre = findViewById(R.id.tv_toolbar_livre);
        tv_toolbar_livre.setText(livre.getTitle());

        dbhc = new DataBaseHelperChapitre(getApplicationContext());
        ArrayList<Chapitre> listeChapitres = dbhc.recupChapitresLivre(livre.getId());
        rv_livre_chapitre = findViewById(R.id.rv_livre_chapitre);
        chapitreAdapter = new ChapitreAdapter(getApplicationContext(), listeChapitres);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rv_livre_chapitre.setAdapter(chapitreAdapter);
        rv_livre_chapitre.setLayoutManager(layoutManager);



    }
}