package com.example.marketplace;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.HashSet;
import java.util.Set;

/**
 * Activity principal que aloja todos los fragmentos de la aplicación (Single-Activity pattern).
 * Gestiona la configuración de la Toolbar y la navegación.
 */
public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar la Toolbar como ActionBar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtener el NavController desde el NavHostFragment.
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            // Definir los destinos de nivel superior (sin flecha de "Atrás" en la Toolbar).
            Set<Integer> topLevelDestinations = new HashSet<>();
            topLevelDestinations.add(R.id.loginFragment);
            topLevelDestinations.add(R.id.productListFragment);

            // Configurar la Toolbar para que funcione con el NavController.
            // Esto gestiona el título y el botón "Atrás" automáticamente.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        }
    }

    /**
     * Gestiona la navegación "hacia arriba" (botón de atrás en la Toolbar).
     * Se delega la acción al NavController.
     */
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
