package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.Quote;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
//import javax.swing.event.AncestorListener;
//import javax.swing.event.AncestorEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class GertaeraBikoiztuGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JDateChooser dateChooser;

	private JLabel lbldata;
	private JLabel lblGertaerak;
	private JLabel lblEmaitza;

	private Date eguna;
	private Event gertaera;
	private boolean aukeratuta = false;

	private JComboBox<Event> gertaerak;
	private DefaultComboBoxModel<Event> gertaLista = new DefaultComboBoxModel<Event>();
	private JButton btnBikoiztu;
	private JLabel lblAukeratu;
	private JLabel lblEvent;
	private JButton btnEzabatuGertaera;
	private JButton btnGehitu;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { GertaeraBikoiztuGUI frame = new
	 * GertaeraBikoiztuGUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public GertaeraBikoiztuGUI() {
		setTitle("Gertaera Bikoiztu");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		BLFacade facade = MainGUI.getBusinessLogic();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				// dateChooser.setDate(eguna);
				eguna = dateChooser.getDate();
				gertaLista.removeAllElements();
				if (!aukeratuta) {
					if (eguna != null) {
						lblEvent.setText("");
						lblAukeratu.setText("");
						gertaera = null;

						// gertaLista.removeAllElements();
						Vector<Event> ev = facade.getEvents(eguna);
						gertaLista.addAll(ev);
						if (ev.isEmpty())
							lblGertaerak.setText("Ez dago gertaerarik egun horretan!");
						else
							lblGertaerak.setText("Aukeratu gertaera:");
					}
				}
			}
		});
		dateChooser.setBounds(10, 67, 97, 20);
		contentPane.add(dateChooser);

		lbldata = new JLabel("Aukeratu data:");
		lbldata.setBounds(10, 40, 393, 14);
		contentPane.add(lbldata);

		lblGertaerak = new JLabel("Gertaerak:");
		lblGertaerak.setBounds(10, 107, 195, 14);
		contentPane.add(lblGertaerak);

		gertaerak = new JComboBox<Event>();
		gertaerak.setModel(gertaLista);
		gertaerak.setBounds(10, 132, 159, 22);
		contentPane.add(gertaerak);

		btnBikoiztu = new JButton("Bikoiztu");
		btnBikoiztu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (aukeratuta) {
					Vector<Event> ev = facade.getEvents(eguna);
					int i = 0;
					boolean aurkitua = false;
					while (i < ev.size() && !aurkitua) {
						if (ev.get(i).getDescription().equals(gertaera.getDescription()))
							aurkitua = true;
						i++;
					}

					if (aurkitua)
						lblEmaitza.setText("Gertaera existitzen da egun horretan!!");
					else {
						Event event = new Event(0, gertaera.getDescription(), eguna);
						boolean ondo = facade.storeEvent(event);
						if (ondo) {
							Event gert = facade.getEvent(gertaera);
							for (Question quest : gert.getQuestions()) {
								try {
									facade.createQuestion(event, quest.getQuestion(), quest.getBetMinimum());
									Question q = facade.getEvent(event).getQuestion(quest);
									// for (Quote qu: quest.getQuoteList()) {
									for (Quote qu : quest.getQuoteList()) {
										facade.createQuote(qu.getQuote(), q, qu.getMultiply());
									}
								} catch (EventFinished | QuestionAlreadyExist e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
							lblEmaitza.setText("Gertaera gorde da!!!");
						} else
							lblEmaitza.setText("Errore bat egon da!!!");
					}
				} else
					lblEmaitza.setText("Gertaera aukeratu behar duzu!!!");
			}
		});
		btnBikoiztu.setBounds(10, 179, 89, 23);
		contentPane.add(btnBikoiztu);

		lblEmaitza = new JLabel("");
		lblEmaitza.setBounds(20, 213, 383, 14);
		contentPane.add(lblEmaitza);

		lblAukeratu = new JLabel("");
		lblAukeratu.setBounds(197, 107, 206, 14);
		contentPane.add(lblAukeratu);

		lblEvent = new JLabel("");
		lblEvent.setBounds(197, 136, 206, 14);
		contentPane.add(lblEvent);

		btnEzabatuGertaera = new JButton("Ezabatu gertaera");
		btnEzabatuGertaera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gertaera != null) {
					lblEvent.setText("");
					lblAukeratu.setText("");
					lbldata.setText("Aukeratu data:");
					aukeratuta = false;
					lblEmaitza.setText("Gertaera kendu da!!");
					gertaera=null;
				}
				else
					lblEmaitza.setText("Gertaera ez dago gehituta!!");
				// #############################BEGIN##################################
				gertaLista.removeAllElements();
				Vector<Event> ev = facade.getEvents(eguna);
				gertaLista.addAll(ev);
				if (ev.isEmpty())
					lblGertaerak.setText("Ez dago gertaerarik egun horretan!");
				else
					lblGertaerak.setText("Aukeratu gertaera:");
				// #############################END##################################
			}
		});
		btnEzabatuGertaera.setBounds(109, 179, 148, 23);
		contentPane.add(btnEzabatuGertaera);

		btnGehitu = new JButton("Aukeratu gertaera");
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// #############################BEGIN##################################
					if (gertaLista.getSelectedItem() != null) {
						gertaera = (Event) gertaLista.getSelectedItem();
						lblEvent.setText(gertaera + "");
						lblAukeratu.setText("Aukeratutako gertaera: ");
						lbldata.setText("Aukeratu gertaera bikoiztu nahi duzun eguna: ");
						aukeratuta = true;
						lblEmaitza.setText("Gertaera aukeratuta.");
					} else
						lblEmaitza.setText("Gertaera aukeratzeko lehenik aukeratu behar duzu!!");
				// #############################END##################################
			}
		});
		btnGehitu.setBounds(267, 179, 146, 23);
		contentPane.add(btnGehitu);
	}
}
