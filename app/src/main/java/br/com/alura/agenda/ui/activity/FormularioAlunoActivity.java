package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Objects;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_UPDATE_ALUNO = "Edita Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        inicializacaoCampos();

        // Obtém dos "extras" o parâmetro aluno que esperamos ser passado
        getAlunoFromParceable();
    }

    /**
     * Criar menu de opções
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Adicionando menus a partir de um arquivo estático
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.activity_lista_alunos_menu_salvar) {
            populaDadosAluno();
            salvarAluno(aluno, alunoDAO);
        }

        return super.onOptionsItemSelected(item);
    }

    public void getAlunoFromParceable () {
        if (getIntent().hasExtra(ConstantesActivities.CHAVE_ALUNO)) {
            aluno = Objects.requireNonNull(getIntent().getExtras()).getParcelable(ConstantesActivities.CHAVE_ALUNO);

            if (aluno != null) {
                // Adicionar título
                setTitle(TITULO_UPDATE_ALUNO);
                campoNome.setText(aluno.getNome());
                campoTelefone.setText(aluno.getTelefone());
                campoEmail.setText(aluno.getEmail());
            }
        } else {
            // Adicionar título
            setTitle(TITULO_NOVO_ALUNO);
            aluno = new Aluno();
        }
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
