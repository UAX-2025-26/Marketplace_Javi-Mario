package com.example.marketplace.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.marketplace.data.local.AppDatabase;
import com.example.marketplace.data.local.CartDao;
import com.example.marketplace.data.local.CartItem;
import java.util.List;

public class CartRepository {

    private CartDao cartDao;
    private LiveData<List<CartItem>> allCartItems;

    public CartRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        cartDao = db.cartDao();
        allCartItems = cartDao.getAllCartItems();
    }

    public LiveData<List<CartItem>> getAllCartItems() {
        return allCartItems;
    }

    // Lógica para insertar o actualizar un artículo
    public void insertOrUpdate(CartItem cartItem, int quantity) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            CartItem existingItem = cartDao.getCartItemById(cartItem.getProductId());
            if (existingItem != null) {
                // Si el artículo existe, actualiza la cantidad
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                cartDao.update(existingItem);
            } else {
                // Si no existe, inserta el nuevo artículo con la cantidad especificada
                cartItem.setQuantity(quantity);
                cartDao.insert(cartItem);
            }
        });
    }

    public void clearCart() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            cartDao.clearCart();
        });
    }
}
