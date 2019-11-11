package br.com.perfilcar.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.perfilcar.usuario.models.Usuario;


public interface UsuarioRepository  extends JpaRepository<Usuario,Long> {

	Usuario findById(long idUsuario);
	
	Usuario findByNomeUsuarioAndSenhaUsuario(String nomeUsuario, String senhaUsuario);
	
	List<Usuario> findByEmailUsuario(String emailUsuario);
}
