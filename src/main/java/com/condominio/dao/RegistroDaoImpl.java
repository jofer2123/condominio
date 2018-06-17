package com.condominio.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.condominio.entity.Registro;

@Repository
public class RegistroDaoImpl implements RegistroDao{

	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public void guardar(Registro reg) {
		sessionFactory.getCurrentSession().persist(reg);
	}
	@Override
	public void actualizar(Registro reg) {
		sessionFactory.getCurrentSession().update(reg);
	}
	
}
