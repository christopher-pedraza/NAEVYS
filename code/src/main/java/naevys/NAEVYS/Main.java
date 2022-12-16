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
		
		Header[] outputHeaders = new Header[2];
		
		ef.convertExcel("input.xlsx", inputHeaders, outputHeaders);
	}

}
