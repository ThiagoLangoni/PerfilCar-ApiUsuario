package br.com.perfilcar.usuario.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
	private String emailUsuario;
	private String nomeUsuario;
    private String senhaUsuario;
	
}