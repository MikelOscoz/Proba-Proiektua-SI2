package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class PasahitzaAldatuGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField zaharra;
	private JPasswordField berria;
	private JPasswordField konfirmazioa;

	private User user;

	/**
	 * Create the frame.
	 */
	public PasahitzaAldatuGUI(User u) {
		BLFacade facade = MainGUI.getBusinessLogic();
		this.user = facade.getUser(u);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Pasahitza zaharra:");
		lblNewLabel.setBounds(60, 79, 111, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pasahitza berria");
		lblNewLabel_1.setBounds(60, 147, 111, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Konfirmatu pasahitza berria:");
		lblNewLabel_2.setBounds(60, 213, 165, 14);
		contentPane.add(lblNewLabel_2);

		zaharra = new JPasswordField();
		zaharra.setBounds(256, 76, 96, 20);
		contentPane.add(zaharra);

		berria = new JPasswordField();
		berria.setBounds(256, 144, 96, 20);
		contentPane.add(berria);

		konfirmazioa = new JPasswordField();
		konfirmazioa.setBounds(256, 210, 96, 20);
		contentPane.add(konfirmazioa);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(93, 291, 304, 38);
		contentPane.add(textArea);

		JButton btnNewButton = new JButton("Aldatu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pZaharra = zaharra.getPassword();
				char[] pBerria = berria.getPassword();
				char[] pKonfirmazioa = konfirmazioa.getPassword();
				String pBerriaS = new String(pBerria);
				String pKonfirmazioaS = new String(pKonfirmazioa);
				String pZaharraS = new String(pZaharra);
				if (facade.isLogin(user.getUserName(), new String(pZaharra)) != null) {
					if (!pZaharraS.equals(pBerriaS)) {
						if (pBerriaS.equals(pKonfirmazioaS)) {
							boolean ondo = false;
							ondo = facade.pasahitzaAldatu(user, new String(pBerria));
							if (ondo) {
								textArea.setText("Pasahitza aldatu da");
							} else {
								textArea.setText("Errore bat egon da");
							}
						} else {
							textArea.setText("Pasahitza berria eta konfirmatua ez dira berdinak");
						}
					} else {
						textArea.setText("Pasahitza berria ezin da zaharraren berdina izan");
					}
				} else {
					textArea.setText("Pasahitza zaharra ez da egokia");
				}
			}
		});
		btnNewButton.setBounds(195, 257, 89, 23);
		contentPane.add(btnNewButton);

	}
}
