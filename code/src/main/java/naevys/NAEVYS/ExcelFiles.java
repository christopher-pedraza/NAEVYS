package naevys.NAEVYS;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;

public class ExcelFiles {
	public static void main(String[] args) throws IOException {
		OutputStream os = new FileOutputStream("test.xlsx");
		Workbook wb = new Workbook(os, "MyApplication", "1.0");
	    Worksheet ws = wb.newWorksheet("Sheet 1");
	    ws.value(0, 0, "This is a string in A1");
	    ws.value(0, 1, new Date());
	    ws.value(0, 2, 1234);
	    ws.value(0, 3, 123456L);
	    ws.value(0, 4, 1.234);
	    wb.finish();
    }
}
