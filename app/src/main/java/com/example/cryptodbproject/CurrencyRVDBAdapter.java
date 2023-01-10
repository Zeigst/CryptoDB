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

public class CurrencyRVDBAdapter extends RecyclerView.Adapter<CurrencyRVDBAdapter.CurrencyViewholder> {
    private static DecimalFormat df6 = new DecimalFormat("#.######");
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private ArrayList<CurrencyDBModel> currencyDBModels;
    private Context context;

    public CurrencyRVDBAdapter(ArrayList<CurrencyDBModel> currencyDBModels, Context context) {
        this.currencyDBModels = currencyDBModels;
        this.context = context;
    }

    @NonNull
    @Override
    public CurrencyRVDBAdapter.CurrencyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout file
        View view = LayoutInflater.from(context).inflate(R.layout.currency_db_rv_item, parent, false);
        return new CurrencyRVDBAdapter.CurrencyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRVDBAdapter.CurrencyViewholder holder, int position) {
        // Set data to RecyclerView
        CurrencyDBModel modal = currencyDBModels.get(position);
        holder.symbolDBTV.setText(modal.getSymbol());
        holder.nameDBTV.setText(modal.getName());
        holder.priceDBTV.setText("$ " + df2.format(modal.getPrice()));
        holder.change1hDBTV.setText(df6.format(modal.getChange1h()) + " %");
        holder.change24hDBTV.setText(df6.format(modal.getChange24h()) + " %");
        holder.marketcapDBTV.setText(df2.format(modal.getMarket_cap()));
        holder.csupplyDBTV.setText(df2.format(modal.getC_supply()));
        holder.lastupdatedDBTV.setText(modal.getLast_updated());
    }

    @Override
    public int getItemCount() {
        return currencyDBModels.size();
    }

    public class CurrencyViewholder extends RecyclerView.ViewHolder {
        private TextView symbolDBTV, nameDBTV, priceDBTV, change1hDBTV, change24hDBTV, marketcapDBTV, csupplyDBTV, lastupdatedDBTV;

        public CurrencyViewholder(@NonNull View itemView) {
            super(itemView);
            // Initialize TextViews
            symbolDBTV = itemView.findViewById(R.id.idDBTVSymbol);
            nameDBTV = itemView.findViewById(R.id.idDBTVName);
            priceDBTV = itemView.findViewById(R.id.idDBTVPrice);
            change1hDBTV = itemView.findViewById(R.id.idDBTVChange1h);
            change24hDBTV = itemView.findViewById(R.id.idDBTVChange24h);
            marketcapDBTV = itemView.findViewById(R.id.idDBTVMarketCap);
            csupplyDBTV = itemView.findViewById(R.id.idDBTVCSupply);
            lastupdatedDBTV = itemView.findViewById(R.id.idDBTVLastUpdated);
        }
    }
}
