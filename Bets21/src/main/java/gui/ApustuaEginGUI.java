package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import businessLogic.BLFacade;
import domain.Apostatu;
import domain.Event;
import domain.Mugimendua;
import domain.Question;
import domain.Quote;
import domain.User;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JList;

public class ApustuaEginGUI extends JFrame {

	private JComboBox<Quote> quote;
	private JComboBox<Question> question;
	private JComboBox<Event> gertaera;
	private JComboBox<Quote> apustuak;
	private JDateChooser dateChooser;
	private JSpinner JSpinnerDirua;
	private JButton btnApostatu;
	private JButton remove;
	private int kop = 0;
	private float minimum = 0;
	private double multiply = 1;
	private Vector<Question> galdevector = new Vector<Question>();

	private DefaultComboBoxModel<Event> gertaeraLista = new DefaultComboBoxModel<Event>();
	private DefaultComboBoxModel<Question> questionLista = new DefaultComboBoxModel<Question>();
	private DefaultComboBoxModel<Quote> quoteLista = new DefaultComboBoxModel<Quote>();
	private DefaultComboBoxModel<Quote> apostuLista = new DefaultComboBoxModel<Quote>();

	private JPanel contentPane;
	private JLabel lblDirua;
	private JLabel lblEmaitza;
	private JLabel betMinimum;

