package naevys.NAEVYS;

// Importaciones
// Utilidades para la interfaz
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
// Abrir archivos y sus excepciones
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Elementos de la interfaz
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase que construye la interfaz grafica del programa
 * 
 * @author Christopher Gabriel Pedraza Pohlenz
 */
public class GUI implements ActionListener {
	// Valor por defecto del patron de fecha para el registro de errores en caso de
	// que haya un error antes de poder leer el archivo de configuracion
	private static final String DEFAULT_LOG_DATE_PATTERN = "yyyy-MM-dd-HH:mm:ss";
	// Constantes necesarias para la interfaz grafica. Estas constantes no se
	// agregaron a la clase Constants debido a que no se espera que el usuario las
	// modifique
	private static final Color GREY_54 = new Color(54, 54, 54);
	private static final Color GREY_48 = new Color(48, 48, 48);
	private static final Color GREY_31 = new Color(31, 31, 31);
	private static final Color WHITE = Color.WHITE;
	// Variables globales necesarias para poder se modificadas desde multiples
	// funciones
	// Arreglos recibidos desde la clase Main que seran enviados a la clase
	// ExcelFiles cuando se genere el Excel exportado
	private static Header[] OUTPUTHEADERS;
	private static ExcelConstant[] EXCONSTANTS;
	private static Style[] STYLES;
	// Nombres del archivo de entrada y de salida
	private static String INPUT_FILE;
	private static String OUTPUT_FILE;
	// Elementos que necesitaran modificaciones posteriores a su creacion por otras
	// funciones
	private static JFrame frame;
	private static JButton btnConfirm;
	private static JButton btnConfig;
	private static JButton btnInput;
	private static JTextField tfOutput;

	/**
	 * <h1><i>init</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public init(Header[] outputHeaders, ExcelConstant[] exConstants, Style[] styles)</code>
	 * </p>
	 * <p>
	 * Funcion para inicializar la creacion de la interfaz grafica. Este metodo es
	 * llamado desde la clase Main y llama a su vez las funciones que crean los
	 * elementos individuales de la interfaz.
	 * </p>
	 * 
	 * @param outputHeaders Especificaciones de las columnas que se exportaran
	 * @param exConstants   Especificaciones de las constantes que se exportaran
	 * @param styles        Estilos disponibles para aplicar a las celdas
	 */
	public void init(Header[] outputHeaders, ExcelConstant[] exConstants, Style[] styles) {
		// Se guardan los arreglos recibidos en las variables globales para que puedan
		// mandarse sus valores desde otra funcion
		OUTPUTHEADERS = outputHeaders;
		EXCONSTANTS = exConstants;
		STYLES = styles;

		// Se crea la ventana que contendra todos los elementos
		createFrame();

		// A la ventana se le agrega un panel que a su vez contiene 2 subpaneles (uno
		// superior y otro inferior)
		JPanel mainPanel = createMainPanel();
		JPanel topPanel = createSubPanel();
		// Se agregan los elementos al subpanel superior
		topPanel = addTopElements(topPanel);
		JPanel botPanel = createSubPanel();
		// Se agregan los elementos al subpanel inferior
		botPanel = addBotElements(botPanel);

		// Al panel principal se agregan los dos subpaneles
		mainPanel.add(topPanel);
		mainPanel.add(botPanel);
		// A la ventana se agrega el panel principal
		frame.add(mainPanel);

		// Se hace visible la ventana una vez que ya se agregaron todos los elementos
		frame.setVisible(true);
	}

	/**
	 * <h1><i>addTopElements</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private addTopElements(JPanel panel)</code>
	 * </p>
	 * <p>
	 * Funcion para agregar los elementos que van en el subpanel superior.
	 * </p>
	 * 
	 * @param panel Panel al que se le agregaran los elementos
	 * @return <b>panel</b> Panel superior con los elementos agregados
	 */
	private JPanel addTopElements(JPanel panel) {
		// Se crea el boton con icono de configuracion
		btnConfig = createIconButton(getIcon("settings_white.png"));
		// Centra el boton y lo agrega al subpanel recibido
		panel.add(center(btnConfig));
		// Crea un boton con texto
		btnInput = createTextButton("Import File");
		// Centra el boton y lo agrega al subpanel recibido
		panel.add(center(btnInput));
		// Regresa el subpanel
		return panel;
	}

