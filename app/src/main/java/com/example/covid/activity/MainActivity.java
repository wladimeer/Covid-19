package com.example.covid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import com.example.covid.R;
import com.example.covid.adapter.Adapter;
import com.example.covid.model.Country;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private String information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadInformation();
    }

    public void loadInformation() {
        AsyncHttpClient client = new AsyncHttpClient();
        String URL = "https://api.covid19api.com/summary";

        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                information = new String(responseBody);
                processInformation();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Error", error.toString());
            }
        });
    }

    private void processInformation() {
        try {
            JSONObject bodyInformation = new JSONObject(information);
            JSONArray countries = bodyInformation.getJSONArray("Countries");
            List<Country> countryList = new ArrayList<>();

            for(int i = 0; i < countries.length(); i++) {
                JSONObject country = countries.getJSONObject(i);

                Country objectCountry = new Country();
                objectCountry.setName(country.getString("Country"));
                objectCountry.setCode(country.getString("CountryCode"));
                objectCountry.setNewConfirmed(country.getInt("NewConfirmed"));
                objectCountry.setTotalConfirmed(country.getInt("TotalConfirmed"));
                objectCountry.setNewDeaths(country.getInt("NewDeaths"));
                objectCountry.setTotalDeaths(country.getInt("TotalDeaths"));
                objectCountry.setNewRecovered(country.getInt("NewRecovered"));
                objectCountry.setTotalRecovered(country.getInt("TotalRecovered"));
                countryList.add(objectCountry);
            }

            RecyclerView am_content = findViewById(R.id.am_content);
            am_content.setLayoutManager(new LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false
            ));

            Adapter adapter = new Adapter(MainActivity.this, countryList);
            am_content.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}