	/**
	 * Create the frame.
	 */
	public ApustuaEginGUI(User u) {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		BLFacade facade = MainGUI.getBusinessLogic();
		System.out.println(multiply);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblData = new JLabel("Aukeratu data:");
		lblData.setBounds(10, 26, 101, 13);
		contentPane.add(lblData);

		JLabel lblGertaera = new JLabel("Gertaerak");
		lblGertaera.setBounds(10, 64, 177, 13);
		contentPane.add(lblGertaera);

		JLabel lblQuestion = new JLabel("Galderak");
		lblQuestion.setBounds(197, 64, 172, 13);
		contentPane.add(lblQuestion);

		JLabel lblQuote = new JLabel("Kuotak");
		lblQuote.setBounds(10, 115, 177, 13);
		contentPane.add(lblQuote);

		lblEmaitza = new JLabel("");
		lblEmaitza.setBounds(64, 277, 384, 29);
		contentPane.add(lblEmaitza);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(221, 137, 124, 22);
		contentPane.add(textArea);

		dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date data = dateChooser.getDate();
				Vector<Event> ev;
				if (data != null) {
					gertaeraLista.removeAllElements();
					questionLista.removeAllElements();
					lblQuestion.setText("NO Questions");
					quoteLista.removeAllElements();
					lblQuote.setText("NO Quote");
					ev = facade.getEvents(data);
					gertaeraLista.addAll(ev);
					if (ev.isEmpty())
						lblGertaera.setText("NO Events");
					else
						lblGertaera.setText("Events");
				}
			}
		});
		dateChooser.setBounds(105, 19, 137, 20);
		contentPane.add(dateChooser);

		gertaera = new JComboBox<Event>();
		gertaera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Question> qu;
				Event gertaera = (Event) gertaeraLista.getSelectedItem();
				if (gertaera != null) {
					questionLista.removeAllElements();
					quoteLista.removeAllElements();
					lblQuote.setText("NO Quote");
					qu = gertaera.getQuestions();
					questionLista.addAll(qu);
					if (qu.isEmpty())
						lblQuestion.setText("NO Questions");
					else
						lblQuestion.setText("Questions");
				}
			}
		});
		gertaera.setModel(gertaeraLista);
		gertaera.setBounds(10, 88, 165, 21);
		contentPane.add(gertaera);

		question = new JComboBox<Question>();
		question.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Quote> q;
				Question quest = (Question) questionLista.getSelectedItem();
				if (quest != null) {
					quoteLista.removeAllElements();
					q = quest.getQuoteList();
					quoteLista.addAll(q);
					if (q.isEmpty())
						lblQuote.setText("NO Quote");
					else
						lblQuote.setText("Quote");
				}
			}
		});
		question.setModel(questionLista);
		question.setBounds(197, 88, 227, 21);
		contentPane.add(question);

		quote = new JComboBox<Quote>();
		quote.setModel(quoteLista);

		quote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quote quot = (Quote) quoteLista.getSelectedItem();
				if (quot == null)
					textArea.setText("");
				else
					textArea.setText("" + quot.getMultiply());
			}
		});

		quote.setBounds(10, 139, 186, 21);
		contentPane.add(quote);

		lblDirua = new JLabel("Sartu Dirua:");
		lblDirua.setBounds(10, 172, 80, 13);
		contentPane.add(lblDirua);

		JSpinnerDirua = new JSpinner();
		JSpinnerDirua.setBounds(10, 193, 124, 20);
		contentPane.add(JSpinnerDirua);

		btnApostatu = new JButton("Apostatu");
		btnApostatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date data = dateChooser.getDate();
				Event gertaera = (Event) gertaeraLista.getSelectedItem();
				Question quest = (Question) questionLista.getSelectedItem();
				Quote quot = (Quote) quoteLista.getSelectedItem();
				int dirua = (int) JSpinnerDirua.getValue();
				if (kop == 0) {
					minimum = quest.getBetMinimum();
					betMinimum.setText(minimum + "");
					multiply = quot.getMultiply();
					// apostuLista.addElement(quot);
				}
				// User u=null;//TODO hau aldatu
				if (data == null || gertaera == null || quest == null || quot == null)
					lblEmaitza.setText("Datuak sartu behar dituzu");
				else if (facade.isEmaitza(quest) != null)
					lblEmaitza.setText("Ezin duzu apostatu. Galdera honen emaitza publikatuta dago.");

				else {
					if (dirua < minimum)
						lblEmaitza.setText(
								"Apustu dezakezun dirua gainditu behar du minimo bat: " + betMinimum.getText());
					else if (dirua > facade.getUser(u).getDirua())
						lblEmaitza.setText("Ez duzu hainbeste diru");
					else {

						System.out.println("Userra duen dirua:" + facade.getUser(u).getDirua());
						double driakAP = facade.getUser(u).getDirua();
						Apostatu apostatu = new Apostatu(gertaera, dirua, u, true, multiply);
						// boolean store=facade.storeApostua(apostatu);

						if (kop > 0) {
							for (int j = 0; j < apostuLista.getSize(); j++) {
								apostatu.addQuote(apostuLista.getElementAt(j));
							}
						} else if (kop == 0) {
							apostatu.addQuote(quot);
							galdevector.add(quest);

							System.out.println(galdevector.toString());
						}

						boolean store = facade.storeApostua(apostatu, galdevector);

						System.out.println(apostatu.toString());

						if (store) {
							lblEmaitza.setText("Zure apustua gorde da!!!");
							facade.removeMoney(u, dirua);
							Mugimendua mugi = new Mugimendua(0, driakAP, gertaera, quest,
									"Apostatu duzu:" + dirua + "€");
							facade.storeMugimenduak(u, mugi);
							// facade.addApostuaQuestion(quest, apostatu);
							System.out.println("Mugimenduak eguneratu dira");
						} else
							lblEmaitza.setText("Zure apustua ez da gorde arazoren batengatik!!!");
						// ##########################START####################################
						// if(!u.getJarraitzaileak().isEmpty() ) {
						Set<User> use = u.getJarraitzaileak();
						if (use != null && !use.isEmpty()) {
							for (User jarraitzaile : u.getJarraitzaileak()) {
								if (jarraitzaile.getDirua()>=dirua) {
									Apostatu apostu2 = new Apostatu(gertaera, dirua, jarraitzaile, true, multiply);
									if (kop > 0) {
										for (int j = 0; j < apostuLista.getSize(); j++) {
											apostu2.addQuote(apostuLista.getElementAt(j));
										}
									} else if (kop == 0) {
										apostu2.addQuote(quot);
										galdevector.add(quest);
										// System.out.println(galdevector.toString());
									}

									boolean gorde = facade.storeApostua(apostu2, galdevector);

									if (gorde) {
										// lblEmaitza.setText("Zure apustua gorde da!!!");
										facade.removeMoney(jarraitzaile, dirua);
										double driak = facade.getUser(jarraitzaile).getDirua();
										Mugimendua mugi2 = new Mugimendua(0, driak, gertaera, quest,
												"Apostatu duzu:" + dirua + "€");
										facade.storeMugimenduak(jarraitzaile, mugi2);
										// facade.addApostuaQuestion(quest, apostatu);
										// System.out.println("Mugimenduak eguneratu dira");
									}
								}else {
									Mugimendua mugi2 = new Mugimendua(0, null, null,
											"Ez duzu hainbeste dirua!");
									facade.storeMugimenduak(jarraitzaile, mugi2);
								}
							}
						}
						// ##########################END####################################
					}

				}
			}
		});
		btnApostatu.setBounds(29, 244, 116, 21);
		contentPane.add(btnApostatu);

		apustuak = new JComboBox<Quote>();
		apustuak.setModel(apostuLista);
		apustuak.setBounds(322, 192, 126, 22);
		contentPane.add(apustuak);

		JButton Gehitu = new JButton("Gehitu apostua");
		Gehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quote quot = (Quote) quoteLista.getSelectedItem();
				Question quest = (Question) questionLista.getSelectedItem();
				if (quot != null) {
					boolean aurkitua = false;
					int i = 0;
					while (i < apostuLista.getSize() && !aurkitua) {
						if (apostuLista.getElementAt(i).getQuestion().equals(quest))
							aurkitua = true;
						i++;
					}
					if (aurkitua) {
						lblEmaitza.setText("Ezin duzu berriz apostatu galdera berdinean.");
					} else {
						lblEmaitza.setText("Apostua gehitu da.");
						apostuLista.addElement(quot);
						galdevector.add(quest);

						System.out.println(galdevector.toString());

						kop++;
						multiply = multiply * quot.getMultiply();
						System.out.println(multiply);
						minimum += quest.getBetMinimum();
						betMinimum.setText(minimum + "");
					}
				} else
					lblEmaitza.setText("Dirua sartu eta quota sartu behar dituzu.");
			}
		});
		Gehitu.setBounds(168, 243, 137, 23);
		contentPane.add(Gehitu);

		JLabel Apostuak = new JLabel("Haukeratutako kuotak:");
		Apostuak.setBounds(168, 196, 144, 14);
		contentPane.add(Apostuak);

		remove = new JButton("KenduApostua");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quote aukQuote = (Quote) apostuLista.getSelectedItem();
				if (aukQuote == null)
					lblEmaitza.setText("Apostua kentzeko lehenik aukeratu behar duzu listatik");
				else {
					apostuLista.removeElement(aukQuote);
					galdevector.removeElement(aukQuote.getQuestion());
					lblEmaitza.setText("Apostua kendu da.");

					System.out.println(galdevector.toString());

					kop--;
					if (kop == 0) {
						multiply = 1;
						minimum = 0;
						betMinimum.setText(minimum + "");
					} else {
						multiply = multiply / aukQuote.getMultiply();
						System.out.println(multiply);
						minimum -= aukQuote.getQuestion().getBetMinimum();
						betMinimum.setText(minimum + "");
					}
				}
			}
		});
		remove.setBounds(331, 243, 137, 23);
		contentPane.add(remove);

		betMinimum = new JLabel("0");
		betMinimum.setBounds(385, 142, 46, 14);
		contentPane.add(betMinimum);

	}
}
