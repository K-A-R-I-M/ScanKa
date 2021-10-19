package com.util.scanka;

import static com.util.scanka.AjoutLivre.NV_LIVRE;
import static com.util.scanka.AjoutLivre.response_ajt_livre;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragmentVisualisation extends Fragment {

    private FloatingActionButton fab_add_livre;
    private DataBaseHelperLivre dbhl;
    private RecyclerView.LayoutManager layoutManager;
    private LivreAdapter livreAdapter;
    private ArrayList<Livre> listeLivre;
    private RecyclerView rv_livre;
    public static final String CURRENT_LIVRE = "current livre";

    public static final int request_ajt_livre = 104;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_visualisation, container, false);

        fab_add_livre = view.findViewById(R.id.fab_add_livre);
        rv_livre = view.findViewById(R.id.rv_livre);

        dbhl = new DataBaseHelperLivre(getContext());
        listeLivre = dbhl.recupLivre();
        livreAdapter = new LivreAdapter(getContext(), listeLivre);
        layoutManager = new GridLayoutManager(getContext(),2);
        rv_livre.setLayoutManager(layoutManager);
        rv_livre.setAdapter(livreAdapter);


        fab_add_livre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AjoutLivre.class);
                startActivityForResult(intent, request_ajt_livre);
            }
        });

        livreAdapter.setOnItemClickListener(new LivreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), FicheLivre.class);
                intent.putExtra(CURRENT_LIVRE, listeLivre.get(position));
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == response_ajt_livre && requestCode == request_ajt_livre){

            Livre livre = (Livre) data.getSerializableExtra(NV_LIVRE);

            dbhl.insertLivre(livre);
            livre.setId(dbhl.recupLivre().size()-1);

            listeLivre.add(livre);
            livreAdapter.notifyDataSetChanged();
        }
    }
}