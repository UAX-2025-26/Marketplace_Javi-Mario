package com.example.marketplace.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private long date;
    private String itemsSummary;
    private double totalPrice;

    // Constructor
    public Order(long date, String itemsSummary, double totalPrice) {
        this.date = date;
        this.itemsSummary = itemsSummary;
        this.totalPrice = totalPrice;
    }

    // Getters
    public int getId() { return id; }
    public long getDate() { return date; }
    public String getItemsSummary() { return itemsSummary; }
    public double getTotalPrice() { return totalPrice; }

    // Setters
    public void setId(int id) { this.id = id; }
}
