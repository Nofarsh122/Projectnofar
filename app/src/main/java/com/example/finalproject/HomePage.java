package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.graphics.Insets;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    Button btnRegHome, btnLogHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init_views();
    }

    private void init_views() {
        btnLogHome=findViewById(R.id.btnLogHome);
        btnLogHome.setOnClickListener(this);
        btnRegHome=findViewById(R.id.btnRegHome);
        btnRegHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (btnRegHome==v) {
            Intent goReg = new Intent(getApplicationContext(), Register_Acticity.class);
            startActivity(goReg);

        }


    }
}