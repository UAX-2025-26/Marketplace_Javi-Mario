package com.example.marketplace.ui.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.marketplace.data.OrderRepository;
import com.example.marketplace.data.local.Order;

import java.util.List;

/**
 * ViewModel para el historial de pedidos.
 * Conecta la UI con el OrderRepository.
 */
public class OrderViewModel extends AndroidViewModel {

    private OrderRepository repository;
    private final LiveData<List<Order>> allOrders;

    public OrderViewModel (Application application) {
        super(application);
        // El ViewModel se comunica con el repositorio, que es la única fuente de verdad.
        repository = new OrderRepository(application);
        allOrders = repository.getAllOrders();
    }

    /**
     * Expone la lista de pedidos como LiveData para ser observada por la UI.
     */
    public LiveData<List<Order>> getAllOrders() { 
        return allOrders; 
    }

    /**
     * Delega la inserción de un nuevo pedido al repositorio.
     */
    public void insert(Order order) { 
        repository.insert(order); 
    }

    /**
     * Delega la acción de borrar todos los pedidos al repositorio.
     */
    public void clearOrders() {
        repository.clearOrders();
    }
}
