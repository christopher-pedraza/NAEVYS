package naevys.NAEVYS;

public class Main {

	public static void main(String[] args) {
		TextFiles tf = new TextFiles();
		ExcelFiles ef = new ExcelFiles();

		Header[] headers = tf.readFile(Constants.TF.INPUT_FILE_NAME);

		ef.convertExcel("D:/Folders/Desktop/Consulta20220929_224718.xlsx", "output.xlsx", headers);
	}

}
