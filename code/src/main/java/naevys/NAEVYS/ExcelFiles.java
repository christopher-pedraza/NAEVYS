package naevys.NAEVYS;

// Para especificar la direccion de un archivo y abrirlo
import java.io.FileInputStream;
// Excepciones usadas para comprobar errores y resolverlos
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//Para abrir archivos de entrada y de salida
import java.io.InputStream;
import java.io.OutputStream;
// Para abrir un flujo de la hoja de calculo de Excel
import java.util.stream.Stream;

// Importaciones de FastExcel
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

/**
 * Clase que maneja las interacciones del programa con archivos de Excel, ya sea
 * para leerlos o imprimir en estos
 * 
 * @author Christopher Gabriel Pedraza Pohlenz
 */
public class ExcelFiles {
	// Variables de control especificando la fila y columna actual. Se actualizan
	// los valores en cada iteracion
	private static int col = 0;
	private static int row = 0;

	/**
	 * <h1><i>convertExcel</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public convertExcel(String inputFileDir, String outputFileDir, Header[] outputHeaders, ExcelConstant[] exConstants)</code>
	 * </p>
	 * <p>
	 * Funcion para crear un nuevo archivo de Excel usando datos de otro archivo
	 * fuente al igual que otros valores especificados en los archivos de
	 * configuracion
	 * </p>
	 * 
	 * @param inputFileDir  Direccion del archivo de entrada
	 * @param outputFileDir Direccion del archivo de salida
	 * @param outputHeaders Especificaciones de las columnas que se exportaran
	 * @param exConstants   Especificaciones de las constantes que se exportaran
	 * @param styles        Estilos disponibles para aplicar a las celdas
	 */
	public void convertExcel(String inputFileDir, String outputFileDir, Header[] outputHeaders,
			ExcelConstant[] exConstants, Style[] styles) {
		// Abre el archivo de Excel de entrada y crea uno de salida
		try (InputStream is = new FileInputStream(inputFileDir);
				ReadableWorkbook inWb = new ReadableWorkbook(is);
				OutputStream os = new FileOutputStream(outputFileDir)) {

			// Output Workbook
			// Crea un libro de Excel nuevo
			Workbook outWb = new Workbook(os, Constants.EF.APPLICATION_NAME, Constants.EF.APPLICATION_VERSION);

			// Escribe la hoja de las tarifas y de constantes
			writeConstantsWorksheet(outWb, exConstants, styles);
			writeDataWorksheet(inWb, outWb, outputHeaders, styles);

			// Se cierra el archivo de entrada y se escriben los cambios al archivo de
			// salida
			inWb.close();
			outWb.finish();
		} catch (FileNotFoundException e) {
			// Si encuentra un error, mostrar mensaje descriptivo
			GUI.showErrorMessage(e);
		} catch (IOException e) {
			// Si encuentra un error, mostrar mensaje descriptivo
			GUI.showErrorMessage(e);
		}
	}

