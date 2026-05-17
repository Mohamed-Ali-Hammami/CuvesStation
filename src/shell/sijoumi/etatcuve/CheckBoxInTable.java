package shell.sijoumi.etatcuve;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

public class CheckBoxInTable extends JFrame implements ActionListener{

	Timer timer;
	
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField_pop1 = new JTextField();
	private JTextField textField_pop2;
	private JTextField textField_pop3;
	private JTextField textField_pop4;
	private JTextField textField_pop5;
	private JTextField textField_pop6;
	private JTextField textField_pop7;
	private JTextField textField_pop8;
	private JTextField textField_pop9;
	private JTextField textField_pop10;
	private JTextField textField_pop11;
	private JTextField textField_pop12;
	private JTextField txtHhmm;
	private JTextField txt_h_fin;
	private JTextField textField_totale;
	private JTable table_result_pompe;
	private JCheckBox check_pop1;
	private JLabel lblPompe12;

	private JTextField totale;

	private static int width;
	private static int height;
	private String t1;
	private String t2;

	private JDateChooser date_deb;
	private JDateChooser date_fin;

	Connection connection= null;
	ResultSet rs = null;
	PreparedStatement pst= null;

	private List<Transaction> listjournal; 
	private JTextField textField_pompe12;
	private JTextField textField_recherch_mont;
	private JTextField Search_montant;
	private JTextField recherche_montant;
	
	
	private List<String> listpompes = new ArrayList<String>();


	public CheckBoxInTable() {
		
		getContentPane().setBackground(new Color(192, 192, 192));


		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		width = gd.getDisplayMode().getWidth();
		height = gd.getDisplayMode().getHeight();

		getContentPane().setSize(width-20,height);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(21, 80, width, 267);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(12, 65, 116, 189);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btn_validation = new JButton("Validation");
		btn_validation.setFont(new Font("Times New Roman",Font.ITALIC, 10));
		btn_validation.setBounds(12, 50, 80, 25);
		panel_1.add(btn_validation);

		JButton btn_montant = new JButton("Montant");
		btn_montant.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		btn_montant.setBounds(12, 148, 80, 25);
		panel_1.add(btn_montant);

		JLabel icon_btn = new JLabel("New label");
		icon_btn.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_btn.setBounds(0, 0, 116, 189);
		panel_1.add(icon_btn);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.BLACK, 1, true));
		panel_2.setBounds(110, 65, 80, 189);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lbl_pop1 = new JLabel("Pompe1");
		lbl_pop1.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lbl_pop1.setBounds(26, 35,56, 16);
		
		panel_2.add(lbl_pop1);


		JCheckBox check_pop1 = new JCheckBox("");
		check_pop1.setBounds(51, 80, 25, 22);
		panel_2.add(check_pop1);
	
		
/*------------------------------------------------------- POMPE 1------------------*/			
		check_pop1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lbl_pop1.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					//con.connexionFTP3(new Date());
					 
					//List<String> listpompes = new ArrayList<String>();
					listpompes.add("PMP:1");
					//listpompes.add(t1);
					//listpompes.add(t2);
					double total=0;
					double totalP1=0;
					try {
						listjournal=new ArrayList<Transaction>();

						////listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:1"))
									totalP1=totalP1+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop1.setText(df.format(totalP1));
						}


					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
					table_result_pompe.setAutoCreateRowSorter(true);

				} 
				else {

					lbl_pop1.setForeground(Color.GREEN);
					
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:1"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP1=0;
					try {
						listjournal=new ArrayList<Transaction>();

					//	//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:1"))
									totalP1=totalP1+t.getMontant();
							}
							
							
						}
                        System.out.println("tolap1"+totalP1);
						textField_totale.setText(df.format(total));
						textField_pop1.setText(df.format(totalP1));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);


				}
			}
		});



		textField_pop1 = new JTextField();
		textField_pop1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		textField_pop1.setBounds(12, 149, 104, 22);
		panel_2.add(textField_pop1);
		textField_pop1.setColumns(10);
		
		
