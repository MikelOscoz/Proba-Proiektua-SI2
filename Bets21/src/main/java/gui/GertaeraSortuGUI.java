package gui;

import java.awt.EventQueue;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import businessLogic.BLFacade;
import domain.Event;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GertaeraSortuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField talde1;
	private JTextField talde2;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaeraSortuGUI frame = new GertaeraSortuGUI();
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
	public GertaeraSortuGUI() {
		setTitle("Create event");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aukeratu data:");
		lblNewLabel.setBounds(69, 53, 110, 14);
		contentPane.add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(243, 47, 149, 20);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_2 = new JLabel("1. Taldea");
		lblNewLabel_2.setBounds(155, 124, 79, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("2. Taldea");
		lblNewLabel_3.setBounds(429, 124, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		talde1 = new JTextField();
		talde1.setBounds(126, 149, 125, 26);
		contentPane.add(talde1);
		talde1.setColumns(10);
		
		talde2 = new JTextField();
		talde2.setBounds(387, 149, 136, 26);
		contentPane.add(talde2);
		talde2.setColumns(10);
		
		JButton botoia = new JButton("Sortu");
		botoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				Date data = dateChooser.getDate();
				String taldea1=talde1.getText();
				String taldea2=talde2.getText();
				String deskripzioa=taldea1+"-"+taldea2;
				
				Vector<Event>eventList;
				eventList = facade.getEvents(data);
				int i=0;
				boolean aurkitua=false;
				while (i<eventList.size() && !aurkitua) {
					if (deskripzioa.equals(eventList.get(i).getDescription())) {
						aurkitua=true;
					}
					i++;
				}
				if (aurkitua) {
					textArea.setText("Gertaera hori existitzen da datu basean!");
				}
				else {
					Event gertaera = new Event(0,deskripzioa,data);
					facade.storeEvent(gertaera);
					textArea.setText("Gertaera gehitu da");
				}	
			}
		});
		botoia.setBounds(272, 200, 110, 44);
		contentPane.add(botoia);
		
		textArea = new JTextArea();
		textArea.setBounds(155, 262, 360, 58);
		contentPane.add(textArea);
	}
}


