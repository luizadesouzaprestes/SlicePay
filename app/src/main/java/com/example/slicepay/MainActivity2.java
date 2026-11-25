package com.example.slicepay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    EditText edtValorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtValorTotal = findViewById(R.id.input_divisao2);
    }




    public void avancarTela3(View view) {
        String textoValor = edtValorTotal.getText().toString().trim();

        // notificação se estiver vazio
        if (textoValor.isEmpty()) {
            Toast.makeText(this, "Digite o valor total da conta!", Toast.LENGTH_SHORT).show();
            return;
        }

        // troca vírgula por ponto caso o usuário digite "50,00" em vez de "50.00"
        textoValor = textoValor.replace(",", ".");
        double valorConta = Double.parseDouble(textoValor);

        // notificação se o valor não for maior que zero
        if (valorConta <= 0) {
            Toast.makeText(this, "O valor deve ser maior que zero!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("VALOR_CONTA", valorConta);
        startActivity(intent);
    }

    public void voltarTela1(View view) {
        finish();
    }




}