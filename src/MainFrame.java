import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.awt.Toolkit;

public class MainFrame {

	private JFrame frmPasswortmanager;
	private JTable table;
	private Database db = new Database();
	private DefaultTableModel model;
	private String[][] input;
	private static Path path_enc = Paths.get("./data.enc");
	private static Path path_dnc = Paths.get("./accounting.db");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		if (Files.exists(path_enc)) {
			String key_in = JOptionPane.showInputDialog("Bitte das Passwort eingeben:");
			SecretKey key = convertStringToSecretKeyto(key_in);
			File_dnc(key);
			try {
				Files.delete(path_enc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmPasswortmanager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswortmanager = new JFrame();
		frmPasswortmanager.setIconImage(Toolkit.getDefaultToolkit().getImage("./resources/show.png"));
		frmPasswortmanager.addWindowListener(new WindowEventHandler());
		frmPasswortmanager.setTitle("Passwortmanager");
		frmPasswortmanager.setBounds(100, 100, 692, 440);
		frmPasswortmanager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmPasswortmanager.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		model = new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Name", "EMail", "Telefon", "Username", "Passwort" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		// main
		JPanel main = new JPanel();
		tabbedPane.addTab("Main", null, main, null);
		GridBagLayout gbl_main = new GridBagLayout();
		gbl_main.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_main.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_main.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_main.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		main.setLayout(gbl_main);

		JLabel lblPlatzhalter3 = new JLabel("");
		GridBagConstraints gbc_lblPlatzhalter3 = new GridBagConstraints();
		gbc_lblPlatzhalter3.gridheight = 12;
		gbc_lblPlatzhalter3.gridwidth = 2;
		gbc_lblPlatzhalter3.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlatzhalter3.gridx = 0;
		gbc_lblPlatzhalter3.gridy = 0;
		main.add(lblPlatzhalter3, gbc_lblPlatzhalter3);

		JLabel lblTitel = new JLabel("Passwortmanager");
		lblTitel.setForeground(new Color(0, 153, 0));
		lblTitel.setFont(new Font("Sitka Display", Font.BOLD, 46));
		GridBagConstraints gbc_lblTitel = new GridBagConstraints();
		gbc_lblTitel.gridwidth = 4;
		gbc_lblTitel.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitel.gridx = 2;
		gbc_lblTitel.gridy = 0;
		main.add(lblTitel, gbc_lblTitel);

		JLabel lblUntertitel = new JLabel("Neues Passwort hinzufügen");
		lblUntertitel.setFont(new Font("Book Antiqua", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_lblUntertitel = new GridBagConstraints();
		gbc_lblUntertitel.anchor = GridBagConstraints.WEST;
		gbc_lblUntertitel.gridwidth = 2;
		gbc_lblUntertitel.insets = new Insets(0, 0, 5, 5);
		gbc_lblUntertitel.gridx = 2;
		gbc_lblUntertitel.gridy = 1;
		main.add(lblUntertitel, gbc_lblUntertitel);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 2;
		main.add(lblName, gbc_lblName);

		JTextField textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.gridx = 3;
		gbc_textName.gridy = 2;
		main.add(textName, gbc_textName);
		textName.setColumns(10);
		textName.setDocument(new LimitJTextField(30));

		JLabel lblPlatzhalter = new JLabel("");
		GridBagConstraints gbc_lblPlatzhalter = new GridBagConstraints();
		gbc_lblPlatzhalter.gridheight = 8;
		gbc_lblPlatzhalter.gridwidth = 2;
		gbc_lblPlatzhalter.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlatzhalter.gridx = 4;
		gbc_lblPlatzhalter.gridy = 2;
		main.add(lblPlatzhalter, gbc_lblPlatzhalter);

		JLabel lblEMail = new JLabel("EMail");
		lblEMail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEMail = new GridBagConstraints();
		gbc_lblEMail.anchor = GridBagConstraints.EAST;
		gbc_lblEMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEMail.gridx = 2;
		gbc_lblEMail.gridy = 3;
		main.add(lblEMail, gbc_lblEMail);

		JTextField textEMail = new JTextField();
		textEMail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_textEMail = new GridBagConstraints();
		gbc_textEMail.insets = new Insets(0, 0, 5, 5);
		gbc_textEMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEMail.gridx = 3;
		gbc_textEMail.gridy = 3;
		main.add(textEMail, gbc_textEMail);
		textEMail.setColumns(10);
		textEMail.setDocument(new LimitJTextField(50));

		JLabel lblTelNummer = new JLabel("Telefon-Nummer");
		lblTelNummer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTelNummer = new GridBagConstraints();
		gbc_lblTelNummer.anchor = GridBagConstraints.EAST;
		gbc_lblTelNummer.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelNummer.gridx = 2;
		gbc_lblTelNummer.gridy = 4;
		main.add(lblTelNummer, gbc_lblTelNummer);

		JTextField textTelNum = new JTextField();
		textTelNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_textTelNum = new GridBagConstraints();
		gbc_textTelNum.insets = new Insets(0, 0, 5, 5);
		gbc_textTelNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelNum.gridx = 3;
		gbc_textTelNum.gridy = 4;
		main.add(textTelNum, gbc_textTelNum);
		textTelNum.setColumns(10);
		textTelNum.setDocument(new LimitJTextField(30));

		JLabel lblBenutzername = new JLabel("Benutzername");
		lblBenutzername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblBenutzername = new GridBagConstraints();
		gbc_lblBenutzername.anchor = GridBagConstraints.EAST;
		gbc_lblBenutzername.insets = new Insets(0, 0, 5, 5);
		gbc_lblBenutzername.gridx = 2;
		gbc_lblBenutzername.gridy = 5;
		main.add(lblBenutzername, gbc_lblBenutzername);

		JTextField textBenutzername = new JTextField();
		textBenutzername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_textBenutzername = new GridBagConstraints();
		gbc_textBenutzername.insets = new Insets(0, 0, 5, 5);
		gbc_textBenutzername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textBenutzername.gridx = 3;
		gbc_textBenutzername.gridy = 5;
		main.add(textBenutzername, gbc_textBenutzername);
		textBenutzername.setColumns(10);
		textBenutzername.setDocument(new LimitJTextField(50));

		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPasswort = new GridBagConstraints();
		gbc_lblPasswort.anchor = GridBagConstraints.EAST;
		gbc_lblPasswort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswort.gridx = 2;
		gbc_lblPasswort.gridy = 6;
		main.add(lblPasswort, gbc_lblPasswort);

		JTextField textPasswort = new JTextField();
		textPasswort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_textPasswort = new GridBagConstraints();
		gbc_textPasswort.insets = new Insets(0, 0, 5, 5);
		gbc_textPasswort.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPasswort.gridx = 3;
		gbc_textPasswort.gridy = 6;
		main.add(textPasswort, gbc_textPasswort);
		textPasswort.setColumns(10);
		textPasswort.setDocument(new LimitJTextField(30));

		JLabel lblNotiz = new JLabel("Notiz");
		lblNotiz.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNotiz = new GridBagConstraints();
		gbc_lblNotiz.anchor = GridBagConstraints.EAST;
		gbc_lblNotiz.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotiz.gridx = 2;
		gbc_lblNotiz.gridy = 7;
		main.add(lblNotiz, gbc_lblNotiz);

		JTextArea textNotiz = new JTextArea();
		textNotiz.setWrapStyleWord(true);
		textNotiz.setLineWrap(true);
		textNotiz.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_textNotiz = new GridBagConstraints();
		gbc_textNotiz.gridheight = 2;
		gbc_textNotiz.insets = new Insets(0, 0, 5, 5);
		gbc_textNotiz.fill = GridBagConstraints.BOTH;
		gbc_textNotiz.gridx = 3;
		gbc_textNotiz.gridy = 7;
		main.add(textNotiz, gbc_textNotiz);

		JButton btnNewPasswd = new JButton("Passwort hinzufügen");
		btnNewPasswd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				String email = textEMail.getText();
				String telnum = textTelNum.getText();
				String user = textBenutzername.getText();
				String passwd = textPasswort.getText();
				String notiz = textNotiz.getText();
				if (name != "" && name != " " && name != null) {
					int id = db.get_id();
					if (id >= 0) {
						System.out.println("id >=0 | = " + id);
					} else {
						System.out.println("id < 0 | = " + id);
						id = 0;
					}
					db.add_passwd((id + 1), name, email, telnum, user, passwd, notiz);
				} else {
					JOptionPane.showMessageDialog(btnNewPasswd, "Bitte füllen Sie mindestens den Namen aus");
				}
				textName.setText("");
				textEMail.setText("");
				textTelNum.setText("");
				textBenutzername.setText("");
				textPasswort.setText("");
				textNotiz.setText("");
			}
		});
		btnNewPasswd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnNewPasswd = new GridBagConstraints();
		gbc_btnNewPasswd.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewPasswd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewPasswd.gridx = 3;
		gbc_btnNewPasswd.gridy = 9;
		main.add(btnNewPasswd, gbc_btnNewPasswd);

		JLabel lblPlatzhalter2 = new JLabel("");
		GridBagConstraints gbc_lblPlatzhalter2 = new GridBagConstraints();
		gbc_lblPlatzhalter2.gridheight = 2;
		gbc_lblPlatzhalter2.gridwidth = 4;
		gbc_lblPlatzhalter2.gridx = 2;
		gbc_lblPlatzhalter2.gridy = 10;
		main.add(lblPlatzhalter2, gbc_lblPlatzhalter2);

		// list
		JPanel list = new JPanel();
		tabbedPane.addTab("List", null, list, null);
		GridBagLayout gbl_list = new GridBagLayout();
		gbl_list.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gbl_list.rowHeights = new int[] { 27, 0, 0 };
		gbl_list.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_list.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		list.setLayout(gbl_list);

		JTextField tSearchField = new JTextField();
		tSearchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tSearchField.setText("");
			}
		});
		GridBagConstraints gbc_tSearchField = new GridBagConstraints();
		gbc_tSearchField.gridwidth = 4;
		gbc_tSearchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tSearchField.insets = new Insets(0, 0, 5, 5);
		gbc_tSearchField.gridx = 17;
		gbc_tSearchField.gridy = 0;
		list.add(tSearchField, gbc_tSearchField);
		tSearchField.setColumns(10);

		JButton btnAktualisierung = new JButton("Aktualisieren");
		btnAktualisierung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tSearchField.setText("");
				input = db.select_all();
				model = add_row(model, input);
			}
		});
		GridBagConstraints gbc_btnAktualisierung = new GridBagConstraints();
		gbc_btnAktualisierung.anchor = GridBagConstraints.WEST;
		gbc_btnAktualisierung.gridwidth = 3;
		gbc_btnAktualisierung.insets = new Insets(0, 0, 5, 5);
		gbc_btnAktualisierung.gridx = 0;
		gbc_btnAktualisierung.gridy = 0;
		list.add(btnAktualisierung, gbc_btnAktualisierung);

		JTable table = new JTable(model);
		table.setShowVerticalLines(false);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getSelectionModel()
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setVisible(true);
		table.setFocusable(false);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) { // to detect double click events
					JTable target = (JTable) me.getSource();
					int row = target.getSelectedRow(); // select a row
					int column = target.getSelectedColumn(); // select a column
					//JOptionPane.showMessageDialog(null, table.getValueAt(row, column));
					// String.valueOf(row));
					String[] in = input[row];
					deletePasswd passwd_delete = new deletePasswd(in, db);
					passwd_delete.setModal(true);
					passwd_delete.setVisible(true);
					passwd_delete.setVisible(false);
					passwd_delete.setVisible(true);
					//System.out.println(input[row][0] + "|" + input[row][1] + "|" + input[row][2] + "|");
					tSearchField.setText("");
					input = db.select_all();
					model = add_row(model, input);
				}
			}
		});

		tabbedPane.addChangeListener(new ChangeListener() { // hier unten, weil sonst TSearchFiled = unknown
			public void stateChanged(ChangeEvent e) {
				JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
				int selectedIndex = tabbedPane.getSelectedIndex();
				// JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
				if (selectedIndex == 1) {
					tSearchField.setText("");
					input = db.select_all();
					model = add_row(model, input);
				}
			}
		});

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Name", "EMail", "Username", "Telefon", "Passwort" }));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 22;
		gbc_comboBox.gridy = 0;
		list.add(comboBox, gbc_comboBox);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 26;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		list.add(new JScrollPane(table), gbc_table);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = comboBox.getSelectedItem().toString();
				String search = tSearchField.getText();
				switch (selected) {
				case "Name":
					selected = "PassTitel";
					break;
				case "EMail":
					selected = "PassEMail";
					break;
				case "Username":
					selected = "PassNutzername";
					break;
				case "Telefon":
					selected = "PassTel";
					break;
				case "Passwort":
					selected = "PassPasswd";
					break;
				default:
					selected = "PassTitel";
					break;
				}
				input = db.select_search(search, selected);
				model = add_row(model, input);
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 21;
		gbc_btnSearch.gridy = 0;
		list.add(btnSearch, gbc_btnSearch);
	}

	private DefaultTableModel add_row(DefaultTableModel model, String[][] input) {
		model.setRowCount(0);
		for (int i = 0; i < input.length; i++) {
			model.addRow(new Object[] { input[i][1], input[i][2], input[i][3], input[i][4], input[i][5] });
		}
		return model;
	}

	private static void File_dnc(SecretKey key) {

		try {
			Cipher chipher;
			chipher = Cipher.getInstance("AES");
			byte[] data = Files.readAllBytes(path_enc);
			byte[] db_data = FileEncrypterDecrypter.decryption(data, key, chipher);
			
			Files.write(path_dnc, db_data);
			Files.delete(path_enc);
		} catch (Exception e) {

		}
	}

	private static String File_enc() {
		try {
			SecretKey key = FileEncrypterDecrypter.gernerateKey("AES");
			
			Cipher chipher;
			chipher = Cipher.getInstance("AES");
			byte[] data = Files.readAllBytes(path_dnc);
			byte[] db_data = FileEncrypterDecrypter.encryption(data, key, chipher);
			Files.write(path_enc, db_data);
			Files.delete(path_dnc);
			String key_s = convertSecretKeyToString(key);
			return key_s;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Es gab einen Fehler bei der Verschlüsselung, die Datenbank wurd nicht verschlüsselt.");
			return null;
		}
	}

	public static String convertSecretKeyToString(SecretKey secretKey) throws NoSuchAlgorithmException {
		byte[] rawData = secretKey.getEncoded();
		String encodedKey = Base64.getEncoder().encodeToString(rawData);
		return encodedKey;
	}

	public static SecretKey convertStringToSecretKeyto(String encodedKey) {
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		return originalKey;
	}

	class LimitJTextField extends PlainDocument {
		private int max;

		LimitJTextField(int max) {
			super();
			this.max = max;
		}

		public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
			if (text == null)
				return;
			if ((getLength() + text.length()) <= max) {
				super.insertString(offset, text, attr);
			}
		}
	}

	class WindowEventHandler extends WindowAdapter {
		public void windowClosing(WindowEvent evt) {
			int enc = JOptionPane.showConfirmDialog(frmPasswortmanager, "Wollen Sie die Datenbank verschlüsseln?",
					"Verschlüsselung", JOptionPane.YES_NO_OPTION);
			if (Files.exists(path_dnc) && enc == 0) {
				String key = File_enc();
				if (key != null) {
					//JOptionPane.showMessageDialog(frmPasswortmanager, "Dies ist Ihr Passwort bitte speichern Sie sich dieses, da es nicht zurücksetzbar ist:\n" + key);
					JOptionPane.showInputDialog(frmPasswortmanager, "Hier ist das Passwort", key);
				}
				else {
					try {
						Files.delete(path_enc);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
