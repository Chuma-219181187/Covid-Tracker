package za.ac.cput.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class HomeScreen extends AppCompatActivity {

    RelativeLayout mapBtn, newsBtn, statsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mapBtn = findViewById(R.id.map_icon_layout);
        newsBtn = findViewById(R.id.news_icon_layout);
        statsBtn = findViewById(R.id.stats_icon_layout);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, MapScreen.class);
                startActivity(intent);
                finish();
            }
        });

        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, NewsScreen.class);
                startActivity(intent);
                finish();
            }
        });

        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, StatisticsScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}