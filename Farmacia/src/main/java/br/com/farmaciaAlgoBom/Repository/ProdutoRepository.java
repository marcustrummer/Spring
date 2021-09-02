package br.com.farmaciaAlgoBom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciaAlgoBomModel.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	public List<Produto> findAllByNomeContainingIgnoreCase(String produto);
	
}