/*------------------------------------------------------- END POMPE 1------------------*/		

		JLabel icon_pop1 = new JLabel("New label");
		icon_pop1.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop1.setBounds(0, 0,80, 189);
		panel_2.add(icon_pop1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(140, 13, width-210, 34);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblListeDesPompes = new JLabel("Liste des Pompes ");
		lblListeDesPompes.setBounds(803, 0, 307, 34);
		panel_3.add(lblListeDesPompes);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(210, 65, 80, 189);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblPompe2 = new JLabel("Pompe2");
		lblPompe2.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblPompe2.setBounds(39, 32,56, 16);
		panel_4.add(lblPompe2);

		JCheckBox check_pop2 = new JCheckBox("");
		check_pop2.setBounds(45, 80, 25, 25);
		panel_4.add(check_pop2);
	

		textField_pop2 = new JTextField();
		textField_pop2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop2.setBounds(12, 149, 105, 22);
		panel_4.add(textField_pop2);
		textField_pop2.setColumns(10);

		JLabel icon_pop2 = new JLabel("New label");
		icon_pop2.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop2.setBounds(0, 0, 129, 189);
		panel_4.add(icon_pop2);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(320, 65, 80, 189);
		panel.add(panel_5);

		JLabel lblPompe_3 = new JLabel("Pompe3");
		lblPompe_3.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblPompe_3.setBounds(37, 32,56, 16);
		panel_5.add(lblPompe_3);

		JCheckBox check_pop3 = new JCheckBox("");
		check_pop3.setBounds(50, 80, 25, 22);
		panel_5.add(check_pop3);
	

		textField_pop3 = new JTextField();
		textField_pop3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop3.setColumns(10);
		textField_pop3.setBounds(12, 149, 109, 22);
		panel_5.add(textField_pop3);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		lblNewLabel_2.setBounds(0, 0,80, 189);
		panel_5.add(lblNewLabel_2);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(420, 65,80, 189);
		panel.add(panel_6);

		JLabel lblPompe4 = new JLabel("Pompe4");
		lblPompe4.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblPompe4.setBounds(37, 32,56, 16);
		panel_6.add(lblPompe4);

		JCheckBox check_pop4 = new JCheckBox("");
		check_pop4.setBounds(50, 80, 25, 22);
		panel_6.add(check_pop4);
	

		textField_pop4 = new JTextField();
		textField_pop4.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop4.setColumns(10);
		textField_pop4.setBounds(12, 149, 109, 22);
		panel_6.add(textField_pop4);

		JLabel icon_pop3 = new JLabel("New label");
		icon_pop3.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop3.setBounds(0, 0,80, 189);
		panel_6.add(icon_pop3);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(515, 65,80, 189);
		panel.add(panel_7);

		JLabel lblPompe5 = new JLabel("Pompe5");
		lblPompe5.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblPompe5.setBounds(37, 32,56, 16);
		panel_7.add(lblPompe5);

		JCheckBox check_pop5 = new JCheckBox("");
		check_pop5.setBounds(50, 80, 25, 22);
		panel_7.add(check_pop5);
	

		textField_pop5 = new JTextField();
		textField_pop5.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop5.setColumns(10);
		textField_pop5.setBounds(12, 149, 109, 22);
		panel_7.add(textField_pop5);

		JLabel icon_pop5 = new JLabel("New label");
		icon_pop5.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop5.setBounds(0, 0,80, 189);
		panel_7.add(icon_pop5);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBounds(620, 65,80, 189);
		panel.add(panel_8);

		JLabel lblPompe6 = new JLabel("Pompe6");
		lblPompe6.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblPompe6.setBounds(37, 31,56, 16);
		panel_8.add(lblPompe6);

		JCheckBox check_pop6 = new JCheckBox("");
		check_pop6.setBounds(50, 80, 25, 22);
		panel_8.add(check_pop6);
	

		textField_pop6 = new JTextField();
		textField_pop6.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop6.setColumns(10);
		textField_pop6.setBounds(12, 149, 109, 22);
		panel_8.add(textField_pop6);

		JLabel icon_pop6 = new JLabel("New label");
		icon_pop6.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop6.setBounds(0, 0,80, 189);
		panel_8.add(icon_pop6);

		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBounds(720, 65,80, 189);
		panel.add(panel_9);

		JLabel lblPompe7 = new JLabel("Pompe7");
		lblPompe7.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblPompe7.setBounds(37, 29,56, 16);
		panel_9.add(lblPompe7);

		JCheckBox check_pop7 = new JCheckBox("");
		check_pop7.setBounds(50, 80, 25, 22);
		panel_9.add(check_pop7);
	

		textField_pop7 = new JTextField();
		textField_pop7.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop7.setColumns(10);
		textField_pop7.setBounds(12, 149, 109, 22);
		panel_9.add(textField_pop7);

		JLabel icon_pop7 = new JLabel("New label");
		icon_pop7.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop7.setBounds(0, 0,80, 189);
		panel_9.add(icon_pop7);

		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBounds(820, 65,80, 189);
		panel.add(panel_10);

		JLabel lblPompe8 = new JLabel("Pompe8");
		lblPompe8.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblPompe8.setBounds(37, 29,56, 16);
		panel_10.add(lblPompe8);

		JCheckBox check_pop8 = new JCheckBox("");
		check_pop8.setBounds(50, 80, 25, 22);
		panel_10.add(check_pop8);
	

		textField_pop8 = new JTextField();
		textField_pop8.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop8.setColumns(10);
		textField_pop8.setBounds(12, 149, 109, 22);
		panel_10.add(textField_pop8);

		JLabel icon_pop8 = new JLabel("New label");
		icon_pop8.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop8.setBounds(0, 0,80, 189);
		panel_10.add(icon_pop8);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setBounds(920, 65,80, 189);
		panel.add(panel_11);

		JLabel lblPompe9 = new JLabel("Pompe9");
		lblPompe9.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblPompe9.setBounds(37, 29,56, 16);
		panel_11.add(lblPompe9);

		JCheckBox check_pop9 = new JCheckBox("");
		check_pop9.setBounds(50, 80, 25, 22);
		panel_11.add(check_pop9);


		textField_pop9 = new JTextField();
		textField_pop9.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop9.setColumns(10);
		textField_pop9.setBounds(12, 149, 109, 22);
		panel_11.add(textField_pop9);

		JLabel icon_pop9 = new JLabel("New label");
		icon_pop9.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop9.setBounds(0, 0,80, 189);
		panel_11.add(icon_pop9);

		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_12.setBounds(1010, 65,80, 189);
		panel.add(panel_12);

		JLabel lblPompe10 = new JLabel("Pompe10");
		lblPompe10.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		lblPompe10.setBounds(37, 29,56, 16);
		panel_12.add(lblPompe10);

		JCheckBox check_pop10 = new JCheckBox("");
		check_pop10.setBounds(50, 80, 25, 22);
		panel_12.add(check_pop10);


		textField_pop10 = new JTextField();
		textField_pop10.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop10.setColumns(10);
		textField_pop10.setBounds(12, 149, 109, 22);
		panel_12.add(textField_pop10);

		JLabel icon_pop10 = new JLabel("New label");
		icon_pop10.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop10.setBounds(0, 0,80, 189);
		panel_12.add(icon_pop10);

		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_13.setBounds(1100, 65,80, 189);
		panel.add(panel_13);

		JLabel lblPompe11 = new JLabel("Pompe11");
		lblPompe11.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		lblPompe11.setBounds(37, 29,56, 16);
		panel_13.add(lblPompe11);

		JCheckBox check_pop11 = new JCheckBox("");
		check_pop11.setBounds(50, 80, 25, 25);
		panel_13.add(check_pop11);


		textField_pop11 = new JTextField();
		textField_pop11.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pop11.setColumns(10);
		textField_pop11.setBounds(12, 149, 109, 22);
		panel_13.add(textField_pop11);

		JLabel icon_pop_11 = new JLabel("New label");
		icon_pop_11.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_pop_11.setBounds(0, 0,80, 189);
		panel_13.add(icon_pop_11);

		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_14.setBounds(1110, 65,80, 189);
		panel.add(panel_14);

		JLabel lblPompe12 = new JLabel("Pompe12");
		lblPompe12.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblPompe12.setBounds(37, 29,56, 16);
		panel_14.add(lblPompe12);

		JCheckBox checkBox_pop12 = new JCheckBox("");
		checkBox_pop12.setBounds(50, 80, 25, 25);
		panel_14.add(checkBox_pop12);

		textField_pompe12 = new JTextField();
		textField_pompe12.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_pompe12.setColumns(10);
		textField_pompe12.setBounds(12, 149, 109, 22);
		panel_14.add(textField_pompe12);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		lblNewLabel_3.setBounds(0, 0,80, 189);
		panel_14.add(lblNewLabel_3);

		JLabel label_icon_fakher = new JLabel("");
		label_icon_fakher.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		label_icon_fakher.setBounds(0, 0, 1024, 306);
		panel.add(label_icon_fakher);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		lblNewLabel_1.setBounds(873, 0, 1024, 281);
		panel.add(lblNewLabel_1);


		JPanel panel_15 = new JPanel();
		panel_15.setBounds(5, 360, 1488, 97);
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		getContentPane().add(panel_15);
		panel_15.setLayout(null);

		JLabel lbl_rechercher = new JLabel("Rechercher");
		lbl_rechercher.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lbl_rechercher.setBounds(12, 13, 103, 25);
		panel_15.add(lbl_rechercher);

		JLabel lbl_date_deb = new JLabel("Date d\u00E9but :");
		lbl_date_deb.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lbl_date_deb.setBounds(24, 51, 85, 38);
		panel_15.add(lbl_date_deb);

		JLabel lblDateFin = new JLabel("Date fin:");
		lblDateFin.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblDateFin.setBounds(294, 51, 92, 38);
		panel_15.add(lblDateFin);

		JLabel lbl_h_deb = new JLabel("Heure de d\u00E9but :");
		lbl_h_deb.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lbl_h_deb.setBounds(588, 51, 113, 27);
		panel_15.add(lbl_h_deb);



		/* -----------------------------------------------------Search by time*/
		
		
		txtHhmm= new JTextField();
		txtHhmm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ConnextionFTP con = new ConnextionFTP();
				//List<String> listpompes = new ArrayList<String>();
				double total=0;
				/*listpompes.add("PMP:1");
				listpompes.add("PMP:2");
				listpompes.add("PMP:3");
				listpompes.add("PMP:4");
				listpompes.add("PMP:5");
				listpompes.add("PMP:6");
				listpompes.add("PMP:7");
				listpompes.add("PMP:8");
				listpompes.add("PMP:9");
				listpompes.add("PMP:10");
				listpompes.add("PMP:11");
				listpompes.add("PMP:12");
*/
				try {

					listjournal=new ArrayList<Transaction>();

				//	//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
					   total=0; 
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								 
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							 
						}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				TableModel model = new TransactionTableModel(listjournal);
				table_result_pompe.setModel(model);


			}
		});
		txtHhmm.setText("00:00\r\n");
		txtHhmm.setBounds(705, 51, 71, 22);
		panel_15.add(txtHhmm);
		txtHhmm.setColumns(10);



		JLabel lblHeureDeFin = new JLabel("Heure de fin :");
		lblHeureDeFin.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblHeureDeFin.setBounds(750, 51, 92, 27);
		panel_15.add(lblHeureDeFin);

		txt_h_fin = new JTextField();
		txt_h_fin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConnextionFTP con = new ConnextionFTP();
				//List<String> listpompes = new ArrayList<String>();
				/*listpompes.add("PMP:1");
				listpompes.add("PMP:2");
				listpompes.add("PMP:3");
				listpompes.add("PMP:4");
				listpompes.add("PMP:5");
				listpompes.add("PMP:6");
				listpompes.add("PMP:7");
				listpompes.add("PMP:8");
				listpompes.add("PMP:9");
				listpompes.add("PMP:10");
				listpompes.add("PMP:11");
				listpompes.add("PMP:12");
*/
				try {

					listjournal=new ArrayList<Transaction>();

				//	//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
                     double total=0; 
					if (listjournal != null) {
						for (Transaction t:listjournal) {
							total =total+t.getMontant(); 	
							 
						}
						DecimalFormat df = new DecimalFormat("#,###.000");
						textField_totale.setText(df.format(total));
						 
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				TableModel model = new TransactionTableModel(listjournal);
				table_result_pompe.setModel(model);

			}
		});
		txt_h_fin.setText("23:59\r\n");
		txt_h_fin.setColumns(10);
		txt_h_fin.setBounds(918, 51, 71, 22);
		panel_15.add(txt_h_fin);

		/* END Search by time----------------------------------------------------------------- */




		/*----------------- CHOICE BUTTON----------*/

		Label label_pop_rech = new Label("Pompe");
		label_pop_rech.setBounds(1033, 51, 55, 24);
		//panel_15.add(label_pop_rech);

		Choice choice_pop = new Choice();

		choice_pop.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		choice_pop.add("Tout");
		choice_pop.add("Pompe1");
		choice_pop.add("Pompe2");
		choice_pop.add("Pompe3");
		choice_pop.add("Pompe4");
		choice_pop.add("Pompe5");
		choice_pop.add("Pompe6");
		choice_pop.add("Pompe7");
		choice_pop.add("Pompe8");
		choice_pop.add("Pompe9");
		choice_pop.add("Pompe10");
		choice_pop.add("Pompe11");
		choice_pop.add("Pompe12");
		choice_pop.setBounds(950, 51, 127, 22);
		panel_15.add(choice_pop);


		/*--------------------------------------END CHOICE BUTTON -----------------*/




		/*-------------------------TOTALE ---------------*/
		JLabel lbl_totale = new JLabel("Totale");
		lbl_totale.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lbl_totale.setBounds(1110, 24, 92, 65);
		panel_15.add(lbl_totale);

		textField_totale = new JTextField();
		textField_totale.setFont(new Font("Times New Roman", Font.BOLD, 17));
		textField_totale.setBounds(1100, 29, 116, 60);
		panel_15.add(textField_totale);
		textField_totale.setColumns(10);
		/*----------------------------END TOTALE ----------------------*/



		/*---*************************************************************----DATE PICKER--*/

		JDateChooser date_deb = new JDateChooser();
		date_deb.setBounds(114, 56, 127, 22);
		panel_15.add(date_deb);
		Date date = date_deb.getDate();
		String strDate = DateFormat.getDateInstance().format(date);


		JDateChooser date_fin = new JDateChooser();
		date_fin.setBounds(352, 56, 127, 22);
		panel_15.add(date_fin);

		JLabel recherch_icon = new JLabel("New label");
		recherch_icon.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		recherch_icon.setBounds(0, 0, 784, 107);
		panel_15.add(recherch_icon);

		JLabel icon_reche2 = new JLabel("");
		icon_reche2.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_reche2.setBounds(777, 0, 711, 107);
		panel_15.add(icon_reche2);

		/*--********************----END DATE PICKER ************************************--*/



		JPanel panel_16 = new JPanel();
		panel_16.setBounds(10, 470, 1310, 200);
		panel_16.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		getContentPane().add(panel_16);
		panel_16.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 50, 1300, 120);
		panel_16.add(scrollPane);

		table_result_pompe = new JTable();
		scrollPane.setViewportView(table_result_pompe);
		listjournal=new ArrayList<Transaction>();

		Transaction t=new Transaction();		 
		listjournal.add(t);

		TableModel model = new TransactionTableModel(listjournal);

		table_result_pompe .setModel(model);


