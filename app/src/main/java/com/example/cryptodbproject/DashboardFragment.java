package com.example.cryptodbproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment {
    private RecyclerView currencyDBRV;
    private ArrayList<CurrencyDBModel> currencyDBModelArrayList;
    private CurrencyRVDBAdapter currencyRVDBAdapter;
    private Button refreshButton;
    private Button sortSymbolButton;
    private Button sortNameButton;
    private Button sortPriceButton;
    private Button sortChange1hButton;
    private Button sortChange24hButton;
    private Button sortMarketCapButton;
    private Button sortCSupplyButton;
    private Button sortLastUpdatedButton;
    private boolean bSymbol;
    private boolean bName;
    private boolean bPrice;
    private boolean bChange1h;
    private boolean bChange24h;
    private boolean bMarketCap;
    private boolean bCSupply;
    private boolean bLastUpdated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        currencyDBRV = (RecyclerView) getView().findViewById(R.id.idRVDBcurrency);
        currencyDBModelArrayList = new ArrayList<>();

        refreshButton = (Button) getView().findViewById(R.id.refresh_button);

        sortSymbolButton = (Button) getView().findViewById(R.id.db_symbol);
        bSymbol = true;
        sortNameButton = (Button) getView().findViewById(R.id.db_name);
        bName = true;
        sortPriceButton = (Button) getView().findViewById(R.id.db_price);
        bPrice = true;
        sortChange1hButton = (Button) getView().findViewById(R.id.db_change_1h);
        bChange1h = true;
        sortChange24hButton = (Button) getView().findViewById(R.id.db_change_24h);
        bChange24h = true;
        sortMarketCapButton = (Button) getView().findViewById(R.id.db_market_cap);
        bMarketCap = true;
        sortCSupplyButton = (Button) getView().findViewById(R.id.db_c_supply);
        bCSupply = true;
        sortLastUpdatedButton = (Button) getView().findViewById(R.id.db_last_updated);
        bLastUpdated = true;

        Context context = getActivity();
        currencyRVDBAdapter = new CurrencyRVDBAdapter(currencyDBModelArrayList, context);

        // Set LayoutManager
        currencyDBRV.setLayoutManager(new LinearLayoutManager(context));

        // Set Adapter
        currencyDBRV.setAdapter(currencyRVDBAdapter);

        // Call API
        getData();

        // Sort Buttons
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        sortSymbolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(currencyDBModelArrayList, new Comparator<CurrencyDBModel>() {
                    @Override
                    public int compare(CurrencyDBModel o1, CurrencyDBModel o2) {
                        return o1.getSymbol().compareTo(o2.getSymbol());
                    }
                });
                if (bSymbol) {
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bSymbol = false;
                }
                else {
                    Collections.reverse(currencyDBModelArrayList);
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bSymbol = true;
                }
            }
        });

        sortNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(currencyDBModelArrayList, new Comparator<CurrencyDBModel>() {
                    @Override
                    public int compare(CurrencyDBModel o1, CurrencyDBModel o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                if (bName) {
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bName = false;
                }
                else {
                    Collections.reverse(currencyDBModelArrayList);
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bName = true;
                }
            }
        });

        sortPriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(currencyDBModelArrayList, new Comparator<CurrencyDBModel>() {
                    @Override
                    public int compare(CurrencyDBModel o1, CurrencyDBModel o2) {
                        return (int) (o2.getPrice() - o1.getPrice());
                    }
                });
                if (bPrice) {
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bName = false;
                }
                else {
                    Collections.reverse(currencyDBModelArrayList);
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bPrice = true;
                }
            }
        });

        sortChange1hButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(currencyDBModelArrayList, new Comparator<CurrencyDBModel>() {
                    @Override
                    public int compare(CurrencyDBModel o1, CurrencyDBModel o2) {
                        return (int) (o2.getChange1h() - o1.getChange1h());
                    }
                });
                if (bChange1h) {
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bChange1h = false;
                }
                else {
                    Collections.reverse(currencyDBModelArrayList);
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bChange1h = true;
                }
            }
        });

        sortChange24hButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(currencyDBModelArrayList, new Comparator<CurrencyDBModel>() {
                    @Override
                    public int compare(CurrencyDBModel o1, CurrencyDBModel o2) {
                        return (int) (o2.getChange24h() - o1.getChange24h());
                    }
                });
                if (bChange24h) {
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bChange24h = false;
                }
                else {
                    Collections.reverse(currencyDBModelArrayList);
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bChange24h = true;
                }
            }
        });

        sortMarketCapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(currencyDBModelArrayList, new Comparator<CurrencyDBModel>() {
                    @Override
                    public int compare(CurrencyDBModel o1, CurrencyDBModel o2) {
                        return (int) (o2.getMarket_cap() - o1.getMarket_cap());
                    }
                });
                if (bMarketCap) {
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bMarketCap = false;
                }
                else {
                    Collections.reverse(currencyDBModelArrayList);
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bMarketCap = true;
                }
            }
        });

        sortCSupplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(currencyDBModelArrayList, new Comparator<CurrencyDBModel>() {
                    @Override
                    public int compare(CurrencyDBModel o1, CurrencyDBModel o2) {
                        return (int) (o2.getC_supply() - o1.getC_supply());
                    }
                });
                if (bCSupply) {
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bCSupply = false;
                }
                else {
                    Collections.reverse(currencyDBModelArrayList);
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bCSupply = true;
                }
            }
        });

        sortLastUpdatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(currencyDBModelArrayList, new Comparator<CurrencyDBModel>() {
                    @Override
                    public int compare(CurrencyDBModel o1, CurrencyDBModel o2) {
                        return o1.getLast_updated().compareTo(o2.getLast_updated());
                    }
                });
                if (bLastUpdated) {
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bLastUpdated = false;
                }
                else {
                    Collections.reverse(currencyDBModelArrayList);
                    currencyRVDBAdapter.notifyDataSetChanged();
                    bLastUpdated = true;
                }
            }
        });
    }

    // Call API Functions
    private void getData() {
        // Clear ArrayList
        currencyDBModelArrayList.clear();
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        Context context = getActivity();
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Get Response
                    JSONArray dataArray = response.getJSONArray("data");
                    // Get & Save Data form Response
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObj = dataArray.getJSONObject(i);
                        String symbol = dataObj.getString("symbol");
                        String name = dataObj.getString("name");
                        double c_supply = dataObj.getDouble("circulating_supply");
                        String last_updated = dataObj.getString("last_updated");
                        JSONObject quote = dataObj.getJSONObject("quote");
                        JSONObject USD = quote.getJSONObject("USD");
                        double price = USD.getDouble("price");
                        double change1h = USD.getDouble("percent_change_1h");
                        double change24h = USD.getDouble("percent_change_24h");
                        double market_cap = USD.getDouble("market_cap");

                        // Add to ArrayList
                        currencyDBModelArrayList.add(new CurrencyDBModel(name, symbol, price, change1h, change24h, market_cap, c_supply, last_updated));
                    }
                    currencyRVDBAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    // JSON Exception
                    e.printStackTrace();
                    Toast.makeText(context, "Something's wrong. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle Error
                Toast.makeText(context, "Something's wrong. Please try again later", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                // Set API key to headers
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY", "1b15b62c-2812-4019-9941-6a52cc9e4f92");
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }
}