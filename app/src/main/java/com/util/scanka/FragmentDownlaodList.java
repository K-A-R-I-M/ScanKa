package com.util.scanka;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.util.scanka.AjoutChapitre.NB_CHAP;
import static com.util.scanka.AjoutChapitre.NB_CHAP_SUIV;
import static com.util.scanka.AjoutChapitre.response_ajt_chapitre;

public class FragmentDownlaodList extends Fragment {
    private RecyclerView rv_mangas;
    private FloatingActionButton fab_add_mangas;
    private DLChapitreAdapter dlChapitreAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Chapitre> listeChapitre;
    private String titre;
    private DataBaseHelperChapitre dbhc;
    public static final int request_ajt_chapitre = 100;

    public FragmentDownlaodList() {
        this.titre = "Download";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download_list, container, false);

        rv_mangas = view.findViewById(R.id.rv_mangas);
        fab_add_mangas = view.findViewById(R.id.fab_add_mangas);

        dbhc = new DataBaseHelperChapitre(getContext());
        listeChapitre = dbhc.recupChapitre();
        dlChapitreAdapter = new DLChapitreAdapter(listeChapitre, getContext());
        layoutManager = new LinearLayoutManager(getContext());
        rv_mangas.setLayoutManager(layoutManager);
        rv_mangas.setAdapter(dlChapitreAdapter);

        fab_add_mangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AjoutChapitre.class);
                startActivityForResult(intent, request_ajt_chapitre);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == response_ajt_chapitre && requestCode == request_ajt_chapitre){
            Chapitre chapitre = (Chapitre) data.getSerializableExtra(NB_CHAP);
            int nb_chap_suiv = (Integer) data.getSerializableExtra(NB_CHAP_SUIV);
            int nb_base_chap = Integer.parseInt(chapitre.getName());
            for (int i = 0; i < nb_chap_suiv; i++) {

                chapitre.setName(String.valueOf(nb_base_chap+i));
                chapitre.setNb(nb_base_chap+i);
                System.out.println(chapitre);
                System.out.println(chapitre.getName());

                dbhc.insertChapitre(chapitre);
                chapitre.setId(dbhc.recupChapitre().size() - 1+i);

                listeChapitre.add(chapitre);
            }
            dlChapitreAdapter.notifyDataSetChanged();
        }
    }
}