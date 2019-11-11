package br.com.perfilcar.usuario.resources;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.perfilcar.usuario.models.Usuario;
import br.com.perfilcar.usuario.repository.UsuarioRepository;
import br.com.perfilcar.usuario.service.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/usuario")
@Api(value = "API REST Usuarios")
public class UsuarioResource {

	private final Producer producerkafka;

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioResource(UsuarioRepository usuarioRepository, Producer producer) {
		this.usuarioRepository = usuarioRepository;
		this.producerkafka = producer;
	}

	@GetMapping("/usuarios")
	@ApiOperation(value="Retorna uma lista de usuarios")
	public List<Usuario> listaUsuarios() {
		String value = "Pedido de Lista de Usuario" + "-" + ZonedDateTime.now().toString();
		this.producerkafka.sendMessage(value);

		return usuarioRepository.findAll();
	}

	@GetMapping("/usuarios/{emailUsuario}")
	@ApiOperation(value="Retorna um usuario por email")
	public List<Usuario> listaUsuariosPorEmail(@PathVariable(value = "emailUsuario") String emailUsuario) {
		String value = "Pedido de Lista de Usuario por e-mail" + "-" + emailUsuario + "-"
				+ ZonedDateTime.now().toString();

		this.producerkafka.sendMessage(value);
		return usuarioRepository.findByEmailUsuario(emailUsuario);
	}

	@GetMapping("/usuario/{idUsuario}")
	@ApiOperation(value="Retorna um usuario por id")
	public Usuario listausuarioUnico(@PathVariable(value = "idUsuario") long idUsuario) {
		String value = "Pedido de Lista de Usuario por ID" + "-" + idUsuario + "-" + ZonedDateTime.now().toString();

		this.producerkafka.sendMessage(value);

		return usuarioRepository.findById(idUsuario);
	}

	@PostMapping("/usuario")
	@ApiOperation(value="Cria um novo usuario")
	public Usuario salvaUsuario(@RequestBody Usuario usuario) {
		String value = "Pedido de criacao de Usuario" + "-" + ZonedDateTime.now().toString();

		this.producerkafka.sendMessage(value);

		return usuarioRepository.save(usuario);
	}

	@PutMapping("/usuario")
	@ApiOperation(value="Atualiza um novo usuario")
	public Usuario atualizaUsuario(@RequestBody Usuario usuario) {
		String value = "Pedido de atualizacao de Usuario" + "-" + ZonedDateTime.now().toString();

		this.producerkafka.sendMessage(value);

		return usuarioRepository.save(usuario);
	}

	@DeleteMapping("/usuario")
	@ApiOperation(value="Exclui um novo usuario")
	public void deletaUsuario(@RequestBody Usuario usuario) {
		String value = "Pedido de delecao de usuario" + "-" + ZonedDateTime.now().toString();

		this.producerkafka.sendMessage(value);

		usuarioRepository.delete(usuario);
	}

}