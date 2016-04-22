package es.uniovi.asw.physicalVotes.physicalVotesConfig;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Hace la validación del usuario admin y comprueba todo
 * lo necesario antes de pedir los datos de las votaciones
 */
public class RVotes implements ReadVotes {
	/**
	 * Método que lee todos los votantes incluidos en un archivo Excel y los
	 * devuelve
	 */
	@Override
	public Map<Integer, ArrayList<String>> leerFichero(String nombreFichero) {
		Map<Integer, ArrayList<String>> mapa = new HashMap<Integer, ArrayList<String>>();
		int cont = 0;

		try {

			FileInputStream file = new FileInputStream(new File(nombreFichero));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();

				ArrayList<String> arr = new ArrayList<String>();

				if (!(row.getRowNum() == 0)) { // Para omitir la primera fila...
					// Tipo voto
					if (row.getCell(0) == null)
						arr.add("");
					else {
						arr.add(row.getCell(0).toString());
						System.out.println(row.getCell(0).toString());
					}
					// opcion escogida
					if (row.getCell(1) == null)
						arr.add("-1");
					else {
						arr.add(row.getCell(1).toString());
						System.out.println(row.getCell(1).toString());
					}
					// total votos
					if (row.getCell(2) == null)
						arr.add("-1");
					else {
						arr.add(row.getCell(2).toString());
						System.out.println(row.getCell(2).toString());
					}
					// id votacion
					if (row.getCell(3) == null)
						arr.add("-1");
					else {
						arr.add(row.getCell(3).toString());
						System.out.println(row.getCell(3).toString());
					}
					// codigo colegio electoral
					if (row.getCell(4) == null)
						arr.add("");
					else{
						arr.add(row.getCell(4).toString());//
						System.out.println(row.getCell(4).toString());
					}

					mapa.put(cont++, arr);
				}

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						// System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case Cell.CELL_TYPE_STRING:
						// System.out.print(cell.getStringCellValue() + "\t");
						break;
					}

					workbook.close();

				}
			}
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapa;

	}
}
