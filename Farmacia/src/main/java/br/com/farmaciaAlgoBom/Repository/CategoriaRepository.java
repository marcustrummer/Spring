package br.com.farmaciaAlgoBom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciaAlgoBomModel.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findAllByDescricaoStringContainingIgnoreCase(String descricao);
}
