package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erabiltzaile;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MezuakIkusiGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private Erabiltzaile era;

	/**
	 * Create the frame.
	 */
	public MezuakIkusiGUI(Erabiltzaile e) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.era= facade.getErabiltzaile(e);
		JButton btnNewButton = new JButton("Mezuak ikusi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(era.mezuakIkusi());
			}
		});
		btnNewButton.setBounds(259, 11, 156, 40);
		contentPane.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 62, 651, 349);
		contentPane.add(textArea);
	}
}