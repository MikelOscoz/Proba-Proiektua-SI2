package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeeMovementsGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private User us;

	/**
	 * Create the frame.
	 */
	public SeeMovementsGUI(User u) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.us= facade.getUser(u);
		JButton btnNewButton = new JButton("Mugimenduak ikusi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(us.printMugimenduak());
			}
		});
		btnNewButton.setBounds(261, 23, 137, 23);
		contentPane.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 62, 661, 300);
		contentPane.add(textArea);
	}
}
