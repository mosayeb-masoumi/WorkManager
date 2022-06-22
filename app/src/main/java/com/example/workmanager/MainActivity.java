package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.workmanager.onetime.OneTimeJavaActivity;
import com.example.workmanager.onetime.OneTimeKotlinActivity;
import com.example.workmanager.periodical.PeriodicalJavaActivity;
import com.example.workmanager.periodical.PeriodicalKotlinActivity;

public class MainActivity extends AppCompatActivity {

      Button btn_periodical_java,btn_periodical_kotlin , btn_onetime_java , btn_onetime_kotlin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_periodical_java = findViewById(R.id.btn_periodical_java);
        btn_periodical_kotlin = findViewById(R.id.btn_periodical_kotlin);
        btn_onetime_java = findViewById(R.id.btn_onetime_java);
        btn_onetime_kotlin = findViewById(R.id.btn_onetime_kotlin);

        btn_periodical_java.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PeriodicalJavaActivity.class)));
        btn_periodical_kotlin.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PeriodicalKotlinActivity.class)));
        btn_onetime_java.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, OneTimeJavaActivity.class)));
        btn_onetime_kotlin.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, OneTimeKotlinActivity.class)));

    }
}