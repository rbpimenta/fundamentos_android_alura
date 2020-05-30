package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position) != null ? alunos.get(position).getId() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater
                .from(this.context)
                .inflate(R.layout.item_aluno, parent, false);

        Aluno aluno = alunos.get(position);
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNome());

        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        telefone.setText(aluno.getTelefone());


        return view;
    }



    public void clear() {
        alunos.clear();
    }

    public void addAll(List<Aluno> all) {
        alunos.addAll(all);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
    }
}
