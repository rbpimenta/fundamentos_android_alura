package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private static int counterId = 0;

    private final static List<Aluno> alunos = new ArrayList<> (
            Arrays.asList(
                    new Aluno(counterId++, "Rodrigo", "Rodrigo@email.com", "(xxx) xxxx-xxxx"),
                    new Aluno(counterId++, "Maurício", "Mauricio@email.com", "(xxx) xxxx-xxxx")
            )
    );

    public void update (Aluno aluno) {
        /*
         Ps: Não vamos usar stream pois seria necessário um nível de API superior ou igual a 24
         representando o Android 7.0
        */

        // Obtém o aluno a ser editado
        Aluno alunoEncontrado = null;
        for (Aluno alunoOld : alunos) {
            if (alunoOld.getId().equals(aluno.getId())) {
                alunoEncontrado = alunoOld;
                break;
            }
        }

        // Edita os valores do aluno encontrado
        if (alunoEncontrado != null) {
            alunos.set(
                    alunos.indexOf(alunoEncontrado),
                    aluno
            );
        }
    }

    public void save(Aluno aluno) {
        aluno.setId(counterId++);
        alunos.add(aluno);
    }

    public List<Aluno> findAll() {
        return new ArrayList<>(alunos);
    }
}
