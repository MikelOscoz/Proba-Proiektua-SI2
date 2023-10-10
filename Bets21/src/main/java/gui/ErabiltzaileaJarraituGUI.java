package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErabiltzaileaJarraituGUI extends JFrame {

	private JPanel contentPane;
	private User user;
	private JTextField targetUser;

	/**
	 * Create the frame.
	 */
	public ErabiltzaileaJarraituGUI(User u) {
		BLFacade facade = MainGUI.getBusinessLogic();
		this.user=u;
		
		setTitle("Erabiltzailea jarraitu");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sartu jarraitu nahi duzun erabiltzailearen username: ");
		lblNewLabel.setBounds(75, 24, 252, 14);
		contentPane.add(lblNewLabel);
		
		targetUser = new JTextField();
		targetUser.setBounds(75, 60, 252, 20);
		contentPane.add(targetUser);
		targetUser.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(75, 178, 252, 54);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Follow");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User target=(User)facade.getUserWithLog(targetUser.getText());
				if(target!=null) {
					boolean ondo=facade.erabiltzaileaJarraitu(target, user);
					if(ondo) {
						textArea.setText("Orain "+target.getUserName()+" -en jarraitzailea zara");
						Mugimendua mugi = new Mugimendua(0, null, null,
								"Orain "+target.getUserName()+" -en jarraitzailea zara");
						facade.storeMugimenduak(u, mugi);
					}else {
						textArea.setText("Errore bat egon da");
					}
				}else {
					textArea.setText("Erabiltzaile hori ez da existitzen");
				}
			}
		});
		btnNewButton.setBounds(160, 120, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
