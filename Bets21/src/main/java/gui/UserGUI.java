package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import businessLogic.BLFacade;
import domain.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UserGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonMugimenduak=null;
	private JButton jButtonDirua=null;
	private JButton jButtonMezua=null;
	private JButton jButtonMezuaIkusi=null;
	private JButton jButtonPasahitza=null;
	private JButton jButtonJarraitzaile=null;
	private User user;
    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton jButtonApostatu;
	

	
	
	public UserGUI(User u) {
		super();
		this.user=u;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(507, 324);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridLayout(4, 1, 0, 0));
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton5());
			jContentPane.add(getBoton6());
			jContentPane.add(getJButtonApostatu());
			jContentPane.add(getBoton7());
			jContentPane.add(getBoton8());
			jContentPane.add(getBoton9());
			jContentPane.add(getBoton10());
			jContentPane.add(getPanel());
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	
	private JButton getBoton5() {
		if (jButtonMugimenduak == null) {
			User u = user;
			jButtonMugimenduak = new JButton();
			jButtonMugimenduak.setText(ResourceBundle.getBundle("Etiquetas").getString("SeeMovements"));
			jButtonMugimenduak.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new SeeMovementsGUI(u);
					a.setVisible(true);
				}
			});
		}
		return jButtonMugimenduak;
	}
	
	private JButton getBoton6() {
		if (jButtonDirua == null) {
			User u = user;
			jButtonDirua = new JButton();
			jButtonDirua.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartu"));
			jButtonDirua.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new DiruaSartuGUI(u);
					a.setVisible(true);
				}
			});
		}
		return jButtonDirua;
	}
	
	private JButton getBoton7() {
		if (jButtonMezua == null) {
			jButtonMezua = new JButton();
			jButtonMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("MezuaBidali"));
			jButtonMezua.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new MezuaBidaliGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonMezua;
	}
	
	private JButton getBoton8() {
		if (jButtonMezuaIkusi == null) {
			User u =user;
			jButtonMezuaIkusi = new JButton();
			jButtonMezuaIkusi.setText(ResourceBundle.getBundle("Etiquetas").getString("MezuakIkusi"));
			jButtonMezuaIkusi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new MezuakIkusiGUI(u);

					a.setVisible(true);
				}
			});
		}
		return jButtonMezuaIkusi;
	}
	
	private JButton getBoton9() {
		if (jButtonPasahitza == null) {
			User u =user;
			jButtonPasahitza = new JButton();
			jButtonPasahitza.setText(ResourceBundle.getBundle("Etiquetas").getString("PasahitzaAldatu"));
			jButtonPasahitza.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new PasahitzaAldatuGUI(u);

					a.setVisible(true);
				}
			});
		}
		return jButtonPasahitza;
	}
	
	private JButton getBoton10() {
		if (jButtonJarraitzaile == null) {
			User u =user;
			jButtonJarraitzaile = new JButton();
			jButtonJarraitzaile.setText(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaJarraitu"));
			jButtonJarraitzaile.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ErabiltzaileaJarraituGUI(u);

					a.setVisible(true);
				}
			});
		}
		return jButtonJarraitzaile;
	}
	
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		//jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	private JButton getJButtonApostatu() {
		if (jButtonApostatu == null) {
			User u = user;
			jButtonApostatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UserGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonApostatu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ApustuaEginGUI(u);
					a.setVisible(true);
				}
			});
		}
		return jButtonApostatu;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

