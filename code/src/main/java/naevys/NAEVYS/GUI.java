package naevys.NAEVYS;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {

	private static Color GREY_48 = new Color(48, 48, 48);
	private static Color GREY_31 = new Color(31, 31, 31);
	private static Color WHITE = Color.WHITE;
	private static String INPUT = "Import File";
	private static Header[] OUTPUTHEADERS;
	private static ExcelConstant[] EXCONSTANTS;
	private static Style[] STYLES;
	private static String INPUT_FILE;
	private static String OUTPUT_FILE;
	private static JButton btnConfirm;
	private static JButton btnConfig;
	private static JButton btnInput;
	private static JTextField tfOutput;

	public void init(Header[] outputHeaders, ExcelConstant[] exConstants, Style[] styles) {
		OUTPUTHEADERS = outputHeaders;
		EXCONSTANTS = exConstants;
		STYLES = styles;

		createFrame();
		JPanel mainPanel = createMainPanel();
		JPanel topPanel = createSubPanel();
		topPanel = addTopElements(topPanel);
		JPanel botPanel = createSubPanel();
		botPanel = addBotElements(botPanel);
		mainPanel.add(topPanel);
		mainPanel.add(botPanel);
		add(mainPanel);
		setVisible(true);
	}

	private JPanel addTopElements(JPanel panel) {
		btnConfig = createIconButton(getIcon("settings_white.png"));
		panel.add(center(btnConfig));
		btnInput = createTextButton(INPUT);
		panel.add(center(btnInput));
		return panel;
	}

	private JPanel addBotElements(JPanel panel) {
		panel.add(center(createTextField()));
		btnConfirm = createIconButton(getIcon("confirm_white.png"));
		btnConfirm.setEnabled(false);
		btnConfirm.setText(null);
		panel.add(center(btnConfirm));
		return panel;
	}
	
	private JTextField createTextField() {
		tfOutput = new JTextField();
		tfOutput.setBackground(GREY_48);
		tfOutput.setForeground(WHITE);
		tfOutput.setHorizontalAlignment(JTextField.CENTER);
		tfOutput.setFont(new Font("Verdana", Font.ITALIC, 24));
		tfOutput.setEditable(false);
		tfOutput.setBorder(BorderFactory.createEmptyBorder());
		tfOutput.setText("output.xlsx");
		return tfOutput;
	}

	private JPanel center(Component component) {
		JPanel panel = new JPanel();
		panel.setBackground(null);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(component);
		return panel;
	}

	private JButton createTextButton(String text) {
		JButton button = new JButton(text);
		button.setBackground(null);
		button.setForeground(WHITE);
		button.setFont(new Font("Verdana", Font.BOLD, 50));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(true);
		button.setFocusable(false);
		button.addActionListener(this);
		return button;
	}

	private ImageIcon getIcon(String fileName) {
		return new ImageIcon(Main.class.getResource("/" + fileName));
	}

	private JButton createIconButton(ImageIcon icon) {
		JButton button = new JButton();
		button.setBackground(GREY_48);
		button.setIcon(icon);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(true);
		button.setFocusable(false);
		button.addActionListener(this);
		return button;
	}

	private JPanel createMainPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(GREY_31);
		int m = 15;
		panel.setLayout(new GridLayout(2, 1, m, m));
		panel.setBorder(BorderFactory.createEmptyBorder(m, m, m, m));
		return panel;
	}

	private JPanel createSubPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setBackground(GREY_48);
		return panel;
	}

	private void createFrame() {
		setName("NAEVYS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent event) {
		JButton source = (JButton) event.getSource();
		if (source == btnConfig) {
			try {
				File file = new File(Paths.get("").toAbsolutePath().toString());
				Desktop.getDesktop().open(file);
			} catch (IllegalArgumentException | IOException e) {
			}
		}
		if (source == btnConfirm) {
			ExcelFiles ef = new ExcelFiles();
			OUTPUT_FILE = tfOutput.getText();
			ef.convertExcel(INPUT_FILE, OUTPUT_FILE, OUTPUTHEADERS, EXCONSTANTS, STYLES);
		}
		if (source == btnInput) {
			final JFileChooser fc = new JFileChooser(Paths.get("").toAbsolutePath().toString());
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				INPUT_FILE = file.toString();
				OUTPUT_FILE = file.getName().replace(".xlsx", "_ex.xlsx");
				btnConfirm.setEnabled(true);
				tfOutput.setEditable(true);
				tfOutput.setText(OUTPUT_FILE);
			} else {
			}
		}

	}

}
