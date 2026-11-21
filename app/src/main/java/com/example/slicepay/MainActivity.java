package com.example.slicepay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnNovaDivisao;
    TextView textSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        btnNovaDivisao = findViewById(R.id.button1);
        textSobre = findViewById(R.id.sobre1);

        }
        public void abrirNovaDivisao(View v){
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
        public void abrirSobre(View v) {
            Intent intent = new Intent(this, MainActivity5.class);
            startActivity(intent);
        }


    }