	/**
	 * <h1><i>writeDataWorksheet</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private writeDataWorksheet(ReadableWorkbook inWb, Workbook outWb, Header[] outputHeaders)</code>
	 * </p>
	 * <p>
	 * Funcion para abrir un archivo de Excel de entrada, leer las columnas
	 * especificadas por los headers e imprimir los valores en un nuevo archivo de
	 * salida.
	 * </p>
	 * 
	 * @param inWb          Libro que se leera para obtener los datos
	 * @param outWb         Libro donde se escribiran los datos
	 * @param outputHeaders Especificaciones de las columnas que se exportaran
	 * @param styles        Estilos disponibles para aplicar a las celdas
	 */
	private void writeDataWorksheet(ReadableWorkbook inWb, Workbook outWb, Header[] outputHeaders, Style[] styles)
			throws IOException {
		// En el libro de Excel previo se crea una hoja de calculo
		Worksheet outWs = outWb.newWorksheet(Constants.EF.OUTPUT_SHEET_2_NAME);

		// Input workbook
		// Obtiene la primer hoja del archivo de Excel especificado por inputFileDir
		// Sheet sheet = inWb.getFirstSheet();
		Sheet sheet = inWb.getSheet(Constants.EF.INPUT_SHEET_INDEX).get();

		// Separa la hoja de calculo en filas para poder desplazarse por ellas
		try (Stream<Row> rows = sheet.openStream()) {
			// Reiniciar el valor de row para permitir exportar multiples archivos de excel
			// sin dejar lineas en blanco
			row = 0;

			// Se itera por cada una de las filas del Excel de entrada. Cada fila es
			// guardada en cada iteracion en la variable 'r'
			rows.forEach(r -> {
				// Por cada fila se itera en el arreglo de headers porque este contiene las
				// especificaciones de cada columna que se va a imprimir en el archivo de
				// salida. Si el header dice que sera una referencia, se tendra que leer la
				// columna al igual que imprimirla, de lo contrario, no se necesitara leer la
				// columna y solo se imprimira otro valor distinto
				for (int i = 0; i < outputHeaders.length; i++) {
					// col toma el valor del indice donde se especifico en el header que la columna
					// se deberia de imprimir
					col = outputHeaders[i].getColIndex();

					// Si se trata de la primera fila, significa que es la fila de los titulos
					if (row == 0) {
						// Se imprime en la columna correspondiente (especificada por colIndex) y en la
						// primera fila el nombre de la columna especificado por el usuario
						outWs.value(row, col, outputHeaders[i].getColTitle());
						// Aplicar estilo guardado en el objeto a la celda
						styleCell(outWs, row, col, styles, outputHeaders[i].getStyleName());

						// En caso de que se trate de un valor por referencia, se tendra que determinar
						// si los indices especificados en el archivo corresponden al nombre
						// especificado en el mismo lugar. Se toma prioridad por el nombre que por el
						// indice, por lo que se checa que el indice corresponda con el nombre y no
						// viceversa. Solo se corrige en caso de que sea una referencia debido a que las
						// formulas o valores estaticos no contienen el dato del indice de la columna de
						// entrada
						if (outputHeaders[i].getId() == Constants.EF.REFERENCE_ID) {
							// Si el valor en string de la celda en la posicion especificada por el indice
							// valueIndex no corresponde con el valor (nombre de la columna) en el header,
							// se tiene que corregir el indice
							if (!r.getCell(outputHeaders[i].getValueIndex()).getValue().toString()
									.equals(outputHeaders[i].getValue())) {
								// Se itera la primer fila por la totalidad de sus columnas para checar todos
								// los titulos de la columnas del archivo de entrada
								for (int j = 0; j < r.getCellCount(); j++) {
									// Si se encuentra una celda con el mismo valor especificado en el header, se
									// actualiza el indice con el valor de la variable de control del ciclo 'j'
									if (outputHeaders[i].getValue().equals(r.getCell(j).getValue().toString())) {
										// Como la numeracion de las columnas es 0-basada, y el metodo
										// 'setValueIndex' le resta 1 a cualquier valor recibido (para que sea mas
										// intuitivo para el usuario especificar la primer columna como 1 en vez de
										// 0), se tiene que sumar +1 al valor de 'j'
										outputHeaders[i].setValueIndex(j + 1);
									}
								}
							}
						}
					}

					// Si no es la primera fila, se checa el identificador del header para saber de
					// que tipo de columna se trata.

					// Si se trata de una columna con valores por referencia (valores copiados del
					// Excel de entrada)
					else if (outputHeaders[i].getId() == Constants.EF.REFERENCE_ID) {
						// Se obtiene la celda en el indice especificado por el header (que ya fue
						// corregido si era necesario)
						Cell c = r.getCell(outputHeaders[i].getValueIndex());

						// Se determina el tipo de dato guardado en la celda. Esto se hace para intentar
						// imprimir en el archivo de Excel de salida en el mismo tipo de dato. Si no se
						// realizara esto podria haber errores al imprimir en un tipo erroneo o se
						// tendria que imprimir todo como texto, lo que arruinaria numeros, fechas,
						// valores booleanos, formulas, etc.
						switch (c.getType()) {
							// Si es un STRING (Texto), se toma el valor de la celda como String y se
							// imprime en la celda especifica
							case STRING: {
								outWs.value(row, col, c.asString());
								break; // Para prevenir que se chequen los demas casos
							}
							// Si es un BOOLEAN (Tipo de dato logico), se toma el valor de la celda como
							// Boolean y se imprime en la celda especifica
							case BOOLEAN: {
								outWs.value(row, col, c.asBoolean());
								break; // Para prevenir que se chequen los demas casos
							}
							// Si es un NUMBER (Numero), se toma el valor de la celda como BigDecimal y se
							// imprime en la celda especifica
							case NUMBER: {
								outWs.value(row, col, c.asNumber());
								break; // Para prevenir que se chequen los demas casos
							}
							// Si es una FORMULA (Formula), se toma el valor de la celda como String y se
							// imprime en la celda especifica
							case FORMULA: {
								// A diferencia de la funcion 'value', 'formula' le agrega al texto un '=' antes
								// y le da formato a la celda como una formula
								outWs.formula(row, col, c.getFormula());
								break; // Para prevenir que se chequen los demas casos
							}
							default: {
								// Si la celda tiene un error, esta vacia o no se logro determinar su tipo de
								// dato, se pone un espacio (DEFAULT_CELL_PLACEHOLDER) como valor por defecto
								outWs.value(row, col, Constants.EF.DEFAULT_CELL_PLACEHOLDER);
								break; // Para prevenir que se chequen los demas casos
							}
						}
					}

					// Si se trata de un valor estatico (el mismo valor en todas las columnas)
					else if (outputHeaders[i].getId() == Constants.EF.STATIC_ID) {
						// Se determina si el valor estatico prodria ser un numero. En caso de que si se
						// pueda convertir, se imrpimira en el Excel como numero
						if (isNumeric(outputHeaders[i].getValue())) {
							// Se convierte el String a un Double en caso de que contenga decimales e
							// imprime en el Excel
							outWs.value(row, col, Double.parseDouble(outputHeaders[i].getValue()));
						}
						// De lo contrario, se imprimira solamente como un texto
						else {
							outWs.value(row, col, outputHeaders[i].getValue());
						}
					}

					// Si se trata de una formula
					else if (outputHeaders[i].getId() == Constants.EF.FORMULA_ID) {
						// Se guarda la formula guardada en el header en un String a parte para hacer
						// modificaciones
						String formula = outputHeaders[i].getValue();

						// Se checa si el string contiene el placeholder del valor de la fila. Es decir,
						// es un placeholder que tomara el valor del indice de la fila en la que se esta
						// imprimiendo. De esta manera se puede hacer formulas que tomen valores de la
						// misma fila. Por ejemplo: "A#+B#" seria equivalente a "A3+B3" en la tercera
						// fila
						if (formula.contains(Constants.EF.FORMULA_CURRENT_ROW_PLACEHOLDER)) {
							// Se reemplaza el placeholder por el indice de la fila + 1. Se le suma 1 porque
							// las filas estan numeradas basadas en 0, por lo que la fila 1 seria 0.
							formula = formula.replaceAll(Constants.EF.FORMULA_CURRENT_ROW_PLACEHOLDER,
									Integer.toString(row + 1));
						}

						// Se checa si el string contiene el placeholder de la hoja de constantes. De
						// esta manera se puede hacer formulas usando constantes. Por ejemplo: "1 +
						// @variable" seria equivalente a "1 + Datos!variable"
						if (formula.contains(Constants.EF.FORMULA_CONSTANTS_PLACEHOLDER)) {
							// Se reemplaza el placeholder por el indice de la fila + 1. Se le suma 1 porque
							// las filas estan numeradas basadas en 0, por lo que la fila 1 seria 0.
							formula = formula.replaceAll(Constants.EF.FORMULA_CONSTANTS_PLACEHOLDER,
									Constants.EF.OUTPUT_SHEET_1_NAME + "!");
						}

						// Una vez que ya se hicieron las modificaciones (o no en caso de que no
						// contenia ningun placeholder), se imprime en la celda del archivo de salida
						outWs.formula(row, col, formula);
					}
				}

				// Actualizamos el indice de la fila para que las iteraciones de las filas del
				// archivo de entrada correspondan con las filas donde se esta escribiendo en el
				// archivo de salida
				row++;

				// Escribe las filas en memoria al output stream del libro de Excel. Es
				// recomendable llamar a este metodo periodicamente en caso de que se trabaje
				// que grandes cantidades de datos para no sobrecargar la memoria
				try {
					outWs.flush();
				} catch (IOException e) {
					// Si encuentra un error, mostrar mensaje descriptivo
					GUI.showErrorMessage(e);
				}
			});
		}

		for (Header h : outputHeaders) {
			outWs.range(1, h.getColIndex(), row - 1, h.getColIndex()).setName(h.getColName());
			;
		}

		// Cerrar hoja de calculo del libro de salida. No se pueden hacer modificaciones
		// en otra hoja cuando se hace flush hasta que se llame a finish()
		outWs.finish();
	}

