/*      */ package shell.sijoumi.etatcuve;
/*     */ 
/*      */ import com.toedter.calendar.JDateChooser;
/*      */ import java.awt.Choice;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Font;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.Label;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.text.DateFormat;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.Timer;
/*      */ import javax.swing.border.LineBorder;
/*      */ import javax.swing.table.TableModel;
/*      */ 
/*      */ public class CheckBoxInTable
/*      */   extends JFrame
/*      */   implements ActionListener {
/*      */   Timer timer;
/*      */   private static final long serialVersionUID = 1L;
/*      */   private JTable table;
/*   44 */   private JTextField textField_pop1 = new JTextField();
/*      */   
/*      */   private JTextField textField_pop2;
/*      */   
/*      */   private JTextField textField_pop3;
/*      */   
/*      */   private JTextField textField_pop4;
/*      */   
/*      */   private JTextField textField_pop5;
/*      */   private JTextField textField_pop6;
/*      */   private JTextField textField_pop7;
/*      */   private JTextField textField_pop8;
/*      */   private JTextField textField_pop9;
/*      */   private JTextField textField_pop10;
/*      */   private JTextField textField_pop11;
/*      */   private JTextField textField_pop12;
/*      */   private JTextField txtHhmm;
/*      */   private JTextField txt_h_fin;
/*      */   private JTextField textField_totale;
/*      */   private JTable table_result_pompe;
/*      */   private JCheckBox check_pop1;
/*      */   private JLabel lblPompe12;
/*      */   private JTextField totale;
/*      */   private static int width;
/*      */   private static int height;
/*      */   private String t1;
/*      */   private String t2;
/*      */   private JDateChooser date_deb;
/*      */   private JDateChooser date_fin;
/*   73 */   Connection connection = null;
/*   74 */   ResultSet rs = null;
/*   75 */   PreparedStatement pst = null;
/*      */   
/*      */   private List<Transaction> listjournal;
/*      */   
/*      */   private JTextField textField_pompe12;
/*      */   
/*      */   private JTextField textField_recherch_mont;
/*      */   private JTextField Search_montant;
/*      */   private JTextField recherche_montant;
/*   84 */   private List<String> listpompes = new ArrayList<>();
/*      */ 
/*      */ 
/*      */   
/*      */   public CheckBoxInTable() {
/*   89 */     getContentPane().setBackground(new Color(192, 192, 192));
/*      */ 
/*      */     
/*   92 */     GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
/*   93 */     width = gd.getDisplayMode().getWidth();
/*   94 */     height = gd.getDisplayMode().getHeight();
/*      */     
/*   96 */     getContentPane().setSize(width - 20, height);
/*   97 */     getContentPane().setLayout((LayoutManager)null);
/*      */     
/*   99 */     JPanel panel = new JPanel();
/*  100 */     panel.setBounds(21, 80, width, 267);
/*  101 */     panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
/*  102 */     getContentPane().add(panel);
/*  103 */     panel.setLayout((LayoutManager)null);
/*      */     
/*  105 */     JPanel panel_1 = new JPanel();
/*  106 */     panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  107 */     panel_1.setBounds(12, 65, 116, 189);
/*  108 */     panel.add(panel_1);
/*  109 */     panel_1.setLayout((LayoutManager)null);
/*      */     
/*  111 */     JButton btn_validation = new JButton("Validation");
/*  112 */     btn_validation.setFont(new Font("Times New Roman", 2, 10));
/*  113 */     btn_validation.setBounds(12, 50, 80, 25);
/*  114 */     panel_1.add(btn_validation);
/*      */     
/*  116 */     JButton btn_montant = new JButton("Montant");
/*  117 */     btn_montant.setFont(new Font("Times New Roman", 2, 10));
/*  118 */     btn_montant.setBounds(12, 148, 80, 25);
/*  119 */     panel_1.add(btn_montant);
/*      */     
/*  121 */     JLabel icon_btn = new JLabel("New label");
/*  122 */     icon_btn.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  123 */     icon_btn.setBounds(0, 0, 116, 189);
/*  124 */     panel_1.add(icon_btn);
/*      */     
/*  126 */     JPanel panel_2 = new JPanel();
/*  127 */     panel_2.setBorder(new LineBorder(Color.BLACK, 1, true));
/*  128 */     panel_2.setBounds(110, 65, 80, 189);
/*  129 */     panel.add(panel_2);
/*  130 */     panel_2.setLayout((LayoutManager)null);
/*      */     
/*  132 */     final JLabel lbl_pop1 = new JLabel("Pompe1");
/*  133 */     lbl_pop1.setFont(new Font("Times New Roman", 2, 12));
/*  134 */     lbl_pop1.setBounds(26, 35, 56, 16);
/*      */     
/*  136 */     panel_2.add(lbl_pop1);
/*      */ 
/*      */     
/*  139 */     JCheckBox check_pop1 = new JCheckBox("");
/*  140 */     check_pop1.setBounds(51, 80, 25, 22);
/*  141 */     panel_2.add(check_pop1);
/*      */ 
/*      */ 
/*      */     
/*  145 */     check_pop1.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/*  147 */             if (e.getStateChange() == 1) {
/*      */               
/*  149 */               lbl_pop1.setForeground(Color.RED);
/*  150 */               ConnextionFTP con = new ConnextionFTP();
/*  151 */               con.connexionFTP3(new Date());
/*      */ 
/*      */               
/*  154 */               CheckBoxInTable.this.listpompes.add("PMP:1");
/*      */ 
/*      */               
/*  157 */               double total = 0.0D;
/*  158 */               double totalP1 = 0.0D;
/*      */               try {
/*  160 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/*  162 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/*  164 */                 if (CheckBoxInTable.this.listjournal != null) {
/*  165 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/*  166 */                     total += t.getMontant();
/*  167 */                     if (t.getPompe().equals("PMP:1"))
/*  168 */                       totalP1 += t.getMontant(); 
/*      */                   } 
/*  170 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/*  171 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/*  172 */                   CheckBoxInTable.this.textField_pop1.setText(df.format(totalP1));
/*      */                 }
/*      */               
/*      */               }
/*  176 */               catch (ClassNotFoundException e1) {
/*      */                 
/*  178 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/*  181 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/*  182 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*  183 */               CheckBoxInTable.this.table_result_pompe.setAutoCreateRowSorter(true);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  188 */               lbl_pop1.setForeground(Color.GREEN);
/*      */               
/*  190 */               ConnextionFTP con = new ConnextionFTP();
/*  191 */               int i = 0;
/*  192 */               for (String s : CheckBoxInTable.this.listpompes) {
/*  193 */                 if (s.equals("PMP:1"))
/*      */                   break; 
/*  195 */                 i++;
/*      */               } 
/*  197 */               CheckBoxInTable.this.listpompes.remove(i);
/*  198 */               System.out.println(CheckBoxInTable.this.listpompes);
/*  199 */               double total = 0.0D;
/*  200 */               double totalP1 = 0.0D;
/*      */               try {
/*  202 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/*  204 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*  205 */                 System.out.println(CheckBoxInTable.this.listjournal);
/*  206 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/*  207 */                 if (CheckBoxInTable.this.listjournal != null) {
/*  208 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/*  209 */                     total += t.getMontant();
/*  210 */                     if (t.getPompe().equals("PMP:1")) {
/*  211 */                       totalP1 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */                 
/*  216 */                 System.out.println("tolap1" + totalP1);
/*  217 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/*  218 */                 CheckBoxInTable.this.textField_pop1.setText(df.format(totalP1));
/*  219 */               } catch (ClassNotFoundException e1) {
/*      */                 
/*  221 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/*  224 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/*  225 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  234 */     this.textField_pop1 = new JTextField();
/*  235 */     this.textField_pop1.setFont(new Font("Times New Roman", 1, 17));
/*  236 */     this.textField_pop1.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent arg0) {}
/*      */         });
/*      */     
/*  241 */     this.textField_pop1.setBounds(12, 149, 104, 22);
/*  242 */     panel_2.add(this.textField_pop1);
/*  243 */     this.textField_pop1.setColumns(10);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  248 */     JLabel icon_pop1 = new JLabel("New label");
/*  249 */     icon_pop1.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  250 */     icon_pop1.setBounds(0, 0, 80, 189);
/*  251 */     panel_2.add(icon_pop1);
/*      */     
/*  253 */     JPanel panel_3 = new JPanel();
/*  254 */     panel_3.setBounds(140, 13, width - 210, 34);
/*  255 */     panel.add(panel_3);
/*  256 */     panel_3.setLayout((LayoutManager)null);
/*      */     
/*  258 */     JLabel lblListeDesPompes = new JLabel("Liste des Pompes ");
/*  259 */     lblListeDesPompes.setBounds(803, 0, 307, 34);
/*  260 */     panel_3.add(lblListeDesPompes);
/*      */     
/*  262 */     JPanel panel_4 = new JPanel();
/*  263 */     panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  264 */     panel_4.setBounds(210, 65, 80, 189);
/*  265 */     panel.add(panel_4);
/*  266 */     panel_4.setLayout((LayoutManager)null);
/*      */     
/*  268 */     final JLabel lblPompe2 = new JLabel("Pompe2");
/*  269 */     lblPompe2.setFont(new Font("Times New Roman", 2, 12));
/*  270 */     lblPompe2.setBounds(39, 32, 56, 16);
/*  271 */     panel_4.add(lblPompe2);
/*      */     
/*  273 */     JCheckBox check_pop2 = new JCheckBox("");
/*  274 */     check_pop2.setBounds(45, 80, 25, 25);
/*  275 */     panel_4.add(check_pop2);
/*      */ 
/*      */     
/*  278 */     this.textField_pop2 = new JTextField();
/*  279 */     this.textField_pop2.setFont(new Font("Times New Roman", 1, 17));
/*  280 */     this.textField_pop2.setBounds(12, 149, 105, 22);
/*  281 */     panel_4.add(this.textField_pop2);
/*  282 */     this.textField_pop2.setColumns(10);
/*      */     
/*  284 */     JLabel icon_pop2 = new JLabel("New label");
/*  285 */     icon_pop2.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  286 */     icon_pop2.setBounds(0, 0, 129, 189);
/*  287 */     panel_4.add(icon_pop2);
/*      */     
/*  289 */     JPanel panel_5 = new JPanel();
/*  290 */     panel_5.setLayout((LayoutManager)null);
/*  291 */     panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  292 */     panel_5.setBounds(320, 65, 80, 189);
/*  293 */     panel.add(panel_5);
/*      */     
/*  295 */     final JLabel lblPompe_3 = new JLabel("Pompe3");
/*  296 */     lblPompe_3.setFont(new Font("Times New Roman", 2, 12));
/*  297 */     lblPompe_3.setBounds(37, 32, 56, 16);
/*  298 */     panel_5.add(lblPompe_3);
/*      */     
/*  300 */     JCheckBox check_pop3 = new JCheckBox("");
/*  301 */     check_pop3.setBounds(50, 80, 25, 22);
/*  302 */     panel_5.add(check_pop3);
/*      */ 
/*      */     
/*  305 */     this.textField_pop3 = new JTextField();
/*  306 */     this.textField_pop3.setFont(new Font("Times New Roman", 1, 17));
/*  307 */     this.textField_pop3.setColumns(10);
/*  308 */     this.textField_pop3.setBounds(12, 149, 109, 22);
/*  309 */     panel_5.add(this.textField_pop3);
/*      */     
/*  311 */     JLabel lblNewLabel_2 = new JLabel("New label");
/*  312 */     lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  313 */     lblNewLabel_2.setBounds(0, 0, 80, 189);
/*  314 */     panel_5.add(lblNewLabel_2);
/*      */     
/*  316 */     JPanel panel_6 = new JPanel();
/*  317 */     panel_6.setLayout((LayoutManager)null);
/*  318 */     panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  319 */     panel_6.setBounds(420, 65, 80, 189);
/*  320 */     panel.add(panel_6);
/*      */     
/*  322 */     final JLabel lblPompe4 = new JLabel("Pompe4");
/*  323 */     lblPompe4.setFont(new Font("Times New Roman", 2, 12));
/*  324 */     lblPompe4.setBounds(37, 32, 56, 16);
/*  325 */     panel_6.add(lblPompe4);
/*      */     
/*  327 */     JCheckBox check_pop4 = new JCheckBox("");
/*  328 */     check_pop4.setBounds(50, 80, 25, 22);
/*  329 */     panel_6.add(check_pop4);
/*      */ 
/*      */     
/*  332 */     this.textField_pop4 = new JTextField();
/*  333 */     this.textField_pop4.setFont(new Font("Times New Roman", 1, 17));
/*  334 */     this.textField_pop4.setColumns(10);
/*  335 */     this.textField_pop4.setBounds(12, 149, 109, 22);
/*  336 */     panel_6.add(this.textField_pop4);
/*      */     
/*  338 */     JLabel icon_pop3 = new JLabel("New label");
/*  339 */     icon_pop3.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  340 */     icon_pop3.setBounds(0, 0, 80, 189);
/*  341 */     panel_6.add(icon_pop3);
/*      */     
/*  343 */     JPanel panel_7 = new JPanel();
/*  344 */     panel_7.setLayout((LayoutManager)null);
/*  345 */     panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  346 */     panel_7.setBounds(515, 65, 80, 189);
/*  347 */     panel.add(panel_7);
/*      */     
/*  349 */     final JLabel lblPompe5 = new JLabel("Pompe5");
/*  350 */     lblPompe5.setFont(new Font("Times New Roman", 2, 12));
/*  351 */     lblPompe5.setBounds(37, 32, 56, 16);
/*  352 */     panel_7.add(lblPompe5);
/*      */     
/*  354 */     JCheckBox check_pop5 = new JCheckBox("");
/*  355 */     check_pop5.setBounds(50, 80, 25, 22);
/*  356 */     panel_7.add(check_pop5);
/*      */ 
/*      */     
/*  359 */     this.textField_pop5 = new JTextField();
/*  360 */     this.textField_pop5.setFont(new Font("Times New Roman", 1, 17));
/*  361 */     this.textField_pop5.setColumns(10);
/*  362 */     this.textField_pop5.setBounds(12, 149, 109, 22);
/*  363 */     panel_7.add(this.textField_pop5);
/*      */     
/*  365 */     JLabel icon_pop5 = new JLabel("New label");
/*  366 */     icon_pop5.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  367 */     icon_pop5.setBounds(0, 0, 80, 189);
/*  368 */     panel_7.add(icon_pop5);
/*      */     
/*  370 */     JPanel panel_8 = new JPanel();
/*  371 */     panel_8.setLayout((LayoutManager)null);
/*  372 */     panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  373 */     panel_8.setBounds(620, 65, 80, 189);
/*  374 */     panel.add(panel_8);
/*      */     
/*  376 */     final JLabel lblPompe6 = new JLabel("Pompe6");
/*  377 */     lblPompe6.setFont(new Font("Times New Roman", 2, 12));
/*  378 */     lblPompe6.setBounds(37, 31, 56, 16);
/*  379 */     panel_8.add(lblPompe6);
/*      */     
/*  381 */     JCheckBox check_pop6 = new JCheckBox("");
/*  382 */     check_pop6.setBounds(50, 80, 25, 22);
/*  383 */     panel_8.add(check_pop6);
/*      */ 
/*      */     
/*  386 */     this.textField_pop6 = new JTextField();
/*  387 */     this.textField_pop6.setFont(new Font("Times New Roman", 1, 17));
/*  388 */     this.textField_pop6.setColumns(10);
/*  389 */     this.textField_pop6.setBounds(12, 149, 109, 22);
/*  390 */     panel_8.add(this.textField_pop6);
/*      */     
/*  392 */     JLabel icon_pop6 = new JLabel("New label");
/*  393 */     icon_pop6.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  394 */     icon_pop6.setBounds(0, 0, 80, 189);
/*  395 */     panel_8.add(icon_pop6);
/*      */     
/*  397 */     JPanel panel_9 = new JPanel();
/*  398 */     panel_9.setLayout((LayoutManager)null);
/*  399 */     panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  400 */     panel_9.setBounds(720, 65, 80, 189);
/*  401 */     panel.add(panel_9);
/*      */     
/*  403 */     final JLabel lblPompe7 = new JLabel("Pompe7");
/*  404 */     lblPompe7.setFont(new Font("Times New Roman", 2, 12));
/*  405 */     lblPompe7.setBounds(37, 29, 56, 16);
/*  406 */     panel_9.add(lblPompe7);
/*      */     
/*  408 */     JCheckBox check_pop7 = new JCheckBox("");
/*  409 */     check_pop7.setBounds(50, 80, 25, 22);
/*  410 */     panel_9.add(check_pop7);
/*      */ 
/*      */     
/*  413 */     this.textField_pop7 = new JTextField();
/*  414 */     this.textField_pop7.setFont(new Font("Times New Roman", 1, 17));
/*  415 */     this.textField_pop7.setColumns(10);
/*  416 */     this.textField_pop7.setBounds(12, 149, 109, 22);
/*  417 */     panel_9.add(this.textField_pop7);
/*      */     
/*  419 */     JLabel icon_pop7 = new JLabel("New label");
/*  420 */     icon_pop7.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  421 */     icon_pop7.setBounds(0, 0, 80, 189);
/*  422 */     panel_9.add(icon_pop7);
/*      */     
/*  424 */     JPanel panel_10 = new JPanel();
/*  425 */     panel_10.setLayout((LayoutManager)null);
/*  426 */     panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  427 */     panel_10.setBounds(820, 65, 80, 189);
/*  428 */     panel.add(panel_10);
/*      */     
/*  430 */     final JLabel lblPompe8 = new JLabel("Pompe8");
/*  431 */     lblPompe8.setFont(new Font("Times New Roman", 2, 12));
/*  432 */     lblPompe8.setBounds(37, 29, 56, 16);
/*  433 */     panel_10.add(lblPompe8);
/*      */     
/*  435 */     JCheckBox check_pop8 = new JCheckBox("");
/*  436 */     check_pop8.setBounds(50, 80, 25, 22);
/*  437 */     panel_10.add(check_pop8);
/*      */ 
/*      */     
/*  440 */     this.textField_pop8 = new JTextField();
/*  441 */     this.textField_pop8.setFont(new Font("Times New Roman", 1, 17));
/*  442 */     this.textField_pop8.setColumns(10);
/*  443 */     this.textField_pop8.setBounds(12, 149, 109, 22);
/*  444 */     panel_10.add(this.textField_pop8);
/*      */     
/*  446 */     JLabel icon_pop8 = new JLabel("New label");
/*  447 */     icon_pop8.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  448 */     icon_pop8.setBounds(0, 0, 80, 189);
/*  449 */     panel_10.add(icon_pop8);
/*      */     
/*  451 */     JPanel panel_11 = new JPanel();
/*  452 */     panel_11.setLayout((LayoutManager)null);
/*  453 */     panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  454 */     panel_11.setBounds(920, 65, 80, 189);
/*  455 */     panel.add(panel_11);
/*      */     
/*  457 */     final JLabel lblPompe9 = new JLabel("Pompe9");
/*  458 */     lblPompe9.setFont(new Font("Times New Roman", 2, 12));
/*  459 */     lblPompe9.setBounds(37, 29, 56, 16);
/*  460 */     panel_11.add(lblPompe9);
/*      */     
/*  462 */     JCheckBox check_pop9 = new JCheckBox("");
/*  463 */     check_pop9.setBounds(50, 80, 25, 22);
/*  464 */     panel_11.add(check_pop9);
/*      */ 
/*      */     
/*  467 */     this.textField_pop9 = new JTextField();
/*  468 */     this.textField_pop9.setFont(new Font("Times New Roman", 1, 17));
/*  469 */     this.textField_pop9.setColumns(10);
/*  470 */     this.textField_pop9.setBounds(12, 149, 109, 22);
/*  471 */     panel_11.add(this.textField_pop9);
/*      */     
/*  473 */     JLabel icon_pop9 = new JLabel("New label");
/*  474 */     icon_pop9.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  475 */     icon_pop9.setBounds(0, 0, 80, 189);
/*  476 */     panel_11.add(icon_pop9);
/*      */     
/*  478 */     JPanel panel_12 = new JPanel();
/*  479 */     panel_12.setLayout((LayoutManager)null);
/*  480 */     panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  481 */     panel_12.setBounds(1010, 65, 80, 189);
/*  482 */     panel.add(panel_12);
/*      */     
/*  484 */     final JLabel lblPompe10 = new JLabel("Pompe10");
/*  485 */     lblPompe10.setFont(new Font("Times New Roman", 2, 10));
/*  486 */     lblPompe10.setBounds(37, 29, 56, 16);
/*  487 */     panel_12.add(lblPompe10);
/*      */     
/*  489 */     JCheckBox check_pop10 = new JCheckBox("");
/*  490 */     check_pop10.setBounds(50, 80, 25, 22);
/*  491 */     panel_12.add(check_pop10);
/*      */ 
/*      */     
/*  494 */     this.textField_pop10 = new JTextField();
/*  495 */     this.textField_pop10.setFont(new Font("Times New Roman", 1, 17));
/*  496 */     this.textField_pop10.setColumns(10);
/*  497 */     this.textField_pop10.setBounds(12, 149, 109, 22);
/*  498 */     panel_12.add(this.textField_pop10);
/*      */     
/*  500 */     JLabel icon_pop10 = new JLabel("New label");
/*  501 */     icon_pop10.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  502 */     icon_pop10.setBounds(0, 0, 80, 189);
/*  503 */     panel_12.add(icon_pop10);
/*      */     
/*  505 */     JPanel panel_13 = new JPanel();
/*  506 */     panel_13.setLayout((LayoutManager)null);
/*  507 */     panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  508 */     panel_13.setBounds(1100, 65, 80, 189);
/*  509 */     panel.add(panel_13);
/*      */     
/*  511 */     final JLabel lblPompe11 = new JLabel("Pompe11");
/*  512 */     lblPompe11.setFont(new Font("Times New Roman", 2, 10));
/*  513 */     lblPompe11.setBounds(37, 29, 56, 16);
/*  514 */     panel_13.add(lblPompe11);
/*      */     
/*  516 */     JCheckBox check_pop11 = new JCheckBox("");
/*  517 */     check_pop11.setBounds(50, 80, 25, 25);
/*  518 */     panel_13.add(check_pop11);
/*      */ 
/*      */     
/*  521 */     this.textField_pop11 = new JTextField();
/*  522 */     this.textField_pop11.setFont(new Font("Times New Roman", 1, 17));
/*  523 */     this.textField_pop11.setColumns(10);
/*  524 */     this.textField_pop11.setBounds(12, 149, 109, 22);
/*  525 */     panel_13.add(this.textField_pop11);
/*      */     
/*  527 */     JLabel icon_pop_11 = new JLabel("New label");
/*  528 */     icon_pop_11.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  529 */     icon_pop_11.setBounds(0, 0, 80, 189);
/*  530 */     panel_13.add(icon_pop_11);
/*      */     
/*  532 */     JPanel panel_14 = new JPanel();
/*  533 */     panel_14.setLayout((LayoutManager)null);
/*  534 */     panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
/*  535 */     panel_14.setBounds(1110, 65, 80, 189);
/*  536 */     panel.add(panel_14);
/*      */     
/*  538 */     final JLabel lblPompe12 = new JLabel("Pompe12");
/*  539 */     lblPompe12.setFont(new Font("Times New Roman", 2, 12));
/*  540 */     lblPompe12.setBounds(37, 29, 56, 16);
/*  541 */     panel_14.add(lblPompe12);
/*      */     
/*  543 */     JCheckBox checkBox_pop12 = new JCheckBox("");
/*  544 */     checkBox_pop12.setBounds(50, 80, 25, 25);
/*  545 */     panel_14.add(checkBox_pop12);
/*      */     
/*  547 */     this.textField_pompe12 = new JTextField();
/*  548 */     this.textField_pompe12.setFont(new Font("Times New Roman", 1, 17));
/*  549 */     this.textField_pompe12.setColumns(10);
/*  550 */     this.textField_pompe12.setBounds(12, 149, 109, 22);
/*  551 */     panel_14.add(this.textField_pompe12);
/*      */     
/*  553 */     JLabel lblNewLabel_3 = new JLabel("New label");
/*  554 */     lblNewLabel_3.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  555 */     lblNewLabel_3.setBounds(0, 0, 80, 189);
/*  556 */     panel_14.add(lblNewLabel_3);
/*      */     
/*  558 */     JLabel label_icon_fakher = new JLabel("");
/*  559 */     label_icon_fakher.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  560 */     label_icon_fakher.setBounds(0, 0, 1024, 306);
/*  561 */     panel.add(label_icon_fakher);
/*      */     
/*  563 */     JLabel lblNewLabel_1 = new JLabel("New label");
/*  564 */     lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  565 */     lblNewLabel_1.setBounds(873, 0, 1024, 281);
/*  566 */     panel.add(lblNewLabel_1);
/*      */ 
/*      */     
/*  569 */     JPanel panel_15 = new JPanel();
/*  570 */     panel_15.setBounds(5, 360, 1488, 97);
/*  571 */     panel_15.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
/*  572 */     getContentPane().add(panel_15);
/*  573 */     panel_15.setLayout((LayoutManager)null);
/*      */     
/*  575 */     JLabel lbl_rechercher = new JLabel("Rechercher");
/*  576 */     lbl_rechercher.setFont(new Font("Times New Roman", 1, 18));
/*  577 */     lbl_rechercher.setBounds(12, 13, 103, 25);
/*  578 */     panel_15.add(lbl_rechercher);
/*      */     
/*  580 */     JLabel lbl_date_deb = new JLabel("Date début :");
/*  581 */     lbl_date_deb.setFont(new Font("Times New Roman", 2, 12));
/*  582 */     lbl_date_deb.setBounds(24, 51, 85, 38);
/*  583 */     panel_15.add(lbl_date_deb);
/*      */     
/*  585 */     JLabel lblDateFin = new JLabel("Date fin:");
/*  586 */     lblDateFin.setFont(new Font("Times New Roman", 2, 12));
/*  587 */     lblDateFin.setBounds(294, 51, 92, 38);
/*  588 */     panel_15.add(lblDateFin);
/*      */     
/*  590 */     JLabel lbl_h_deb = new JLabel("Heure de début :");
/*  591 */     lbl_h_deb.setFont(new Font("Times New Roman", 2, 12));
/*  592 */     lbl_h_deb.setBounds(588, 51, 113, 27);
/*  593 */     panel_15.add(lbl_h_deb);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  600 */     this.txtHhmm = new JTextField();
/*  601 */     this.txtHhmm.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent arg0) {
/*  604 */             ConnextionFTP con = new ConnextionFTP();
/*      */             
/*  606 */             double total = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             try {
/*  622 */               CheckBoxInTable.this.listjournal = new ArrayList();
/*      */               
/*  624 */               CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*  625 */               total = 0.0D;
/*  626 */               if (CheckBoxInTable.this.listjournal != null) {
/*  627 */                 for (Transaction t : CheckBoxInTable.this.listjournal) {
/*  628 */                   total += t.getMontant();
/*      */                 }
/*      */                 
/*  631 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/*  632 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/*      */               }
/*      */             
/*  635 */             } catch (ClassNotFoundException e1) {
/*  636 */               e1.printStackTrace();
/*      */             } 
/*      */             
/*  639 */             TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/*  640 */             CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  645 */     this.txtHhmm.setText("00:00\r\n");
/*  646 */     this.txtHhmm.setBounds(705, 51, 71, 22);
/*  647 */     panel_15.add(this.txtHhmm);
/*  648 */     this.txtHhmm.setColumns(10);
/*      */ 
/*      */ 
/*      */     
/*  652 */     JLabel lblHeureDeFin = new JLabel("Heure de fin :");
/*  653 */     lblHeureDeFin.setFont(new Font("Times New Roman", 2, 12));
/*  654 */     lblHeureDeFin.setBounds(750, 51, 92, 27);
/*  655 */     panel_15.add(lblHeureDeFin);
/*      */     
/*  657 */     this.txt_h_fin = new JTextField();
/*  658 */     this.txt_h_fin.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent e) {
/*  661 */             ConnextionFTP con = new ConnextionFTP();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             try {
/*  678 */               CheckBoxInTable.this.listjournal = new ArrayList();
/*      */               
/*  680 */               CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*  681 */               double total = 0.0D;
/*  682 */               if (CheckBoxInTable.this.listjournal != null) {
/*  683 */                 for (Transaction t : CheckBoxInTable.this.listjournal) {
/*  684 */                   total += t.getMontant();
/*      */                 }
/*      */                 
/*  687 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/*  688 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/*      */               }
/*      */             
/*      */             }
/*  692 */             catch (ClassNotFoundException e1) {
/*  693 */               e1.printStackTrace();
/*      */             } 
/*      */             
/*  696 */             TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/*  697 */             CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */           }
/*      */         });
/*      */     
/*  701 */     this.txt_h_fin.setText("23:59\r\n");
/*  702 */     this.txt_h_fin.setColumns(10);
/*  703 */     this.txt_h_fin.setBounds(918, 51, 71, 22);
/*  704 */     panel_15.add(this.txt_h_fin);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  713 */     Label label_pop_rech = new Label("Pompe");
/*  714 */     label_pop_rech.setBounds(1033, 51, 55, 24);
/*      */ 
/*      */     
/*  717 */     Choice choice_pop = new Choice();
/*      */     
/*  719 */     choice_pop.setFont(new Font("Times New Roman", 2, 12));
/*  720 */     choice_pop.add("Tout");
/*  721 */     choice_pop.add("Pompe1");
/*  722 */     choice_pop.add("Pompe2");
/*  723 */     choice_pop.add("Pompe3");
/*  724 */     choice_pop.add("Pompe4");
/*  725 */     choice_pop.add("Pompe5");
/*  726 */     choice_pop.add("Pompe6");
/*  727 */     choice_pop.add("Pompe7");
/*  728 */     choice_pop.add("Pompe8");
/*  729 */     choice_pop.add("Pompe9");
/*  730 */     choice_pop.add("Pompe10");
/*  731 */     choice_pop.add("Pompe11");
/*  732 */     choice_pop.add("Pompe12");
/*  733 */     choice_pop.setBounds(950, 51, 127, 22);
/*  734 */     panel_15.add(choice_pop);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  743 */     JLabel lbl_totale = new JLabel("Totale");
/*  744 */     lbl_totale.setFont(new Font("Times New Roman", 1, 22));
/*  745 */     lbl_totale.setBounds(1110, 24, 92, 65);
/*  746 */     panel_15.add(lbl_totale);
/*      */     
/*  748 */     this.textField_totale = new JTextField();
/*  749 */     this.textField_totale.setFont(new Font("Times New Roman", 1, 17));
/*  750 */     this.textField_totale.setBounds(1100, 29, 116, 60);
/*  751 */     panel_15.add(this.textField_totale);
/*  752 */     this.textField_totale.setColumns(10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  759 */     JDateChooser date_deb = new JDateChooser();
/*  760 */     date_deb.setBounds(114, 56, 127, 22);
/*  761 */     panel_15.add((Component)date_deb);
/*  762 */     Date date = date_deb.getDate();
/*      */     if (date == null) {
/*      */       date = new Date();
/*      */     }
/*  763 */     String strDate = DateFormat.getDateInstance().format(date);
/*      */ 
/*      */     
/*  766 */     JDateChooser date_fin = new JDateChooser();
/*  767 */     date_fin.setBounds(352, 56, 127, 22);
/*  768 */     panel_15.add((Component)date_fin);
/*      */     
/*  770 */     JLabel recherch_icon = new JLabel("New label");
/*  771 */     recherch_icon.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  772 */     recherch_icon.setBounds(0, 0, 784, 107);
/*  773 */     panel_15.add(recherch_icon);
/*      */     
/*  775 */     JLabel icon_reche2 = new JLabel("");
/*  776 */     icon_reche2.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  777 */     icon_reche2.setBounds(777, 0, 711, 107);
/*  778 */     panel_15.add(icon_reche2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  784 */     JPanel panel_16 = new JPanel();
/*  785 */     panel_16.setBounds(10, 470, 1310, 200);
/*  786 */     panel_16.setBorder(new LineBorder(new Color(0, 0, 0), 5));
/*  787 */     getContentPane().add(panel_16);
/*  788 */     panel_16.setLayout((LayoutManager)null);
/*      */     
/*  790 */     JScrollPane scrollPane = new JScrollPane();
/*  791 */     scrollPane.setBounds(5, 50, 1300, 120);
/*  792 */     panel_16.add(scrollPane);
/*      */     
/*  794 */     this.table_result_pompe = new JTable();
/*  795 */     scrollPane.setViewportView(this.table_result_pompe);
/*  796 */     this.listjournal = new ArrayList<>();
/*      */     
/*  798 */     Transaction t = new Transaction();
/*  799 */     this.listjournal.add(t);
/*      */     
/*  801 */     TableModel model = new TransactionTableModel(this.listjournal);
/*      */     
/*  803 */     this.table_result_pompe.setModel(model);
/*      */ 
/*      */ 
/*      */     
/*  807 */     this.recherche_montant = new JTextField();
/*  808 */     this.recherche_montant.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent e) {
/*  810 */             System.out.println("ok");
/*  811 */             System.out.println("***" + CheckBoxInTable.this.recherche_montant.getText());
/*  812 */             ConnextionFTP con = new ConnextionFTP();
/*  813 */             con.connexionFTP3(new Date());
/*      */             
/*  815 */             double total = 0.0D;
/*      */ 
/*      */             
/*  818 */             CheckBoxInTable.this.listpompes.add("PMP:1");
/*  819 */             CheckBoxInTable.this.listpompes.add("PMP:2");
/*  820 */             CheckBoxInTable.this.listpompes.add("PMP:3");
/*  821 */             CheckBoxInTable.this.listpompes.add("PMP:4");
/*  822 */             CheckBoxInTable.this.listpompes.add("PMP:5");
/*  823 */             CheckBoxInTable.this.listpompes.add("PMP:6");
/*  824 */             CheckBoxInTable.this.listpompes.add("PMP:7");
/*  825 */             CheckBoxInTable.this.listpompes.add("PMP:8");
/*  826 */             CheckBoxInTable.this.listpompes.add("PMP:9");
/*  827 */             CheckBoxInTable.this.listpompes.add("PMP:10");
/*  828 */             CheckBoxInTable.this.listpompes.add("PMP:11");
/*  829 */             CheckBoxInTable.this.listpompes.add("PMP:12");
/*      */             
/*      */             try {
/*  832 */               CheckBoxInTable.this.listjournal = new ArrayList();
/*      */ 
/*      */ 
/*      */               
/*  836 */               List<Transaction> l = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */ 
/*      */               
/*  839 */               for (Transaction t : l) {
/*  840 */                 if (t.getMontant() == Double.parseDouble(CheckBoxInTable.this.recherche_montant.getText())) {
/*  841 */                   CheckBoxInTable.this.listjournal.add(t);
/*      */                 }
/*      */               } 
/*      */               
/*  845 */               total = 0.0D;
/*  846 */               if (CheckBoxInTable.this.listjournal != null) {
/*  847 */                 for (Transaction t : CheckBoxInTable.this.listjournal) {
/*  848 */                   total += t.getMontant();
/*      */                 }
/*      */                 
/*  851 */                 DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
/*  852 */                 CheckBoxInTable.this.textField_totale.setText(decimalFormat.format(total));
/*      */               } 
/*      */               
/*  855 */               DecimalFormat df = new DecimalFormat("#,###.000");
/*  856 */               CheckBoxInTable.this.textField_totale.setText(df.format(total));
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*  861 */             catch (ClassNotFoundException e1) {
/*  862 */               e1.printStackTrace();
/*      */             } 
/*      */             
/*  865 */             TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/*  866 */             CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  872 */     this.recherche_montant.setBounds(1661, 15, 116, 22);
/*  873 */     panel_16.add(this.recherche_montant);
/*  874 */     this.recherche_montant.setColumns(10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  882 */     JButton loadData = new JButton("Load Data");
/*  883 */     loadData.setBounds(22, 12, 139, 25);
/*  884 */     loadData.setFont(new Font("Times New Roman", 2, 12));
/*  885 */     panel_16.add(loadData);
/*      */     
/*  887 */     JButton btnAlerte = new JButton("Alerte");
/*  888 */     btnAlerte.setBounds(41, 42, 97, 25);
/*  889 */     getContentPane().add(btnAlerte);
/*  890 */     btnAlerte.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent arg0) {
/*  892 */             Paramettre c = new Paramettre();
/*      */             
/*  894 */             c.setVisible(true);
/*      */           }
/*      */         });
/*      */     
/*  898 */     JLabel icon_fakher_icon = new JLabel("");
/*  899 */     icon_fakher_icon.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  900 */     icon_fakher_icon.setBounds(0, 0, 1024, 747);
/*  901 */     getContentPane().add(icon_fakher_icon);
/*      */     
/*  903 */     JLabel lblNewLabel = new JLabel("New label");
/*  904 */     lblNewLabel.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  905 */     lblNewLabel.setBounds(1021, 0, 903, 747);
/*  906 */     getContentPane().add(lblNewLabel);
/*      */     
/*  908 */     JLabel lblNewLabel_4 = new JLabel("New label");
/*  909 */     lblNewLabel_4.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  910 */     lblNewLabel_4.setBounds(0, 746, 985, 309);
/*  911 */     getContentPane().add(lblNewLabel_4);
/*      */     
/*  913 */     JLabel lblNewLabel_5 = new JLabel("New label");
/*  914 */     lblNewLabel_5.setIcon(new ImageIcon(getClass().getResource("items/Shell_test.jpg")));
/*  915 */     lblNewLabel_5.setBounds(971, 727, 953, 328);
/*  916 */     getContentPane().add(lblNewLabel_5);
/*      */     
/*  918 */     loadData.addActionListener(new ActionListener()
/*      */         {
/*      */           
/*      */           public void actionPerformed(ActionEvent arg0)
/*      */           {
/*  923 */             ConnextionFTP con = new ConnextionFTP();
/*  924 */             double total = 0.0D;
/*  925 */             CheckBoxInTable.this.listpompes.add("PMP:1");
/*  926 */             CheckBoxInTable.this.listpompes.add("PMP:2");
/*  927 */             CheckBoxInTable.this.listpompes.add("PMP:3");
/*  928 */             CheckBoxInTable.this.listpompes.add("PMP:4");
/*  929 */             CheckBoxInTable.this.listpompes.add("PMP:5");
/*  930 */             CheckBoxInTable.this.listpompes.add("PMP:6");
/*  931 */             CheckBoxInTable.this.listpompes.add("PMP:7");
/*  932 */             CheckBoxInTable.this.listpompes.add("PMP:8");
/*  933 */             CheckBoxInTable.this.listpompes.add("PMP:9");
/*  934 */             CheckBoxInTable.this.listpompes.add("PMP:10");
/*  935 */             CheckBoxInTable.this.listpompes.add("PMP:11");
/*  936 */             CheckBoxInTable.this.listpompes.add("PMP:12");
/*      */             
/*      */             try {
/*  939 */               CheckBoxInTable.this.listjournal = new ArrayList();
/*      */               
/*  941 */               CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */ 
/*      */               
/*  944 */               for (Transaction t : CheckBoxInTable.this.listjournal) {
/*  945 */                 total += t.getMontant();
/*      */               }
/*      */               
/*  948 */               DecimalFormat df = new DecimalFormat("#,###.000");
/*  949 */               CheckBoxInTable.this.textField_totale.setText(df.format(total));
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*  954 */             catch (ClassNotFoundException e1) {
/*  955 */               e1.printStackTrace();
/*      */             } 
/*      */             
/*  958 */             TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/*  959 */             CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  983 */     check_pop2.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/*  985 */             if (e.getStateChange() == 1) {
/*      */               
/*  987 */               lblPompe2.setForeground(Color.RED);
/*  988 */               ConnextionFTP con = new ConnextionFTP();
/*  989 */               con.connexionFTP3(new Date());
/*      */               
/*  991 */               CheckBoxInTable.this.listpompes.add("PMP:2");
/*  992 */               double total = 0.0D;
/*  993 */               double totalP2 = 0.0D;
/*      */               try {
/*  995 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/*  997 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/*  999 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1000 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1001 */                     total += t.getMontant();
/* 1002 */                     if (t.getPompe().equals("PMP:2"))
/* 1003 */                       totalP2 += t.getMontant(); 
/*      */                   } 
/* 1005 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1006 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1007 */                   CheckBoxInTable.this.textField_pop2.setText(df.format(totalP2));
/*      */                 }
/*      */               
/* 1010 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1012 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1015 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1016 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             }
/*      */             else {
/*      */               
/* 1020 */               lblPompe2.setForeground(Color.GREEN);
/* 1021 */               ConnextionFTP con = new ConnextionFTP();
/* 1022 */               int i = 0;
/* 1023 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1024 */                 if (s.equals("PMP:2"))
/*      */                   break; 
/* 1026 */                 i++;
/*      */               } 
/* 1028 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1029 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1030 */               double total = 0.0D;
/* 1031 */               double totalP2 = 0.0D;
/*      */               try {
/* 1033 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1035 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1036 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1037 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1038 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1039 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1040 */                     total += t.getMontant();
/* 1041 */                     if (t.getPompe().equals("PMP:2")) {
/* 1042 */                       totalP2 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */ 
/*      */                 
/* 1048 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1049 */                 CheckBoxInTable.this.textField_pop2.setText(df.format(totalP2));
/* 1050 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1052 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1055 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1056 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1065 */     check_pop3.addItemListener(new ItemListener()
/*      */         {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1068 */             if (e.getStateChange() == 1) {
/*      */               
/* 1070 */               lblPompe_3.setForeground(Color.RED);
/* 1071 */               ConnextionFTP con = new ConnextionFTP();
/* 1072 */               CheckBoxInTable.this.listpompes.add("PMP:3");
/* 1073 */               double total = 0.0D;
/* 1074 */               double totalP3 = 0.0D;
/*      */               try {
/* 1076 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1078 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1080 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1081 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1082 */                     total += t.getMontant();
/* 1083 */                     if (t.getPompe().equals("PMP:3"))
/* 1084 */                       totalP3 += t.getMontant(); 
/*      */                   } 
/* 1086 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1087 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1088 */                   CheckBoxInTable.this.textField_pop3.setText(df.format(totalP3));
/*      */                 }
/*      */               
/* 1091 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1093 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1096 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1097 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } else {
/*      */               
/* 1100 */               lblPompe_3.setForeground(Color.GREEN);
/* 1101 */               ConnextionFTP con = new ConnextionFTP();
/* 1102 */               int i = 0;
/* 1103 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1104 */                 if (s.equals("PMP:3"))
/*      */                   break; 
/* 1106 */                 i++;
/*      */               } 
/* 1108 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1109 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1110 */               double total = 0.0D;
/* 1111 */               double totalP3 = 0.0D;
/*      */               try {
/* 1113 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1115 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1116 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1117 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1118 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1119 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1120 */                     total += t.getMontant();
/* 1121 */                     if (t.getPompe().equals("PMP:3")) {
/* 1122 */                       totalP3 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */ 
/*      */                 
/* 1128 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1129 */                 CheckBoxInTable.this.textField_pop3.setText(df.format(totalP3));
/* 1130 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1132 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1135 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1136 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1146 */     check_pop4.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1148 */             if (e.getStateChange() == 1) {
/*      */               
/* 1150 */               lblPompe4.setForeground(Color.RED);
/* 1151 */               ConnextionFTP con = new ConnextionFTP();
/* 1152 */               CheckBoxInTable.this.listpompes.add("PMP:4");
/* 1153 */               double total = 0.0D;
/* 1154 */               double totalP4 = 0.0D;
/*      */               try {
/* 1156 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1158 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1160 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1161 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1162 */                     total += t.getMontant();
/* 1163 */                     if (t.getPompe().equals("PMP:4"))
/* 1164 */                       totalP4 += t.getMontant(); 
/*      */                   } 
/* 1166 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1167 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1168 */                   CheckBoxInTable.this.textField_pop4.setText(df.format(totalP4));
/*      */                 }
/*      */               
/* 1171 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1173 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1176 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1177 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/* 1182 */               lblPompe4.setForeground(Color.GREEN);
/* 1183 */               ConnextionFTP con = new ConnextionFTP();
/* 1184 */               int i = 0;
/* 1185 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1186 */                 if (s.equals("PMP:4"))
/*      */                   break; 
/* 1188 */                 i++;
/*      */               } 
/* 1190 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1191 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1192 */               double total = 0.0D;
/* 1193 */               double totalP4 = 0.0D;
/*      */               try {
/* 1195 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1197 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1198 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1199 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1200 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1201 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1202 */                     total += t.getMontant();
/* 1203 */                     if (t.getPompe().equals("PMP:4")) {
/* 1204 */                       totalP4 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */ 
/*      */                 
/* 1210 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1211 */                 CheckBoxInTable.this.textField_pop4.setText(df.format(totalP4));
/* 1212 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1214 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1217 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1218 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1230 */     check_pop5.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1232 */             if (e.getStateChange() == 1) {
/*      */               
/* 1234 */               lblPompe5.setForeground(Color.RED);
/* 1235 */               ConnextionFTP con = new ConnextionFTP();
/* 1236 */               CheckBoxInTable.this.listpompes.add("PMP:5");
/* 1237 */               double total = 0.0D;
/* 1238 */               double totalP5 = 0.0D;
/*      */               try {
/* 1240 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1242 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1244 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1245 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1246 */                     total += t.getMontant();
/* 1247 */                     if (t.getPompe().equals("PMP:5"))
/* 1248 */                       totalP5 += t.getMontant(); 
/*      */                   } 
/* 1250 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1251 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1252 */                   CheckBoxInTable.this.textField_pop5.setText(df.format(totalP5));
/*      */                 }
/*      */               
/* 1255 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1257 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1260 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1261 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             }
/*      */             else {
/*      */               
/* 1265 */               lblPompe5.setForeground(Color.GREEN);
/* 1266 */               ConnextionFTP con = new ConnextionFTP();
/* 1267 */               int i = 0;
/* 1268 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1269 */                 if (s.equals("PMP:5"))
/*      */                   break; 
/* 1271 */                 i++;
/*      */               } 
/* 1273 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1274 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1275 */               double total = 0.0D;
/* 1276 */               double totalP5 = 0.0D;
/*      */               try {
/* 1278 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1280 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1281 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1282 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1283 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1284 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1285 */                     total += t.getMontant();
/* 1286 */                     if (t.getPompe().equals("PMP:5")) {
/* 1287 */                       totalP5 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */ 
/*      */                 
/* 1293 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1294 */                 CheckBoxInTable.this.textField_pop5.setText(df.format(totalP5));
/* 1295 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1297 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1300 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1301 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1309 */     check_pop6.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1311 */             if (e.getStateChange() == 1) {
/*      */               
/* 1313 */               lblPompe6.setForeground(Color.RED);
/* 1314 */               ConnextionFTP con = new ConnextionFTP();
/* 1315 */               CheckBoxInTable.this.listpompes.add("PMP:6");
/* 1316 */               double total = 0.0D;
/* 1317 */               double totalP6 = 0.0D;
/*      */               try {
/* 1319 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1321 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1323 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1324 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1325 */                     total += t.getMontant();
/* 1326 */                     if (t.getPompe().equals("PMP:6"))
/* 1327 */                       totalP6 += t.getMontant(); 
/*      */                   } 
/* 1329 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1330 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1331 */                   CheckBoxInTable.this.textField_pop6.setText(df.format(totalP6));
/*      */                 }
/*      */               
/* 1334 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1336 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1339 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1340 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             }
/*      */             else {
/*      */               
/* 1344 */               lblPompe6.setForeground(Color.GREEN);
/* 1345 */               ConnextionFTP con = new ConnextionFTP();
/* 1346 */               int i = 0;
/* 1347 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1348 */                 if (s.equals("PMP:6"))
/*      */                   break; 
/* 1350 */                 i++;
/*      */               } 
/* 1352 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1353 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1354 */               double total = 0.0D;
/* 1355 */               double totalP6 = 0.0D;
/*      */               try {
/* 1357 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1359 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1360 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1361 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1362 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1363 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1364 */                     total += t.getMontant();
/* 1365 */                     if (t.getPompe().equals("PMP:6")) {
/* 1366 */                       totalP6 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */ 
/*      */                 
/* 1372 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1373 */                 CheckBoxInTable.this.textField_pop6.setText(df.format(totalP6));
/* 1374 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1376 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1379 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1380 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1388 */     check_pop7.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1390 */             if (e.getStateChange() == 1) {
/*      */               
/* 1392 */               lblPompe7.setForeground(Color.RED);
/* 1393 */               ConnextionFTP con = new ConnextionFTP();
/* 1394 */               CheckBoxInTable.this.listpompes.add("PMP:7");
/* 1395 */               double total = 0.0D;
/* 1396 */               double totalP7 = 0.0D;
/*      */               try {
/* 1398 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1400 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1402 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1403 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1404 */                     total += t.getMontant();
/* 1405 */                     if (t.getPompe().equals("PMP:7"))
/* 1406 */                       totalP7 += t.getMontant(); 
/*      */                   } 
/* 1408 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1409 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1410 */                   CheckBoxInTable.this.textField_pop7.setText(df.format(totalP7));
/*      */                 }
/*      */               
/* 1413 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1415 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1418 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1419 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/* 1424 */               lblPompe7.setForeground(Color.GREEN);
/* 1425 */               ConnextionFTP con = new ConnextionFTP();
/* 1426 */               int i = 0;
/* 1427 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1428 */                 if (s.equals("PMP:7"))
/*      */                   break; 
/* 1430 */                 i++;
/*      */               } 
/* 1432 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1433 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1434 */               double total = 0.0D;
/* 1435 */               double totalP7 = 0.0D;
/*      */               try {
/* 1437 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1439 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1440 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1441 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1442 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1443 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1444 */                     total += t.getMontant();
/* 1445 */                     if (t.getPompe().equals("PMP:7")) {
/* 1446 */                       totalP7 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */ 
/*      */                 
/* 1452 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1453 */                 CheckBoxInTable.this.textField_pop7.setText(df.format(totalP7));
/* 1454 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1456 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1459 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1460 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1468 */     check_pop8.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1470 */             if (e.getStateChange() == 1) {
/*      */               
/* 1472 */               lblPompe8.setForeground(Color.RED);
/* 1473 */               ConnextionFTP con = new ConnextionFTP();
/* 1474 */               CheckBoxInTable.this.listpompes.add("PMP:8");
/* 1475 */               double total = 0.0D;
/* 1476 */               double totalP8 = 0.0D;
/*      */               try {
/* 1478 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1480 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1482 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1483 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1484 */                     total += t.getMontant();
/* 1485 */                     if (t.getPompe().equals("PMP:8"))
/* 1486 */                       totalP8 += t.getMontant(); 
/*      */                   } 
/* 1488 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1489 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1490 */                   CheckBoxInTable.this.textField_pop8.setText(df.format(totalP8));
/*      */                 }
/*      */               
/* 1493 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1495 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1498 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1499 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             }
/*      */             else {
/*      */               
/* 1503 */               lblPompe8.setForeground(Color.GREEN);
/* 1504 */               ConnextionFTP con = new ConnextionFTP();
/* 1505 */               int i = 0;
/* 1506 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1507 */                 if (s.equals("PMP:8"))
/*      */                   break; 
/* 1509 */                 i++;
/*      */               } 
/* 1511 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1512 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1513 */               double total = 0.0D;
/* 1514 */               double totalP8 = 0.0D;
/*      */               try {
/* 1516 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1518 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1519 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1520 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1521 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1522 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1523 */                     total += t.getMontant();
/* 1524 */                     if (t.getPompe().equals("PMP:8")) {
/* 1525 */                       totalP8 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */ 
/*      */                 
/* 1531 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1532 */                 CheckBoxInTable.this.textField_pop8.setText(df.format(totalP8));
/* 1533 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1535 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1538 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1539 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1548 */     check_pop9.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1550 */             if (e.getStateChange() == 1) {
/*      */               
/* 1552 */               lblPompe9.setForeground(Color.RED);
/* 1553 */               ConnextionFTP con = new ConnextionFTP();
/* 1554 */               CheckBoxInTable.this.listpompes.add("PMP:9");
/* 1555 */               double total = 0.0D;
/* 1556 */               double totalP9 = 0.0D;
/*      */               try {
/* 1558 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1560 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1562 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1563 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1564 */                     total += t.getMontant();
/* 1565 */                     if (t.getPompe().equals("PMP:9"))
/* 1566 */                       totalP9 += t.getMontant(); 
/*      */                   } 
/* 1568 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1569 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1570 */                   CheckBoxInTable.this.textField_pop9.setText(df.format(totalP9));
/*      */                 }
/*      */               
/* 1573 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1575 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1578 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1579 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             }
/*      */             else {
/*      */               
/* 1583 */               lblPompe9.setForeground(Color.GREEN);
/* 1584 */               ConnextionFTP con = new ConnextionFTP();
/* 1585 */               int i = 0;
/* 1586 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1587 */                 if (s.equals("PMP:9"))
/*      */                   break; 
/* 1589 */                 i++;
/*      */               } 
/* 1591 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1592 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1593 */               double total = 0.0D;
/* 1594 */               double totalP9 = 0.0D;
/*      */               try {
/* 1596 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1598 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1599 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1600 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1601 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1602 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1603 */                     total += t.getMontant();
/* 1604 */                     if (t.getPompe().equals("PMP:9")) {
/* 1605 */                       totalP9 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */ 
/*      */                 
/* 1611 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1612 */                 CheckBoxInTable.this.textField_pop9.setText(df.format(totalP9));
/* 1613 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1615 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1618 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1619 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1628 */     check_pop10.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1630 */             if (e.getStateChange() == 1) {
/*      */               
/* 1632 */               lblPompe10.setForeground(Color.RED);
/* 1633 */               ConnextionFTP con = new ConnextionFTP();
/* 1634 */               CheckBoxInTable.this.listpompes.add("PMP:10");
/* 1635 */               double total = 0.0D;
/* 1636 */               double totalP10 = 0.0D;
/*      */               try {
/* 1638 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1640 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1642 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1643 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1644 */                     total += t.getMontant();
/* 1645 */                     if (t.getPompe().equals("PMP:10"))
/* 1646 */                       totalP10 += t.getMontant(); 
/*      */                   } 
/* 1648 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1649 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1650 */                   CheckBoxInTable.this.textField_pop10.setText(df.format(totalP10));
/*      */                 }
/*      */               
/* 1653 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1655 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1658 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1659 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             }
/*      */             else {
/*      */               
/* 1663 */               lblPompe10.setForeground(Color.GREEN);
/* 1664 */               ConnextionFTP con = new ConnextionFTP();
/* 1665 */               int i = 0;
/* 1666 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1667 */                 if (s.equals("PMP:10"))
/*      */                   break; 
/* 1669 */                 i++;
/*      */               } 
/* 1671 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1672 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1673 */               double total = 0.0D;
/* 1674 */               double totalP10 = 0.0D;
/*      */               try {
/* 1676 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1678 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1679 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1680 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1681 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1682 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1683 */                     total += t.getMontant();
/* 1684 */                     if (t.getPompe().equals("PMP:10")) {
/* 1685 */                       totalP10 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */                 
/* 1690 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1691 */                 CheckBoxInTable.this.textField_pop10.setText(df.format(totalP10));
/* 1692 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1694 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1697 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1698 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1708 */     check_pop11.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1710 */             if (e.getStateChange() == 1) {
/*      */               
/* 1712 */               lblPompe11.setForeground(Color.RED);
/* 1713 */               ConnextionFTP con = new ConnextionFTP();
/* 1714 */               CheckBoxInTable.this.listpompes.add("PMP:11");
/* 1715 */               double total = 0.0D;
/* 1716 */               double totalP11 = 0.0D;
/*      */               try {
/* 1718 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1720 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1722 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1723 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1724 */                     total += t.getMontant();
/* 1725 */                     if (t.getPompe().equals("PMP:11"))
/* 1726 */                       totalP11 += t.getMontant(); 
/*      */                   } 
/* 1728 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1729 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1730 */                   CheckBoxInTable.this.textField_pop11.setText(df.format(totalP11));
/*      */                 }
/*      */               
/* 1733 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1735 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1738 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1739 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/* 1744 */               lblPompe11.setForeground(Color.GREEN);
/* 1745 */               ConnextionFTP con = new ConnextionFTP();
/* 1746 */               int i = 0;
/* 1747 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1748 */                 if (s.equals("PMP:11"))
/*      */                   break; 
/* 1750 */                 i++;
/*      */               } 
/* 1752 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1753 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1754 */               double total = 0.0D;
/* 1755 */               double totalP11 = 0.0D;
/*      */               try {
/* 1757 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1759 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1760 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1761 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1762 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1763 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1764 */                     total += t.getMontant();
/* 1765 */                     if (t.getPompe().equals("PMP:11")) {
/* 1766 */                       totalP11 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */                 
/* 1771 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1772 */                 CheckBoxInTable.this.textField_pop11.setText(df.format(totalP11));
/* 1773 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1775 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1778 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1779 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1791 */     checkBox_pop12.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent e) {
/* 1793 */             if (e.getStateChange() == 1) {
/*      */               
/* 1795 */               lblPompe12.setForeground(Color.RED);
/* 1796 */               ConnextionFTP con = new ConnextionFTP();
/* 1797 */               CheckBoxInTable.this.listpompes.add("PMP:12");
/* 1798 */               double total = 0.0D;
/* 1799 */               double totalP12 = 0.0D;
/*      */               try {
/* 1801 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1803 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/*      */                 
/* 1805 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1806 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1807 */                     total += t.getMontant();
/* 1808 */                     if (t.getPompe().equals("PMP:12"))
/* 1809 */                       totalP12 += t.getMontant(); 
/*      */                   } 
/* 1811 */                   DecimalFormat df = new DecimalFormat("#,###.000");
/* 1812 */                   CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1813 */                   CheckBoxInTable.this.textField_pompe12.setText(df.format(totalP12));
/*      */                 }
/*      */               
/* 1816 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1818 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1821 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1822 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/* 1827 */               lblPompe12.setForeground(Color.GREEN);
/* 1828 */               ConnextionFTP con = new ConnextionFTP();
/* 1829 */               int i = 0;
/* 1830 */               for (String s : CheckBoxInTable.this.listpompes) {
/* 1831 */                 if (s.equals("PMP:12"))
/*      */                   break; 
/* 1833 */                 i++;
/*      */               } 
/* 1835 */               CheckBoxInTable.this.listpompes.remove(i);
/* 1836 */               System.out.println(CheckBoxInTable.this.listpompes);
/* 1837 */               double total = 0.0D;
/* 1838 */               double totalP12 = 0.0D;
/*      */               try {
/* 1840 */                 CheckBoxInTable.this.listjournal = new ArrayList();
/*      */                 
/* 1842 */                 CheckBoxInTable.this.listjournal = con.selectAlljournal(new Date(), CheckBoxInTable.this.txtHhmm.getText(), CheckBoxInTable.this.txt_h_fin.getText(), CheckBoxInTable.this.listpompes);
/* 1843 */                 System.out.println(CheckBoxInTable.this.listjournal);
/* 1844 */                 DecimalFormat df = new DecimalFormat("#,###.000");
/* 1845 */                 if (CheckBoxInTable.this.listjournal != null) {
/* 1846 */                   for (Transaction t : CheckBoxInTable.this.listjournal) {
/* 1847 */                     total += t.getMontant();
/* 1848 */                     if (t.getPompe().equals("PMP:12")) {
/* 1849 */                       totalP12 += t.getMontant();
/*      */                     }
/*      */                   } 
/*      */                 }
/*      */                 
/* 1854 */                 CheckBoxInTable.this.textField_totale.setText(df.format(total));
/* 1855 */                 CheckBoxInTable.this.textField_pompe12.setText(df.format(totalP12));
/* 1856 */               } catch (ClassNotFoundException e1) {
/*      */                 
/* 1858 */                 e1.printStackTrace();
/*      */               } 
/*      */               
/* 1861 */               TableModel model = new TransactionTableModel(CheckBoxInTable.this.listjournal);
/* 1862 */               CheckBoxInTable.this.table_result_pompe.setModel(model);
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void actionPerformed(ActionEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/* 1889 */     SwingUtilities.invokeLater(new Runnable()
/*      */         {
/*      */           public void run()
/*      */           {
/* 1893 */             CheckBoxInTable frame = new CheckBoxInTable();
/* 1894 */             frame.setSize(CheckBoxInTable.width, CheckBoxInTable.height);
/*      */             
/* 1896 */             frame.setDefaultCloseOperation(3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1902 */             frame.setVisible(true);
/*      */           }
/*      */         });
/*      */   }
/*      */ }


/* Location:              D:\Data\Projets\JaugeageCuveV1\APPJeauge.jar!\shell\sijoumi\etatcuve\CheckBoxInTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
