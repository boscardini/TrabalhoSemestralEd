package model.dto;

import java.time.LocalDate;

public class Inscricao implements IEntity {
	private Integer id;
	private Integer idProcesso;
	private Integer IdProfessor;
	private LocalDate data;
	
	public Inscricao(Integer id,Integer idProcesso, Integer IdProfessor, LocalDate data) {
		this.id= id;
		this.idProcesso= idProcesso;
		this.IdProfessor= IdProfessor;
		this.data= data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(Integer idProcesso) {
		this.idProcesso = idProcesso;
	}

	public Integer getIdProfessor() {
		return IdProfessor;
	}

	public void setCpfProfessor(Integer idProfessor) {
		this.IdProfessor = idProfessor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getDataFormatada() {
		return data.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

	@Override
	public String toString() {
		return "Inscrição [id=" + id + ", idProcesso=" + idProcesso + ", IdProfessor=" + IdProfessor + ", data=" + data
				+ "]";
	}
	
}