	/**
	 * <h1><i>addBotElements</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private addBotElements(JPanel panel)</code>
	 * </p>
	 * <p>
	 * Funcion para agregar los elementos que van en el subpanel inferior.
	 * </p>
	 * 
	 * @param panel Panel al que se le agregaran los elementos
	 * @return <b>panel</b> Panel inferior con los elementos agregados
	 */
	private JPanel addBotElements(JPanel panel) {
		// Crea un campo de texto, lo centra y lo agrega al subpanel
		panel.add(center(createTextField()));
		// Se crea el boton con icono de confirmacion
		btnConfirm = createIconButton(getIcon("confirm_white.png"));
		// Bloquea el boton de confirmacion para que solo se habilite cuando se haya
		// importado el archivo fuente
		btnConfirm.setEnabled(false);
		// Centra el boton y lo agrega al subpanel recibido
		panel.add(center(btnConfirm));
		// Regresa el subpanel
		return panel;
	}

	/**
	 * <h1><i>createTextField</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private createTextField()</code>
	 * </p>
	 * <p>
	 * Funcion para crear y configurar un campo de texto.
	 * </p>
	 * 
	 * @return <b>tfOutput</b> Panel inferior con los elementos agregados
	 */
	private JTextField createTextField() {
		// Se crea una instancia de la clase del campo de texto
		tfOutput = new JTextField();
		// Se cambia el color del fondo a gris (color definido en las constantes de
		// arriba)
		tfOutput.setBackground(GREY_48);
		// Se cambia el color de las letras a blanco (color definido en las constantes
		// de arriba)
		tfOutput.setForeground(WHITE);
		// Se centra el texto
		tfOutput.setHorizontalAlignment(JTextField.CENTER);
		// Se define la fuente que se usara para el campo de texto
		tfOutput.setFont(new Font("Verdana", Font.ITALIC, 24));
		// Se deshabilita la funcion para editar para que solo se habilite cuando se
		// haya importado el archivo fuente
		tfOutput.setEditable(false);
		// Se remueve el borde por estetica
		tfOutput.setBorder(BorderFactory.createEmptyBorder());
		// Se define un texto como placeholder. Este texto se modificara por el nombre
		// del archivo fuente importado + un texto diferenciador por si el usuario
		// quiere exportar un excel con un nombre similar
		tfOutput.setText(Constants.G.DEFAULT_FILE_NAME + ".xlsx");
		// Cambiar el color del cursor a blanco
		tfOutput.setCaretColor(WHITE);
		// Agregar un mensaje cuando se coloque el cursor sobre el area
		tfOutput.setToolTipText("Write the output filename.");
		// Se regresa el campo de texto
		return tfOutput;
	}

	/**
	 * <h1><i>createTextButton</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private createTextButton(String text)</code>
	 * </p>
	 * <p>
	 * Funcion para crear y configurar un boton con texto.
	 * </p>
	 * 
	 * @param text Texto que se asignara al boton
	 * @return <b>button</b> Boton creado y configurado
	 */
	private JButton createTextButton(String text) {
		// Crea una instancia de un boton y le pasa como parametro el texto que se
		// asignara
		JButton button = new JButton(text);
		// Elimina el color de fondo
		button.setBackground(null);
		// Asigna blanco como el color de las letras
		button.setForeground(WHITE);
		// Asigna una fuente especifica al texto del boton
		button.setFont(new Font("Verdana", Font.BOLD, 50));
		// Elimina el borde
		button.setBorder(BorderFactory.createEmptyBorder());
		// Elimina el borde que se pone para mostrar que se puede dar clic al boton
		button.setFocusable(false);
		// Se agrega un listener para detectar cuando se da clic en el boton
		button.addActionListener(this);
		// Agrega un MouseListener para detectar los eventos del raton que interactuen
		// con el boton. En especifico, sirve para modificar el color del fondo cuando
		// el cursor entra y sale del boton
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				button.setBackground(GREY_48);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setBackground(GREY_54);

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		// Regresa el boton configurado
		return button;
	}

