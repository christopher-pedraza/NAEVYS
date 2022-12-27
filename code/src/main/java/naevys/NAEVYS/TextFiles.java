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
	// Declara un arreglo de objetos Style para guardar los estilos que se pueden
	// aplicar en el archivo de salida
	private Style[] styles;

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
						// Si se trata del archivo de estilos, la division en las lineas es distinta, al
						// igual del conteo de la linea actual
						if (fileName.contains(Constants.TF.STYLES_FILE_NAME)) {
							// Eliminar el signo que simboliza que son atributos de 
							line = line.replaceAll(String.valueOf(Constants.TF.STYLE_SIGN), "");
							
							// Divide la linea usando el signo separador. Por ejemplo, si la linea contiene:
							// "Bold:True" guardaria en el arreglo cada elemento en una casilla por
							// separado:
							// lineData[0]="Bold", lineData[1]="True"
							String[] lineData = line.split(Constants.TF.STYLES_DIVIDER);

							// TODO: Hacer una manera para cambiar el current line solo cuando se lee un
							// nuevo nombre de estilo y no con los atributos
							if (lineData.length == 1) {
								// Aumenta la linea actual que es equivalente a la cantidad de estilos
								currentLine++;
							}
							
							// Manda este arreglo de strings al metodo 'processLine' donde se creara un
							// objeto de Header/ExcelConstant. Este se agregara al arreglo de objetos
							// 'headers'/'constants'
							processLine(fileName, lineData, currentLine);
						}
						// En cambio, si son los otros archivos
						else {
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
	 * <h1><i>getStylesArray</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public getStylesArray()</code>
	 * </p>
	 * <p>
	 * Funcion para obtener el arreglo de objetos Style con los estilos disponibles.
	 * </p>
	 * 
	 * @return <b>styles</b> Arreglo con los estilos
	 */
	public Style[] getStylesArray() {
		return styles;
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
						// Si se trata del archivo de estilos
						if (fileName.contains(Constants.TF.STYLES_FILE_NAME)) {
							// Si la linea no comienza con el signo identificador de estilo
							if (line.charAt(0) != Constants.TF.STYLE_SIGN) {
								// Si no es comentario, esta vacia la linea o es un atributo del estilo, se
								// aumenta la cantidad porque signfica que se trata de una linea con
								// el nombre de estilo
								fileSize++;
							}
						}
						// Si se trata de los archivos de input o constantes
						else {
							// Si no es comentario ni esta vacia la linea, se aumenta la cantidad porque
							// signfica que se trata de una linea con cofiguracion
							fileSize++;
						}
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
		} else if (fileName.contains(Constants.TF.STYLES_FILE_NAME)) {
			// Se le resta 1 al indce para hacer referencia al arreglo de estilos (que es 0
			// basado)
			styles[index-1] = processStyleLine(lineData, index - 1);
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
			String colTitle = lineData[0];
			// Referencia de la columna completa para usar en formulas
			String colName = lineData[1];
			// Indice de la columna donde se imprimiran los datos
			int colIndex = Integer.parseInt(lineData[2]);
			// Tipo de columna de la que se trata (estatica o formula)
			char id = lineData[3].charAt(0);
			// Valor que se pondra en la columna (valor estatico o formula)
			String value = lineData[4];
			// Nombre del estilo que se aplicara al titulo de la columna
			String styleName = lineData[5];
			// Se crea el objeto de Header usando los parametros de la linea
			return new Header(colTitle, colName, colIndex, id, value, styleName);
		}
		// Si se trata de una linea con la configuracion de una columna con valores por
		// referencia
		else if (lineData.length == Constants.TF.REFERENCE_LINE_SIZE) {
			// Nombre de la columna donde se pondran los datos
			String colTitle = lineData[0];
			// Referencia de la columna completa para usar en formulas
			String colName = lineData[1];
			// Indice de la columna donde se imprimiran los datos
			int colIndex = Integer.parseInt(lineData[2]);
			// Tipo de columna de la que se trata (referencia)
			char id = lineData[3].charAt(0);
			// Valor que se pondra en la columna (nombre de la columna de donde tomara los
			// datos del archivo de entrada)
			String value = lineData[4];
			// Indice de la columna de donde tomara los datos del archivo de entrada
			int valueIndex = Integer.parseInt(lineData[5]);
			// Nombre del estilo que se aplicara al titulo de la columna
			String styleName = lineData[6];
			// Se crea el objeto de Header usando los parametros de la linea
			return new Header(colTitle, colName, colIndex, id, value, valueIndex, styleName);
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
		// Nombre del estilo que se aplicara al titulo de la columna
		String styleName = lineData[3];
		
		// Se crea el objeto de ExcelConstant usando los parametros de la linea
		return new ExcelConstant(colName, constantName, value, styleName);
	}

	/**
	 * <h1><i>processStyleLine</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public processStyleLine(String[] lineData)</code>
	 * </p>
	 * <p>
	 * Funcion para crear un objeto de Style usando los datos de estilo del archivo
	 * de configuracion.
	 * </p>
	 * 
	 * @param lineData Arreglo con cada dato de configuracion de una linea del
	 *                 archivo de configuraciones
	 * @return Un objeto Style con los datos del estilo
	 */
	private Style processStyleLine(String[] lineData, int index) {
		// Si la linea solo tiene 1 valor, significa que se trata del nombre del estilo
		if (lineData.length == 1) {
			// Se crea un objeto de Style usando el nombre especificado
			return new Style(lineData[0]);
		}
		// En cambio, si tiene mas se trata de los atributos del estilo
		else {
			styles[index].addProperty(lineData);
			return styles[index];
		}
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
		} else if (fileName.contains(Constants.TF.STYLES_FILE_NAME)) {
			// Se inicializa el arreglo con las constantes
			styles = new Style[fileSize];
		}
	}
}