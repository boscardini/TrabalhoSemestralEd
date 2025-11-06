package model.dao;

import java.io.IOException;
import model.dto.Disciplina;

public class DisciplinaArq extends GenericArq <Disciplina,Integer> {


	public DisciplinaArq() throws IOException {
       super("disciplinas.csv");
    }

   
    protected String entityToCSV(Disciplina entidade) {
        return entidade.getId() + ";" +
               entidade.getNome() + ";" +
               entidade.getDia() + ";" +
               entidade.getHora() + ";" +
               entidade.getQtdHoras() + ";" +
               entidade.getIdCurso();
    }

    
    protected Disciplina csvToEntity(String[] dados) {
        Integer id = Integer.parseInt(dados[0]);
        String nome = dados[1];
        String diaSemana = dados[2];
        String horaInicial = dados[3];
        Double qtdHoras = Double.parseDouble(dados[4]);
        Integer idCurso = Integer.parseInt(dados[5]);

        return new Disciplina(id, nome, diaSemana, horaInicial, qtdHoras, idCurso);
    }

}    