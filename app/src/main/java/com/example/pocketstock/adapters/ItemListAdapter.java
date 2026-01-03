package com.example.pocketstock.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketstock.R;
import com.example.pocketstock.models.Item;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private final Context context;
    private final List<Item> items;

    public ItemListAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName, category, price, quantity;

        public ViewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productNameTextView);
            category = itemView.findViewById(R.id.categoryTextView);
            price = itemView.findViewById(R.id.priceTextView);
            quantity = itemView.findViewById(R.id.quantityTextView);

        }

        public TextView getProductName() {
            return productName;
        }

        public TextView getCategory() {
            return category;
        }

        public TextView getPrice() {
            return price;
        }

        public TextView getQuantity() {
            return quantity;
        }
    }

    @NonNull
    @Override
    public ItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_view_holder, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ViewHolder holder, int position) {

        holder.getProductName().setText(items.get(position).productName);
        holder.getCategory().setText(items.get(position).category);
        holder.getPrice().setText("Php " + items.get(position).price);
        holder.getQuantity().setText("Stock: " + (items.get(position).quantity));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View addItemLayout = LayoutInflater.from(context).inflate(R.layout.add_item_layout, null);

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                builder
                        .setView(addItemLayout)
                        .setTitle("Update Item")
                        .setMessage("Update this item.")
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {



                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        }).setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {




                            }
                        });

                TextInputEditText itemName, category, price, quantity ;
                itemName = addItemLayout.findViewById(R.id.itemNameInput);
                category = addItemLayout.findViewById(R.id.categoryInput);
                price = addItemLayout.findViewById(R.id.priceInput);
                quantity = addItemLayout.findViewById(R.id.quantityInput);

                itemName.setText(items.get(position).productName);
                category.setText(items.get(position).category);
                price.setText(String.valueOf(items.get(position).price));
                quantity.setText(String.valueOf(items.get(position).quantity));

                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
