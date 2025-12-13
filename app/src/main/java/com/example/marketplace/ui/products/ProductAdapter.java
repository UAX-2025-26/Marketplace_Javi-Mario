package com.example.marketplace.ui.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.databinding.ItemProductBinding;
import com.example.marketplace.models.Product;

import java.util.List;

/**
 * Adapter para el RecyclerView de la lista de productos.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;
    private final OnProductClickListener listener;

    /**
     * Interfaz para comunicar los eventos de clic desde el adapter al fragment.
     */
    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    public ProductAdapter(List<Product> productList, OnProductClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del ítem con ViewBinding.
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Vincular el producto de la posición actual con el ViewHolder.
        Product product = productList.get(position);
        holder.bind(product, listener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    /**
     * ViewHolder que contiene la vista de un solo producto.
     * Utiliza ViewBinding para acceder a las vistas.
     */
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductBinding binding;

        public ProductViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Asigna los datos del producto a las vistas y configura el listener de clic.
         */
        public void bind(final Product product, final OnProductClickListener listener) {
            binding.textViewProductName.setText(product.getName());
            binding.textViewProductDescription.setText(product.getDescription());
            binding.textViewProductPrice.setText(String.format("€ %.2f", product.getPrice()));
            
            // Propagar el evento de clic al fragment a través de la interfaz.
            itemView.setOnClickListener(v -> listener.onProductClick(product));
        }
    }
}
