package br.com.alura.agenda.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.alura.agenda.R;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        Button botaoSalvarAluno = findViewById(R.id.activity_formulario_aluno_salvar);
        botaoSalvarAluno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(FormularioAlunoActivity.this, "Salvar Aluno", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
