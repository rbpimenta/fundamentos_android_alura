package br.com.alura.agenda;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Iniciando APP", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);

        List<String> alunos = Arrays.asList("Alex", "Fran", "Jose", "Rodrigo");
        ListView listViewAlunos = findViewById(R.id.activity_main_lista_alunos);
        listViewAlunos.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos)
        );
    }
}
