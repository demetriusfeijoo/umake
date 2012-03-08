package br.com.umake.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teste")
public class Teste implements Serializable {

	@Id
	private Long name;

	public Long getNome() {
		return name;
	}

	public void setNome(Long nome) {
		this.name = nome;
	}
	
	
}