/*--------------------------------------Recherche montant ---------*/
		recherche_montant = new JTextField();
		recherche_montant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ok");
				System.out.println("***"+recherche_montant.getText());
				ConnextionFTP con = new ConnextionFTP();
				//con.connexionFTP3(new Date());

				double total=0;
				
				//List<String> listpompes = new ArrayList<String>();
				listpompes.add("PMP:1");
				listpompes.add("PMP:2");
				listpompes.add("PMP:3");
				listpompes.add("PMP:4");
				listpompes.add("PMP:5");
				listpompes.add("PMP:6");
				listpompes.add("PMP:7");
				listpompes.add("PMP:8");
				listpompes.add("PMP:9");
				listpompes.add("PMP:10");
				listpompes.add("PMP:11");
				listpompes.add("PMP:12");

				try {
					listjournal=new ArrayList<Transaction>();

	 

					//List<Transaction>l = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
					

					/*for (Transaction t:l) {
						if (t.getMontant() == Double.parseDouble(recherche_montant.getText())) {
							listjournal.add(t);
						}
						
					}*/
					   total=0; 
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								 
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							 
						}
					DecimalFormat df = new DecimalFormat("#,###.000");
					textField_totale.setText(df.format(total));




				} catch (Exception e1) {
					e1.printStackTrace();
				}

				TableModel model = new TransactionTableModel(listjournal);
				table_result_pompe.setModel(model);

				
				
			}
		});
		recherche_montant.setBounds(1661, 15, 116, 22);
		panel_16.add(recherche_montant);
		recherche_montant.setColumns(10);
