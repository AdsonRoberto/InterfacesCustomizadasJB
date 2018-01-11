package org.jb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
	private Integer id;
	private Cliente cliente;
	private Date data;
	private SituacaoPedido situacao = SituacaoPedido.NOVO;
	private List<Item> itens;

	public Pedido() {
		itens = new ArrayList<Item>();
		situacao = SituacaoPedido.ABERTO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public SituacaoPedido getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoPedido situacao) {
		this.situacao = situacao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(id);
	}
}