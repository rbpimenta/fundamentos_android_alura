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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

// AppCompatActivity -> ele é uma boa prática no Android, por dar suporte a versões anteriores do Android
public class ListaAlunosActivity extends AppCompatActivity {
    private static final String APP_TITULO = "Lista de Alunos";
    private static final String CHAVE_ALUNO = "aluno";

    final AlunoDAO alunoDAO = new AlunoDAO();
    private ArrayAdapter<Aluno> listaAdapter;

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
        atualizarListaAlunos();
    }

    private void configuraLista() {
        // Adicionando lista de alunos
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configurarAdapter(listaAlunos);
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
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Adicionando menus a partir de um arquivo estático
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    /**
     * Evento acionado quando um item de contexto é selecionado
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.activity_lista_alunos_menu_remover) {
            /*
             * Como estamos falando de um AdapterView, precisamos pegar as informações do menu utilizando
             * a classe AdapterView.AdapterContextMenuInfo
             */
            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            // Obtendo o item selecionado a partir do menu de contexto
            Aluno alunoSelecionado = listaAdapter.getItem(adapterContextMenuInfo.position);
            removerAluno(alunoSelecionado);
        }

        return super.onContextItemSelected(item);

    }

    private void atualizarListaAlunos() {
        listaAdapter.clear();
        listaAdapter.addAll(alunoDAO.findAll());
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
            goToFormularioAluno.putExtra(ConstantesActivities.CHAVE_ALUNO, alunoEscolhido);
        }
        startActivity(goToFormularioAluno);
    }

    private void removerAluno(Aluno alunoSelecionado) {
        if (alunoSelecionado != null) {
            alunoDAO.remove(alunoSelecionado.getId());
            listaAdapter.remove(alunoSelecionado);
        }
    }

    private void configuraListenerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                        Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                        Log.i("Student", alunoEscolhido.toString());
                        abreFormularioAluno(alunoEscolhido);
                    }
                }
        );
    }

    private void configurarAdapter(ListView listaAlunos) {
        listaAdapter = new ArrayAdapter<>(
                this,
                R.layout.item_aluno
        );
        listaAlunos.setAdapter(
                listaAdapter
        );
    }
}
