package com.example.marketplace.ui.cart;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.data.local.CartItem;
import com.example.marketplace.databinding.ItemCartBinding;

import java.util.List;

/**
 * Adapter para el RecyclerView del carrito de compras.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del ítem con ViewBinding.
        ItemCartBinding binding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Vincular el ítem del carrito con el ViewHolder.
        CartItem currentItem = cartItems.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    /**
     * Actualiza la lista de items y notifica al adapter.
     * Se llama desde el observador de LiveData en el fragment.
     */
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        // notifyDataSetChanged es simple pero efectivo para listas pequeñas.
        // Para optimizar, se podría implementar DiffUtil.
        notifyDataSetChanged();
    }

    /**
     * ViewHolder que contiene la vista de un solo ítem en el carrito.
     */
    static class CartViewHolder extends RecyclerView.ViewHolder {
        private final ItemCartBinding binding;

        public CartViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Asigna los datos del CartItem a las vistas.
         */
        public void bind(CartItem cartItem) {
            binding.textViewCartItemName.setText(cartItem.getName());
            binding.textViewCartItemQuantity.setText(String.format("x%d", cartItem.getQuantity()));
            binding.textViewCartItemPrice.setText(String.format("€ %.2f", cartItem.getPrice()));
        }
    }
}
