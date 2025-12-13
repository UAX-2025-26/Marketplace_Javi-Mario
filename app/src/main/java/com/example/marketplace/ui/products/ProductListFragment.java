package com.example.marketplace.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.marketplace.R;
import com.example.marketplace.databinding.FragmentProductListBinding;
import com.example.marketplace.models.Product;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * Muestra la lista de productos disponibles usando un RecyclerView.
 * Desde aquí, el usuario puede navegar al detalle de un producto o al carrito.
 */
public class ProductListFragment extends Fragment implements ProductAdapter.OnProductClickListener {

    private FragmentProductListBinding binding;
    private ProductAdapter productAdapter;
    private NavController navController;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilitar el menú de opciones para este fragment.
        setHasOptionsMenu(true);
        mAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setupRecyclerView();

        // Listener del FAB para ir al carrito.
        binding.fabGoToCart.setOnClickListener(v -> {
            navController.navigate(R.id.action_productListFragment_to_cartFragment);
        });
    }

    /**
     * Configura el RecyclerView, su LayoutManager y el Adapter.
     */
    private void setupRecyclerView() {
        binding.recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        // Los datos de productos están hardcodeados por ahora.
        productAdapter = new ProductAdapter(getChristmasProducts(), this);
        binding.recyclerViewProducts.setAdapter(productAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflar el menú de la Toolbar.
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_view_orders) {
            // Navegar al historial de pedidos.
            navController.navigate(R.id.action_productListFragment_to_orderListFragment);
            return true;
        } else if (itemId == R.id.action_logout) {
            // Cerrar sesión y volver al login.
            mAuth.signOut();
            navController.navigate(R.id.action_global_to_loginFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Devuelve una lista de productos de ejemplo.
     * En una app real, esto vendría de una API o base de datos.
     */
    private List<Product> getChristmasProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Árbol de Navidad Artificial", "1.80, fácil de montar.", 89.99));
        products.add(new Product("2", "Set de 50 Bolas de Navidad", "Rojas y doradas.", 19.95));
        products.add(new Product("3", "Luces LED de Colores", "10 metros, 8 modos de iluminación.", 25.50));
        products.add(new Product("4", "Jersey Navideño con Renos", "100% algodón.", 35.00));
        products.add(new Product("5", "Turrón de Jijona Artesano", "Tableta de 300g.", 9.75));
        products.add(new Product("6", "Calcetín de Papá Noel para Chimenea", "Grande, para colgar.", 12.00));
        return products;
    }

    /**
     * Callback del listener del adapter. Se ejecuta al hacer clic en un producto.
     * @param product El producto seleccionado.
     */
    @Override
    public void onProductClick(Product product) {
        // Navegar a la pantalla de detalle, pasando los datos del producto de forma segura con Safe Args.
        ProductListFragmentDirections.ActionProductListFragmentToProductDetailFragment action =
                ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        (float) product.getPrice()
                );
        navController.navigate(action);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
