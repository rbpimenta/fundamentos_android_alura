package br.com.alura.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter listaAdapter;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        listaAdapter = new ListaAlunosAdapter(context);
        alunoDAO = new AlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        // Adicionar dialog para confirmar exclusão de aluno
        new AlertDialog.Builder(context)
                .setTitle("Remover Aluno")
                .setMessage("Quer remover o aluno?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", (dialog, which) -> {
                    /*
                     * Como estamos falando de um AdapterView, precisamos pegar as informações do menu utilizando
                     * a classe AdapterView.AdapterContextMenuInfo
                     */
                    AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                    // Obtendo o item selecionado a partir do menu de contexto
                    Aluno alunoSelecionado = listaAdapter.getItem(adapterContextMenuInfo.position);
                    removerAluno(alunoSelecionado);
                })
                .show();
    }

    public void atualizarListaAlunos() {
        listaAdapter.atualizar(alunoDAO.findAll());
    }

    public void configurarAdapter(ListView listaAlunos) {
        listaAlunos.setAdapter(listaAdapter);
    }

    private void removerAluno(Aluno alunoSelecionado) {
        if (alunoSelecionado != null) {
            alunoDAO.remove(alunoSelecionado.getId());
            listaAdapter.remove(alunoSelecionado);
        }
    }
}
