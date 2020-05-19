package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String APP_TITULO = "Lista de Alunos";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        // Adicionar título
        setTitle(APP_TITULO);

        inicializacaoCampos();
        configuraBotaoSalvar();

        // Obtém dos "extras" o parâmetro aluno que esperamos ser passado
        getAlunoFromParceable();
    }

    public void getAlunoFromParceable () {
        if (getIntent().hasExtra("aluno")) {
            aluno = getIntent().getExtras().getParcelable("aluno");

            if (aluno != null) {
                campoNome.setText(aluno.getNome());
                campoTelefone.setText(aluno.getTelefone());
                campoEmail.setText(aluno.getEmail());
            }
        } else {
            aluno = new Aluno();
        }
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvarAluno = findViewById(R.id.activity_formulario_aluno_salvar);
        botaoSalvarAluno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        populaDadosAluno();
                        salvarAluno(aluno, alunoDAO);
                    }
                }
        );
    }

    private void inicializacaoCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvarAluno(Aluno aluno, AlunoDAO alunoDAO) {
        if (aluno.getId() != null) {
            alunoDAO.update(aluno);
        } else {
            alunoDAO.save(aluno);
        }
        finish();
    }

    private void populaDadosAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
    }
}
