package naevys.NAEVYS;

// Para leer archivos de texto
import java.io.BufferedReader;
import java.io.FileReader;
// Para manejar excepciones correspondientes al manejo de archivos de texto
import java.io.IOException;

/**
 * Clase que lee los archivos de configuracion en archivos de texto y obtiene
 * los datos necesarios.
 * 
 * @author Christopher Gabriel Pedraza Pohlenz
 */
public class TextFiles {
	// Declara un arreglo de objetos Header para guardar las configuraciones de las
	// columnas para el archivo de salida
	private Header[] headers;
	// Declara un arreglo de objetos ExcelConstant para guardar las constantes que
	// se imprimiran en el archivo de salida
	private ExcelConstant[] constants;

	/**
	 * <h1><i>readHeaderFile</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public readHeaderFile(String fileName)</code>
	 * </p>
	 * <p>
	 * Funcion para leer el archivo de texto con las configuraciones del archivo de
	 * Excel que se exportara.
	 * </p>
	 * 
	 * @param fileName Direccion del archivo de configuracion
	 * @return <b>headers</b> Arreglo de objetos Header con las configuraciones de
	 *         todas las columnas para el archivo de Excel que se exportara
	 */
	public void readFile(String fileName) {
		initializeArray(fileName);

		// Variable para llevar registro de las lineas de configuracion leidas
		int currentLine = 0;

		// Abre el archivo de texto en la direccion especificada por el parametro
		// fileName
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			// Mientras que la linea leida no sea nula (ultima linea del archivo de texto),
			// se iterara. Se lee primero una linea fuera del ciclo por si acaso el archivo
			// esta completamente vacio
			String line = br.readLine();
			while (line != null) {
				// Se remueven todos los espacios y tabulaciones de la linea leida. Esto incluye
				// espacios en medio del texto. Por ejemplo: " tex to" se convierte en "texto"
				line = line.replaceAll("\\s+", "");

				// Se checa que cada linea no sea ni un comentario ni que este vacia. De lo
				// contrario, se ignora
				// Si la linea tiene una longitud mayor a 0 (significando que no esta vacia)
				if (line.length() > 0) {
					// Si la linea no comienza con un signo de comentario
					if (line.charAt(0) != Constants.TF.COMMENT_SIGN) {
						// Divide la linea usando el signo separador. Por ejemplo, si la linea contiene:
						// "Nombre,2" guardaria en el arreglo cada elemento en una casilla por separado:
						// lineData[0]="Nombre", lineData[1]="2"
						String[] lineData = line.split(Constants.TF.CONFIG_DIVIDER);

						// Manda este arreglo de strings al metodo 'processLine' donde se creara un
						// objeto de Header/ExcelConstant. Este se agregara al arreglo de objetos
						// 'headers'/'constants'
						processLine(fileName, lineData, currentLine);

						// Aumenta la linea actual que es equivalente a la cantidad de lineas de
						// configuracion (ignorando comentarios y lineas vacias) leidas
						currentLine++;
					}
				}
				// Lee la siguiente linea
				line = br.readLine();
			}
			// Cierra el archivo
			br.close();
		}
		// TODO: Resolucion de errores
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <h1><i>getHeaderArray</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public getHeaderArray()</code>
	 * </p>
	 * <p>
	 * Funcion para obtener el arreglo de objetos Header con las configuraciones de
	 * las columnas.
	 * </p>
	 * 
	 * @return <b>headers</b> Arreglo con las configuraciones de las columnas del
	 *         archivo de salida
	 */
	public Header[] getHeaderArray() {
		return headers;
	}

	/**
	 * <h1><i>getConstantsArray</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public getConstantsArray()</code>
	 * </p>
	 * <p>
	 * Funcion para obtener el arreglo de objetos ExcelConstant con los datos de las
	 * constantes del archivo de salida
	 * </p>
	 * 
	 * @return <b>constants</b> Arreglo con las constantes
	 */
	public ExcelConstant[] getConstantsArray() {
		return constants;
	}

