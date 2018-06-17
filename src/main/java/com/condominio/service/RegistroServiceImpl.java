package com.condominio.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.dao.RegistroDao;
import com.condominio.entity.Registro;

@Service
public class RegistroServiceImpl implements RegistroService{
	
	@Autowired
	RegistroDao registroDao;
	
	
	@Transactional
	@Override
	public void guardar(Registro reg) {
		registroDao.guardar(reg);
	}
	@Transactional
	@Override
	public void actualizar(Registro reg) {
		registroDao.actualizar(reg);
	}
}
