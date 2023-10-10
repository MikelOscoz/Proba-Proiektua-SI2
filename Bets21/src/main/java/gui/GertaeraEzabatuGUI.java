package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import businessLogic.BLFacade;
import domain.Event;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Vector;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GertaeraEzabatuGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox = new JComboBox();
	private DefaultComboBoxModel<Event> gertaera = new DefaultComboBoxModel<Event>();
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaeraEzabatuGUI frame = new GertaeraEzabatuGUI();
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
	public GertaeraEzabatuGUI() {
		setTitle("Gertaera ezabatu");
		BLFacade facad = MainGUI.getBusinessLogic();

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Aukeratu data");
		lblNewLabel.setBounds(79, 34, 94, 14);
		contentPane.add(lblNewLabel);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date data = dateChooser.getDate();
				Vector<Event> ev;
				if (data != null) {
					gertaera.removeAllElements();
					ev = facad.getEvents(data);
					gertaera.addAll(ev);
					comboBox.setModel(gertaera);
				}
			}
		});
		dateChooser.setBounds(246, 34, 149, 20);
		contentPane.add(dateChooser);

		JLabel lblNewLabel_1 = new JLabel("Aukeratu gertaera");
		lblNewLabel_1.setBounds(69, 87, 104, 14);
		contentPane.add(lblNewLabel_1);

		// comboBox = new JComboBox();
		// comboBox.setModel(gertaera);
		comboBox.setBounds(246, 83, 149, 22);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Ezabatu gertaera");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event ezabatu = (Event) comboBox.getSelectedItem();
				if (ezabatu == null) {
					textArea.setText("Gertaera aukeratu mesedez");
				} else {
					boolean ondo = false;
					ondo = facad.deleteEvent(ezabatu);
					if (ondo) {
						textArea.setText("Gertaera ezabatu da");
					} else {
						textArea.setText("Errore bat egon da");
					}
				}
				Date data = dateChooser.getDate();
				Vector<Event> ev;
				if (data != null) {
					gertaera.removeAllElements();
					ev = facad.getEvents(data);
					gertaera.addAll(ev);
					comboBox.setModel(gertaera);
				}
				
			}
		});
		btnNewButton.setBounds(170, 172, 149, 44);
		contentPane.add(btnNewButton);

		textArea = new JTextArea();
		textArea.setBounds(52, 227, 391, 63);
		contentPane.add(textArea);
	}
}