/*-------------------------------------------------------END Recherche ---------------*/
		
		
		
		

		/*----------------------------------LOAD DATA TO TABLE --------------*/
		JButton loadData = new JButton("Load Data");
		loadData.setBounds(22, 12, 139, 25);
		loadData.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		panel_16.add(loadData);
		
		JButton btnAlerte = new JButton("Alerte");
		btnAlerte.setBounds(41, 42, 97, 25);
		getContentPane().add(btnAlerte);
		btnAlerte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Paramettre c = new Paramettre();
			 
				c.setVisible(true);
			}
		});
		
		JLabel icon_fakher_icon = new JLabel("");
		icon_fakher_icon.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		icon_fakher_icon.setBounds(0, 0, 1024, 747);
		getContentPane().add(icon_fakher_icon);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		lblNewLabel.setBounds(1021, 0, 903, 747);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		lblNewLabel_4.setBounds(0, 746, 985, 309);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\sqlite\\Shell_App\\shell_test.jpg"));
		lblNewLabel_5.setBounds(971, 727, 953, 328);
		getContentPane().add(lblNewLabel_5);
		
		loadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				

				ConnextionFTP con = new ConnextionFTP();
				double total=0;
				listpompes.add("PMP:1");
				listpompes.add("PMP:2");
				listpompes.add("PMP:3");
				listpompes.add("PMP:4");
				listpompes.add("PMP:5");
				listpompes.add("PMP:6");
				listpompes.add("PMP:7");
				listpompes.add("PMP:8");
				listpompes.add("PMP:9");
				listpompes.add("PMP:10");
				listpompes.add("PMP:11");
				listpompes.add("PMP:12");

				try {
					listjournal=new ArrayList<Transaction>();

					////listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);


					for (Transaction t:listjournal) {
						total =total+t.getMontant(); 	
					}

					DecimalFormat df = new DecimalFormat("#,###.000");
					textField_totale.setText(df.format(total));




				} catch (Exception e1) {
					e1.printStackTrace();
				}

				TableModel model = new TransactionTableModel(listjournal);
				table_result_pompe.setModel(model);



			}
		});

		/*-------------------------------------------END LOAD DATA FROM TABEL---------------------------*/


		
		







		/*-----------------------------------------------CHEK POMPE COLOR --------------------*/
		

		
		/*-------------------------POMP2------------------*/
		check_pop2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe2.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					//con.connexionFTP3(new Date());
					//List<String> listpompes = new ArrayList<String>();
					listpompes.add("PMP:2");
					double total=0;
					double totalP2=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:2"))
									totalP2=totalP2+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop2.setText(df.format(totalP2));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				}
				else {

					lblPompe2.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:2"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP2=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:2"))
									totalP2=totalP2+t.getMontant();
							}
							
							
						}
                       
						textField_totale.setText(df.format(total));
						textField_pop2.setText(df.format(totalP2));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
					
				}
			}
		});
