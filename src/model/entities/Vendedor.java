package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Vendedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String email;
	private Date dataDeNascimento;
	private Double salariobase;
	
	private Departamento departamento;
	
	public Vendedor() {
		
	}

	public Vendedor(Integer id, String nome, String email, Date dataDeNascimento, Double salariobase,
			Departamento departamento) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.salariobase = salariobase;
		this.departamento = departamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Double getSalariobase() {
		return salariobase;
	}

	public void setSalariobase(Double salariobase) {
		this.salariobase = salariobase;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Vendedor: id = " + id + ", nome = " + nome + ", email = " + email + ", dataDeNascimento = " + dataDeNascimento
				+ ", salariobase = " + salariobase + ", departamento = " + departamento;
	}
	
	
	
	
	

}
