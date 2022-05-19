package za.ac.cput.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    ImageView logo;
    TextView appName, groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.covidTracker_txt);
        groupName = findViewById(R.id.groupName_txt);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeScreen.class);

                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View, String> (logo, "logo_image");
                pairs[1] = new Pair<View, String> (appName, "appName_txt");
                pairs[2] = new Pair<View, String> (groupName, "groupName_txt");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, SPLASH_SCREEN);
    }
}