/*----------------------------------------------------POMP2------------------*/
		
		
	/*-----------------------------------------------------------------POP3----------------------*/	
		check_pop3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == 1) {

					lblPompe_3.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:3");
					double total=0;
					double totalP3=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:3"))
									totalP3=totalP3+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop3.setText(df.format(totalP3));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				} 
				else {
					lblPompe_3.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:3"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP3=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:3"))
									totalP3=totalP3+t.getMontant();
							}
							
							
						}
                      
						textField_totale.setText(df.format(total));
						textField_pop3.setText(df.format(totalP3));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
					
				}
			}
		});

/*-------------------------------------------------END POP3---------------------------------------*/
		
	
/*--------------------------------------------------POP4----------------------*/		
		check_pop4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe4.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:4");
					double total=0;
					double totalP4=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:4"))
									totalP4=totalP4+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop4.setText(df.format(totalP4));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
					
				} 
				else {

					lblPompe4.setForeground(Color.GREEN);					
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:4"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP4=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:4"))
									totalP4=totalP4+t.getMontant();
							}
							
							
						}
                     
						textField_totale.setText(df.format(total));
						textField_pop4.setText(df.format(totalP4));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);


					
					
				}
			}
		});
/*-------------------------------------------------------- END POP4---------------*/

	
/*------------------------------------- POP5------------------------------------*/		
		check_pop5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe5.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:5");
					double total=0;
					double totalP5=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:5"))
									totalP5=totalP5+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop5.setText(df.format(totalP5));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				}
				else {

					lblPompe5.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:5"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP5=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:5"))
									totalP5=totalP5+t.getMontant();
							}
							
							
						}
                    
						textField_totale.setText(df.format(total));
						textField_pop5.setText(df.format(totalP5));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				}
			}
		});

