package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        final AlunoDAO alunoDAO = new AlunoDAO();

        final EditText campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        final EditText campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        final EditText campoEmail = findViewById(R.id.activity_formulario_aluno_email);

        Button botaoSalvarAluno = findViewById(R.id.activity_formulario_aluno_salvar);
        botaoSalvarAluno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nome = campoNome.getText().toString();
                        String email = campoEmail.getText().toString();
                        String telefone = campoTelefone.getText().toString();

                        Aluno novoAluno = new Aluno(nome, email, telefone);

                        alunoDAO.save(novoAluno);

                        startActivity(
                                new Intent(
                                        FormularioAlunoActivity.this,
                                        ListaAlunosActivity.class
                                )
                        );
                    }
                }
        );
    }
}
