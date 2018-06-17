package com.condominio.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios", schema = "dbo")
public class Usuarios implements Serializable{

	private String user;
	private String pass;
	private String mail;

	@Id
	@Column(name = "usuario", unique = true, nullable = false, length = 50)
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Column(name = "pass", unique = true, nullable = false, length = 50)
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getMail() {
		return mail;
	}
	@Column(name = "mail", unique = true, nullable = false, length = 50)
	public void setMail(String mail) {
		this.mail = mail;
	}


	
	
}
