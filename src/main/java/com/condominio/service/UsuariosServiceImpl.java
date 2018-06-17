package com.condominio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.dao.UsuariosDao;
import com.condominio.entity.Usuarios;

@Service
public class UsuariosServiceImpl implements UsuariosService{

	@Autowired
	UsuariosDao usuariosDao;
	
	@Transactional
	@Override
	public Usuarios buscarUser(String user) {
		return usuariosDao.buscarUser(user);
	}
	@Transactional
	@Override
	public void guardar(Usuarios user) {
		usuariosDao.guardar(user);
	}
	@Transactional
	@Override
	public void actualizar(Usuarios user) {
		usuariosDao.actualizar(user);
	}
	@Transactional
	@Override
	public List<Usuarios> lista(){
		return usuariosDao.lista();
	}
	
}
