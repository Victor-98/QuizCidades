package com.example.quizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TelaFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_final);

        Intent it = getIntent();
        // Certifica que intent pode ser recebido.
        if (it == null)
            return;

        Bundle params = it.getExtras();
        // Certifica que os parâmetros foram enviados corretamente.
        if (params == null)
            return;

        Integer pontos = params.getInt("pontos");
        TextView pontosText = findViewById(R.id.pontosText);
        pontosText.setText("Você fez " + pontos + " pontos.");
    }
}