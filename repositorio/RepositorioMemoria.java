package repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RepositorioMemoria<T> implements Repositorio<T> {

	private List<T> dados = new ArrayList<>();

	@Override
	public void salvar(T obj) {
		dados.add(obj);
	}

	@Override
	public List<T> listar() {
		return dados;
	}

	@Override
	public List<T> buscar(Predicate<T> filtro) {
		return dados.stream()
				.filter(filtro)
				.toList();
	}
}
