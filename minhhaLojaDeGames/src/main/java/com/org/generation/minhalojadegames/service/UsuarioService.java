package com.org.generation.minhalojadegames.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.org.generation.minhalojadegames.model.UserLogin;
import com.org.generation.minhalojadegames.model.Usuario;
import com.org.generation.minhalojadegames.repository.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public String encoder(String senha) {

		return encoder.encode(senha);
	}
	
	public List<Usuario> listarUsuario(){
		return repository.findAll();
	}
	
	public Optional<Usuario> buscarUsuarioId(long id){
		return repository.findById(id);
	}
	
	
	public Optional<Usuario> CadastraUsuario(Usuario usuario) {
		if(repository.findByUsuario(usuario.getUsuario()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuario ja existe!!", null);
		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
		
		if(idade<18)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário é menor de idade!", null);
		usuario.setSenha(encoder(usuario.getSenha()));
		
		return Optional.of(repository.save(usuario));
	}	
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		for (Usuario user : this.listarUsuario()) {
            if (user.getUsuario().equals(usuario.getUsuario())) {
            	throw new ResponseStatusException(HttpStatus.CONFLICT, "Ja existe um usuario com esse nome", null);
            }
        }
		if (repository.findByUsuario(usuario.getUsuario()).isEmpty()){
			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
			if (idade < 18)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário é menor de idade!", null);
			
			usuario.setSenha(encoder(usuario.getSenha()));
			return Optional.of(repository.save(usuario));
		
		}else{
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O Usuário não encontrado!", null);
			
		}
		
	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user ){
		
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));	
				String authHeader = "Basic " + new String(encodeAuth);
				
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setToken(authHeader);
				
				return user;
			}
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "O Usuário ou Senha Inválidos!", null);
	}
	

}
