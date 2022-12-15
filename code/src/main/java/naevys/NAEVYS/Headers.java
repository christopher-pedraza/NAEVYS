package naevys.NAEVYS;

public class Headers {
	private String colName;
	// Indice de la columna (el programa checara si corresponde a la columna con el
	// nombre descrito en colName, sino, iterara hasta buscar la columna correcta)
	private int colIndex;

	/**
	 * @param colName
	 * @param colIndex
	 */
	public Headers(String colName, int colIndex) {
		this.colName = colName;
		this.colIndex = colIndex;
	}

	/**
	 * @return the colName
	 */
	public String getColName() {
		return colName;
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
