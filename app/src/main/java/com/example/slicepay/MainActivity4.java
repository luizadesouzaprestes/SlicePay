package com.example.slicepay;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity4 extends AppCompatActivity {

    TextView txtValorIndividual, txtMensagemCobranca;
    EditText edtChavePix;
    CardView cardArredondar;

    double valorOriginal = 0.0;
    double valorAtual = 0.0;
    boolean estaArredondado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // recupera o valor calculado na tela3
        valorOriginal = getIntent().getDoubleExtra("VALOR_INDIVIDUAL", 0.0);
        valorAtual = valorOriginal;

        txtValorIndividual = findViewById(R.id.valor_final4);
        txtMensagemCobranca = findViewById(R.id.mensagem_c4);
        edtChavePix = findViewById(R.id.chave_pix4);

        // atualiza os textos logo que a tela abre
        atualizarTela();

        // atualiza a mensagem automaticamente enquanto o usuário digita a chave
        edtChavePix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                atualizarTela();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    public void atualizarTela() {
        String valorFormatado = String.format(new Locale("pt", "BR"), "%.2f", valorAtual);
        txtValorIndividual.setText(valorFormatado);

        String pix = edtChavePix.getText().toString().trim();
        if (pix.isEmpty()) {
            pix = "chavepix@chave.pix";
        }

        String mensagem = "Olá, segue sua parte da conta: R$ " + valorFormatado +
                ". Minha chave é: " + pix +
                ". Obrigada por partilhar esse momento comigo!";

        txtMensagemCobranca.setText(mensagem);
    }

    public void alternarArredondamento(View view) {
        estaArredondado = !estaArredondado;

        if (estaArredondado) {
            valorAtual = Math.ceil(valorOriginal); // Arredonda para cima
            cardArredondar.setCardBackgroundColor(Color.parseColor("#00662E")); // Verde escuro indicando ativo
            Toast.makeText(this, "Valor arredondado para cima!", Toast.LENGTH_SHORT).show();
        } else {
            valorAtual = valorOriginal; // Volta ao valor exato
            cardArredondar.setCardBackgroundColor(Color.parseColor("#00B050")); // Verde padrão
            Toast.makeText(this, "Valor exato restaurado!", Toast.LENGTH_SHORT).show();
        }

        atualizarTela();
    }

    public void copiarMensagem(View view) {
        String textoParaCopiar = txtMensagemCobranca.getText().toString();

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Mensagem SlicePay", textoParaCopiar);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "Mensagem copiada para a área de transferência!", Toast.LENGTH_SHORT).show();
    }

    public void finalizarDivisao(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void voltarTela3(View view) {
        finish();
    }



}