	/**
	 * <h1><i>writeConstantsWorksheet</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private writeConstantsWorksheet(Workbook outWb, ExcelConstant[] exConstants)</code>
	 * </p>
	 * <p>
	 * Funcion para imprimir los valores de las constantes en una nueva hoja del
	 * archivo de Excel.
	 * </p>
	 * 
	 * @param outWb       Libro donde se escribiran los datos
	 * @param exConstants Especificaciones de las constantes que se exportaran
	 * @param styles      Estilos disponibles para aplicar a las celdas
	 * @throws IOException
	 */
	private void writeConstantsWorksheet(Workbook outWb, ExcelConstant[] exConstants, Style[] styles)
			throws IOException {
		// En el libro de Excel se crea una hoja de calculo
		Worksheet outWs = outWb.newWorksheet(Constants.EF.OUTPUT_SHEET_1_NAME);

		// Se restaura el valor de la columna que se actualizara cada iteracion
		col = 0;
		// Itera en el arreglo de las constantes
		for (ExcelConstant constant : exConstants) {
			// Escribe el nombre de la constante (titulo de la columna)
			outWs.value(0, col, constant.getColTitle());
			// Escribe el valor de la constante
			outWs.value(1, col, constant.getValue());
			// A la celda con el valor de la constante se le aplica el nombre para que se
			// pueda hacer referencia
			outWs.range(1, col, 1, col).setName(constant.getConstantName());
			// Aplica estilo a la celda con el titulo de la columna
			styleCell(outWs, 0, col, styles, constant.getStyleName());
			// Aumenta el valor de la columna
			col++;
		}

		// Cerrar hoja de calculo del libro de salida. No se pueden hacer modificaciones
		// en otra hoja cuando se hace flush hasta que se llame a finish()
		outWs.finish();
	}

