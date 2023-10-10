package gui;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.Quote;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmaitzaIpiniGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private DefaultComboBoxModel<Event> gertaeraLista = new DefaultComboBoxModel<Event>();
	private JComboBox comboBox_1;
	private DefaultComboBoxModel<Question> questionLista = new DefaultComboBoxModel<Question>();
	private JComboBox comboBox_2;
	private DefaultComboBoxModel<Quote> quoteLista = new DefaultComboBoxModel<Quote>();
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmaitzaIpiniGUI frame = new EmaitzaIpiniGUI();
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
	public EmaitzaIpiniGUI() {
		setTitle("Emaitza ipini");
		BLFacade facad = MainGUI.getBusinessLogic();

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Data aukeratu:");
		lblNewLabel.setBounds(86, 39, 103, 14);
		contentPane.add(lblNewLabel);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date data = dateChooser.getDate();
				Vector<Event> ev;
				if (data != null) {
					gertaeraLista.removeAllElements();
					questionLista.removeAllElements();
					quoteLista.removeAllElements();
					ev = facad.getEvents(data);
					gertaeraLista.addAll(ev);
					comboBox.setModel(gertaeraLista);
				}
			}
		});
		dateChooser.setBounds(261, 39, 149, 20);
		contentPane.add(dateChooser);

		JLabel lblNewLabel_1 = new JLabel("Gertaera aukeratu:");
		lblNewLabel_1.setBounds(86, 107, 118, 14);
		contentPane.add(lblNewLabel_1);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Question> qu;
				Event gertaera = (Event) gertaeraLista.getSelectedItem();
				if (gertaera != null) {
					questionLista.removeAllElements();
					quoteLista.removeAllElements();
					qu = gertaera.getQuestions();
					questionLista.addAll(qu);
					comboBox_1.setModel(questionLista);
				}
			}
		});
		comboBox.setBounds(261, 103, 149, 22);
		contentPane.add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("Galdera aukeratu:");
		lblNewLabel_2.setBounds(86, 171, 92, 14);
		contentPane.add(lblNewLabel_2);

		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Quote> q;
				Question quest = (Question) questionLista.getSelectedItem();
				if (quest != null) {
					quoteLista.removeAllElements();
					q = quest.getQuoteList();
					quoteLista.addAll(q);
					comboBox_2.setModel(quoteLista);
				}
			}
		});
		comboBox_1.setBounds(261, 167, 149, 22);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel_3 = new JLabel("Aukeratu emaitza irabazlea:");
		lblNewLabel_3.setBounds(54, 232, 157, 14);
		contentPane.add(lblNewLabel_3);

		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(261, 228, 149, 22);
		contentPane.add(comboBox_2);

		JButton btnNewButton = new JButton("Konfirmatu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quote irabazi = (Quote) comboBox_2.getSelectedItem();
				Question galdera = (Question) comboBox_1.getSelectedItem();
				if (irabazi == null) {
					textArea.setText("Kuota irabazlea aukeratu mesedez");
				} else {
					if (facad.isEmaitza(galdera) != null)
						textArea.setText("Emaitza gehituta dago");
					else {
						boolean gehituta = facad.storeEmaitza(galdera, irabazi);
						if (gehituta) {
							boolean ondo = facad.emaitzaBanatu(irabazi);
							if (ondo) {
								textArea.setText("Eragiketa ondo egin da");
							} else {
								textArea.setText("Errore bat egon da");
							}
						} else
							textArea.setText("Errore bat egon da Emaitza jartzerakoan");
					}
				}
			}
		});
		btnNewButton.setBounds(174, 276, 149, 35);
		contentPane.add(btnNewButton);

		textArea = new JTextArea();
		textArea.setBounds(69, 320, 380, 63);
		contentPane.add(textArea);
	}
}
