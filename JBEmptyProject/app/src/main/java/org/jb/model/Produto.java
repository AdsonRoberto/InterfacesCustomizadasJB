package org.jb.model;

import java.util.List;

public class Produto {
	private Integer id;
	private String descricao;
	private Double valor;
	private List<Catalogo> catalogos;

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<Catalogo> getCatalogos() {
		return catalogos;
	}

	public void setCatalogos(List<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return descricao;
	}
}