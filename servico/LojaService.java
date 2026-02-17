package servico;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Produto;
import repositorio.Repositorio;

public class LojaService {

	private Repositorio<Produto> produtos;
	private Repositorio<Cliente> clientes;
	private Repositorio<Pedido> pedidos;

	public LojaService(Repositorio<Produto> produtos,
	                   Repositorio<Cliente> clientes,
	                   Repositorio<Pedido> pedidos) {
		this.produtos = produtos;
		this.clientes = clientes;
		this.pedidos = pedidos;
	}

	// ================= PRODUTOS =================

	public List<Produto> listarProdutosAtivos() {
		return produtos.listar()
				.stream()
				.filter(Produto::isAtivo)
				.toList();
	}

	public List<Produto> produtosPorCategoria(String categoria) {
		return produtos.listar()
				.stream()
				.filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
				.toList();
	}

	public List<Produto> produtosOrdenadosPorPreco() {
		return produtos.listar()
				.stream()
				.sorted(Comparator.comparing(Produto::getPreco))
				.toList();
	}

	public boolean existeProdutoGratis() {
		return produtos.listar()
				.stream()
				.anyMatch(p -> p.getPreco() == 0);
	}

	// ================= CLIENTES =================

	public Optional<Cliente> buscarClientePorCpf(String cpf) {
		return clientes.buscar(c -> c.getCpf().equals(cpf))
				.stream()
				.findFirst();
	}




	// ================= PEDIDOS =================

	public List<Pedido> pedidosPagos() {
		return pedidos.listar()
				.stream()
				.filter(p -> p.getStatus() == Pedido.StatusPedido.PAGO)
				.toList();
	}

	public double calcularTotalPedido(Pedido pedido) {
		return pedido.getItens()
				.stream()
				.mapToDouble(item ->
						item.getProduto().getPreco() * item.getQuantidade()
				)
				.sum();
	}

	public double faturamentoTotal() {
		return pedidos.listar()
				.stream()
				.filter(p -> p.getStatus() == Pedido.StatusPedido.PAGO)
				.mapToDouble(this::calcularTotalPedido)
				.sum();
	}

	public void relatorioPedidosPorCliente(String nome) {

		List<Pedido> pedidosCliente = pedidos.listar()
				.stream()
				.filter(p -> p.getCliente().getNome().equalsIgnoreCase(nome))
				.toList();

		if (pedidosCliente.isEmpty()) {
			System.out.println("Cliente não encontrado ou sem pedidos.");
			return;
		}

		System.out.println("Cliente: " + nome);
		System.out.println("Pedidos:");

		pedidosCliente.forEach(p -> {
			System.out.println(
					"- " + p.getCodigo()
					+ " | Total: R$ " + calcularTotalPedido(p)
					+ " | Status: " + p.getStatus()
			);
		});
	}
}

