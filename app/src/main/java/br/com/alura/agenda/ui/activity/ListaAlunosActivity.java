package br.com.alura.agenda.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

// AppCompatActivity -> ele é uma boa prática no Android, por dar suporte a versões anteriores do Android
public class ListaAlunosActivity extends AppCompatActivity {
    private static final String APP_TITULO = "Lista de Alunos";

    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ListaAlunosAdapter listaAdapter;

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
     *
     * @param menu Menu
     * @param v View
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
            confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void confirmaRemocao(final MenuItem item) {
        // Adicionar dialog para confirmar exclusão de aluno
        new AlertDialog.Builder(this)
                .setTitle("Remover Aluno")
                .setMessage("Quer remover o aluno?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*
                         * Como estamos falando de um AdapterView, precisamos pegar as informações do menu utilizando
                         * a classe AdapterView.AdapterContextMenuInfo
                         */
                        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                        // Obtendo o item selecionado a partir do menu de contexto
                        Aluno alunoSelecionado = listaAdapter.getItem(adapterContextMenuInfo.position);
                        removerAluno(alunoSelecionado);
                    }
                })
                .show();
    }

    private void atualizarListaAlunos() {
        listaAdapter.atualizar(alunoDAO.findAll());
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
        Intent goToFormularioAluno = new Intent(this, FormularioAlunoActivity.class);
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
        listaAdapter = new ListaAlunosAdapter(this);
        listaAlunos.setAdapter(listaAdapter);
    }
}
