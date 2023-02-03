package naevys.NAEVYS;

public class Main {

	public static void main(String[] args) {
		
		Constants.initConstants();
		
		TextFiles tf = new TextFiles();
		
		tf.readFile(Constants.TF.INPUT_FILE_NAME);
		Header[] headers = tf.getHeaderArray();

		tf.readFile(Constants.TF.CONSTANTS_FILE_NAME);
		ExcelConstant[] constants = tf.getConstantsArray();
		
		tf.readFile(Constants.TF.STYLES_FILE_NAME);
		Style[] styles = tf.getStylesArray();
	
		GUI gui = new GUI();
		gui.init(headers, constants, styles);
	}
}