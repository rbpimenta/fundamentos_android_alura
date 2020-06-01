package br.com.alura.agenda;

import android.app.Application;

import java.util.Arrays;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.createWith(
                Arrays.asList(
                        new Aluno(0, "Rodrigo", "Rodrigo@email.com", "(11) 1111-1111"),
                        new Aluno(1, "Maurício", "Mauricio@email.com", "(22) 2222-2222")
                )
        );
    }
}
