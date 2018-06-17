package com.condominio.bean;

import static com.condominio.util.Util.addDetailMessage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.omnifaces.util.Faces;

import com.github.adminfaces.template.session.AdminSession;

/**
 *
 * Clase encargada del login del sistema.
 * @author Marval.
 * @version 1.0.
 */
@Named
@SessionScoped
@Specializes
public class LoginBean extends AdminSession implements Serializable {

	private String currentUser;
	private String email;
	private String password;
	private String usuario;
	private boolean remember;
	
	@Inject
	private AdminSession adminSession;
	
	
	/**
	 * Método constructor de la clase.
	 */
	

	/**
	 * Método encargado de realizar login de usuario.
	 * 
	 * @return String.
	 * @throws IOException.
	 */
	public void login() throws IOException {

		currentUser = usuario;
		addDetailMessage("Login exitoso como: <b>" + usuario + "</b>");
		adminSession.setIsLoggedIn(true);
		Faces.getExternalContext().getFlash().setKeepMessages(true);
	        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	        String path = ctx.getContextPath();
	            FacesContext.getCurrentInstance().getExternalContext().redirect(path + "/index.xhtml" );
	}

	@Override
	public boolean isLoggedIn() {
		
		//return adminSession.isLoggedIn();
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	
}