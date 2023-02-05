package naevys.NAEVYS;

import org.dhatim.fastexcel.BorderSide;

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
	 * Determina si la celda tendra el atributo borderColorSide
	 */
	private boolean hasBorderColorSide;
	/**
	 * Determina si la celda tendra el atributo borderStyle
	 */
	private boolean hasBorderStyle;
	/**
	 * Determina si la celda tendra el atributo borderStyleSide
	 */
	private boolean hasBorderStyleSide;
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
	private BorderSide borderColorSide;
	/**
	 * Estilo del borde
	 * 
	 * <p>
	 * <b>Opciones posibles:</b>
	 * </p>
	 * <p style="margin-left: 10px">
	 * <code>none, thin, medium, dashed, dotted, thick, double, hair, mediumDashed, dashDot, mediumDashDot, dashDotDot, mediumDashDotDot, slantDashDot</code>
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
	private BorderSide borderStyleSide;
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
	 * <code>general/left/center/right/fill/justify/centerContinuous/distributed</code>
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
		this.hasBorderColorSide = false;
		this.hasBorderStyle = false;
		this.hasBorderStyleSide = false;
		this.hasFillColor = false;
		this.hasFontColor = false;
		this.hasFontName = false;
		this.hasFontSize = false;
		this.hasHorizontalAlignment = false;
	}

	/**
	 * @return <b>styleName</b> Nombre con el que se puede referenciar a esta
	 *         configuracion de propiedades de estilo de celda
	 */
	public String getStyleName() {
		return styleName;
	}

	/**
	 * @return <b>isBold</b> Determina si la celda tendra el atributo bold
	 */
	public boolean isBold() {
		return isBold;
	}

	/**
	 * @return <b>isItalic</b> Determina si la celda tendra el atributo italic
	 */
	public boolean isItalic() {
		return isItalic;
	}

	/**
	 * @return <b>hasBorderColor</b> Determina si la celda tendra el atributo
	 *         borderColor
	 */
	public boolean hasBorderColor() {
		return hasBorderColor;
	}

	/**
	 * @return <b>hasBorderColorSide</b> Determina si la celda tendra el atributo
	 *         borderColorSide
	 */
	public boolean hasBorderColorSide() {
		return hasBorderColorSide;
	}

	/**
	 * @return <b>hasBorderStyle</b> Determina si la celda tendra el atributo
	 *         borderStyle
	 */
	public boolean hasBorderStyle() {
		return hasBorderStyle;
	}

	/**
	 * @return <b>hasBorderStyleSide</b> Determina si la celda tendra el atributo
	 *         borderStyleSide
	 */
	public boolean hasBorderStyleSide() {
		return hasBorderStyleSide;
	}

	/**
	 * @return <b>hasFillColor</b> Determina si la celda tendra el atributo
	 *         fillColor
	 */
	public boolean hasFillColor() {
		return hasFillColor;
	}

	/**
	 * @return <b>hasFontColor</b> Determina si la celda tendra el atributo
	 *         fontColor
	 */
	public boolean hasFontColor() {
		return hasFontColor;
	}

	/**
	 * @return <b>hasFontName</b> Determina si la celda tendra el atributo fontName
	 */
	public boolean hasFontName() {
		return hasFontName;
	}

	/**
	 * @return <b>hasFontSize</b> Determina si la celda tendra el atributo fontSize
	 */
	public boolean hasFontSize() {
		return hasFontSize;
	}

	/**
	 * @return <b>hasHorizontalAlignment</b> Determina si la celda tendra el
	 *         atributo horizontalAlignment
	 */
	public boolean hasHorizontalAlignment() {
		return hasHorizontalAlignment;
	}

	/**
	 * @return <b>borderColor</b> Color del borde en hexadecimal
	 * 
	 *         <p>
	 *         <b>Ejemplo:</b>
	 *         </p>
	 *         <p style="margin-left: 10px">
	 *         <code>1a1a1a</code>
	 *         </p>
	 */
	public String getBorderColor() {
		return borderColor;
	}

	/**
	 * @return <b>borderColorSide</b> Borde al que se le aplicara el color
	 * 
	 *         <p>
	 *         <b>Opciones posibles:</b>
	 *         </p>
	 *         <p style="margin-left: 10px">
	 *         <code>TOP, BOTTOM, RIGHT, LEFT, DIAGONAL</code>
	 *         </p>
	 */
	public BorderSide getBorderColorSide() {
		return borderColorSide;
	}

	/**
	 * @return <b>borderStyle</b> Estilo del borde
	 * 
	 *         <p>
	 *         <b>Opciones posibles:</b>
	 *         </p>
	 *         <p style="margin-left: 10px">
	 *         <code>DASH_DOT, DASH_DOT_DOT, DASHED, DOTTED, DOUBLE, HAIR, MEDIUM, MEDIUM_DASH_DOT, MEDIUM_DASH_DOT_DOT, MEDIUM_DASHED, SLANT_DASH_DOT, THICK, THIN, NONE</code>
	 *         </p>
	 */
	public String getBorderStyle() {
		return borderStyle;
	}

	/**
	 * @return <b>borderStyleSide</b> Borde al que se le aplicara el estilo
	 * 
	 *         <p>
	 *         <b>Opciones posibles:</b>
	 *         </p>
	 *         <p style="margin-left: 10px">
	 *         <code>TOP, BOTTOM, RIGHT, LEFT, DIAGONAL</code>
	 *         </p>
	 */
	public BorderSide getBorderStyleSide() {
		return borderStyleSide;
	}

	/**
	 * @return <b>fillColor</b> Color de relleno de la celda
	 * 
	 *         <p>
	 *         <b>Ejemplo:</b>
	 *         </p>
	 *         <p style="margin-left: 10px">
	 *         <code>1a1a1a</code>
	 *         </p>
	 */
	public String getFillColor() {
		return fillColor;
	}

	/**
	 * @return <b>fontColor</b> Color de la letra
	 * 
	 *         <p>
	 *         <b>Ejemplo:</b>
	 *         </p>
	 *         <p style="margin-left: 10px">
	 *         <code>1a1a1a</code>
	 *         </p>
	 */
	public String getFontColor() {
		return fontColor;
	}

	/**
	 * @return <b>fontName</b> Nombre del estilo de letra
	 * 
	 *         <p>
	 *         <b>Ejemplo:</b>
	 *         </p>
	 *         <p style="margin-left: 10px">
	 *         <code>Arial</code>
	 *         </p>
	 */
	public String getFontName() {
		return fontName;
	}

	/**
	 * @return <b>fontSize</b> Tamaño de la letra
	 * 
	 *         <p>
	 *         <b>Ejemplo:</b>
	 *         </p>
	 *         <p style="margin-left: 10px">
	 *         <code>12</code>
	 *         </p>
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * @return <b>horizontalAlignment</b> Alineacion del contenido de la celda
	 * 
	 *         <p>
	 *         <b>Ejemplo:</b>
	 *         </p>
	 *         <p style="margin-left: 10px">
	 *         <code>RIGHT/LEFT/CENTER</code>
	 *         </p>
	 */
	public String getHorizontalAlignment() {
		return horizontalAlignment;
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
		} else if (property.equals(Constants.S.ITALIC)) {
			if (value.equals(Constants.S.TRUE)) {
				isItalic = true;
			}
		} else if (property.equals(Constants.S.BORDER_COLOR)) {
			hasBorderColor = true;
			borderColor = value;
		} else if (property.equals(Constants.S.BORDER_COLOR_SIDE)) {
			hasBorderColorSide = true;
			if (value.equals(Constants.S.SIDE_TOP)) {
				borderColorSide = BorderSide.TOP;
			}
			if (value.equals(Constants.S.SIDE_BOTTOM)) {
				borderColorSide = BorderSide.BOTTOM;
			}
			if (value.equals(Constants.S.SIDE_LEFT)) {
				borderColorSide = BorderSide.LEFT;
			}
			if (value.equals(Constants.S.SIDE_RIGHT)) {
				borderColorSide = BorderSide.RIGHT;
			}
			if (value.equals(Constants.S.SIDE_DIAGONAL)) {
				borderColorSide = BorderSide.DIAGONAL;
			}
		} else if (property.equals(Constants.S.BORDER_STYLE)) {
			hasBorderStyle = true;
			borderStyle = value;
		} else if (property.equals(Constants.S.BORDER_STYLE_SIDE)) {
			hasBorderStyleSide = true;
			if (value.equals(Constants.S.SIDE_TOP)) {
				borderStyleSide = BorderSide.TOP;
			}
			if (value.equals(Constants.S.SIDE_BOTTOM)) {
				borderStyleSide = BorderSide.BOTTOM;
			}
			if (value.equals(Constants.S.SIDE_LEFT)) {
				borderStyleSide = BorderSide.LEFT;
			}
			if (value.equals(Constants.S.SIDE_RIGHT)) {
				borderStyleSide = BorderSide.RIGHT;
			}
			if (value.equals(Constants.S.SIDE_DIAGONAL)) {
				borderStyleSide = BorderSide.DIAGONAL;
			}
		} else if (property.equals(Constants.S.FILL_COLOR)) {
			hasFillColor = true;
			fillColor = value;
		} else if (property.equals(Constants.S.FONT_COLOR)) {
			hasFontColor = true;
			fontColor = value;
		} else if (property.equals(Constants.S.FONT_NAME)) {
			hasFontName = true;
			fontName = value;
		} else if (property.equals(Constants.S.FONT_SIZE)) {
			hasFontSize = true;
			fontSize = Integer.parseInt(value);
		} else if (property.equals(Constants.S.HORIZONTAL_ALIGNMENT)) {
			hasHorizontalAlignment = true;
			horizontalAlignment = value;
		}
	}

	// TODO: QUITAR ESTA FUNCION??
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
