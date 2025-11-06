package model.dao;

import java.io.IOException;

import model.dto.AreaConhecimento;

public class AreaConhecimentoArq extends GenericArq< AreaConhecimento, Integer> {

	public AreaConhecimentoArq() throws IOException {
		super("areas.csv");
	}


	protected String entityToCSV(AreaConhecimento entidade) {
		return entidade.getId() + ";" + entidade.getNome();
	}

	
	protected AreaConhecimento csvToEntity(String[] dados) {
		Integer id = Integer.parseInt(dados[0]);
		String nome = dados[1];
		return new AreaConhecimento(id, nome);
	}

}