/*--------------------------------------------------- end pop5-------------------------------*/

	/*--------------------------- pop 6 -------------------*/
		check_pop6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe6.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:6");
					double total=0;
					double totalP6=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:6"))
									totalP6=totalP6+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop6.setText(df.format(totalP6));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				} 
				else {

					lblPompe6.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:6"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP6=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:6"))
									totalP6=totalP6+t.getMontant();
							}
							
							
						}
                   
						textField_totale.setText(df.format(total));
						textField_pop6.setText(df.format(totalP6));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				}
			}
		});

/*-----------------------------------------------------------END POP 6 ---------------*/
		
	/*------------------------------------POP7----------------------------*/
		check_pop7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe7.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:7");
					double total=0;
					double totalP7=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:7"))
									totalP7=totalP7+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop7.setText(df.format(totalP7));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
					
				} 
				else {

					lblPompe7.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:7"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP7=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:7"))
									totalP7=totalP7+t.getMontant();
							}
							
							
						}
                  
						textField_totale.setText(df.format(total));
						textField_pop7.setText(df.format(totalP7));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				}
			}
		});
/*---------------------------------------- END POP7-----------------------------*/

		
/*--------------------------POP8---------------------------------*/		
		check_pop8.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe8.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:8");
					double total=0;
					double totalP8=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:8"))
									totalP8=totalP8+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop8.setText(df.format(totalP8));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				} 
				else {

					lblPompe8.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:8"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP8=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:8"))
									totalP8=totalP8+t.getMontant();
							}
							
							
						}
                 
						textField_totale.setText(df.format(total));
						textField_pop8.setText(df.format(totalP8));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				}
			}
		});

