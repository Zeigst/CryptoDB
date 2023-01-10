package com.example.cryptodbproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.CurrencyViewholder> {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private ArrayList<CurrencyModel> currencyModels;
    private Context context;

    public CurrencyRVAdapter(ArrayList<CurrencyModel> currencyModels, Context context) {
        this.currencyModels = currencyModels;
        this.context = context;
    }

   // Filter list function
    public void filterList(ArrayList<CurrencyModel> filterlist) {
        currencyModels = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyRVAdapter.CurrencyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout file
        View view = LayoutInflater.from(context).inflate(R.layout.currency_rv_item, parent, false);
        return new CurrencyRVAdapter.CurrencyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRVAdapter.CurrencyViewholder holder, int position) {
        // Setting data to RecyclerView
        CurrencyModel modal = currencyModels.get(position);
        holder.nameTV.setText(modal.getName());
        holder.rateTV.setText("$ " + df2.format(modal.getPrice()));
        holder.symbolTV.setText(modal.getSymbol());
    }

    @Override
    public int getItemCount() {
        return currencyModels.size();
    }

    // Create Viewholder class
    public class CurrencyViewholder extends RecyclerView.ViewHolder {
        private TextView symbolTV, rateTV, nameTV;

        public CurrencyViewholder(@NonNull View itemView) {
            super(itemView);
            // Initialize all textViews
            symbolTV = itemView.findViewById(R.id.idTVSymbol);
            rateTV = itemView.findViewById(R.id.idTVRate);
            nameTV = itemView.findViewById(R.id.idTVName);
        }
    }
}
