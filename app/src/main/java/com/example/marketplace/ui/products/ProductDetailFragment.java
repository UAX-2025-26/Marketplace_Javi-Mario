package com.example.marketplace.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.marketplace.databinding.FragmentProductDetailBinding;
import com.example.marketplace.models.Product;
import com.example.marketplace.data.local.CartItem;
import com.example.marketplace.ui.viewmodels.CartViewModel;

/**
 * Fragment que muestra los detalles de un producto específico.
 * Permite al usuario seleccionar una cantidad y añadir el producto al carrito.
 */
public class ProductDetailFragment extends Fragment {

    private FragmentProductDetailBinding binding;
    private CartViewModel cartViewModel;
    private Product currentProduct;
    private int quantity = 1;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // Recuperar el producto de los argumentos de navegación (pasados desde ProductListFragment).
        if (getArguments() != null) {
            ProductDetailFragmentArgs args = ProductDetailFragmentArgs.fromBundle(getArguments());
            currentProduct = new Product(
                    args.getProductId(),
                    args.getProductName(),
                    args.getProductDescription(),
                    args.getProductPrice()
            );
            displayProductDetails(currentProduct);
        }

        setupQuantityControls();

        // Listener para añadir el producto al carrito.
        binding.buttonAddToCart.setOnClickListener(v -> {
            if (currentProduct != null) {
                addToCart(currentProduct, quantity);
            }
        });
    }

    /**
     * Configura los botones para aumentar/disminuir la cantidad.
     */
    private void setupQuantityControls() {
        binding.textViewQuantity.setText(String.valueOf(quantity));

        binding.buttonIncreaseQuantity.setOnClickListener(v -> {
            quantity++;
            binding.textViewQuantity.setText(String.valueOf(quantity));
        });

        binding.buttonDecreaseQuantity.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                binding.textViewQuantity.setText(String.valueOf(quantity));
            }
        });
    }

    /**
     * Muestra los datos del producto en la UI.
     */
    private void displayProductDetails(Product product) {
        binding.textViewDetailProductName.setText(product.getName());
        binding.textViewDetailProductDescription.setText(product.getDescription());
        binding.textViewDetailProductPrice.setText(String.format("€ %.2f", product.getPrice()));
    }

    /**
     * Añade el producto al carrito a través del ViewModel y navega hacia atrás.
     */
    private void addToCart(Product product, int quantity) {
        CartItem cartItem = new CartItem(product.getId(), product.getName(), product.getPrice(), 0);
        cartViewModel.insertOrUpdate(cartItem, quantity);
        Toast.makeText(getContext(), quantity + " x " + product.getName() + " añadido/s", Toast.LENGTH_SHORT).show();
        // Volver a la lista de productos.
        navController.navigateUp();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
