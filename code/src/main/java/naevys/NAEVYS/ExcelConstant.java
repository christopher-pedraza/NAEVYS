package naevys.NAEVYS;

/**
 * Clase utilizada para guardar los datos de las constantes que se guardaran en
 * el archivo de salida de Excel
 * 
 * @author Christopher Gabriel Pedraza Pohlenz
 */
public class ExcelConstant {
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
	 * Nombre del estilo que se aplicara a la celda de titulo de la columna
	 */
	private String styleName;

	/**
	 * @param colName      Nombre de la columna que contendra el valor de la
	 *                     constante
	 * @param constantName Nombre de la celda que contendra el valor de la constante
	 * @param value        Valor de la constante
	 * @param styleName    Nombre del estilo que se aplicara a la celda de titulo de
	 *                     la columna
	 */
	public ExcelConstant(String colName, String constantName, double value, String styleName) {
		this.colName = colName;
		this.constantName = cleanName(constantName);
		this.value = value;
		this.styleName = styleName;
	}

	/**
	 * @return <b>colName</b> Nombre de la columna que contendra el valor de la
	 *         constante
	 */
	public String getColName() {
		return colName;
	}

	/**
	 * @return <b>constantName</b> Nombre de la celda que contendra el valor de la
	 *         constante
	 */
	public String getConstantName() {
		return constantName;
	}

	/**
	 * @return <b>value</b> Valor de la constante
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @return <b>styleName</b> Nombre del estilo que se aplicara a la celda de
	 *         titulo de la columna
	 */
	public String getStyleName() {
		return styleName;
	}

	/**
	 * <h1><i>cleanName</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public cleanName(String str)</code>
	 * <p>
	 * Funcion para remover cualquier caracter invalido (que no sea Alfanumerico o
	 * guion bajo) para asignar en el nombre de una celda de Excel.
	 * </p>
	 * 
	 * @param str Nombre de la celda que tiene que ser limpiado
	 * @return Nombre sin caracteres invalidos
	 */
	private String cleanName(String str) {
		return str.replaceAll("[^A-Za-z0-9_]", "");
	}

}
