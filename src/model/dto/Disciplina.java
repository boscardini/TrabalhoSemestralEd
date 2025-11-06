package model.dto;

public class Disciplina implements IEntity {
	private Integer id;
	private String nome;
	private String dia;
	private String hora;
	private double qtdHoras;
	private Integer idCurso;
	
	public Disciplina(Integer id, String nome, String dia, String hora, double qtdHoras, int idCurso) {
		this.id= id;
		this.nome= nome;
		this.dia= dia;
		this.hora= hora;
		this.qtdHoras= qtdHoras;
		this.idCurso = idCurso;
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id= id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public double getQtdHoras() {
		return qtdHoras;
	}
	public void setQtdHoras(double qtdHoras) {
		this.qtdHoras = qtdHoras;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	@Override
	public String toString() {
		return "Disciplina [nome=" + nome + "]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Disciplina)) return false;
		Disciplina disciplina = (Disciplina) obj;
		return id != null && id.equals (disciplina.id);
	}
	
	@Override
	public int hashCode() {
		if (id !=null) {
			return id.hashCode();
		}else {
			return 0;
		}
}
}
