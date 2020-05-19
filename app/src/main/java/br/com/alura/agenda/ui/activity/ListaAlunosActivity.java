package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

// AppCompatActivity -> ele é uma boa prática no Android, por dar suporte a versões anteriores do Android
public class ListaAlunosActivity extends AppCompatActivity {
    private static final String APP_TITULO = "Lista de Alunos";
    final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        // Adicionar título
        setTitle(APP_TITULO);
        configurarBotaoAdicionarAluno();

    }

    private void configurarBotaoAdicionarAluno() {
        // Adicionar ação do botão de incluir aluno
        FloatingActionButton adicionarNovoAluno = findViewById(R.id.lista_alunos_fab_novo_aluno);
        adicionarNovoAluno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        abreFormularioAluno(null);
                    }
                }
        );
    }

    private void abreFormularioAluno(Aluno alunoEscolhido) {
        Intent goToFormularioAluno = new Intent(this,FormularioAlunoActivity.class);
        if (alunoEscolhido != null) {
            goToFormularioAluno.putExtra("aluno", alunoEscolhido);
        }
        startActivity(goToFormularioAluno);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        // Adicionando lista de alunos
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        final List<Aluno> alunos = alunoDAO.findAll();
        listaAlunos.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        alunos
                )
        );

        listaAlunos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                        Aluno alunoEscolhido = alunos.get(posicao);
                        Log.i("Student", alunoEscolhido.toString());
                        abreFormularioAluno(alunoEscolhido);
                    }
                }
        );
    }
}