	/**
	 * <h1><i>createIconButton</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private createIconButton(ImageIcon icon)</code>
	 * </p>
	 * <p>
	 * Funcion para crear y configurar un boton con icono.
	 * </p>
	 * 
	 * @param icon Icono que se asignara al boton
	 * @return <b>button</b> Boton creado y configurado
	 */
	private JButton createIconButton(ImageIcon icon) {
		// Crea una instancia de un boton y le pasa como parametro el icono que se
		// asignara
		JButton button = new JButton(icon);
		// Asigna un color de fondo gris
		button.setBackground(GREY_48);
		// Elimina el borde del boton
		button.setBorder(BorderFactory.createEmptyBorder());
		// Elimina el borde que se pone para mostrar que se puede dar clic al boton
		button.setFocusable(false);
		// Se agrega un listener para detectar cuando se da clic en el boton
		button.addActionListener(this);
		// Agrega un MouseListener para detectar los eventos del raton que interactuen
		// con el boton. En especifico, sirve para modificar el color del fondo cuando
		// el cursor entra y sale del boton
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				button.setBackground(GREY_48);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setBackground(GREY_54);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		// Regresa el boton configurado
		return button;
	}

	/**
	 * <h1><i>getIcon</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private getIcon(String fileName)</code>
	 * </p>
	 * <p>
	 * Funcion para obtener un icono de los recursos del programa.
	 * </p>
	 * 
	 * @param fileName Nombre del icono guardado en los recursos del programa
	 * @return Imagen obtenida de los recursos
	 */
	private ImageIcon getIcon(String fileName) {
		// Obtiene el URL del recurso, crea una instancia de imagen y regresa el objeto
		return new ImageIcon(Main.class.getResource("/" + fileName));
	}

	/**
	 * <h1><i>center</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private center(Component component)</code>
	 * </p>
	 * <p>
	 * Funcion para colocar un componente de JSwing en un panel con un layout de
	 * GridLayout con 1 fila y 1 columna. Esto permite que el elemento se centre y
	 * el layout maneje el tamaño del elemento cuando se modifique las dimensiones
	 * de la ventana.
	 * </p>
	 * <p>
	 * Component es la clase padre de los componentes de JSwing, por lo que con esta
	 * funcion se pueden recibir cualquier tipo de componente sin necesitar hacer
	 * una sobrecarga de funciones.
	 * </p>
	 * 
	 * @param component Objeto que se centrara
	 * @return <b>panel</b> Panel con el objeto centrado
	 */
	private JPanel center(Component component) {
		// Se crea una instancia de un panel
		JPanel panel = new JPanel();
		// Elimina el color de fondo
		panel.setBackground(null);
		// Le aplica un layout que permite centrar los elementos y manejar su tamaño
		// cuando se redimensiona la ventana
		panel.setLayout(new GridLayout(1, 1));
		// Agrega el componente recibido al panel
		panel.add(component);
		// Regresa el panel
		return panel;
	}

	/**
	 * <h1><i>createMainPanel</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private createMainPanel()</code>
	 * </p>
	 * <p>
	 * Funcion para crear y configurar el panel principal de la aplicacion donde se
	 * colocaran los subpaneles superior e inferior.
	 * </p>
	 * 
	 * @return <b>panel</b> Panel principal configurado
	 */
	private JPanel createMainPanel() {
		// Crea una instancia de un panel
		JPanel panel = new JPanel();
		// Le aplica un color de fondo gris
		panel.setBackground(GREY_31);
		// Aplica un layot de 2 filas y 1 columna con una separacion horizontal y
		// vertical de 'm' unidades
		int m = 15;
		panel.setLayout(new GridLayout(2, 1, m, m));
		// Coloca un borde vacio de 'm' unidades para todos los lados
		panel.setBorder(BorderFactory.createEmptyBorder(m, m, m, m));
		// Regresa el panel configurado
		return panel;
	}

