package model.dao;

import java.io.IOException;

import model.dto.Processo;

public class ProcessoArq extends GenericArq<Processo, Integer> {

    public ProcessoArq() throws IOException {
        super("processos.csv");
    }

    
    protected String entityToCSV(Processo entidade) {
        return entidade.getId() + ";" + entidade.getIdDisciplina();
    }

    
    protected Processo csvToEntity(String[] dados) {
        Integer id = Integer.parseInt(dados[0]);
        Integer idDisciplina = Integer.parseInt(dados[1]);
        return new Processo(id, idDisciplina);
    }
}