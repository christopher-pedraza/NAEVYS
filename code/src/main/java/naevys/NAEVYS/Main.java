package naevys.NAEVYS;

/**
 * Clase principal del NAEVYS. Esta clase llama a todas las demas para construir
 * la aplicacion.
 * 
 * @author Christopher Gabriel Pedraza Pohlenz
 */
public class Main {

	/**
	 * <h1><i>main</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public main(String[] args)</code>
	 * </p>
	 * <p>
	 * Funcion para inicializar y llamar a todas las demas clases que componen a
	 * NAEVYS.
	 * </p>
	 * 
	 * @param args Argumentos que se pueden pasar cuando se ejecuta la aplicacion
	 */
	public static void main(String[] args) {
		// Clase que contiene todas las constantes usadas a lo largo de la aplicacion
		// Inicializa las constantes leyendo el archivo de configuracion
		Constants.initConstants();
		
		// Clase que maneja la lectura de los archivos de texto
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