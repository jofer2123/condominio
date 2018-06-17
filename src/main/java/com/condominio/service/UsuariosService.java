package com.condominio.service;

import java.util.List;

import com.condominio.entity.Usuarios;

public interface UsuariosService {

	List<Usuarios> lista();

	void actualizar(Usuarios user);

	void guardar(Usuarios user);

	Usuarios buscarUser(String user);

}
