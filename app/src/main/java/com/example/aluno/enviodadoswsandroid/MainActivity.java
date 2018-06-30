package com.example.aluno.enviodadoswsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enviarWS(View view){
        String nome = ((EditText) findViewById(R.id.nome)).getText().toString();
        String cidade = ((EditText) findViewById(R.id.cidade)).getText().toString();
        Time time = new Time();
        time.setNome(nome);
        time.setCidade(cidade);

        EnviarTimeWS enviarTimeWS = new EnviarTimeWS(this);
        enviarTimeWS.execute(time);
    }
}
