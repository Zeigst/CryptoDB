package com.example.cryptodbproject;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrackerFragment extends Fragment {
    private RecyclerView currencyRV;
    private EditText searchEdt;
    private ArrayList<CurrencyModel> currencyModelArrayList;
    private CurrencyRVAdapter currencyRVAdapter;
    private ProgressBar loadingPB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tracker, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        searchEdt = (EditText) getView().findViewById(R.id.idEdtCurrency);
        loadingPB = (ProgressBar) getView().findViewById(R.id.idPBLoading);
        currencyRV = (RecyclerView) getView().findViewById(R.id.idRVcurrency);
        currencyModelArrayList = new ArrayList<>();

        Context context = getActivity();
        currencyRVAdapter = new CurrencyRVAdapter(currencyModelArrayList, context);

        // Set LayoutManager
        currencyRV.setLayoutManager(new LinearLayoutManager(context));

        // Set Adapter
        currencyRV.setAdapter(currencyRVAdapter);

        // Call API
        getData();

        // TextWatcher for EditText
        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Call filter function when text is changed
                filter(s.toString());
            }
        });
    }

    private void filter(String filter) {
        Context context = getActivity();
        ArrayList<CurrencyModel> filteredlist = new ArrayList<>();

        for (CurrencyModel item : currencyModelArrayList) {
            // Get items & add to filterd list
            if (item.getName().toLowerCase().contains(filter.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(context, "No currency found..", Toast.LENGTH_SHORT).show();
        } else {
            // Call filter function
            currencyRVAdapter.filterList(filteredlist);
        }
    }

    private void getData() {
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        Context context = getActivity();
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPB.setVisibility(View.GONE);
                try {
                    // Get Response
                    JSONArray dataArray = response.getJSONArray("data");
                    // Get & Save data from Response
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObj = dataArray.getJSONObject(i);
                        String symbol = dataObj.getString("symbol");
                        String name = dataObj.getString("name");
                        JSONObject quote = dataObj.getJSONObject("quote");
                        JSONObject USD = quote.getJSONObject("USD");
                        double price = USD.getDouble("price");
                        // Add to ArrayList
                        currencyModelArrayList.add(new CurrencyModel(name, symbol, price));
                    }
                    currencyRVAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    // JSON Exception
                    e.printStackTrace();
                    Toast.makeText(context, "Something's wrong. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handling Error
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