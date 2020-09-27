package com.example.covid.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.covid.R;

public class DetailActivity extends AppCompatActivity {

    TextView id_country, id_totalConfirmed, id_totalDeaths, id_totalRecovered;
    TextView id_newConfirmed, id_newDeaths, id_newRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        id_country = findViewById(R.id.id_country);
        id_newConfirmed = findViewById(R.id.id_newConfirmed);
        id_totalConfirmed = findViewById(R.id.id_totalConfirmed);
        id_newDeaths = findViewById(R.id.id_newDeaths);
        id_totalDeaths = findViewById(R.id.id_totalDeaths);
        id_newRecovered = findViewById(R.id.id_newRecovered);
        id_totalRecovered = findViewById(R.id.id_totalRecovered);

        id_country.setText(this.getIntent().getStringExtra("NAME"));
        id_newConfirmed.setText(this.getIntent().getStringExtra("NEWCONFIRMED"));
        id_totalConfirmed.setText(this.getIntent().getStringExtra("TOTALCONFIRMED"));
        id_newDeaths.setText(this.getIntent().getStringExtra("NEWDEATHS"));
        id_totalDeaths.setText(this.getIntent().getStringExtra("TOTALDEATHS"));
        id_newRecovered.setText(this.getIntent().getStringExtra("NEWRECOVERED"));
        id_totalRecovered.setText(this.getIntent().getStringExtra("TOTALRECOVERED"));
    }

}