	/**
	 * <h1><i>getFileSize</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public getFileSize(String fileName)</code>
	 * </p>
	 * <p>
	 * Funcion para obtener el tamaño del archivo de configuraciones quitando los
	 * comentarios y lineas vacias.
	 * </p>
	 * 
	 * @param fileName Direccion del archivo de configuracion
	 * @return <b>fileSize</b> Cantidad de lineas con datos que contiene el archivo
	 *         de configuraciones
	 */
	private int getFileSize(String fileName) {
		// Variable para guardar la cantidad de lineas que contienen datos de
		// configuracion
		int fileSize = 0;

		// Abre el archivo de texto en la direccion especificada por el parametro
		// fileName
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			// Determinar el tamaño del archivo. Mientras que la linea leida no sea nula
			// (ultima linea del archivo de texto), se iterara
			String line = br.readLine();
			while (line != null) {
				// Se remueven todos los espacios y tabulaciones de la linea leida. Esto incluye
				// espacios en medio del texto. Por ejemplo: " tex to" se convierte en "texto"
				line = line.replaceAll("\\s+", "");

				// Se checa que cada linea no sea ni un comentario ni que este vacia. De lo
				// contrario, se ignora
				// Si la linea tiene una longitud mayor a 0 (significando que no esta vacia)
				if (line.length() > 0) {
					// Si la linea no comienza con un signo de comentario
					if (line.charAt(0) != Constants.TF.COMMENT_SIGN) {
						// Si no es comentario ni esta vacia la linea, se aumenta la cantidad porque
						// signfica que se trata de una linea con cofiguracion
						fileSize++;
					}
				}
				// Leer la siguiente linea
				line = br.readLine();
			}
			// Cierra el archivo
			br.close();
		}
		// TODO: Resolucion de errores
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Regresa la cantidad de lineas con datos de configuracion
		return fileSize;
	}

	/**
	 * <h1><i>processLine</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public processLine(String fileName, String[] lineData)</code>
	 * </p>
	 * <p>
	 * Funcion que decide con que funcion procesar la linea. Dependiendo del nombre
	 * del archivo puede procesarlo como un objeto de Header o de ExcelConstant.
	 * </p>
	 * 
	 * @param fileName Direccion del archivo de configuracion
	 * @param lineData Arreglo con cada dato de configuracion de una linea del
	 *                 archivo de configuraciones
	 */
	private void processLine(String fileName, String[] lineData, int index) {
		// Dependiendo del archivo que se esta procesando es el arreglo que se
		// inicializara
		if (fileName.contains(Constants.TF.INPUT_FILE_NAME)) {
			headers[index] = processHeaderLine(lineData);
		} else if (fileName.contains(Constants.TF.CONSTANTS_FILE_NAME)) {
			constants[index] = processConstantLine(lineData);
		}
	}

	/**
	 * <h1><i>processHeaderLine</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public processHeaderLine(String[] lineData)</code>
	 * </p>
	 * <p>
	 * Funcion para crear un objeto de Header usando la configuracion de 1 linea del
	 * archivo de configuraciones.
	 * </p>
	 * 
	 * @param lineData Arreglo con cada dato de configuracion de una linea del
	 *                 archivo de configuraciones
	 * @return Un objeto Header con los datos de configuracion especificados en la
	 *         linea recibida
	 */
	private Header processHeaderLine(String[] lineData) {
		// Las lineas con la configuracion para columnas con valores por referencia
		// tienen 1 parametro mas que las demas que es el indice de dicha columna. Por
		// lo anterior, aqui se hace la distincion
		// Si se trata de una linea con la configuracion de una columna de formula o
		// valores estaticos
		if (lineData.length == Constants.TF.NORMAL_LINE_SIZE) {
			// Nombre de la columna donde se pondran los datos
			String colName = lineData[0];
			// Indice de la columna donde se imprimiran los datos
			int colIndex = Integer.parseInt(lineData[1]);
			// Tipo de columna de la que se trata (estatica o formula)
			char id = lineData[2].charAt(0);
			// Valor que se pondra en la columna (valor estatico o formula)
			String value = lineData[3];
			// Se crea el objeto de Header usando los parametros de la linea
			return new Header(colName, colIndex, id, value);
		}
		// Si se trata de una linea con la configuracion de una columna con valores por
		// referencia
		else if (lineData.length == Constants.TF.REFERENCE_LINE_SIZE) {
			// Nombre de la columna donde se pondran los datos
			String colName = lineData[0];
			// Indice de la columna donde se imprimiran los datos
			int colIndex = Integer.parseInt(lineData[1]);
			// Tipo de columna de la que se trata (referencia)
			char id = lineData[2].charAt(0);
			// Valor que se pondra en la columna (nombre de la columna de donde tomara los
			// datos del archivo de entrada)
			String value = lineData[3];
			// Indice de la columna de donde tomara los datos del archivo de entrada
			int valueIndex = Integer.parseInt(lineData[4]);
			// Se crea el objeto de Header usando los parametros de la linea
			return new Header(colName, colIndex, id, value, valueIndex);
		}

		// Si llego aqui es que hubo un error y se regresa null
		return null;
	}

	/**
	 * <h1><i>processConstantLine</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public processConstantLine(String[] lineData)</code>
	 * </p>
	 * <p>
	 * Funcion para crear un objeto de ExcelConstant usando los datos de la
	 * constante del archivo de configuracion
	 * </p>
	 * 
	 * @param lineData Arreglo con cada dato de configuracion de una linea del
	 *                 archivo de configuraciones
	 * @return Un objeto ExcelConstant con los datos de la constante
	 */
	private ExcelConstant processConstantLine(String[] lineData) {
		// Nombre de la columna donde se pondra la constante
		String colName = lineData[0];
		// Nombre que tendra la celda con el valor de la constante
		String constantName = lineData[1];
		// Nombre que tendra la celda con el valor de la constante
		double value = Double.parseDouble(lineData[2]);
		


		// Se crea el objeto de ExcelConstant usando los parametros de la linea
		return new ExcelConstant(colName, constantName, value);
	}

	private void initializeArray(String fileName) {
		// Obtiene la cantidad de lineas con datos de configuracion en el archivo
		int fileSize = getFileSize(fileName);

		// Dependiendo del archivo que se esta procesando es el arreglo que se
		// inicializara
		if (fileName.contains(Constants.TF.INPUT_FILE_NAME)) {
			// Se inicializa el arreglo con las configuraciones de las columnas
			headers = new Header[fileSize];
		} else if (fileName.contains(Constants.TF.CONSTANTS_FILE_NAME)) {
			// Se inicializa el arreglo con las constantes
			constants = new ExcelConstant[fileSize];
		}
	}
}