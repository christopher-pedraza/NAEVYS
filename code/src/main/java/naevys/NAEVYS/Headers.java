package naevys.NAEVYS;

public class Headers {
	private String colName;
	private char dataType;
	// Indice de la columna (el programa checara si corresponde a la columna con el
	// nombre descrito en colName, sino, iterara hasta buscar la columna correcta)
	private int colIndex;

	/**
	 * @param colName
	 * @param dataType
	 * @param colIndex
	 */
	public Headers(String colName, char dataType, int colIndex) {
		this.colName = colName;
		this.dataType = dataType;
		this.colIndex = colIndex;
	}

	/**
	 * @return the colName
	 */
	public String getColName() {
		return colName;
	}

	/**
	 * @param colName the colName to set
	 */
	public void setColName(String colName) {
		this.colName = colName;
	}

	/**
	 * @return the dataType
	 */
	public char getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(char dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the colIndex
	 */
	public int getColIndex() {
		return colIndex;
	}

	/**
	 * @param colIndex the colIndex to set
	 */
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}
}
