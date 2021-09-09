package com.org.generation.minhalojadegames.repository;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.org.generation.minhalojadegames.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional<Usuario> findByUsuario(String Usuario);
	


    // verificar se o usuário já existe
    //public Optional<Usuario>existe(String usuario);
    //public boolean existe1(Usuario usuario);
}
