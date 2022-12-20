package naevys.NAEVYS;

/**
 * Clase utilizada para guardar los datos de las constantes que se guardaran en
 * el archivo de salida de Excel
 * 
 * @author Christopher Gabriel Pedraza Pohlenz
 */
public class ExcelConstants {
	/**
	 * Nombre de la columna que contendra el valor de la constante
	 */
	private String colName;
	/**
	 * Nombre de la celda que contendra el valor de la constante
	 */
	private String constantName;
	/**
	 * Valor de la constante
	 */
	private double value;

	/**
	 * @param colName      Nombre de la columna que contendra el valor de la
	 *                     constante
	 * @param constantName Nombre de la celda que contendra el valor de la constante
	 * @param value        Valor de la constante
	 */
	public ExcelConstants(String colName, String constantName, double value) {
		this.colName = colName;
		this.constantName = cleanName(constantName);
		this.value = value;
	}

	/**
	 * <h1><i>cleanName</i></h1>
	 * <p style="margin-left: 10px; margin-top: 15px; margin-bottom: 15px">
	 * <code> public cleanName(String str)</code>
	 * <p>
	 * Funcion para remover cualquier caracter invalido (que no sea Alfanumerico o
	 * guion bajo) para asignar en el nombre de una celda de Excel.
	 * </p>
	 * 
	 * @param str Nombre de la celda que tiene que ser limpiado
	 */
	private String cleanName(String str) {
		return str;
	}

}
