package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<> (
            Arrays.asList(
                    new Aluno("Rodrigo", "Rodrigo@email.com", "(xxx) xxxx-xxxx"),
                    new Aluno("Maurício", "Mauricio@email.com", "(xxx) xxxx-xxxx")
            )
    );

    public void save(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> findAll() {
        return new ArrayList<>(alunos);
    }
}
