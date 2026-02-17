package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
	private String codigo;
	private Cliente cliente;
	private List<ItemPedido> itens;
	private LocalDateTime data;
	private StatusPedido status;
	
	public Pedido(String codigo, Cliente cliente, List<ItemPedido> itens, LocalDateTime data, StatusPedido status) {
		super();
		this.codigo = codigo;
		this.cliente = cliente;
		this.itens = itens;
		this.data = data;
		this.status = status;
	}

	public enum StatusPedido {
		ABERTO, PAGO, CANCELADO
	}

	@Override
	public String toString() {
		return "Pedido [codigo= " + codigo + ", cliente= " + cliente + ", itens= " + itens + ", data= " + data + ", status= "
				+ status + "]";
	}

	//================= GET =================
	public double getTotal() {
		return itens.stream()
			.mapToDouble(ItemPedido::getSubtotal)
			.sum();
	}
	public List<ItemPedido> getItens() {
		return itens;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getCodigo() {
		return codigo;
	}
	//================= SET =================
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
}
