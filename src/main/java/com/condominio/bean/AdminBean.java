package com.condominio.bean;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.condominio.entity.Usuarios;
import com.condominio.service.UsuariosService;
import com.condominio.util.Util;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

@ManagedBean
@SessionScoped
@Component
public class AdminBean implements Serializable {
	@Autowired
	UsuariosService usuariosService;
	private String[] check;
	private String qr;
	private Usuarios usuario=new Usuarios();
	

	private List<String> listaString=new ArrayList<String>();
	
	public String[] getCheck() {
		return check;
	}


	public void setCheck(String[] check) {
		this.check = check;
	}

	public String getQr() {
		return qr;
	}


	public void setQr(String qr) {
		this.qr = qr;
	}





	public List<String> getListaString() {
		return listaString;
	}


	public void setListaString(List<String> listaString) {
		this.listaString = listaString;
	}


	public Usuarios getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}


	public void recibeXml(FileUploadEvent event) throws Throwable {

		UploadedFile fil = event.getFile();
		String tipo="";

		if (fil != null) {
			String file = fil.getFileName();
			
			
			
			String xml = Util.StringFromInputStream(fil.getInputstream());
			String xmlAux = Util.limpiaXml(xml);
			String jsonAux = Util.stringToJson(xmlAux);
			JSONObject jsonObjPadre = new JSONObject(jsonAux);
		

		}

	}
	public void leeQR(FileUploadEvent event) throws Throwable {
		UploadedFile fil = event.getFile();
		Reader lector = new MultiFormatReader(); 
		  BufferedImage   imagen = ImageIO.read(fil.getInputstream()); 
		  LuminanceSource fuente = new BufferedImageLuminanceSource(imagen); 
		  BinaryBitmap mapaBits = new BinaryBitmap(new HybridBinarizer(fuente)); 
		  Result resultado = lector.decode(mapaBits);
		  this.qr=resultado.getText();
		
		 
	}
	public void leeImagen(FileUploadEvent event) throws Throwable {
		UploadedFile fil = event.getFile();
		ITesseract instance = new Tesseract();
		ClassLoader classLoader = getClass().getClassLoader();
		String patch=classLoader.getResource("tessdata").getPath();
		instance.setDatapath(patch.substring(1, patch.length()));
		instance.setLanguage("spa");
		BufferedImage   imagen = ImageIO.read(fil.getInputstream()); 
		this.qr=instance.doOCR(imagen);
	}
public void guardar() {
	usuariosService.guardar(usuario);
	usuario=new Usuarios();
}

	

	
}
