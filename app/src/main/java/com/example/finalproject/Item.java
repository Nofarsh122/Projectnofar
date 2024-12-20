package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.graphics.Insets;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public abstract class Item extends AppCompatActivity implements View.OnClickListener {

    EditText tvItemName, tvItemDesc, tvItemLoc, tvItemConPer;
    Button btnFindItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init_views();


    }

    private void init_views() {
        tvItemName=findViewById(R.id.tvItemName);
        tvItemDesc=findViewById(R.id.tvItemDesc);
        tvItemLoc=findViewById(R.id.tvItemLoc);
        tvItemConPer=findViewById(R.id.tvItemConPer);
        btnFindItem=findViewById(R.id.btnFindItem);
        btnFindItem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


    }

}