package com.gibranflores.fondos;

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

import com.gibranflores.fondos.FragmentoCliente.AcercaDeCliente;
import com.gibranflores.fondos.FragmentoCliente.CompartirCliente;
import com.gibranflores.fondos.FragmentoCliente.InicioCliente;
import com.gibranflores.fondos.FragmentosAdministrador.InicioAdmin;
import com.gibranflores.fondos.FragmentosAdministrador.ListarAdmin;
import com.gibranflores.fondos.FragmentosAdministrador.PerfilAdmin;
import com.gibranflores.fondos.FragmentosAdministrador.RegistroAdmin;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout DrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.ToolBar);
        setSupportActionBar(toolbar);

        DrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, DrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        DrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /*FirebaseAuth = FirebaseAuth.getInstance();
        User = FirebaseAuth.getCurrentUser();*/

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new InicioCliente()).commit();
            navigationView.setCheckedItem(R.id.InicioCliente);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();

        if (itemId == R.id.InicioCliente) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new InicioCliente()).commit();
        } else if (itemId == R.id.AcercaDE) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AcercaDeCliente()).commit();
        } else if (itemId == R.id.Compartir) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new CompartirCliente()).commit();
        }

        DrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}