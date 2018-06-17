package com.condominio.dao;

import java.util.List;

import com.condominio.entity.Usuarios;

public interface UsuariosDao {

	Usuarios buscarUser(String user);

	void guardar(Usuarios user);

	void actualizar(Usuarios user);

	List<Usuarios> lista();

}
