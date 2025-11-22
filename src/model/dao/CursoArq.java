package model.dao;

import java.io.IOException;

import model.dto.Curso;

public class CursoArq extends GenericArq<Curso, Integer> {

	public CursoArq() throws IOException {
		super("cursos.csv");
	}

	
	public String entityToCSV(Curso entidade) {
		return entidade.getId() + ";" + entidade.getNome() + ";" + entidade.getAreaConhecimento();
	}

	
	protected Curso csvToEntity(String[] dados) {
		Integer id = Integer.parseInt(dados[0]);
		String nome = dados[1];
		String AreaConhecimento = (dados[2]);
		return new Curso(id, nome,AreaConhecimento);
	}

}