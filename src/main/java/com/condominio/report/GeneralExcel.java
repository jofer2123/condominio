package com.condominio.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class GeneralExcel {

	public byte[] crearExcelContenedores(List<Object> list) {
		byte[] data = null;
		List<Object> listAux = new ArrayList<Object>();
		try {
			// CREAMOS EL LIBRO
			Date fecha = new Date();

			File fi = new File(String.valueOf(fecha.getTime()));
			WorkbookSettings conf = new WorkbookSettings();
			conf.setEncoding("ISO-8859-1");
			WritableWorkbook workbook = Workbook.createWorkbook(fi, conf);

			// CREAMOS LAS HOJAS DEL EXCEL
			WritableSheet sheet = workbook.createSheet("Resultado", 0);

			// DEFINIR FUENTE PARA EL CONTENIDO
			WritableFont hv = new WritableFont(WritableFont.COURIER, 10, WritableFont.NO_BOLD);
			WritableFont ht = new WritableFont(WritableFont.COURIER, 13, WritableFont.BOLD);
			WritableFont hc = new WritableFont(WritableFont.COURIER, 11, WritableFont.BOLD);
			WritableFont hd = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
			
			WritableCellFormat hformatTitulos = new WritableCellFormat(ht);
			WritableCellFormat hformatViaje = new WritableCellFormat(hv);
			WritableCellFormat hformatColumnas = new WritableCellFormat(hc);
			WritableCellFormat hformatData = new WritableCellFormat(hd);
			WritableCellFormat hformatDaAmarillo = new WritableCellFormat(hd);
			
			//DEFINIR CELDAS AUTOAJUSTABLES
			hformatData.setShrinkToFit(true);
			hformatColumnas.setShrinkToFit(true);
			
			//DEFINIR COLORES A CELDAS
			ht.setColour(Colour.BLACK);
			hformatColumnas.setBackground(Colour.LIGHT_BLUE);
			hformatDaAmarillo.setBackground(Colour.YELLOW);
			
			//DEFINIR POSICION A CELDA Y BORDE
			hformatTitulos.setAlignment(Alignment.CENTRE);
			hformatViaje.setAlignment(Alignment.CENTRE);
			hformatColumnas.setAlignment(Alignment.CENTRE);
			hformatData.setAlignment(Alignment.CENTRE);
			hformatColumnas.setBorder(Border.ALL, BorderLineStyle.MEDIUM, Colour.BLACK);
			hformatData.setBorder(Border.ALL, BorderLineStyle.DOTTED, Colour.BLACK);
			hformatDaAmarillo.setBorder(Border.ALL, BorderLineStyle.DOTTED, Colour.BLACK);
			

			ClassLoader classLoader = getClass().getClassLoader();
			File imageFile = new File(classLoader.getResource("image/Seaport.png").getFile());
			WritableImage imagen = new WritableImage(16, 1, 3, 4, imageFile);
			// AÑADIR CONTENDIO POR CELDAS
			
			// PARA EL TITULO
			sheet.mergeCells(0,1, 13,1);

			// PARA	COLOCAR LA IMAGEN
			sheet.addImage(imagen);
			sheet.mergeCells(0, 6, 4, 6);
			
			// PARA LLENAR TABLA FCL
			sheet.addCell(new jxl.write.Label(0, 6, "CONTENEDORES FCL/CLIENTES", hformatTitulos));

			sheet.addCell(new jxl.write.Label(0, 7, "N°", hformatColumnas));

			sheet.mergeCells(1, 7, 2, 7);
			sheet.addCell(new jxl.write.Label(1, 7, "CONTENEDOR", hformatColumnas));

			sheet.addCell(new jxl.write.Label(3, 7, "TIPO", hformatColumnas));

			sheet.addCell(new jxl.write.Label(4, 7, "PESO", hformatColumnas));

			sheet.mergeCells(5, 7, 7, 7);
			sheet.addCell(new jxl.write.Label(5, 7, "BL", hformatColumnas));

			sheet.mergeCells(8, 7, 9, 7);
			sheet.addCell(new jxl.write.Label(8, 7, "SELLO", hformatColumnas));

			sheet.mergeCells(10, 7, 13, 7);
			sheet.addCell(new jxl.write.Label(10, 7, "FW", hformatColumnas));

			sheet.addCell(new jxl.write.Label(14, 7, "IMO", hformatColumnas));

			sheet.mergeCells(15, 7, 16, 7);
			sheet.addCell(new jxl.write.Label(15, 7, "CONDICIÓN", hformatColumnas));

			sheet.mergeCells(17, 7, 19, 7);
			sheet.addCell(new jxl.write.Label(17, 7, "AGENCIA DE ADUANA", hformatColumnas));

			sheet.mergeCells(20, 7, 21, 7);
			sheet.addCell(new jxl.write.Label(20, 7, "CÓDIGO AGA", hformatColumnas));
			// lista bl
			
			int i = 8;
			int numero = 1;
			

			workbook.write();
			System.out.println("******Excel descargado******");
			workbook.close();
			Path path = Paths.get(fi.getPath());
			data = Files.readAllBytes(path);

			fi.delete();
		} catch (IOException ex) {
			Logger.getLogger(GeneralExcel.class.getName()).log(Level.SEVERE, null, ex);
		} catch (WriteException ex) {
			Logger.getLogger(GeneralExcel.class.getName()).log(Level.SEVERE, null, ex);
		}
		return data;
	}
}
