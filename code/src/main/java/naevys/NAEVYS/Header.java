package naevys.NAEVYS;

// TODO: agregar archivo de Style.txt donde se puedan crear estilos para aplicar a celdas

/**
 * Clase utilizada para guardar los datos de las columnas que seran leidas del
 * archivo fuente al igual que las columnas que se generaran en el archivo de
 * salida
 */
public class Header {
	private String colName;
	// TODO: Indice de la columna (el programa checara si corresponde a la columna
	// con el nombre descrito en colName, sino, iterara hasta buscar la columna
	// correcta)
	private int colIndex;
	private char id;
	private String value;

	/**
	 * @param colName  nombre de la columna
	 * @param colIndex indice donde se deberia encontra la columna con el nombre
	 *                 colName
	 * @param id       identificador sobre el tipo de columna de la que se trata
	 * @param value    valor o referencia que se colocara en la columna
	 * @param color    color del titulo de la columna
	 */
	public Header(String colName, int colIndex, char id, String value) {
		this.colName = colName;
		this.colIndex = colIndex - 1;
		this.id = id;
		this.value = value;
	}

	/**
	 * @param colName  nombre de la columna
	 * @param colIndex indice donde se deberia encontra la columna con el nombre
	 *                 colName
	 */
	public Header(String colName, int colIndex) {
		this.colName = colName;
		this.colIndex = colIndex - 1;
		this.id = ' ';
		this.value = "";
	}

	/**
	 * @return the colIndex
	 */
	public int getColIndex() {
		return colIndex;
	}

	/**
	 * @return the colName
	 */
	public String getColName() {
		return colName;
	}

	/**
	 * @return the id
	 */
	public char getId() {
		return id;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param colIndex the colIndex to set
	 */
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex - 1;
	}
}
