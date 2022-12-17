package naevys.NAEVYS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

public class ExcelFiles {

	public static int col, row;

//	public static void main(String[] args) throws IOException {
//		OutputStream os = new FileOutputStream("test.xlsx");
//		Workbook wb = new Workbook(os, "MyApplication", "1.0");
//		Worksheet ws = wb.newWorksheet("Sheet 1");
//		ws.value(0, 0, "This is a string in A1");
//		ws.value(0, 1, new Date());
//		ws.style(0, 1).format("yyyy-MM-dd H:mm:ss").set();
//		ws.value(0, 2, 1234);
//		ws.value(0, 3, 123456L);
//		ws.value(0, 4, 1.234);
//		ws.style(0, 0).bold().fillColor(Color.AMETHYST).set();
//		wb.finish();
//	}

	public void convertExcel(String fileDir, Header[] outputHeaders) {

		try (InputStream is = new FileInputStream(fileDir);
				ReadableWorkbook inWb = new ReadableWorkbook(is);
				OutputStream os = new FileOutputStream("output.xlsx")) {
			// Output workbook
			Workbook outWb = new Workbook(os, Constants.EF.APPLICATION_NAME, Constants.EF.APPLICATION_VERSION);
			Worksheet outWs = outWb.newWorksheet(Constants.EF.OUTPUT_SHEET_1_NAME);

			// Input workbook
			Sheet sheet = inWb.getFirstSheet();
			try (Stream<Row> rows = sheet.openStream()) {
				col = 0;
				row = 0;

				rows.forEach(r -> {
					for (int i = 0; i < outputHeaders.length; i++) {
						col = outputHeaders[i].getColIndex();

						if (row == 0) {
							outWs.value(row, col, outputHeaders[i].getColName());

							// Correccion de indice de las columnas especificado en el Header
							if (outputHeaders[i].getId() == 'R') {
								if (!r.getCell(outputHeaders[i].getValueIndex()).getValue().toString()
										.equals(outputHeaders[i].getValue())) {
									for (int j = 0; j < r.getCellCount(); j++) {
										if (outputHeaders[i].getValue().equals(r.getCell(j).getValue().toString())) {
											outputHeaders[i].setValueIndex(j + 1);
										}
									}
								}
							}
						}

						else if (outputHeaders[i].getId() == 'R') {
							Cell c = r.getCell(outputHeaders[i].getValueIndex());

							switch (c.getType()) {
								case STRING: {
									outWs.value(row, col, c.asString());
									break;
								}
								case BOOLEAN: {
									outWs.value(row, col, c.asBoolean());
									break;
								}
								case NUMBER: {
									outWs.value(row, col, c.asNumber());
									break;
								}
								case FORMULA: {
									outWs.formula(row, col, c.getFormula());
									break;
								}
								default: {
									outWs.value(row, col, " ");
									break;
								}
							}
						} else if (outputHeaders[i].getId() == 'S') {
							if (isNumeric(outputHeaders[i].getValue())) {
								outWs.value(row, col, Double.parseDouble(outputHeaders[i].getValue()));
							} else {
								outWs.value(row, col, outputHeaders[i].getValue());
							}
						} else if (outputHeaders[i].getId() == 'F') {
							
						}
					}

					row++;
				});
			}

			inWb.close();
			outWb.finish();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
}
