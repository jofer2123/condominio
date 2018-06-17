package com.condominio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Component;

import com.condominio.util.SSh;

@ManagedBean
@SessionScoped
@Component
public class ControlBean implements Serializable{

	private String comando;
	private List<String> list=new ArrayList<String>();
	SSh ssh=new SSh();
	
	public String getComando() {
		return comando;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public void ejecutaComando() {
		list.clear();
		list.addAll(ssh.comandoLinux(this.comando));
		
		
	}
	
}
