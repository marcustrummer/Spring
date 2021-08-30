package com.org.generation.minhalojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.generation.minhalojadegames.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findAllBySetorContainingIgnoreCase(String setor);
	public List<Categoria> findAllByGeneroContainingIgnoreCase(String genero);

}
