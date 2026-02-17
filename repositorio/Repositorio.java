package repositorio;

import java.util.List;
import java.util.function.Predicate;

public interface Repositorio <T>{
	void salvar(T obj);
	List<T> listar();
	List<T> buscar(Predicate<T> filtro);
}
