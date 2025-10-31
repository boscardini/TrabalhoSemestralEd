package model.dto;


public class ProcessoExibicao implements IEntity {
	private Integer idProcesso;
	private String nomeDisciplina;
	
	public ProcessoExibicao(Integer idProcesso, String nomeDisciplina) {
		this.idProcesso= idProcesso;
		this.nomeDisciplina= nomeDisciplina;
	}

	public Integer getId() {
		return idProcesso;
	}

	public void setId(Integer idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	@Override
	public String toString() {
		return "ProcessoExibição [idProcesso=" + idProcesso + ", nomeDisciplina=" + nomeDisciplina + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProcessoExibicao)) {
			return false;
		}
		ProcessoExibicao other = (ProcessoExibicao) obj;
		return this.idProcesso.equals(other.idProcesso);
	}
	@Override
	public int hashCode() {
		if (idProcesso != null) {
			return idProcesso;
		}else {
			return 0;
		}
	}

}
