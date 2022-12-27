package naevys.NAEVYS;

// TODO: agregar archivo de Style.txt donde se puedan crear estilos para aplicar a celdas y agregar atributo a header para que cada columna sepa que estilo tiene que aplicar

/**
 * Clase utilizada para guardar los datos de las columnas que seran leidas del
 * archivo fuente al igual que las columnas que se generaran en el archivo de
 * salida
 * 
 * @author Christopher Gabriel Pedraza Pohlenz
 */
public class Header {
	/**
	 * Titulo de la columna
	 */
	private String colTitle;
	/**
	 * Referencia que se le aplicara a toda la columna
	 */
	private String colName;
	/**
	 * Indice donde se imprimira la columna en el archivo de salida
	 */
	private int colIndex;
	/**
	 * Identificador sobre el tipo de columna de la que se trata (referencia,
	 * formula, estatico)
	 */
	private char id;
	/**
	 * Valor (estatico o formula) o referencia (nombre de la columna que se leera
	 * del archivo de entrada) que se colocara en la columna
	 */
	private String value;
	/**
	 * Indice de la columna que se leera del archivo de entrada. Toma prioridad el
	 * nombre de la columna expresado en 'value' en caso de que no corresponda el
	 * indice
	 */
	private int valueIndex;
	/**
	 * Nombre del estilo que se aplicara a la celda de titulo de la columna
	 */
	private String styleName;

	/**
	 * @param colTitle   Titulo de la columna
	 * @param colName    Referencia que se le aplicara a toda la columna
	 * @param colIndex   Indice donde se imprimira la columna en el archivo de
	 *                   salida
	 * @param id         Identificador sobre el tipo de columna de la que se trata
	 *                   (referencia, formula, estatico)
	 * @param value      Valor (estatico o formula) o referencia (nombre de la
	 *                   columna que se leera del archivo de entrada) que se
	 *                   colocara en la columna
	 * @param valueIndex Indice de la columna que se leera del archivo de entrada.
	 *                   Toma prioridad el nombre de la columna expresado en 'value'
	 *                   en caso de que no corresponda el indice
	 * @param styleName  Nombre del estilo que se aplicara a la celda de titulo de
	 *                   la columna
	 */
	public Header(String colTitle, String colName, int colIndex, char id, String value, int valueIndex,
			String styleName) {
		this.colTitle = colTitle;
		this.colName = colName;
		this.colIndex = colIndex - 1;
		this.id = id;
		this.value = value;
		this.valueIndex = valueIndex - 1;
		this.styleName = styleName;
	}

	/**
	 * @param colTitle  Titulo de la columna
	 * @param colName   Referencia que se le aplicara a toda la columna
	 * @param colIndex  Indice donde se imprimira la columna en el archivo de salida
	 * @param id        Identificador sobre el tipo de columna de la que se trata
	 *                  (referencia, formula, estatico)
	 * @param value     Valor (estatico o formula) o referencia (nombre de la
	 *                  columna que se leera del archivo de entrada) que se colocara
	 *                  en la columna
	 * @param styleName Nombre del estilo que se aplicara a la celda de titulo de la
	 *                  columna
	 */
	public Header(String colTitle, String colName, int colIndex, char id, String value, String styleName) {
		this.colTitle = colTitle;
		this.colName = colName;
		this.colIndex = colIndex - 1;
		this.id = id;
		this.value = value;
		this.valueIndex = -1;
		this.styleName = styleName;
	}

	/**
	 * @return <b>colIndex</b> Indice donde se imprimira la columna en el archivo de
	 *         salida
	 */
	public int getColIndex() {
		return colIndex;
	}

	/**
	 * @return <b>colTitle</b> Titulo de la columna
	 */
	public String getColTitle() {
		return colTitle;
	}

	/**
	 * @return <b>colName</b> Referencia que se le aplicara a toda la columna
	 */
	public String getColName() {
		return colName;
	}

	/**
	 * @return <b>id</b> Identificador sobre el tipo de columna de la que se trata
	 *         (referencia, formula, estatico)
	 */
	public char getId() {
		return id;
	}

	/**
	 * @param value Valor (estatico o formula) o referencia (nombre de la columna
	 *              que se leera del archivo de entrada) que se colocara en la
	 *              columna
	 */
	public String getValue() {
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
	 * @return <b>styleName</b>
	 */
	public int getValueIndex() {
		return valueIndex;
	}

	/**
	 * @param colIndex Indice donde se imprimira la columna en el archivo de salida
	 */
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex - 1;
	}

	/**
	 * @param valueIndex Indice de la columna que se leera del archivo de entrada.
	 *                   Toma prioridad el nombre de la columna expresado en 'value'
	 *                   en caso de que no corresponda el indice
	 */
	public void setValueIndex(int valueIndex) {
		this.valueIndex = valueIndex - 1;
	}
}
