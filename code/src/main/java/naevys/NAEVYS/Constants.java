package naevys.NAEVYS;

/**
 * Clase con constantes usadas por otras clases
 * 
 * @author Christopher Gabriel Pedraza Pohlenz
 */
public class Constants {
	/**
	 * Constantes de la clase TextFiles
	 * 
	 * @author Christopher Gabriel Pedraza Pohlenz
	 */
	public class TF {
		/**
		 * Signo usado al inicio de cualquier linea que se desea que sea ignorada. Se
		 * puede usar para agregar comentarios en el archivo de configuraciones
		 */
		public static final char COMMENT_SIGN = '#';
		/**
		 * Signo que divide los terminos de una linea de configuracion
		 */
		public static final String CONFIG_DIVIDER = "[|]";
		/**
		 * Nombre del archivo que contiene las configuraciones del archivo de salida de
		 * Excel
		 */
		public static final String INPUT_FILE_NAME = "input.txt";
		/**
		 * Nombre del archivo que contiene las constantes que se imprimiran en el
		 * archivo de salida
		 */
		public static final String CONSTANTS_FILE_NAME = "constants.txt";
		/**
		 * Tamaño de las lineas de configuracion que hacen referencia a una columna con
		 * valores por referencia
		 */
		public static final int REFERENCE_LINE_SIZE = 5;
		/**
		 * Tamaño de las lineas de configuracion que hacen referencia a una columna que
		 * no tenga valores por referencia (valores estaticos o formulas)
		 */
		public static final int NORMAL_LINE_SIZE = 4;
	}

	/**
	 * Constantes de la clase ExcelFiles
	 * 
	 * @author Christopher Gabriel Pedraza Pohlenz
	 */
	public class EF {
		/**
		 * Nombre de la hoja de calculo que contendra todos los datos del archivo de
		 * salida
		 */
		public static final String OUTPUT_SHEET_2_NAME = "Tarifas";
		/**
		 * Nombre de la hoja de calculo que contendra todos los datos del archivo de
		 * salida
		 */
		public static final String OUTPUT_SHEET_1_NAME = "Datos";
		/**
		 * Nombre de la aplicacion que genero el archivo de Excel de salida
		 */
		public static final String APPLICATION_NAME = "NAEVYS";
		/**
		 * Version de la aplicacion que genero el archivo de Excel de salida
		 */
		public static final String APPLICATION_VERSION = "2.0.1";
		/**
		 * Simbolo que se puede agregar a las formulas especificadas por el usuario que
		 * tomara el valor del indice de la fila en la que se imprime
		 */
		public static final String FORMULA_CURRENT_ROW_PLACEHOLDER = "#";
		/**
		 * Simbolo que se puede agregar a las formulas especificadas por el usuario que
		 * tomara el valor del nombre de la hoja de calculo de las constantes
		 */
		public static final String FORMULA_CONSTANTS_PLACEHOLDER = "@@";
		/**
		 * Valor que se imprimira en cualquier celda que sea manejada por el caso por
		 * defecto. Es decir, cualquier celda que tenga un error, este vacia o no se
		 * haya identificado su tipo de dato
		 */
		public static final String DEFAULT_CELL_PLACEHOLDER = " ";
		/**
		 * Identificador de una columna que tendra valores por referencia (copia de los
		 * valores del archivo de entrada)
		 */
		public static final char REFERENCE_ID = 'R';
		/**
		 * Identificador de una columna que tendra formulas
		 */
		public static final char FORMULA_ID = 'F';
		/**
		 * Identificador de una columna que tendra valores estaticos
		 */
		public static final char STATIC_ID = 'S';
	}
}
