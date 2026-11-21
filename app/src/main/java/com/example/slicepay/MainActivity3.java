package com.example.slicepay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    Button btnGorjeta0, btnGorjeta5, btnGorjeta10;
    EditText edtOutraGorjeta;
    TextView txtQtdPessoas;

    double valorConta = 0.0;
    double percentualGorjeta = 0.0;
    int qtdPessoas = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // recupera o valor enviado pela tela2
        valorConta = getIntent().getDoubleExtra("VALOR_CONTA", 0.0);

        btnGorjeta0 = findViewById(R.id.buttonpct_0);
        btnGorjeta5 = findViewById(R.id.buttonpct_5);
        btnGorjeta10 = findViewById(R.id.buttonpct_10);
        edtOutraGorjeta = findViewById(R.id.input_pct3);
        txtQtdPessoas = findViewById(R.id.quant_pessoas);

        // inicia o contador na tela como 1 e 0% como padrão
        txtQtdPessoas.setText(String.valueOf(qtdPessoas));
        atualizarCorBotoesGorjeta(btnGorjeta0);
    }
    public void selecionarGorjeta0(View view) {
        percentualGorjeta = 0.0;
        edtOutraGorjeta.setText("");
        atualizarCorBotoesGorjeta(btnGorjeta0);
    }

    public void selecionarGorjeta5(View view) {
        percentualGorjeta = 5.0;
        edtOutraGorjeta.setText("");
        atualizarCorBotoesGorjeta(btnGorjeta5);
    }

    public void selecionarGorjeta10(View view) {
        percentualGorjeta = 10.0;
        edtOutraGorjeta.setText("");
        atualizarCorBotoesGorjeta(btnGorjeta10);
    }
    // metodo para saber qual gorjeta ta selecionada
    public void atualizarCorBotoesGorjeta(Button selecionado) {
        int verdePadrao = Color.parseColor("#00B050");
        int verdeSelecionado = Color.parseColor("#00662E");

        btnGorjeta0.setBackgroundColor(verdePadrao);
        btnGorjeta5.setBackgroundColor(verdePadrao);
        btnGorjeta10.setBackgroundColor(verdePadrao);

        if (selecionado != null) {
            selecionado.setBackgroundColor(verdeSelecionado);
        }
    }

    public void diminuirPessoas(View view) {
        if (qtdPessoas > 1) {
            qtdPessoas--;
            txtQtdPessoas.setText(String.valueOf(qtdPessoas));
        } else {
            Toast.makeText(this, "Mínimo de 1 pessoa!", Toast.LENGTH_SHORT).show();
        }
    }

    public void aumentarPessoas(View view) {
        qtdPessoas++;
        txtQtdPessoas.setText(String.valueOf(qtdPessoas));
    }

    public void calcularResultado(View view) {
        // Verifica se o usuário digitou algo em "Outra porcentagem (%)"
        String outraTxt = edtOutraGorjeta.getText().toString().trim();
        if (!outraTxt.isEmpty()) {
            outraTxt = outraTxt.replace(",", ".");
            percentualGorjeta = Double.parseDouble(outraTxt);
        }

        // Faz o cálculo do total com gorjeta e o valor individual
        double valorGorjeta = valorConta * (percentualGorjeta / 100.0);
        double totalComGorjeta = valorConta + valorGorjeta;
        double valorIndividual = totalComGorjeta / qtdPessoas;

        // Envia os resultados para a Tela 4
        Intent intent = new Intent(this, MainActivity4.class);
        intent.putExtra("VALOR_INDIVIDUAL", valorIndividual);
        intent.putExtra("TOTAL_CONTA", totalComGorjeta);
        intent.putExtra("QTD_PESSOAS", qtdPessoas);
        startActivity(intent);
    }

    public void voltarTela2(View view) {
        finish();
    }


}