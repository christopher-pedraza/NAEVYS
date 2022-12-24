package naevys.NAEVYS;

public class Style {
	/**
	 * Nombre con el que se puede referenciar a esta configuracion de propiedades de
	 * estilo de celda
	 */
	private String styleName;

	/**
	 * Determina si la celda tendra el atributo bold
	 */
	private boolean isBold;
	/**
	 * Determina si la celda tendra el atributo italic
	 */
	private boolean isItalic;
	/**
	 * Determina si la celda tendra el atributo borderColor
	 */
	private boolean hasBorderColor;
	/**
	 * Determina si la celda tendra el atributo borderStyle
	 */
	private boolean hasBorderStyle;
	/**
	 * Determina si la celda tendra el atributo fillColor
	 */
	private boolean hasFillColor;
	/**
	 * Determina si la celda tendra el atributo fontColor
	 */
	private boolean hasFontColor;
	/**
	 * Determina si la celda tendra el atributo fontName
	 */
	private boolean hasFontName;
	/**
	 * Determina si la celda tendra el atributo fontSize
	 */
	private boolean hasFontSize;
	/**
	 * Determina si la celda tendra el atributo horizontalAlignment
	 */
	private boolean hasHorizontalAlignment;

	/**
	 * Color del borde en hexadecimal
	 * 
	 * <p>
	 * <b>Ejemplo:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>1a1a1a</code>
	 * </p>
	 */
	private String borderColor;
	/**
	 * Borde al que se le aplicara el color
	 * 
	 * <p>
	 * <b>Opciones posibles:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>TOP, BOTTOM, RIGHT, LEFT, DIAGONAL</code>
	 * </p>
	 */
	private String borderColor_border;
	/**
	 * Estilo del borde
	 * 
	 * <p>
	 * <b>Opciones posibles:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>DASH_DOT, DASH_DOT_DOT, DASHED, DOTTED, DOUBLE, HAIR, MEDIUM, MEDIUM_DASH_DOT, MEDIUM_DASH_DOT_DOT, MEDIUM_DASHED, SLANT_DASH_DOT, THICK, THIN, NONE</code>
	 * </p>
	 */
	private String borderStyle;
	/**
	 * Borde al que se le aplicara el estilo
	 * 
	 * <p>
	 * <b>Opciones posibles:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>TOP, BOTTOM, RIGHT, LEFT, DIAGONAL</code>
	 * </p>
	 */
	private String borderStyle_border;
	/**
	 * Color de relleno de la celda
	 * 
	 * <p>
	 * <b>Ejemplo:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>1a1a1a</code>
	 * </p>
	 */
	private String fillColor;
	/**
	 * Color de la letra
	 * 
	 * <p>
	 * <b>Ejemplo:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>1a1a1a</code>
	 * </p>
	 */
	private String fontColor;
	/**
	 * Nombre del estilo de letra
	 * 
	 * <p>
	 * <b>Ejemplo:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>Arial</code>
	 * </p>
	 */
	private String fontName;
	/**
	 * Tamaño de la letra
	 * 
	 * <p>
	 * <b>Ejemplo:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>12</code>
	 * </p>
	 */
	private int fontSize;
	/**
	 * Alineacion del contenido de la celda
	 * 
	 * <p>
	 * <b>Ejemplo:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>RIGHT/LEFT/CENTER</code>
	 * </p>
	 */
	private String horizontalAlignment;

	/**
	 * @param styleName Nombre con el que se puede referenciar a esta configuracion
	 *                  de propiedades de estilo de celda
	 */
	public Style(String styleName) {
		this.styleName = styleName;
	}

	/**
	 * <h1><i>convertExcel</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public addProperty(String line)</code>
	 * </p>
	 * <p>
	 * Funcion para agregar una propiedad al estilo.
	 * </p>
	 * 
	 * @param lineData Linea del archivo de configuracion de estilos con el atributo
	 *                 y su valor
	 */
	public void addProperty(String[] lineData) {
		
	}

}
