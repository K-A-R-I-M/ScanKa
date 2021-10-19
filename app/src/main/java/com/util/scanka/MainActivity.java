package com.util.scanka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    BottomNavigationView.OnNavigationItemSelectedListener gestionNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bnv = findViewById(R.id.menu_main_activity);
        gestionNav = creationGestionnaireNavigation();

        bnv.setOnNavigationItemSelectedListener(gestionNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.conteneur_fragment_main, new FragmentVisualisation()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener creationGestionnaireNavigation(){

        BottomNavigationView.OnNavigationItemSelectedListener gestNav;

        gestNav = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragmentSelection = null;
                switch (menuItem.getItemId()){
                    case R.id.btn_m_visu:
                        fragmentSelection = new FragmentVisualisation();
                        break;
                    case R.id.btn_m_download:
                        fragmentSelection = new FragmentDownlaodList();
                        break;
                    default:
                        fragmentSelection = new FragmentVisualisation();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.conteneur_fragment_main, fragmentSelection).commit();

                return true;
            }
        };

        return gestNav;

    }


}