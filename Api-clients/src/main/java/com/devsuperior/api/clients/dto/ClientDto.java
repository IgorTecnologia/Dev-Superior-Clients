package com.devsuperior.api.clients.dto;

import java.io.Serializable;
import java.time.Instant;

import com.devsuperior.api.clients.entity.Client;

public class ClientDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String cpf;
	private Double income;
	private Instant BirthDate;
	private Integer Children;
	
	public ClientDto() {
	}

	public ClientDto(Long id, String name, String cpf, Double income, Instant birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		BirthDate = birthDate;
		Children = children;
	}
	
	public ClientDto(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cpf = entity.getCpf();
		this.income = entity.getIncome();
		this.BirthDate = entity.getBirthDate();
		this.Children = entity.getChildren();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Instant getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Instant birthDate) {
		BirthDate = birthDate;
	}

	public Integer getChildren() {
		return Children;
	}

	public void setChildren(Integer children) {
		Children = children;
	}	
	
	
}
