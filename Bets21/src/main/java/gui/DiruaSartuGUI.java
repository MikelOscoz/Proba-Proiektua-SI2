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
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DiruaSartuGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;


	/**
	 * Create the frame.
	 */
	public DiruaSartuGUI(User u) {
		setTitle("Dirua sartu");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Zenbat dirua sartu nahi duzu?");
		lblNewLabel.setBounds(20, 23, 168, 14);
		contentPane.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(198, 20, 115, 20);
		contentPane.add(spinner);
		
		BLFacade facad = MainGUI.getBusinessLogic();
		
		JButton btnNewButton = new JButton("Sartu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value= (int)spinner.getValue();
				boolean ok=facad.updateMoney(u, value);
				if(ok) {
					textArea.setText("Dirua ondo sartu da");
					Mugimendua mugi = new Mugimendua(0,null,null, "Dirua sartu duzu zure kontuan : +" +value);
					facad.storeMugimenduak(u, mugi);
					//textArea.setText(Double.toString(u.getDirua()));
				}else {
					textArea.setText("Errore bat egon da");
				}
			}
		});
		btnNewButton.setBounds(141, 67, 89, 23);
		contentPane.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(63, 108, 250, 51);
		contentPane.add(textArea);
	}
}
