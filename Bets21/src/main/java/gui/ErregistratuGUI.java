package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Mugimendua;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;


public class ErregistratuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textErabiltzailea;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textIzena;
	private JTextField textAbizena;
	private JTextField textposta;
	private JSpinner Adinal;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErregistratuGUI frame = new ErregistratuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ErregistratuGUI() {
		setTitle("Erregistratu");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblErabiltzailea = new JLabel("Erabiltzailea");
		lblErabiltzailea.setBounds(46, 11, 91, 14);
		contentPane.add(lblErabiltzailea);

		JLabel lblpasahitza = new JLabel("Pasahitza");
		lblpasahitza.setBounds(46, 36, 69, 14);
		contentPane.add(lblpasahitza);

		JLabel lblpasahitzakonf = new JLabel("Pasahitza konfirmatu");
		lblpasahitzakonf.setBounds(46, 61, 121, 14);
		contentPane.add(lblpasahitzakonf);

		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(46, 86, 46, 14);
		contentPane.add(lblIzena);

		JLabel lblAbizena = new JLabel("Abizena");
		lblAbizena.setBounds(46, 111, 46, 14);
		contentPane.add(lblAbizena);

		JLabel lblAdina = new JLabel("Adina");
		lblAdina.setBounds(46, 136, 46, 14);
		contentPane.add(lblAdina);

		JLabel lblposta = new JLabel("Posta Elektrinikoa");
		lblposta.setBounds(46, 161, 91, 14);
		contentPane.add(lblposta);

		JButton btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				String erabiltzaile = ErregistratuGUI.this.textErabiltzailea.getText();
				char[] pass = ErregistratuGUI.this.passwordField.getPassword();
				char[] pass1 = ErregistratuGUI.this.passwordField_1.getPassword();
				String Izen = ErregistratuGUI.this.textIzena.getText();
				String Abizen = ErregistratuGUI.this.textAbizena.getText();
				int Adin = (int) ErregistratuGUI.this.Adinal.getValue();
				String postaElek = ErregistratuGUI.this.textposta.getText();
				String pass2 = new String(pass);
				LinkedList<Mugimendua> lista=new LinkedList<Mugimendua>();
				if (Adin >= 18) {
					if (facade.isRegistered(erabiltzaile)) {
						ErregistratuGUI.this.textArea.setText("Erabiltzailea erregistratua dago!");
					}

					else if (pass2.equals(new String(pass1))) {
						User user = new User(erabiltzaile, Izen, Abizen, pass2, Adin, postaElek, 0,lista);
						boolean gordeDa = facade.storeUser(user);
						if (gordeDa)
							ErregistratuGUI.this.textArea.setText("Erregistratu zara!");
						else
							ErregistratuGUI.this.textArea.setText("Ez zara erregistratu!");
					} else {
						ErregistratuGUI.this.textArea.setText("error!");
					}
				} else {
					ErregistratuGUI.this.textArea.setText("18 urte baino gehiago izan behar dituzu!");
				}

			}
		});
		btnErregistratu.setBounds(175, 182, 89, 23);
		contentPane.add(btnErregistratu);

		textArea = new JTextArea();
		textArea.setBounds(90, 216, 275, 33);
		contentPane.add(textArea);

		textErabiltzailea = new JTextField();
		textErabiltzailea.setBounds(246, 8, 130, 20);
		contentPane.add(textErabiltzailea);
		textErabiltzailea.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(246, 33, 130, 20);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(246, 58, 130, 20);
		contentPane.add(passwordField_1);

		textIzena = new JTextField();
		textIzena.setBounds(246, 83, 130, 20);
		contentPane.add(textIzena);
		textIzena.setColumns(10);

		textAbizena = new JTextField();
		textAbizena.setText("");
		textAbizena.setBounds(246, 108, 130, 20);
		contentPane.add(textAbizena);
		textAbizena.setColumns(10);

		textposta = new JTextField();
		textposta.setBounds(246, 158, 130, 20);
		contentPane.add(textposta);
		textposta.setColumns(10);

		Adinal = new JSpinner();
		Adinal.setBounds(246, 133, 130, 20);
		contentPane.add(Adinal);
	}
}
