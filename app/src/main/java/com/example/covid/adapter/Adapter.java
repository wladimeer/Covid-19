package com.example.covid.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.covid.R;
import com.example.covid.activity.DetailActivity;
import com.example.covid.model.Country;
import java.util.*;

public class Adapter extends RecyclerView.Adapter<Adapter.CountryHolder> {

    private Activity activity;
    private List<Country> countryList;

    public Adapter(Activity activity, List<Country> countryList) {
        this.activity = activity;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_country, parent, false
        );

        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        Country country = countryList.get(position);

        holder.ic_country.setText(country.getName());
        holder.ic_code.setText(country.getCode());
        holder.id_newConfirmed = String.valueOf(country.getNewConfirmed());
        holder.ic_confirmed.setText(String.valueOf(country.getTotalConfirmed()));
        holder.id_newDeaths = String.valueOf(country.getNewDeaths());
        holder.ic_deaths.setText(String.valueOf(country.getTotalDeaths()));
        holder.id_newRecovered = String.valueOf(country.getNewRecovered());
        holder.ic_recovered.setText(String.valueOf(country.getTotalRecovered()));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {

        TextView ic_country, ic_code, ic_confirmed, ic_deaths, ic_recovered;
        String id_newConfirmed, id_newDeaths, id_newRecovered;
        CardView ic_preview;

        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            ic_country = itemView.findViewById(R.id.ic_country);
            ic_code = itemView.findViewById(R.id.ic_code);
            ic_confirmed = itemView.findViewById(R.id.ic_confirmed);
            ic_deaths = itemView.findViewById(R.id.ic_deaths);
            ic_recovered = itemView.findViewById(R.id.ic_recovered);
            ic_preview = itemView.findViewById(R.id.ic_preview);

            ic_preview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, DetailActivity.class);

                    intent.putExtra("NAME", ic_country.getText().toString());
                    intent.putExtra("NEWCONFIRMED", id_newConfirmed);
                    intent.putExtra("TOTALCONFIRMED", ic_confirmed.getText().toString());
                    intent.putExtra("NEWDEATHS", id_newDeaths);
                    intent.putExtra("TOTALDEATHS", ic_deaths.getText().toString());
                    intent.putExtra("NEWRECOVERED", id_newRecovered);
                    intent.putExtra("TOTALRECOVERED", ic_recovered.getText().toString());
                    activity.startActivity(intent);
                }
            });
        }

    }

}