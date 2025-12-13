package com.example.marketplace.ui.orders;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.data.local.Order;
import com.example.marketplace.databinding.ItemOrderBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Adapter para el RecyclerView que muestra el historial de pedidos.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del ítem con ViewBinding.
        ItemOrderBinding binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        // Vincular el pedido con el ViewHolder.
        Order currentOrder = orders.get(position);
        holder.bind(currentOrder);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    /**
     * Actualiza la lista de pedidos y notifica al adapter.
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder que contiene la vista de un solo pedido en el historial.
     */
    static class OrderViewHolder extends RecyclerView.ViewHolder {
        private final ItemOrderBinding binding;

        public OrderViewHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Asigna los datos del pedido a las vistas.
         */
        public void bind(Order order) {
            // Formatear la fecha a un formato más legible.
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            binding.textViewOrderDate.setText(dateFormat.format(new Date(order.getDate())));
            binding.textViewOrderItems.setText(order.getItemsSummary());
            binding.textViewOrderTotal.setText(String.format("Total: € %.2f", order.getTotalPrice()));
        }
    }
}
