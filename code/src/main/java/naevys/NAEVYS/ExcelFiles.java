package naevys.NAEVYS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Stream;

import org.dhatim.fastexcel.Color;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
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
	
	public void convertExcel(String fileDir, Header[] inputHeaders, Header[] outputHeaders) {

		
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
					r.forEach(c -> {
						outWs.value(row, col, c.getValue().toString());
						System.out.println("[" + row + "," + col + "] " + c.getValue().toString());
						col++;
					});
					col = 0;
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
}
