package naevys.NAEVYS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase con constantes usadas por otras clases
 * 
 * @author Christopher Gabriel Pedraza Pohlenz
 */
public class Constants {
	private static final String configFileName = "app.config";

	/**
	 * <h1><i>initConstants</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public initConstants()</code>
	 * </p>
	 * <p>
	 * Funcion para inicializar las constantes del programa usando los valores
	 * introducidos en el archivo de configuracion.
	 * </p>
	 */
	public static void initConstants() {
		// Abre el archivo de configuracion para leer las propiedades declaradas en este
		Properties prop = new Properties();
		try (FileInputStream fis = new FileInputStream(configFileName)) {
			prop.load(fis);
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}

		// Toma la propiedad del archivo de configuracion y se la asigna a cada
		// constante del programa. Las propedades las toma como String, por lo que las
		// constantes que son caracteres se tienen que convertir a char usando la
		// funcion de la clase Integer: parseInt. Con esta funcion se convierte el
		// string a un valor entero.
		// funcion charAt(0) que toma el caracter en la primera posicion del string.
		// Para los enteros, se utiliza la
		// Constantes de la clase TextFiles
		TF.COMMENT_SIGN = prop.getProperty("NAEVYS.TF.COMMENT_SIGN").charAt(0);
		TF.STYLE_SIGN = prop.getProperty("NAEVYS.TF.STYLE_SIGN").charAt(0);
		TF.CONFIG_DIVIDER = prop.getProperty("NAEVYS.TF.CONFIG_DIVIDER");
		TF.INPUT_FILE_NAME = prop.getProperty("NAEVYS.TF.INPUT_FILE_NAME");
		TF.CONSTANTS_FILE_NAME = prop.getProperty("NAEVYS.TF.CONSTANTS_FILE_NAME");
		TF.STYLES_FILE_NAME = prop.getProperty("NAEVYS.TF.STYLES_FILE_NAME");
		TF.REFERENCE_LINE_SIZE = Integer.parseInt(prop.getProperty("NAEVYS.TF.REFERENCE_LINE_SIZE"));
		TF.NORMAL_LINE_SIZE = Integer.parseInt(prop.getProperty("NAEVYS.TF.NORMAL_LINE_SIZE"));
		// Constantes de la clase ExcelFiles
		EF.OUTPUT_SHEET_2_NAME = prop.getProperty("NAEVYS.EF.OUTPUT_SHEET_2_NAME");
		EF.OUTPUT_SHEET_1_NAME = prop.getProperty("NAEVYS.EF.OUTPUT_SHEET_1_NAME");
		EF.APPLICATION_NAME = prop.getProperty("NAEVYS.EF.APPLICATION_NAME");
		EF.APPLICATION_VERSION = prop.getProperty("NAEVYS.EF.APPLICATION_VERSION");
		EF.FORMULA_CURRENT_ROW_PLACEHOLDER = prop.getProperty("NAEVYS.EF.FORMULA_CURRENT_ROW_PLACEHOLDER");
		EF.FORMULA_CONSTANTS_PLACEHOLDER = prop.getProperty("NAEVYS.EF.FORMULA_CONSTANTS_PLACEHOLDER");
		EF.DEFAULT_CELL_PLACEHOLDER = prop.getProperty("NAEVYS.EF.DEFAULT_CELL_PLACEHOLDER");
		EF.REFERENCE_ID = prop.getProperty("NAEVYS.EF.REFERENCE_ID").charAt(0);
		EF.FORMULA_ID = prop.getProperty("NAEVYS.EF.FORMULA_ID").charAt(0);
		EF.STATIC_ID = prop.getProperty("NAEVYS.EF.STATIC_ID").charAt(0);
	}

	/**
	 * Constantes de la clase TextFiles
	 * 
	 * @author Christopher Gabriel Pedraza Pohlenz
	 */
	public static class TF {
		/**
		 * Signo usado al inicio de cualquier linea que se desea que sea ignorada. Se
		 * puede usar para agregar comentarios en el archivo de configuraciones
		 */
		public static char COMMENT_SIGN;
		/**
		 * Signo usado al inicio de cualquier linea que pertenece al estilo nombrado en
		 * una linea previa que no empieza con este simbolo
		 */
		public static char STYLE_SIGN;
		/**
		 * Signo que divide los terminos de una linea de configuracion
		 */
		public static String CONFIG_DIVIDER;
		/**
		 * Nombre del archivo que contiene las configuraciones del archivo de salida de
		 * Excel
		 */
		public static String INPUT_FILE_NAME;
		/**
		 * Nombre del archivo que contiene las constantes que se imprimiran en el
		 * archivo de salida
		 */
		public static String CONSTANTS_FILE_NAME;
		/**
		 * Nombre del archivo que contiene los estilos que se pueden aplicar a los
		 * titulos del archivo de salida de Excel
		 */
		public static String STYLES_FILE_NAME;
		/**
		 * Tamaño de las lineas de configuracion que hacen referencia a una columna con
		 * valores por referencia
		 */
		public static int REFERENCE_LINE_SIZE;
		/**
		 * Tamaño de las lineas de configuracion que hacen referencia a una columna que
		 * no tenga valores por referencia (valores estaticos o formulas)
		 */
		public static int NORMAL_LINE_SIZE;
	}

	/**
	 * Constantes de la clase ExcelFiles
	 * 
	 * @author Christopher Gabriel Pedraza Pohlenz
	 */
	public static class EF {
		/**
		 * Nombre de la hoja de calculo que contendra todos los datos del archivo de
		 * salida
		 */
		public static String OUTPUT_SHEET_2_NAME;
		/**
		 * Nombre de la hoja de calculo que contendra todos los datos del archivo de
		 * salida
		 */
		public static String OUTPUT_SHEET_1_NAME;
		/**
		 * Nombre de la aplicacion que genero el archivo de Excel de salida
		 */
		public static String APPLICATION_NAME;
		/**
		 * Version de la aplicacion que genero el archivo de Excel de salida
		 */
		public static String APPLICATION_VERSION;
		/**
		 * Simbolo que se puede agregar a las formulas especificadas por el usuario que
		 * tomara el valor del indice de la fila en la que se imprime
		 */
		public static String FORMULA_CURRENT_ROW_PLACEHOLDER;
		/**
		 * Simbolo que se puede agregar a las formulas especificadas por el usuario que
		 * tomara el valor del nombre de la hoja de calculo de las constantes
		 */
		public static String FORMULA_CONSTANTS_PLACEHOLDER;
		/**
		 * Valor que se imprimira en cualquier celda que sea manejada por el caso por
		 * defecto. Es decir, cualquier celda que tenga un error, este vacia o no se
		 * haya identificado su tipo de dato
		 */
		public static String DEFAULT_CELL_PLACEHOLDER;
		/**
		 * Identificador de una columna que tendra valores por referencia (copia de los
		 * valores del archivo de entrada)
		 */
		public static char REFERENCE_ID;
		/**
		 * Identificador de una columna que tendra formulas
		 */
		public static char FORMULA_ID;
		/**
		 * Identificador de una columna que tendra valores estaticos
		 */
		public static char STATIC_ID;
	}
}
