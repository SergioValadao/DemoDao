package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Vendedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String email;
	private Date aniversario;
	private Double SalarioBase;
	private Departamento departamento;
	
	public Vendedor() {
		
	}
	

	public Vendedor(Integer id, String name, String email, Date aniversario, Double salarioBase,
			Departamento departamento) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.aniversario = aniversario;
		SalarioBase = salarioBase;
		this.departamento = departamento;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getAniversario() {
		return aniversario;
	}


	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}


	public Double getSalarioBase() {
		return SalarioBase;
	}


	public void setSalarioBase(Double salarioBase) {
		SalarioBase = salarioBase;
	}


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", name=" + name + ", email=" + email + ", aniversario=" + aniversario
				+ ", SalarioBase=" + SalarioBase + ", departamento=" + departamento + "]";
	}
		
}