/*----------------------------------- END POP8-------------------------*/
		
		
/*--------------------------------------------------------- POP 9--------------------*/		
		check_pop9.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe9.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:9");
					double total=0;
					double totalP9=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:9"))
									totalP9=totalP9+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop9.setText(df.format(totalP9));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				} 
				else {

					lblPompe9.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:9"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP9=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:9"))
									totalP9=totalP9+t.getMontant();
							}
							
							
						}
                
						textField_totale.setText(df.format(total));
						textField_pop9.setText(df.format(totalP9));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				}
			}
		});

/*------------------------------------------------ END POP9 ------------------*/

		
		/*----------------------------------------------- pop 10-----------------------*/
		check_pop10.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe10.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:10");
					double total=0;
					double totalP10=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:10"))
									totalP10=totalP10+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop10.setText(df.format(totalP10));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
				} 
				else {

					lblPompe10.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:10"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP10=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:10"))
									totalP10=totalP10+t.getMontant();
							}

						}
               
						textField_totale.setText(df.format(total));
						textField_pop10.setText(df.format(totalP10));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
	
				}
			}
		});

/*----------------------------------------------------------------- END POP 10 ------------------*/
		
		
/*----------------------------------------- POP 11 ---------------------*/		
		check_pop11.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe11.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:11");
					double total=0;
					double totalP11=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:11"))
									totalP11=totalP11+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pop11.setText(df.format(totalP11));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
					
				} 
				else {

					lblPompe11.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:11"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP11=0;
					try {
						listjournal=new ArrayList<Transaction>();

						////listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:11"))
									totalP11=totalP11+t.getMontant();
							}

						}
              
						textField_totale.setText(df.format(total));
						textField_pop11.setText(df.format(totalP11));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
					
					
					
				}
			}
		});

