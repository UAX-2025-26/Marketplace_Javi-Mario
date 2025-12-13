package com.example.marketplace.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.marketplace.databinding.FragmentOrderListBinding;
import com.example.marketplace.ui.viewmodels.OrderViewModel;

import java.util.ArrayList;

/**
 * Muestra el historial de pedidos del usuario en una lista.
 * Permite borrar todo el historial.
 */
public class OrderListFragment extends Fragment {

    private FragmentOrderListBinding binding;
    private OrderViewModel orderViewModel;
    private OrderAdapter orderAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        setupRecyclerView();

        // Observar la lista de pedidos del ViewModel.
        // La UI se actualiza reactivamente a los cambios en la base de datos.
        orderViewModel.getAllOrders().observe(getViewLifecycleOwner(), orders -> {
            orderAdapter.setOrders(orders);
            // Mostrar u ocultar el botón de borrar según si hay pedidos o no.
            binding.fabClearOrders.setVisibility(orders.isEmpty() ? View.GONE : View.VISIBLE);
        });

        // Listener para borrar el historial de pedidos.
        binding.fabClearOrders.setOnClickListener(v -> {
            orderViewModel.clearOrders();
            Toast.makeText(getContext(), "Historial de pedidos borrado", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupRecyclerView() {
        binding.recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inicializar el adapter con una lista vacía. Los datos llegarán desde LiveData.
        orderAdapter = new OrderAdapter(new ArrayList<>());
        binding.recyclerViewOrders.setAdapter(orderAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
