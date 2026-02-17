package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
	 private String email;
	 private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente(String nome, String cpf, String email) {
		super(nome, cpf);
		this.email = email;
	}
	public void adicionarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        pedidos.add(pedido);
    }
	
	@Override
	public String toString() {
		return "Cliente [email=" + email;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
}
