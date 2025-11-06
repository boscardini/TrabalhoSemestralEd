package model.dao;
import model.dto.Inscricao;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InscricaoArq extends GenericArq<Inscricao, Integer> {

    public InscricaoArq() throws IOException{
        super("inscricoes.csv");
    }
    
    
    protected String entityToCSV(Inscricao entidade) {
        return entidade.getId() + ";" +
               entidade.getIdProcesso() + ";" +
               entidade.getIdProfessor() + ";" +
               entidade.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
    protected Inscricao csvToEntity(String[] dados) {
        Integer id = Integer.parseInt(dados[0]);
        Integer idProcesso = Integer.parseInt(dados[1]);
        Integer idProfessor = Integer.parseInt(dados[2]);
        LocalDate data = LocalDate.parse(dados[3], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return new Inscricao (id, idProcesso, idProfessor, data);
    }
}