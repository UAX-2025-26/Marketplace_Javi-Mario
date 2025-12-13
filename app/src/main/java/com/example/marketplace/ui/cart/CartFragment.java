package com.example.marketplace.ui.cart;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.marketplace.data.local.CartItem;
import com.example.marketplace.data.local.Order;
import com.example.marketplace.databinding.FragmentCartBinding;
import com.example.marketplace.ui.viewmodels.CartViewModel;
import com.example.marketplace.ui.viewmodels.OrderViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Muestra el contenido del carrito de compras y permite al usuario finalizar la compra.
 */
public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartViewModel cartViewModel;
    private OrderViewModel orderViewModel;
    private CartAdapter cartAdapter;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        // Inicializar ViewModels.
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        setupRecyclerView();

        // Observar los items del carrito desde el ViewModel.
        // La UI se actualiza automáticamente cuando los datos cambian.
        cartViewModel.getAllCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            cartAdapter.setCartItems(cartItems);
            // Habilitar el botón de checkout solo si el carrito no está vacío.
            binding.buttonCheckout.setEnabled(cartItems != null && !cartItems.isEmpty());
        });

        // Listener para el botón de finalizar compra.
        binding.buttonCheckout.setOnClickListener(v -> {
            List<CartItem> currentItems = cartViewModel.getAllCartItems().getValue();
            if (currentItems != null && !currentItems.isEmpty()) {
                createOrder(currentItems);
            } else {
                Toast.makeText(getContext(), "El carrito está vacío", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Crea un nuevo pedido, lo guarda en la base de datos y vacía el carrito.
     */
    private void createOrder(List<CartItem> cartItems) {
        StringBuilder itemsSummary = new StringBuilder();
        double totalPrice = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            itemsSummary.append(String.format("%s (x%d)", item.getName(), item.getQuantity()));
            if (i < cartItems.size() - 1) {
                itemsSummary.append(", ");
            }
            totalPrice += item.getPrice() * item.getQuantity();
        }

        // Crear y guardar el nuevo pedido.
        Order newOrder = new Order(System.currentTimeMillis(), itemsSummary.toString(), totalPrice);
        orderViewModel.insert(newOrder);
        
        // Vaciar el carrito.
        cartViewModel.clearCart();

        Toast.makeText(getContext(), "Pedido realizado con éxito", Toast.LENGTH_SHORT).show();
        // Volver a la pantalla anterior.
        navController.navigateUp();
    }

    private void setupRecyclerView() {
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapter(new ArrayList<>());
        binding.recyclerViewCart.setAdapter(cartAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
