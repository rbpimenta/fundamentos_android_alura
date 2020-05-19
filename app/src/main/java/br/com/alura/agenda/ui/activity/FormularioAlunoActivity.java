package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String APP_TITULO = "Lista de Alunos";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        // Adicionar título
        setTitle(APP_TITULO);

        inicializacaoCampos();
        configuraBotaoSalvar();

        // Obtém dos "extras" o parâmetro aluno que esperamos ser passado
        Aluno alunoEscolhido = Objects.requireNonNull(getIntent().getExtras()).getParcelable("aluno");
        Objects.requireNonNull(alunoEscolhido);
        campoNome.setText(alunoEscolhido.getNome());
        campoTelefone.setText(alunoEscolhido.getTelefone());
        campoEmail.setText(alunoEscolhido.getEmail());
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvarAluno = findViewById(R.id.activity_formulario_aluno_salvar);
        botaoSalvarAluno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Aluno novoAluno = criaAluno();
                        salvarAluno(novoAluno, alunoDAO);
                    }
                }
        );
    }

    private void inicializacaoCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvarAluno(Aluno novoAluno, AlunoDAO alunoDAO) {
        alunoDAO.save(novoAluno);
        finish();
    }

    @NonNull
    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String telefone = campoTelefone.getText().toString();

        return new Aluno(nome, email, telefone);
    }
}
