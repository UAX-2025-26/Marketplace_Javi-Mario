package com.example.marketplace.ui.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.marketplace.data.CartRepository;
import com.example.marketplace.data.local.CartItem;
import java.util.List;

/**
 * ViewModel para el carrito de compras.
 * Conecta la UI con el CartRepository y sobrevive a cambios de configuración.
 */
public class CartViewModel extends AndroidViewModel {

    private CartRepository repository;
    private final LiveData<List<CartItem>> allCartItems;

    public CartViewModel (Application application) {
        super(application);
        // El ViewModel no debe acceder directamente a la BD, sino a través de un repositorio.
        repository = new CartRepository(application);
        allCartItems = repository.getAllCartItems();
    }

    /**
     * Expone los datos del carrito como LiveData para que la UI los observe.
     */
    public LiveData<List<CartItem>> getAllCartItems() { 
        return allCartItems; 
    }

    /**
     * Delega la lógica de inserción o actualización al repositorio.
     */
    public void insertOrUpdate(CartItem cartItem, int quantity) { 
        repository.insertOrUpdate(cartItem, quantity); 
    }

    /**
     * Delega la acción de vaciar el carrito al repositorio.
     */
    public void clearCart() {
        repository.clearCart();
    }
}