	/**
	 * <h1><i>styleCell</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private styleCell(Worksheet ws, int r, int c, Style[] styles, String styleName)</code>
	 * </p>
	 * <p>
	 * Funcion para aplicar un estilo a la celda especificada por la fila y columna.
	 * </p>
	 * 
	 * @param ws        Hoja de calculo donde se encuentra la celda a la que se
	 *                  aplicara el estilo
	 * @param row       Fila donde se encuentra la celda
	 * @param col       Columna donde se encuentra la celda
	 * @param styles    Arreglo con los estilos disponibles
	 * @param styleName Nombre del estilo que se tiene que aplicar a la celda
	 */
	private void styleCell(Worksheet ws, int row, int col, Style[] styles, String styleName) {
		if (!styleName.equals(Constants.S.NONE_STYLE)) {
			// Itera a traves de los estilos y si existe uno con el nombre especificado,
			// guarda su indice
			int index = 0;
			boolean found = false;
			for (Style s : styles) {
				// Si los nombres coinciden se sale del ciclo para conservar el indice
				if (s.getStyleName().equals(styleName)) {
					found = true;
					break;
				}
				index++;
			}

			// Si se encontro un estilo con el nombre que se especifico para la celda
			if (found) {
				// Se declara un objeto de estilo para que sea mas corto hacer referencia al
				// estilo
				Style s = styles[index];

				// Dependiendo del estilo definido para la columna, es el estilo que se aplica
				// al titulo de esta. Se aplican...
				// ...negritas
				if (s.isBold()) {
					ws.style(row, col).bold().set();
				}
				// ...italica
				if (s.isItalic()) {
					ws.style(row, col).italic().set();
				}
				// ...color en...
				if (s.hasBorderColor()) {
					// ...todos los bordes
					if (s.hasBorderColorSide()) {
						ws.style(row, col).borderColor(s.getBorderColor()).set();
					}
					// ...un borde en especifico
					else {
						ws.style(row, col).borderColor(s.getBorderColorSide(), s.getBorderColor()).set();
					}
				}
				// ...estilo de borde en...
				if (s.hasBorderStyle()) {
					// ...todos los bordes
					if (s.hasBorderStyleSide()) {
						ws.style(row, col).borderStyle(s.getBorderStyle()).set();
					}
					// ...un borde en especifico
					else {
						ws.style(row, col).borderStyle(s.getBorderStyleSide(), s.getBorderStyle()).set();
					}
				}
				// ...color de relleno
				if (s.hasFillColor()) {
					ws.style(row, col).fillColor(s.getFillColor()).set();
				}
				// ...color de letra
				if (s.hasFontColor()) {
					ws.style(row, col).fontColor(s.getFontColor()).set();
				}
				// ...nombre de fuente
				if (s.hasFontName()) {
					ws.style(row, col).fontName(s.getFontName()).set();
				}
				// ...tamaño de fuente
				if (s.hasFontSize()) {
					ws.style(row, col).fontSize(s.getFontSize()).set();
				}
				// ...alineamiento horizontal
				if (s.hasHorizontalAlignment()) {
					ws.style(row, col).horizontalAlignment(s.getHorizontalAlignment()).set();
				}
			}
		}
	}

	/**
	 * <h1><i>isNumeric</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private isNumeric(String str)</code>
	 * </p>
	 * <p>
	 * Funcion para determinar si el string recibido se puede convertir a un numero.
	 * </p>
	 * 
	 * @param str texto que se checara si se puede convertir en un numero (que puede
	 *            contener punto flotante)
	 * @return <b>Verdadero/Falso</b> si se puede convertir a un numero
	 */
	private static boolean isNumeric(String str) {
		// Intenta convertir el texto a un numero con punto flotante. Si lo logra,
		// regresa Verdadero, de lo contrario, regresa Falso
		try {
			// Convertir string (texto) a double (numero con capacidad de punto flotante)
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
