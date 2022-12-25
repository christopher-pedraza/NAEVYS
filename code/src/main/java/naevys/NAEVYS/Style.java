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
	private String borderColorSide;
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
	private String borderStyleSide;
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
		this.isBold = false;
		this.isItalic = false;
		this.hasBorderColor = false;
		this.hasBorderStyle = false;
		this.hasFillColor = false;
		this.hasFontColor = false;
		this.hasFontName = false;
		this.hasFontSize = false;
		this.hasHorizontalAlignment = false;
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
		String property = lineData[0];
		String value = lineData[1];

		if (property.equals(Constants.S.BOLD)) {
			if (value.equals(Constants.S.TRUE)) {
				isBold = true;
			}
		}
		else if (property.equals(Constants.S.ITALIC)) {
			if (value.equals(Constants.S.TRUE)) {
				isItalic = true;
			}
		}
		else if (property.equals(Constants.S.BORDER_COLOR)) {
			hasBorderColor = true;
			borderColor = value;
		}
		else if (property.equals(Constants.S.BORDER_COLOR_SIDE)) {
			borderColorSide = value;
		}
		else if (property.equals(Constants.S.BORDER_STYLE)) {
			hasBorderStyle = true;
			borderStyle = value;
		}
		else if (property.equals(Constants.S.BORDER_STYLE_SIDE)) {
			borderStyleSide = value;
		}
		else if (property.equals(Constants.S.FILL_COLOR)) {
			hasFillColor = true;
			fillColor = value;
		}
		else if (property.equals(Constants.S.FONT_COLOR)) {
			hasFontColor = true;
			fontColor = value;
		}
		else if (property.equals(Constants.S.FONT_NAME)) {
			hasFontName = true;
			fontName = value;
		}
		else if (property.equals(Constants.S.FONT_SIZE)) {
			hasFontSize = true;
			fontSize = Integer.parseInt(value);
		}
		else if (property.equals(Constants.S.HORIZONTAL_ALIGNMENT)) {
			hasHorizontalAlignment = true;
			horizontalAlignment = value;
		}
	}

	
	public void src() {
		System.out.println("---\n" + styleName);
		if (isBold) {
			System.out.println("BOLD is TRUE");
		}
		if (isItalic) {
			System.out.println("ITALIC is TRUE");
		}
		if (hasBorderColor) {
			System.out.println(borderColor);
			System.out.println(borderColorSide);
		}
		if (hasBorderStyle) {
			System.out.println(borderStyle);
			System.out.println(borderStyleSide);
		}
		if (hasFillColor) {
			System.out.println(fillColor);
		}
		if (hasFontColor) {
			System.out.println(fontColor);
		}
		if (hasFontName) {
			System.out.println(fontName);
		}
		if (hasFontSize) {
			System.out.println(fontSize);
		}
		if (hasHorizontalAlignment) {
			System.out.println(horizontalAlignment);
		}

	}
}
