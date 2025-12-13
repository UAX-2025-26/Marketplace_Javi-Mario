package com.example.marketplace.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Order order);

    @Query("SELECT * FROM orders ORDER BY date DESC")
    LiveData<List<Order>> getAllOrders();

    @Query("DELETE FROM orders")
    void clearOrders();

}