/*------------------------------------------------------------------------- END POP11------------------*/

		
/*------------------------------------------------------------------ POP12---------------------------*/		
		checkBox_pop12.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {

					lblPompe12.setForeground(Color.RED);
					ConnextionFTP con = new ConnextionFTP();
					listpompes.add("PMP:12");
					double total=0;
					double totalP12=0;
					try {
						listjournal=new ArrayList<Transaction>();

					//	//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);

						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 
								if(t.getPompe().equals("PMP:12"))
									totalP12=totalP12+t.getMontant();
							}
							DecimalFormat df = new DecimalFormat("#,###.000");
							textField_totale.setText(df.format(total));
							textField_pompe12.setText(df.format(totalP12));
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
					
				} 
				else {

					lblPompe12.setForeground(Color.GREEN);
					ConnextionFTP con = new ConnextionFTP();
					 int i=0;
					 for(String s:listpompes) {
						 if(s.equals("PMP:12"))
							 break;
						 else i=i+1;
					 }
					listpompes.remove(i);
					 System.out.println(listpompes);
				     double total=0;
					double totalP12=0;
					try {
						listjournal=new ArrayList<Transaction>();

						//listjournal = con.selectAlljournal(new Date(), txtHhmm.getText(), txt_h_fin.getText(), listpompes);
						System.out.println(listjournal);
						DecimalFormat df = new DecimalFormat("#,###.000");
						if (listjournal != null) {
							for (Transaction t:listjournal) {
								total =total+t.getMontant(); 	
								if(t.getPompe().equals("PMP:12"))
									totalP12=totalP12+t.getMontant();
							}

						}
             
						textField_totale.setText(df.format(total));
						textField_pompe12.setText(df.format(totalP12));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					TableModel model = new TransactionTableModel(listjournal);
					table_result_pompe.setModel(model);
					
				
				}
			}
		});
/*---------------------------------------------------------------------------------------------END POP12-----*/

		/*--------------------------------------------END PANEL POMPE COLOR -------------------------------*/	


		
		



	}


	@Override
	public void actionPerformed(ActionEvent e) {
	
	}


	 
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				CheckBoxInTable frame = new CheckBoxInTable();
				frame.setSize(width, height);
				
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				//frame.pack();
				
				
				
				//frame.setLocation(150, 150);
				frame.setVisible(true);
				
			}
		});
	}
}