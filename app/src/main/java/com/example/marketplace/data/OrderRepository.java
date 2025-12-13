package com.example.marketplace.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.marketplace.data.local.AppDatabase;
import com.example.marketplace.data.local.Order;
import com.example.marketplace.data.local.OrderDao;

import java.util.List;

public class OrderRepository {

    private OrderDao orderDao;
    private LiveData<List<Order>> allOrders;

    public OrderRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        orderDao = db.orderDao();
        allOrders = orderDao.getAllOrders();
    }

    public LiveData<List<Order>> getAllOrders() {
        return allOrders;
    }

    public void insert(Order order) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            orderDao.insert(order);
        });
    }

    // Añadir el método para borrar todos los pedidos
    public void clearOrders() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            orderDao.clearOrders();
        });
    }
}
