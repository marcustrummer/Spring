package com.org.generation.minhalojadegames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.generation.minhalojadegames.model.UserLogin;
import com.org.generation.minhalojadegames.model.Usuario;
import com.org.generation.minhalojadegames.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(usuarioService.listarUsuario());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id){
		return usuarioService.buscarUsuarioId(id)
				.map(resp->ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> loginUsuario(@RequestBody Optional<UserLogin> userLogin){
		return usuarioService.Logar(userLogin)
				.map(resp->ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
		return usuarioService.CadastraUsuario(usuario)
				.map(resp->ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario){
		return usuarioService.atualizarUsuario(usuario)
				.map(resp->ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

}
