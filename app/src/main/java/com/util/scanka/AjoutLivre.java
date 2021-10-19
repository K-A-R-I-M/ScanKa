package com.util.scanka;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AjoutLivre extends AppCompatActivity {

    private ImageButton btn_retour_livre;
    private Button btn_ajt_livre;
    private EditText edt_ajt_titre_livre;
    public static final String NV_LIVRE = "ajout livre";
    public static final int response_ajt_livre = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_livre);

        btn_retour_livre = findViewById(R.id.btn_retour_livre);
        btn_ajt_livre = findViewById(R.id.btn_ajt_livre);
        edt_ajt_titre_livre = findViewById(R.id.edt_ajt_titre_livre);

        btn_retour_livre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_ajt_livre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titre = edt_ajt_titre_livre.getText().toString();
                int id_image = 1;
                Intent intent = new Intent();
                intent.putExtra(NV_LIVRE, new Livre(-1, titre, id_image, "desc"));
                setResult(response_ajt_livre, intent);
                finish();
            }
        });

    }
}