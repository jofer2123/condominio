package com.condominio.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

public class Constantes {

	public static final String ESTADO_ACTIVO = "ACTIVO";
	public static final String ESTADO_TRASPASADO = "TRASPASADO";
	public static final String ENTER_KEY = "n";
	public static final String USER = "root";
	public static final String HOST = "192.168.0.14";
	public static final String PASS = "admin123";
	public List<String> SENTIDO_OPER = Arrays.asList("I", "S", "TR", "TRB");
	public List<String> TIPO_SELLO = Arrays.asList("AD", "AG", "CA", "CU", "OTR", "SAG", "SERNAP", "SH", "SUR", "CU", "TO");
	public List<String> TIPO_EQUIPO = Arrays.asList("F", "G", "R");
	public List<String> COND_TRANS = Arrays.asList("HH", "HP", "PP", "PH");
	public List<String> NACION_ID = Arrays.asList("CL", "CN", "CO", "US");
	public List<String> NOM_PARTICIPANTES = Arrays.asList("ALM", "EMI", "REP", "EMIDO", "EMB", "CONS", "NOTI", "NOTI2");
	public List<String> CODIGO_ALMACEN = Arrays.asList("ALM", "ALM2", "ALM3", "ALM4");
	public List<String> COMUNA = Arrays.asList("Santiago", "Viña", "Valparaiso");
	public List<String> SERVICES = Arrays.asList("LINER", "TRAMP");
	public List<String> TIPO_PARTICIPANTE = Arrays.asList("RUT", "PAS", "ADU");
	public List<String> UNIDAD_PESO = Arrays.asList("KGM","D41","FOT","GLD","GLI","GLL","GRM","HGM","HLT","HMT","INH","KTM","KTN","LBR","ONZ","STN","TNE","UNI");
	public List<String> UNIDAD_VOLUMEN = Arrays.asList("CMQ","FTQ","INQ","LTR","MTQ");
	public List<String> TIPO_LOCACION = Arrays.asList("LE", "PE", "PD", "LD", "LEM", "LRM");
	public List<String> STATUS_CONTENEDOR = Arrays.asList("EMPTY", "FCL/FCL", "LCL/LCL", "BB", "FCL/LCL", "FCL/BB",
						"LCL/BB", "FCL/LCL/BB", "CY/CY", "CFS/CFS", "CFS/CY", "CY/CFS", "CY/DOOR", "DOOR/CY", "LCL/FCL", "DOOR/DOOR", "RoRo");
	public List<String> TIPO_REFERENCIAS = Arrays.asList("REF", "REFMANANT", "MADRE", "REFBLANT", "REFDUS");
	public List<String> TIPO_OBSERVACION = Arrays.asList("GRAL", "MOT", "01", "02", "03", "04", "05", "06", "07", "08",	"09", "10", "11", "12");
	public List<String> TIPO_DOCUMENTO = Arrays.asList("BL", "BOO", "CP", "DELIVORDER", "GA", "GCOURIER", "LCORREOS",
						"LENCARGOS", "LPASAJEROS", "LPROVISION", "C", "MFTO", "MIC", "TIF", "DUS");
	public char si = "S".charAt(0);
	public char no = "N".charAt(0);

	public List<SelectItem> listaStringASelect(List<String> lis) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (String t : lis) {
			SelectItem select = new SelectItem();
			select.setValue(t);
			select.setLabel(t);
			lista.add(select);
		}
		return lista;
	}
}
