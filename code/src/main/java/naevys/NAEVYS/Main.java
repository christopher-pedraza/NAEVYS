package naevys.NAEVYS;

public class Main {

	public static void main(String[] args) {
		TextFiles tf = new TextFiles();
		ExcelFiles ef = new ExcelFiles();
		
		Header[] headers = tf.readFile(Constants.TF.INPUT_FILE_NAME);

		for (Header h : headers) {
			System.out.println(h.getColName() + " " + h.getColIndex() + " " + h.getId() + " " + h.getValue());
		}
		
		Header[] inputHeaders = new Header[2];
		inputHeaders[0] = new Header("Col1", 1);
		inputHeaders[1] = new Header("Col7", 7);
		
		Header[] outputHeaders = new Header[2];
		outputHeaders[0] = new Header("Out Col 7", 1, 'R', "Col7");
		outputHeaders[1] = new Header("Out Col 1", 2, 'R', "Col1");
		
		ef.convertExcel("input.xlsx", inputHeaders, outputHeaders);
	}

}
