package com.sistemaelite.portatilplus_2;

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

import com.google.android.material.navigation.NavigationView;
import com.sistemaelite.portatilplus_2.Fragment.MainFragment;
import com.sistemaelite.portatilplus_2.Fragment.ReservaFragment;
import com.sistemaelite.portatilplus_2.Fragment.SettingsFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_home) {
            // Código para nav_home
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
        } else if (item.getItemId() == R.id.nav_home) {
            // Código para nav_settings
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
        } else if (item.getItemId() == R.id.nav_settings) {
            // Código para nav_share
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReservaFragment()).commit();
        } else if (item.getItemId() == R.id.nav_bookmark) {
            // Código para nav_about
        } else if (item.getItemId() == R.id.nav_logout) {
            // Código para nav_logout
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}