	/**
	 * <h1><i>createSubPanel</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private createSubPanel()</code>
	 * </p>
	 * <p>
	 * Funcion para crear y configurar el subpanel superior e inferior.
	 * </p>
	 * 
	 * @return <b>panel</b> Subpanel configurado
	 */
	private JPanel createSubPanel() {
		// Crea una instancia de un panel
		JPanel panel = new JPanel();
		// Aplica un layuout que permite poner los elementos de izquierda a derecha
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		// Asigna un color de fondo gris
		panel.setBackground(GREY_48);
		// Regresa el panel configurado
		return panel;
	}

	/**
	 * <h1><i>createFrame</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> private createFrame()</code>
	 * </p>
	 * <p>
	 * Funcion para crear y configurar la ventana que contendra todos los elementos
	 * de la aplicacion.
	 * </p>
	 */
	private void createFrame() {
		frame = new JFrame();
		// Asigna el titulo de la ventana
		frame.setTitle("NAEVYS");
		// Configura que cuando se cierre la aplicacion se liberen los recursos
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// TODO: Obtener las dimensiones de la pantalla donde se corre la aplicacion y
		// usando un factor de reduccion asignar el tamaño de la ventana
		// Asigna un tamaño a la ventana
		frame.setSize(800, 600);
		// Coloca la ventana en el centro de la pantalla
		frame.setLocationRelativeTo(null);
	}

