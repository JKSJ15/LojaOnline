package modelo;

public class Produto {
	private String nome;
	private double preco;
	private String categoria;
	private boolean ativo;
	
	public Produto(String nome, double preco, String categoria, boolean ativo) {
		super();
		this.nome = nome == null ? null : nome.toUpperCase();
		this.preco = preco;
		this.categoria = categoria;
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", preco=" + preco + ", categoria=" + categoria + ", ativo=" + ativo + "]";
	}

	public boolean isAtivo() {
		return ativo;
	}

	
	//================= GET =================
	public double getPreco() {
		return preco;
	}

	public String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}

	//================= SET =================
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setPreco(double preco) {
		if (preco < 0) {
			throw new IllegalArgumentException("Preço não pode ser negativo");
		}
		this.preco = preco;
}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
