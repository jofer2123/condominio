package com.condominio.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.XML;
import org.omnifaces.util.Messages;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * @author Marval.
 */
@ApplicationScoped
public class Util implements Serializable {

	public static void addDetailMessage(String message) {
		addDetailMessage(message, null);
	}

	public static void addDetailMessage(String message, FacesMessage.Severity severity) {

		FacesMessage facesMessage = Messages.create("").detail(message).get();
		if (severity != null && severity != FacesMessage.SEVERITY_INFO) {
			facesMessage.setSeverity(severity);
		}
		Messages.add(null, facesMessage);
	}

	// Convierte InputStream a Byte
	public byte[] convertirTobyteArray(InputStream is) throws IOException {
		byte[] bytes = null;
		if (is != null) {
			bytes = new byte[is.available()];
			is.read(bytes);
		}
		return bytes;
	}

	// Convierte InputStream a Document
	public Document InputStreamToXml(InputStream fXmlFile) throws Throwable {
		Reader reader = new InputStreamReader(fXmlFile, "UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(is);
		return doc;
	}

	public static String StringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	// Convierte Document a String
	public String xmlToString(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = null;
		try {
			t = tf.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringWriter sw = new StringWriter();
		try {
			t.transform(new DOMSource(doc), new StreamResult(sw));
		} catch (TransformerException e) {
			System.out.println("Error " + e); // TODO Auto-generated catch block
		}
		return sw.toString();
	}

	// Convierte String a JSON
	public static String stringToJson(String xml) {
		org.json.JSONObject xmlJSONObj = XML.toJSONObject(xml);
		String jsonPrettyPrintString = xmlJSONObj.toString(4);
		return jsonPrettyPrintString;
	}

	public Date stringADate(String fecha) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat formatterDMA = new SimpleDateFormat("dd-MM-yyyy");
		if (fecha.length() > 11) {
			return formatter.parse(fecha);
		} else {
			return formatterDMA.parse(fecha);
		}

	}

	public static String descomprimeGZIP(byte[] gzip) throws IOException {

		ByteArrayInputStream bis = new ByteArrayInputStream(gzip);
		GZIPInputStream gis = new GZIPInputStream(bis);
		BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		gis.close();
		bis.close();

		return sb.toString();
	}

	public static String limpiaXml(String xml) {

		String xmlBlsAux = xml.replaceAll("<!\\[CDATA\\[", "");
		String xmlBlsAux2 = xmlBlsAux.replaceAll("\\]\\]>", "");
		String xmlBlsAux3 = xmlBlsAux2.replaceAll("&", "&#038;");
		//String xmlBlsAux4 = xmlBlsAux3.replaceAll("\"", "&quot;");
		xml = xmlBlsAux3.replaceAll("Ë‹", "&apos;");

		return xml;
	}
}