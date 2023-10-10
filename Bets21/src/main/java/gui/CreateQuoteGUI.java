package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import domain.Quote;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class CreateQuoteGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnSortu;
	private JTextArea kuotaktext;
	private JDateChooser dateChooser;
	private DefaultComboBoxModel<Event> gertaera = new DefaultComboBoxModel<Event>();
	private DefaultComboBoxModel<Question> quest = new DefaultComboBoxModel<Question>();
	private JComboBox<Question> galdera;

	/**
	 * Launch the application.
	 */

	public CreateQuoteGUI() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void jbInit() throws Exception {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Create Quote");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		BLFacade facad = MainGUI.getBusinessLogic();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbldata = new JLabel("Aukeratu data:");
		lbldata.setBounds(22, 20, 108, 13);
		contentPane.add(lbldata);

		JLabel lblgertaera = new JLabel("Aukeratu gertaera:");
		lblgertaera.setBounds(18, 62, 157, 13);
		contentPane.add(lblgertaera);

		JLabel lblgaldera = new JLabel("Aukeratu galdera:");
		lblgaldera.setBounds(206, 62, 177, 13);
		contentPane.add(lblgaldera);

		JLabel lblkuotak = new JLabel("Sortu emaitzak:");
		lblkuotak.setBounds(10, 152, 157, 13);
		contentPane.add(lblkuotak);

		JLabel lblkuota = new JLabel("Sartu kuota:");
		lblkuota.setBounds(10, 206, 120, 14);
		contentPane.add(lblkuota);

		JSpinner multiply = new JSpinner();
		multiply.setBounds(10, 231, 153, 20);
		contentPane.add(multiply);

		textArea = new JTextArea();
		textArea.setBounds(206, 217, 204, 36);
		contentPane.add(textArea);

		dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date data = dateChooser.getDate();
				Vector<Event> ev;
				if (data != null) {
					gertaera.removeAllElements();
					quest.removeAllElements();
					lblgaldera.setText("EZ dago galderarik:");
					ev = facad.getEvents(data);
					gertaera.addAll(ev);
					if (ev.isEmpty())
						lblgertaera.setText("EZ dago gertaerarik:");
					else
						lblgertaera.setText("Aukeratu gertaera:");
				}
			}
		});
		dateChooser.setBounds(159, 20, 149, 20);
		contentPane.add(dateChooser);

		kuotaktext = new JTextArea();
		kuotaktext.setBounds(10, 176, 206, 20);
		contentPane.add(kuotaktext);

		JComboBox<Event> comboBox = new JComboBox<Event>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Question> q;
				Event gertaera = (Event) comboBox.getSelectedItem();
				if (gertaera != null) {
					quest.removeAllElements();
					q = gertaera.getQuestions();
					quest.addAll(q);
					if (q.isEmpty())
						lblgaldera.setText("EZ dago galderarik:");
					else
						lblgertaera.setText("Aukeratu galdera:");
				}
			}
		});
		comboBox.setModel(gertaera);
		comboBox.setBounds(22, 91, 160, 21);
		contentPane.add(comboBox);

		// BLFacade facad = MainGUI.getBusinessLogic();
		/*
		Date data = dateChooser.getDate();
		Vector<Event> ev;
		if (data != null) {
			ev = facad.getEvents(data);
			gertaera.addAll(ev);
		}
		*/

		galdera = new JComboBox<Question>();
		galdera.setModel(quest);
		galdera.setBounds(206, 91, 177, 21);
		contentPane.add(galdera);

		/*
		Vector<Question> q;
		Event gertaera = (Event) comboBox.getSelectedItem();
		if (gertaera != null) {
			q = gertaera.getQuestions();
			quest.addAll(q);
		}
		*/
		btnSortu = new JButton("Sortu");
		btnSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();

				String text = kuotaktext.getText();
				if (text.equals("")) {
					textArea.setText("Emaitza posiblea sartu behar duzu");
				} else {
					Event ev = (Event) comboBox.getSelectedItem();
					Question question = (Question) galdera.getSelectedItem();
					int biderkatzailea = (int) multiply.getValue();
					if (biderkatzailea < 1) {
						textArea.setText("Kuota ezin da izan 1 baino txikiagoa.");
					} else if (ev != null && question != null) {	
							Quote emaitza = facade.createQuote(kuotaktext.getText(), question, biderkatzailea);
							if (emaitza.getQuoteNumber() == -1)
								textArea.setText("Kuota existitzen da");
							else
								textArea.setText("Kuota gehitu da");
										
					} else {
						textArea.setText("Datuak Sartu behar dituzu");
					}
				}
			}
		});
		btnSortu.setBounds(260, 178, 85, 21);
		contentPane.add(btnSortu);

	}
}
