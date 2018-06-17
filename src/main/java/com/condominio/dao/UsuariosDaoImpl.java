package com.condominio.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.condominio.entity.Usuarios;

@Repository
public class UsuariosDaoImpl implements UsuariosDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Usuarios buscarUser(String user) {
		String query = "FROM Usuarios as us where us.user=:user";
		return (Usuarios) sessionFactory.getCurrentSession().createQuery(query).setParameter("user", user).list().get(0);
	}
	@Override
	public void guardar(Usuarios user) {
		sessionFactory.getCurrentSession().persist(user);
	}
	@Override
	public void actualizar(Usuarios user) {
		sessionFactory.getCurrentSession().update(user);
	}
	@Override
	public List<Usuarios> lista(){
		String query = "FROM Usuarios as a";
		return sessionFactory.getCurrentSession().createQuery(query).list();
	}
	
}
