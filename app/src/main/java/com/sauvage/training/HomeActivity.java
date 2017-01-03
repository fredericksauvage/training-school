package com.sauvage.training;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends Activity {

    Button btnDictee, btnMaths, btnConjugaison;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        this.btnConjugaison = (Button) findViewById(R.id.btnConjugaison);

        this.btnConjugaison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.this.gotoConjugaison();
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        TrainingApplication app = (TrainingApplication) this.getApplication();

        app.conjugaison.clear();

    }
    public void gotoConjugaison(){
        Intent intent = new Intent(this, CJActivity.class);
        this.startActivity(intent);
    }
}
