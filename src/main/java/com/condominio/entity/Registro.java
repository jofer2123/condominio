package com.condominio.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "registro", schema = "dbo")
public class Registro implements Serializable{
private Long id;
private Usuarios user;
private String accion;
private Date fecha;
@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
@ManyToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "usuario", nullable = false)
public Usuarios getUser() {
	return user;
}
public void setUser(Usuarios user) {
	this.user = user;
}
@Column(name = "fecha")
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
@Column(name = "accion")
public String getAccion() {
	return accion;
}
public void setAccion(String accion) {
	this.accion = accion;
}



}
