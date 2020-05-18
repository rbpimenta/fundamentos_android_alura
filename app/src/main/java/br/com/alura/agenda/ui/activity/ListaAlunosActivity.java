package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;

// AppCompatActivity -> ele é uma boa prática no Android, por dar suporte a versões anteriores do Android
public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        // Adicionar título
        setTitle("Lista de Alunos");

        // Adicionar ação do botão de incluir aluno
        FloatingActionButton adicionarNovoAluno = findViewById(R.id.lista_alunos_fab_novo_aluno);
        adicionarNovoAluno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(
                                new Intent(
                                        ListaAlunosActivity.this,
                                        FormularioAlunoActivity.class
                                )
                        );
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        final AlunoDAO alunoDAO = new AlunoDAO();

        // Adicionando lista de alunos
        ListView listViewAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listViewAlunos.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        alunoDAO.findAll()
                )
        );
    }
}
