package com.example.quizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String[] cidadesString = {         "barcelona",          "brasilia",          "curitiba",          "lasvegas",          "montreal",          "paris",          "riodejaneiro",          "salvador",          "saopaulo",          "toquio"};
    private int[] cidadesDrawable  = {R.drawable.barcelona, R.drawable.brasilia, R.drawable.curitiba, R.drawable.lasvegas, R.drawable.montreal, R.drawable.paris, R.drawable.riodejaneiro, R.drawable.salvador, R.drawable.saopaulo, R.drawable.toquio};
    private List<Cidade> cidades;
    private int pergunta; // Número da pergunta atual.
    private int acertos; // Número de acertos até o momento.
    private Cidade atual; // Cidade da pergunta atual.
    private boolean intervalo;

    private Cidade getNovaPergunta( List<Cidade> cidades ) {
        Random rand = new Random();
        int pos = rand.nextInt(cidades.size());
        Cidade cidade = cidades.get(pos);
        cidades.remove(pos);
        return cidade;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cidades = new ArrayList<Cidade>();
        for (int i = 0; i < cidadesString.length; i++) {
            Cidade cidade = new Cidade(cidadesString[i], cidadesDrawable[i]);
            cidades.add(cidade);
        }

        pergunta = 0;
        acertos = 0;
        processarProximaPergunta();
    }

    private void processarTelaDeFim() {
        // Transfere parâmetros
        Intent it = new Intent(this, TelaFinal.class);
        Bundle params = new Bundle();
        params.putInt("pontos", acertos*25);
        it.putExtras(params);
        startActivity(it);
        finish();
    }

    private void processarProximaPergunta() {
        intervalo = false;

        Cidade cidadeAleatoria = getNovaPergunta(cidades); // Pega uma cidade aleatoria, sem reposição.
        atual = cidadeAleatoria;
        ImageView img = findViewById(R.id.pontoImg);
        img.setImageResource(cidadeAleatoria.getPath());

        // Ajustar pergunta
        pergunta++;
        TextView perguntaText = findViewById(R.id.perguntaText);
        perguntaText.setText("Pergunta " + pergunta);

        // Esvaziar input
        EditText input = findViewById(R.id.cidadeInput);
        input.setText("");

        // Esvaziar campo de resposta
        TextView respostaText = findViewById(R.id.respostaText);
        respostaText.setText("");
    }

    public void tentarCidade(View view) {
        // Caso já esteja exibindo a resposta, apenas retornar.
        if (this.intervalo)
            return;

        this.intervalo = true;
        EditText input = findViewById(R.id.cidadeInput);
        String resposta = input.getText().toString().toLowerCase(); // Deixa o string em minusculo
        resposta = resposta.replaceAll("\\s+",""); // Remove todos os espaços do string

        TextView respostaText = findViewById(R.id.respostaText);
        if (resposta.equals(atual.getNome())) {
            // Acerto
            this.acertos++;
            respostaText.setText("Parabéns, você acertou!");
        } else {
            // Erro
            respostaText.setText("Você errou. A resposta certa era " + atual.getNome());
        }

        // Processar final / próxima pergunta
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (pergunta >= 4) {
                            // Fim
                            // Ir para a proxima activity
                            processarTelaDeFim();
                            finish();
                        } else {
                            // Próxima pergunta
                            processarProximaPergunta();
                        }

                    }
                }, 3000);




    }


}