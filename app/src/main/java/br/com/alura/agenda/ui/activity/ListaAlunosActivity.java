package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.R;

// AppCompatActivity -> ele é uma boa prática no Android, por dar suporte a versões anteriores do Android
public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Iniciando APP", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_lista_alunos);

        // Adicionar título
        setTitle("Lista de Alunos");

        // Adicionando lista de alunos
        List<String> alunos = Arrays.asList("Alex", "Fran", "Jose", "Rodrigo");
        ListView listViewAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listViewAlunos.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos)
        );
    }
}
