package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.ListaAlunosView;

// AppCompatActivity -> ele é uma boa prática no Android, por dar suporte a versões anteriores do Android
public class ListaAlunosActivity extends AppCompatActivity {
    private static final String APP_TITULO = "Lista de Alunos";

    private ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        // Adicionar título
        setTitle(APP_TITULO);
        configurarBotaoAdicionarAluno();

        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizarListaAlunos();
    }

    private void configuraLista() {
        // Adicionando lista de alunos
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaAlunosView.configurarAdapter(listaAlunos);
        configuraListenerDeCliquePorItem(listaAlunos);

        /*
         Espera-se que seja informado uma view de dentro do nosso layout
         Nesse caso, podemos enviar a lista de alunos ou o botão "incluir novo"
         No momento, iremos enviar a nossa lista de alunos
        */
        registerForContextMenu(listaAlunos);
    }

    /**
     * Criação de menu de contexto para uma atividade
     *
     * @param menu     Menu
     * @param v        View
     * @param menuInfo MenuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Adicionando menus a partir de um arquivo estático
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    /**
     * Evento acionado quando um item de contexto é selecionado
     *
     * @param item item
     * @return true or false
     */
    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }



    private void configurarBotaoAdicionarAluno() {
        // Adicionar ação do botão de incluir aluno
        FloatingActionButton adicionarNovoAluno = findViewById(R.id.lista_alunos_fab_novo_aluno);
        adicionarNovoAluno.setOnClickListener(
                view -> abreFormularioAluno(null)
        );
    }

    private void abreFormularioAluno(Aluno alunoEscolhido) {
        Intent goToFormularioAluno = new Intent(this, FormularioAlunoActivity.class);
        if (alunoEscolhido != null) {
            goToFormularioAluno.putExtra(ConstantesActivities.CHAVE_ALUNO, alunoEscolhido);
        }
        startActivity(goToFormularioAluno);
    }

    private void configuraListenerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(
                (adapterView, view, posicao, id) -> {
                    Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                    Log.i("Student", alunoEscolhido.toString());
                    abreFormularioAluno(alunoEscolhido);
                }
        );
    }
}
