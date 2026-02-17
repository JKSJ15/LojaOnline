import java.time.LocalDateTime;
import java.util.List;

import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;
import repositorio.Repositorio;
import repositorio.RepositorioMemoria;
import servico.LojaService;

public class main {

	public static void main(String[] args) {
		// ================= REPOSITÓRIOS =================
		Repositorio<Produto> repoProdutos = new RepositorioMemoria<>();
		Repositorio<Cliente> repoClientes = new RepositorioMemoria<>();
		Repositorio<Pedido> repoPedidos = new RepositorioMemoria<>();

		// ================= SERVICE =================
		LojaService loja = new LojaService(repoProdutos, repoClientes, repoPedidos);

		// ================= PRODUTOS =================
		Produto p1 = new Produto("Notebook", 3500, "Eletronico", true);
		Produto p2 = new Produto("Mouse", 80, "Eletronico", true);
		Produto p3 = new Produto("Camisa", 120, "Roupa", true);
		Produto p4 = new Produto("Curso Java", 0, "Educacao", true);
		Produto p5 = new Produto("TV Antiga", 1500, "Eletronico", false);

		repoProdutos.salvar(p1);
		repoProdutos.salvar(p2);
		repoProdutos.salvar(p3);
		repoProdutos.salvar(p4);
		repoProdutos.salvar(p5);

		// ================= CLIENTES =================
		Cliente c1 = new Cliente("João", "11111111111", "joao@email.com");
		Cliente c2 = new Cliente("Maria", "22222222222", "maria@email.com");
		Cliente c3 = new Cliente("Jakson", "00000000001", "cabecabranca244@email.com");

		repoClientes.salvar(c1);
		repoClientes.salvar(c2);
		repoClientes.salvar(c3);

		// ================= PEDIDOS =================
		ItemPedido ip1 = new ItemPedido(p1, 1); // Notebook
		ItemPedido ip2 = new ItemPedido(p2, 2); // Mouse
		ItemPedido ip3 = new ItemPedido(p3, 3); // Camisa

		Pedido pedido1 = new Pedido(
				"PED-001",
				c1,
				List.of(ip1, ip2),
				LocalDateTime.now(), Pedido.StatusPedido.PAGO
		);

		Pedido pedido2 = new Pedido(
				"PED-002",
				c1,
				List.of(ip3),
				LocalDateTime.now(), Pedido.StatusPedido.ABERTO
		);

		Pedido pedido3 = new Pedido(
				"PED-003",
				c2,
				List.of(ip2),
				LocalDateTime.now(), Pedido.StatusPedido.PAGO
		);

		repoPedidos.salvar(pedido1);
		repoPedidos.salvar(pedido2);
		repoPedidos.salvar(pedido3);

		// ================= TESTES =================
		System.out.println("=== PRODUTOS ATIVOS ===");
		loja.listarProdutosAtivos().forEach(System.out::println);

		System.out.println("\n=== ELETRÔNICOS ===");
		loja.produtosPorCategoria("Eletronico").forEach(System.out::println);

		System.out.println("\n=== PRODUTOS ORDENADOS POR PREÇO ===");
		loja.produtosOrdenadosPorPreco().forEach(System.out::println);

		System.out.println("\nExiste produto grátis? " + loja.existeProdutoGratis());

		System.out.println("\n=== PEDIDOS PAGOS ===");
		loja.pedidosPagos().forEach(p ->
				System.out.println(p.getCodigo() + " - R$ " + loja.calcularTotalPedido(p))
		);

		System.out.println("\n=== FATURAMENTO TOTAL ===");
		System.out.println("R$ " + loja.faturamentoTotal());

		System.out.println("\n=== RELATÓRIO POR CLIENTE (JOÃO) ===");
		loja.relatorioPedidosPorCliente("João");

		System.out.println("\n=== BUSCAR CLIENTE POR CPF ===");
		loja.buscarClientePorCpf("11111111111")
				.ifPresentOrElse(
						c -> System.out.println("Encontrado: " + c.getNome()),
						() -> System.out.println("Cliente não encontrado")
				);
	}
}
