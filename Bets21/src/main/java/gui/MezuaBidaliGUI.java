package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erabiltzaile;
import domain.Mezua;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class MezuaBidaliGUI extends JFrame {

	private JPanel contentPane;
	private JTextField jasotzailea;
	private JTextField asunto;
	private JTextField mezu;
	private User us;
	private JLabel bidaliDa;

	/**
	 * Create the frame.
	 */
	public MezuaBidaliGUI() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BLFacade facade = MainGUI.getBusinessLogic();
		// this.us= facade.getUser(u);

		setBounds(100, 100, 476, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mezu jasotzailearen login-a");
		lblNewLabel.setBounds(27, 21, 193, 14);
		contentPane.add(lblNewLabel);

		jasotzailea = new JTextField();
		jasotzailea.setBounds(27, 46, 137, 20);
		contentPane.add(jasotzailea);
		jasotzailea.setColumns(10);

		bidaliDa = new JLabel("");
		bidaliDa.setBounds(209, 85, 225, 14);
		contentPane.add(bidaliDa);

		JLabel lblNewLabel_1 = new JLabel("Asuntoa");
		lblNewLabel_1.setBounds(27, 97, 113, 14);
		contentPane.add(lblNewLabel_1);

		asunto = new JTextField();
		asunto.setBounds(27, 122, 137, 20);
		contentPane.add(asunto);
		asunto.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Mezua");
		lblNewLabel_2.setBounds(27, 163, 52, 14);
		contentPane.add(lblNewLabel_2);

		mezu = new JTextField();
		mezu.setBounds(27, 188, 377, 126);
		contentPane.add(mezu);
		mezu.setColumns(10);

		JButton btnNewButton = new JButton("Bidali mezua");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String asuntoa = asunto.getText();
				Erabiltzaile jaso = facade.getUserWithLog(jasotzailea.getText());
				if (jaso != null) {
					String mezua = mezu.getText();
					Date orain = new Date();
					Mezua m = new Mezua(asuntoa, mezua, orain, jaso.getBuzoia());
					boolean bidalketa = facade.mezuaBidali(m, jaso);
					if (bidalketa) {
						bidaliDa.setText("Mezua ondo bidali da");
					} else {
						bidaliDa.setText("Errore bat egon da");
					}
				}else {
					bidaliDa.setText("Erabiltzaile hori ez da existitzen");
				}
			}
		});
		btnNewButton.setBounds(154, 325, 129, 42);
		contentPane.add(btnNewButton);

	}
}