	/**
	 * <h1><i>actionPerformed</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public actionPerformed(ActionEvent event)</code>
	 * </p>
	 * <p>
	 * Funcion que maneja las acciones de los botones en esta clase. Recibe un
	 * evento que tiene un identificador de la fuente para determinar el boton que
	 * fue accionado.
	 * </p>
	 * 
	 * @param event Evento que sucedio con un identificador de la fuente
	 */
	public void actionPerformed(ActionEvent event) {
		// Crea un boton usando el identificador de la fuente del evento
		JButton source = (JButton) event.getSource();

		// Se compara la fuente con los botones que estan guardados como variables
		// globales. Si coinciden, se realiza cierta funcion
		// El boton de configuracion abre el directorio donde se encuentra el programa
		if (source == btnConfig) {
			// Intenta abrir el directorio del programa
			try {
				// Obtiene la direccion absoluta del programa
				File file = new File(Paths.get("").toAbsolutePath().toString());
				// Abre la direccion
				Desktop.getDesktop().open(file);
			} catch (IllegalArgumentException | IOException e) {
				// Si encuentra un error, mostrar mensaje descriptivo
				showErrorMessage(e);
			}
		}
		// El boton de confirmar llama a la funcion para exportar el Excel
		if (source == btnConfirm) {
			// Crea una instancia de la clase que maneja los archivos de Excel
			ExcelFiles ef = new ExcelFiles();
			// Obtiene el nombre del archivo de salida del campo de texto
			OUTPUT_FILE = tfOutput.getText();
			// Si el campo esta vacio o carece de la extension del archivo, se le agrega o
			// reemplaza por un nombre por defecto
			if (OUTPUT_FILE.isEmpty()) {
				OUTPUT_FILE = Constants.G.DEFAULT_FILE_NAME + ".xlsx";
			} else if (!OUTPUT_FILE.contains(".xlsx")) {
				OUTPUT_FILE += ".xlsx";
			}
			// LLama a la funcion para exportar el Excel pasando como parametros los
			// arreglos al igual que el nombre del archivo fuente y del que se exportara
			ef.convertExcel(INPUT_FILE, OUTPUT_FILE, OUTPUTHEADERS, EXCONSTANTS, STYLES);
		}
		// El boton de input abre un selector de archivos para que el usuario seleccione
		// el archivo fuente
		if (source == btnInput) {
			// Crea una instancia de un selector de archivos que se abre en la direccion del
			// programa
			final JFileChooser fc = new JFileChooser(Paths.get("").toAbsolutePath().toString());
			fc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx");
			fc.addChoosableFileFilter(filter);
			// Abre el selector de archivos obteniendo como entero si se da clic en
			// confirmar o no
			int returnVal = fc.showOpenDialog(frame);

			// Si se da clic en confirmar
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// Obtiene la direccion del archivo que se selecciono
				File file = fc.getSelectedFile();
				// De la direccion separa el nombre del archivo
				INPUT_FILE = file.toString();

				// Modificaciones autogeneradas (pero pueden ser luego editadas antes de la
				// exportacion) al nombre del archivo de salida
				// Si el usuario lo desea, se utiliza el nombre del archivo de entrada como base
				if (Constants.G.USE_INPUT_FILE_NAME) {
					// Se toma el nombre del archivo leido
					OUTPUT_FILE = file.getName();
				} else {
					// De lo contrario, se utiliza el valor por defecto
					OUTPUT_FILE = Constants.G.DEFAULT_FILE_NAME + ".xlsx";
				}
				// Si el usuario lo especifica, le agrega la fecha al nombre del archivo
				if (Constants.G.INCLUDE_DATE_IN_FILE_NAME) {
					// Agrega despues del nombre del archivo la fecha usando el formato especificado
					// por el usuario
					OUTPUT_FILE = OUTPUT_FILE.replace(".xlsx", "_" + getDate(Constants.G.FILE_DATE_PATTERN) + ".xlsx");
				}
				// Le agrega un texto para diferenciar al nombre del archivo de salida
				OUTPUT_FILE = OUTPUT_FILE.replace(".xlsx", Constants.G.FILE_NAME_SUFFIX + ".xlsx");

				// Habilita el boton para exportar un excel y el campo de texto con el nombre
				// del archivo de salida
				btnConfirm.setEnabled(true);
				tfOutput.setEditable(true);
				// Asigna el texto del archivo de entrada modificado al campo de texto
				tfOutput.setText(OUTPUT_FILE);
			}
		}
	}

	/**
	 * <h1><i>showErrorMessage</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public showErrorMessage(Exception ex)</code>
	 * </p>
	 * <p>
	 * Funcion para mostrar un mensaje grafico al usuario cuando sucede un error al
	 * igual que guardarlo junto con la fecha en el que ocurrio en un archivo de
	 * registro.
	 * </p>
	 * 
	 * @param ex Excepcion o error encontrado por el programa
	 */
	public static void showErrorMessage(Exception ex) {
		// Muestra un mensaje en la interfaz desplegando el mensaje del error
		JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		// Agrega una linea al registro de errores con el error encontrado
		try {
			TextFiles.logErrorMessage("[" + getDate(Constants.G.LOG_DATE_PATTERN) + "] " + ex.toString());
		} catch (IOException writerException) {
			// Si llega a suceder un error al intentar manejar el error, cerrar el programa
			frame.dispose();
		}
	}

	/**
	 * <h1><i>showErrorMessage</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public showErrorMessage()</code>
	 * </p>
	 * <p>
	 * Funcion para mostrar un mensaje grafico al usuario cuando sucede un error al
	 * igual que guardarlo junto con la fecha en el que ocurrio en un archivo de
	 * registro.
	 * </p>
	 */
	public static void showErrorMessage() {
		// Muestra un mensaje en la interfaz desplegando el mensaje del error
		JOptionPane.showMessageDialog(frame, "Unexpected error encountered", "Error", JOptionPane.ERROR_MESSAGE);

		// Agrega una linea al registro de errores con el error encontrado
		try {
			TextFiles.logErrorMessage("[" + getDate(Constants.G.LOG_DATE_PATTERN) + "] Unexpected error encountered.");
		} catch (IOException writerException) {
			// Si llega a suceder un error al intentar manejar el error, cerrar el programa
			frame.dispose();
		}
	}

	/**
	 * <h1><i>getDate</i></h1>
	 * <p style="margin-left: 10px">
	 * <code> public getDate(String pattern)</code>
	 * </p>
	 * <p>
	 * Funcion que regresa un String con una fecha usando el patron recibido.
	 * </p>
	 * 
	 * @param pattern Patron usado para darle formato a la fecha
	 * @return <b>date</b> Fecha usando el patron recibido
	 */
	private static String getDate(String pattern) {
		// Proteccion en contra de que suceda un error antes de poder leer el archivo de
		// configuracion. Asigna un valor por defecto.
		if (pattern == null) {
			pattern = DEFAULT_LOG_DATE_PATTERN;
		} else if (pattern.isEmpty()) {
			pattern = DEFAULT_LOG_DATE_PATTERN;
		}
		// Obtiene la fecha actual y le da un formato especifico
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		// Regresa la fecha
		return date;
	}

}
