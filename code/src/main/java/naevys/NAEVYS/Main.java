package naevys.NAEVYS;

public class Main {

	public static void main(String[] args) {
		TextFiles tf = new TextFiles();
		ExcelFiles ef = new ExcelFiles();

		Header[] headers = tf.readFile(Constants.TF.INPUT_FILE_NAME);

		ef.convertExcel("input.xlsx", "output.xlsx", headers);
	}
}