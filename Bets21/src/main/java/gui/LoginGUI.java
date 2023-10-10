package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Admin;
import domain.Erabiltzaile;
import domain.Langile;
import domain.User;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoginGUI extends JFrame {

	private JPanel contentPane;
	JTextField user;
	JPasswordField password;
	JTextArea textArea;
	JFrame nirePantaila;


	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		
		this.nirePantaila = this;
		setTitle("Login");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.user = new JTextField();
		this.user.setBounds(245, 49, 96, 20);
		this.contentPane.add(this.user);
		this.user.setColumns(10);
		
		this.password = new JPasswordField();
		this.password.setBounds(245, 104, 96, 20);
		this.contentPane.add(this.password);
		
		JLabel lblNewLabel = new JLabel("Erabiltzailea:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(62, 44, 96, 31);
		this.contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pasahitza:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(62, 99, 96, 31);
		this.contentPane.add(lblNewLabel_1);
		
		JButton botoia = new JButton("Sartu");
		botoia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = LoginGUI.this.user.getText();
				char[] pass = LoginGUI.this.password.getPassword();
				BLFacade facade = MainGUI.getBusinessLogic();
				//#############################BEGIN##################################
				Erabiltzaile era = facade.isLogin(userName,new String(pass));
				if(era!=null) {
				//if(facade.isLogin(userName,new String(pass)).getClass().getName()!=null) {
				//#############################END##################################		
					switch (facade.isLogin(userName,new String(pass)).getClass().getSimpleName())
					{
						case "Admin":
							//new AdminGUI();
							Admin ad =(Admin) facade.getUserWithLog(userName);
							AdminGUI b = new AdminGUI(ad);
							b.setVisible(true);
							LoginGUI.this.textArea.setText("AdminGUI");
							LoginGUI.this.nirePantaila.setVisible(false);
							break;
						case "User":
							//new UserGUI();
							User us = (User)facade.isLogin(userName,new String(pass));
							UserGUI a=new UserGUI(us);
							a.setVisible(true);
							LoginGUI.this.textArea.setText("RegisteredGUI");
							LoginGUI.this.nirePantaila.setVisible(false);
							break;
						case "Langile":
							//new LangileGUI();
							Langile la =(Langile) facade.getUserWithLog(userName);
							LangileGUI c = new LangileGUI(la);
							c.setVisible(true);
							LoginGUI.this.textArea.setText("LangileGUI");
							LoginGUI.this.nirePantaila.setVisible(false);
							break;
							
						default:
							LoginGUI.this.textArea.setText("Access denied.");
							break;
					}
				}else {
					LoginGUI.this.textArea.setText("Access denied.");
				}
				}				
			
		});
		botoia.setBounds(159,145,89,23);this.contentPane.add(botoia);

	this.textArea=new JTextArea();this.textArea.setBounds(62,186,279,53);this.contentPane.add(this.textArea);
}}
