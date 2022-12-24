package naevys.NAEVYS;

public class Main {

	public static void main(String[] args) {
		Constants.initConstants();
		
		TextFiles tf = new TextFiles();
		ExcelFiles ef = new ExcelFiles();

		tf.readFile(Constants.TF.INPUT_FILE_NAME);
		Header[] headers = tf.getHeaderArray();

		tf.readFile(Constants.TF.CONSTANTS_FILE_NAME);
		ExcelConstant[] constants = tf.getConstantsArray();
		
		tf.readFile(Constants.TF.STYLES_FILE_NAME);
		Style[] styles = tf.getStylesArray();

		ef.convertExcel("input.xlsx", "output.xlsx", headers, constants);
	
	}
}