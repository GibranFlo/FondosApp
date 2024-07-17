package com.gibranflores.fondos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.gibranflores.fondos.FragmentosAdministrador.InicioAdmin;
import com.gibranflores.fondos.FragmentosAdministrador.ListarAdmin;
import com.gibranflores.fondos.FragmentosAdministrador.PerfilAdmin;
import com.gibranflores.fondos.FragmentosAdministrador.RegistroAdmin;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityAdministrador extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout DrawerLayout;

    FirebaseAuth FirebaseAuth;
    FirebaseUser User;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_administrador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout_A), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.ToolBarA);
        setSupportActionBar(toolbar);

        DrawerLayout = findViewById(R.id.drawer_layout_A);

        NavigationView navigationView = findViewById(R.id.nav_viewA);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, DrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        DrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FirebaseAuth = FirebaseAuth.getInstance();
        User = FirebaseAuth.getCurrentUser();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new InicioAdmin()).commit();
            navigationView.setCheckedItem(R.id.InicioAdmin);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();

        if (itemId == R.id.InicioAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new InicioAdmin()).commit();
        } else if (itemId == R.id.PerfilAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new PerfilAdmin()).commit();
        } else if (itemId == R.id.RegistrarAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new RegistroAdmin()).commit();
        } else if (itemId == R.id.ListarAdmin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA,
                    new ListarAdmin()).commit();
        } else if (itemId == R.id.Salir) {
            CerrarSesion();
            // Aquí podrías agregar la lógica para cerrar sesión
        }

        DrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ComprobandoInicioSesion() {

        if (User != null) {

            Toast.makeText(this, "Se ha iniciado sesión", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(MainActivityAdministrador.this, MainActivity.class));
            finish();
        }
    }

    private void CerrarSesion() {
        FirebaseAuth.signOut();
        startActivity(new Intent(MainActivityAdministrador.this, MainActivity.class));
        Toast.makeText(this, "Cerró sesión Correctamente", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        ComprobandoInicioSesion();
        super.onStart();
    }
}
