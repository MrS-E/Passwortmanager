import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class deletePasswd extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			deletePasswd dialog = new deletePasswd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public deletePasswd(String[] in, Database db) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./resources/show.png"));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTitel = new JLabel("Passwort:");
			lblTitel.setFont(new Font("Book Antiqua", Font.BOLD | Font.ITALIC, 17));
			GridBagConstraints gbc_lblTitel = new GridBagConstraints();
			gbc_lblTitel.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitel.gridx = 0;
			gbc_lblTitel.gridy = 0;
			contentPanel.add(lblTitel, gbc_lblTitel);
		}
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			JLabel lblNameVar = new JLabel(in[1]);
			lblNameVar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String myString = lblNameVar.getText();
					StringSelection stringSelection = new StringSelection(myString);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
			});
			lblNameVar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNameVar = new GridBagConstraints();
			gbc_lblNameVar.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblNameVar.insets = new Insets(0, 0, 5, 0);
			gbc_lblNameVar.gridx = 1;
			gbc_lblNameVar.gridy = 1;
			contentPanel.add(lblNameVar, gbc_lblNameVar);
		}
		{
			JLabel lblEMail = new JLabel("Email:");
			lblEMail.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblEMail = new GridBagConstraints();
			gbc_lblEMail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEMail.gridx = 0;
			gbc_lblEMail.gridy = 2;
			contentPanel.add(lblEMail, gbc_lblEMail);
		}
		{
			JLabel lblEmailVar = new JLabel(in[2]);
			lblEmailVar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String myString = lblEmailVar.getText();
					StringSelection stringSelection = new StringSelection(myString);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
			});
			lblEmailVar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblEmailVar = new GridBagConstraints();
			gbc_lblEmailVar.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblEmailVar.insets = new Insets(0, 0, 5, 0);
			gbc_lblEmailVar.gridx = 1;
			gbc_lblEmailVar.gridy = 2;
			contentPanel.add(lblEmailVar, gbc_lblEmailVar);
		}
		{
			JLabel lblTelNum = new JLabel("Telefon:");
			lblTelNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblTelNum = new GridBagConstraints();
			gbc_lblTelNum.insets = new Insets(0, 0, 5, 5);
			gbc_lblTelNum.gridx = 0;
			gbc_lblTelNum.gridy = 3;
			contentPanel.add(lblTelNum, gbc_lblTelNum);
		}
		{
			JLabel lblTelNumVar = new JLabel(in[3]);
			lblTelNumVar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String myString = lblTelNumVar.getText();
					StringSelection stringSelection = new StringSelection(myString);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
			});
			lblTelNumVar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblTelNumVar = new GridBagConstraints();
			gbc_lblTelNumVar.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblTelNumVar.insets = new Insets(0, 0, 5, 0);
			gbc_lblTelNumVar.gridx = 1;
			gbc_lblTelNumVar.gridy = 3;
			contentPanel.add(lblTelNumVar, gbc_lblTelNumVar);
		}
		{
			JLabel lblUser = new JLabel("Benutzer:");
			lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblUser = new GridBagConstraints();
			gbc_lblUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblUser.gridx = 0;
			gbc_lblUser.gridy = 4;
			contentPanel.add(lblUser, gbc_lblUser);
		}
		{
			JLabel lblBenutzerVar = new JLabel(in[4]);
			lblBenutzerVar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String myString = lblBenutzerVar.getText();
					StringSelection stringSelection = new StringSelection(myString);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
			});
			lblBenutzerVar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblBenutzerVar = new GridBagConstraints();
			gbc_lblBenutzerVar.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblBenutzerVar.insets = new Insets(0, 0, 5, 0);
			gbc_lblBenutzerVar.gridx = 1;
			gbc_lblBenutzerVar.gridy = 4;
			contentPanel.add(lblBenutzerVar, gbc_lblBenutzerVar);
		}
		{
			JLabel lblPasswort = new JLabel("Passwort:");
			lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblPasswort = new GridBagConstraints();
			gbc_lblPasswort.insets = new Insets(0, 0, 5, 5);
			gbc_lblPasswort.gridx = 0;
			gbc_lblPasswort.gridy = 5;
			contentPanel.add(lblPasswort, gbc_lblPasswort);
		}
		{
			JLabel lblPasswortVar = new JLabel(in[5]);
			lblPasswortVar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String myString = lblPasswortVar.getText();
					StringSelection stringSelection = new StringSelection(myString);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
			});
			lblPasswortVar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblPasswortVar = new GridBagConstraints();
			gbc_lblPasswortVar.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblPasswortVar.insets = new Insets(0, 0, 5, 0);
			gbc_lblPasswortVar.gridx = 1;
			gbc_lblPasswortVar.gridy = 5;
			contentPanel.add(lblPasswortVar, gbc_lblPasswortVar);
		}
		{
			JLabel lblNotiz = new JLabel("Notiz:");
			lblNotiz.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNotiz = new GridBagConstraints();
			gbc_lblNotiz.anchor = GridBagConstraints.NORTH;
			gbc_lblNotiz.gridheight = 3;
			gbc_lblNotiz.insets = new Insets(0, 0, 5, 5);
			gbc_lblNotiz.gridx = 0;
			gbc_lblNotiz.gridy = 6;
			contentPanel.add(lblNotiz, gbc_lblNotiz);
		}
		{
			JTextArea lblNotizVar = new JTextArea();
			lblNotizVar.setWrapStyleWord(true);
			lblNotizVar.setLineWrap(true);
			lblNotizVar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String myString = lblNotizVar.getText();
					StringSelection stringSelection = new StringSelection(myString);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
			});
			lblNotizVar.setText(in[6]);
			lblNotizVar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNotizVar.setEditable(false);
			lblNotizVar.setBackground(UIManager.getColor("Label.background"));
			GridBagConstraints gbc_lblNotizVar = new GridBagConstraints();
			gbc_lblNotizVar.gridheight = 3;
			gbc_lblNotizVar.insets = new Insets(0, 0, 5, 0);
			gbc_lblNotizVar.fill = GridBagConstraints.BOTH;
			gbc_lblNotizVar.gridx = 1;
			gbc_lblNotizVar.gridy = 6;
			contentPanel.add(lblNotizVar, gbc_lblNotizVar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDelete = new JButton("Löschen");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						delete(Integer.parseInt(in[0]),db);
					}
				});
				buttonPane.add(btnDelete);
			}
			{
				JButton btnChange = new JButton("Ändern");
				btnChange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						changePassed change  = new changePassed(db, Integer.parseInt(in[0]), in[1], in[2], in[3], in[4], in[5],in[6]);
						Thread thread = new Thread(change);
						setModal(false);
						setVisible(false);
						setVisible(true);
						thread.start();
						try {
							thread.join();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						
					}
				});
				buttonPane.add(btnChange);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private void delete(int id, Database db) {
		db.delete(id);
		dispose();
	}
}
