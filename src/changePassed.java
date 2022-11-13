import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class changePassed extends JFrame implements Runnable {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JTextField textEmail;
	private JTextField textTelNum;
	private JTextField textUser;
	private JTextField textPasswd;
	/**
	 * Create the dialog.
	 */
	public changePassed(Database db, int id, String name, String email, String tel, String user, String passwd, String notiz) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./resources/show.png"));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		System.out.println("id:"+id+"|name:"+name+"|email:"+email+"|tel:"+tel+"|user:"+user+"|passwd:"+passwd+"|notiz:"+notiz);
		
		JLabel lblTitle = new JLabel("Passwort ändern");
		lblTitle.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPanel.add(lblTitle, gbc_lblTitle);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		contentPanel.add(lblName, gbc_lblName);

		textName = new JTextField();
		
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.insets = new Insets(0, 0, 5, 0);
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.gridx = 1;
		gbc_textName.gridy = 1;
		contentPanel.add(textName, gbc_textName);
		textName.setColumns(10);
		textName.setDocument(new LimitJTextField(30));

		JLabel lblEmail = new JLabel("EMail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		contentPanel.add(lblEmail, gbc_lblEmail);

		textEmail = new JTextField();
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.gridx = 1;
		gbc_textEmail.gridy = 2;
		contentPanel.add(textEmail, gbc_textEmail);
		textEmail.setColumns(10);
		textEmail.setDocument(new LimitJTextField(50));

		JLabel lblTelNum = new JLabel("Telefon");
		lblTelNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTelNum = new GridBagConstraints();
		gbc_lblTelNum.anchor = GridBagConstraints.EAST;
		gbc_lblTelNum.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelNum.gridx = 0;
		gbc_lblTelNum.gridy = 3;
		contentPanel.add(lblTelNum, gbc_lblTelNum);

		textTelNum = new JTextField();
		GridBagConstraints gbc_textTelNum = new GridBagConstraints();
		gbc_textTelNum.insets = new Insets(0, 0, 5, 0);
		gbc_textTelNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelNum.gridx = 1;
		gbc_textTelNum.gridy = 3;
		contentPanel.add(textTelNum, gbc_textTelNum);
		textTelNum.setColumns(10);
		textTelNum.setDocument(new LimitJTextField(30));

		JLabel lblUser = new JLabel("Benutzer");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 4;
		contentPanel.add(lblUser, gbc_lblUser);
		

		textUser = new JTextField();
		GridBagConstraints gbc_textUser = new GridBagConstraints();
		gbc_textUser.insets = new Insets(0, 0, 5, 0);
		gbc_textUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUser.gridx = 1;
		gbc_textUser.gridy = 4;
		contentPanel.add(textUser, gbc_textUser);
		textUser.setColumns(10);
		textUser.setDocument(new LimitJTextField(50));
		
		JLabel lblPasswd = new JLabel("Passwort");
		lblPasswd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPasswd = new GridBagConstraints();
		gbc_lblPasswd.anchor = GridBagConstraints.EAST;
		gbc_lblPasswd.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswd.gridx = 0;
		gbc_lblPasswd.gridy = 5;
		contentPanel.add(lblPasswd, gbc_lblPasswd);

		textPasswd = new JTextField(passwd);
		GridBagConstraints gbc_textPasswd = new GridBagConstraints();
		gbc_textPasswd.insets = new Insets(0, 0, 5, 0);
		gbc_textPasswd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPasswd.gridx = 1;
		gbc_textPasswd.gridy = 5;
		contentPanel.add(textPasswd, gbc_textPasswd);
		textPasswd.setColumns(10);
		textPasswd.setDocument(new LimitJTextField(30));

		JLabel lblNotiz = new JLabel("Notiz");
		lblNotiz.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNotiz = new GridBagConstraints();
		gbc_lblNotiz.insets = new Insets(0, 0, 0, 5);
		gbc_lblNotiz.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNotiz.gridx = 0;
		gbc_lblNotiz.gridy = 6;
		contentPanel.add(lblNotiz, gbc_lblNotiz);

		JTextArea textNotiz = new JTextArea();
		textNotiz.setWrapStyleWord(true);
		textNotiz.setLineWrap(true);
		GridBagConstraints gbc_textNotiz = new GridBagConstraints();
		gbc_textNotiz.fill = GridBagConstraints.BOTH;
		gbc_textNotiz.gridx = 1;
		gbc_textNotiz.gridy = 6;
		contentPanel.add(textNotiz, gbc_textNotiz);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				String email = textEmail.getText();
				String telnum = textTelNum.getText();
				String user = textUser.getText();
				String passwd = textPasswd.getText();
				String Notiz = textNotiz.getText();
				db.change(id, name, email, telnum, user, passwd, Notiz);
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
			
		textName.setText(name);
		textEmail.setText(email);
		textPasswd.setText(passwd);
		textNotiz.setText(notiz);
		textUser.setText(user);
		textTelNum.setText(tel);
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
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
