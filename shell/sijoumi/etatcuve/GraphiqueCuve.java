package shell.sijoumi.etatcuve;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import shell.sijoumi.jeauge.JSpeedometer;

































public class GraphiqueCuve
  extends JPanel
{
  private static final Color APP_BG = new Color(238, 243, 246);
  private static final Color CARD_BG = new Color(248, 251, 252);
  private static final Color CARD_BORDER = new Color(198, 211, 218);
  private static final Color FIELD_BG = new Color(231, 241, 246);
  private static final Color PRIMARY_TEXT = new Color(28, 43, 51);
  private static final Color MUTED_TEXT = new Color(75, 92, 103);
  private static final Color BLUE_VALUE = new Color(0, 86, 210);
  private static final Color RED_VALUE = new Color(208, 32, 32);
  private static final Color GOOD_GREEN = new Color(0, 174, 68);
  private static final Font BASE_FONT = new Font("Segoe UI", Font.PLAIN, 12);
  private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 12);
  private static final Font VALUE_FONT = new Font("Segoe UI", Font.BOLD, 14);
  private static final ImageIcon ICON_UP = itemIcon("flech3.png");
  private static final ImageIcon ICON_DOWN = itemIcon("images.jpg");
  private static final ImageIcon ICON_ALARM = itemIcon("ValeurInitiale.png");
  private static final ImageIcon ICON_TEMP = itemIcon("phototemperature.png");
  private static final ImageIcon ICON_RESERVOIR = itemIcon("photoreservoir1.png");
  private static final ImageIcon ICON_PROGRESS_BG = itemIcon("PROGBAR_C_Glow_Bckgrd.png");
  private static final ImageIcon ICON_VALID = itemIcon("valid.png");
  private static ImageIcon itemIcon(String name) {
    java.net.URL url = GraphiqueCuve.class.getResource("items/" + name);
    if (url == null) {
      AppLogger.warn("Missing icon resource: items/" + name);
      return new ImageIcon();
    }
    return new ImageIcon(url);
  }
  private static String capacityText(double capacity) {
    return String.format("%,.0f", Double.valueOf(capacity)).replace(',', ' ');
  }
  private double qteG1;
  private double qteG2;
  private double qteSSP1;
  private double qtePET;
  private double qteG50;
  private double qteSSp2;
  private JButton validbuttonG1;
  private JButton validbuttonG2;
  private JButton validbuttonSSP;
  private JButton validbuttonPET;
  private JButton validbuttonG50;
  private JButton validbuttonSSP2;
  JLabel cuve_labelg1;
  JLabel cuve_labelg2;
  JLabel cuve_labelssp;
  JLabel cuve_labelPET;
  JLabel cuve_labelG50;
  JLabel cuve_labelSSP2;
  JTextField nameCuveG1;
  JTextField nameCuveG2;
  JTextField nameCuveSSP;
  JTextField nameCuvePET;
  JTextField nameCuveG50;
  JTextField nameCuveSSP2;
  JLabel capacite_labelg1;
  JLabel capacite_labelg2;
  JLabel capacite_labelssp;
  JLabel capacite_labelPET;
  JLabel capacite_labelG50;
  JLabel capacite_labelSSP2;
  JTextField capaciteCuveG1;
  JTextField capaciteCuveG2;
  JTextField capaciteCuveSSP;
  JTextField capaciteCuvePET;
  JTextField capaciteCuveG50;
  JTextField capaciteCuveSSP2;
  JLabel date;
  JLabel heure;
  JLabel QTE_labelg1;
  JLabel QTE_labelg2;
  JLabel QTE_labelssp;
  JLabel QTE_labelPET;
  JLabel QTE_labelG50;
  JLabel QTE_labelSSP2;
  JLabel VIDE_labelg1;
  JLabel VIDE_labelg2;
  JLabel VIDE_labelssp;
  JLabel VIDE_labelPET;
  JLabel VIDE_labelG50;
  JLabel VIDE_labelSSP2;
  JTextField QTECuveG1;
  JTextField QTECuveG2;
  JTextField QTECuveSSP;
  JTextField QTECuvePET;
  JTextField QTECuveG50;
  JTextField QTECuveSSP2;
  JLabel EauLCuveG1;
  JLabel EauLCuveG2;
  JLabel EauLCuveSSP;
  JLabel EauLCuvePET;
  JLabel EauLCuveG50;
  JLabel EauLCuveSSP2;
  JTextField eauCuveG1;
  JTextField eauCuveG2;
  JTextField eauCuveSSP;
  JTextField eauCuvePET;
  JTextField eauCuveG50;
  JTextField eauCuveSSP2;
  JTextField eaumCuveG1;
  JTextField eaumCuveG2;
  JTextField eaumpCuveSSP;
  JTextField eaumpCuvePET;
  JTextField eaumpCuveG50;
  JTextField eaumpCuveSSP2;
  JTextField tempCuveG1;
  JTextField tempCuveG2;
  JTextField tempCuveSSP;
  JTextField tempCuvePET;
  JTextField tempCuveG50;
  JTextField tempCuveSSP2;
  JLabel TempCuveG1;
  JLabel TempCuveG2;
  JLabel TempCuveSSP;
  JLabel TempCuvePET;
  JLabel TempCuveG50;
  JLabel TempCuveSSP2;
  JLabel EauMMCuveG1;
  JLabel EauMMCuveG2;
  JLabel EauMMCuveSSP;
  JLabel EauMMCuvePET;
  JLabel EauMMCuveG50;
  JLabel EauMMCuveSSP2;
  JTextField VIDECuveG1;
  JTextField VIDECuveG2;
  JTextField VIDECuveSSP;
  JTextField VIDECuvePET;
  JTextField VIDECuveG50;
  JTextField VIDECuveSSP2;
  Color white;
  Color green;
  Border redline;
  Border whiteline;
  JProgressBar progressBarG1 = new JProgressBar(0, 100);
  JProgressBar progressBarG2 = new JProgressBar(0, 100);
  JProgressBar progressBarSSP = new JProgressBar(0, 100);
  JProgressBar progressBarPET = new JProgressBar(0, 100);
  JProgressBar progressBarG50 = new JProgressBar(0, 100);
  JProgressBar progressBarSSP2 = new JProgressBar(0, 100);
  
  JProgressBar progressBarG1_2;
  JProgressBar progressBarG2_2;
  JProgressBar progressBarSSP_2;
  JProgressBar progressBarPET_2;
  JProgressBar progressBarG50_2;
  JProgressBar progressBarSSP2_2;
  public boolean carry = true;
  JPanel GASOILUPDATE = new JPanel();
  JPanel GASOIL1 = new JPanel();
  JPanel GASOIL2 = new JPanel();
  JPanel SSP = new JPanel();
  JPanel GASOIL50 = new JPanel();
  JPanel PETROLE = new JPanel();
  JPanel SSP2 = new JPanel();
  
  JPanel content4GASOIL1;
  JLabel jl3;
  JLabel jl_2;
  JLabel jlp_1;
  JLabel jls_1;
  JLabel jlg50;
  JLabel jls2;
  JLabel alG1 = new JLabel();
  JLabel alG2 = new JLabel();
  JLabel alSP1 = new JLabel();
  JLabel alSPET = new JLabel();
  JLabel alG50 = new JLabel();
  JLabel alSSP2 = new JLabel();
  double qte_G1;
  double qte_G2;
  double qte_SSP1;
  double qte_SSP2;
  double qte_PET;
  double qte_G50;
  double Oqte_G1;
  double Oqte_G2;
  double Oqte_SSP1;
  double Oqte_SSP2;
  double Oqte_PET;
  double Oqte_G50;
  int width;
  int height;
  JSpeedometer gs;
  JSpeedometer g2;
  JSpeedometer g3;
  JSpeedometer g4;
  JSpeedometer g5;
  JSpeedometer g6;
  private final SoundService alarmService = new SoundService();
  private boolean alarmActive = false;
  private Timer refreshTimer;
  private SwingWorker<List<Geauge>, Void> refreshWorker;
  
  public GraphiqueCuve() {
    try {
      initialisercuveGASOIL1();
      initialisercuveGASOIL2();
      initialisercuveSSP();
      initialisercuvePET();
      initialisercuveG50();
      initialisercuveSSP2();
      this.green = new Color(1, 180, 35);
      graphyque();
      modernizeDashboard();
      refreshAsync(true);
      startRefreshTimer();
    } catch (Exception e) {
      AppLogger.error("GraphiqueCuve startup failed", e);
    } 
  }

  
  public void graphyque() {
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    this.width = 1360;
    this.height = 768;

    
    this.redline = BorderFactory.createLineBorder(Color.red);
    Border blackline = BorderFactory.createLineBorder(Color.red);
    this.white = new Color(127, 185, 196);
    this.green = new Color(1, 180, 35);
    JPanel j = new JPanel();
    j.setBackground(this.white);
    j.setPreferredSize(new Dimension(this.width / 2 - 70, 30));
    this.GASOILUPDATE.setAlignmentX(1.0F);
    this.GASOILUPDATE.setPreferredSize(new Dimension(this.width / 2, 15));
    this.GASOILUPDATE.setBackground(this.white);
    this.GASOILUPDATE.add(this.heure);
    JSplitPane sp0 = new JSplitPane(1, j, this.GASOILUPDATE);
    add(sp0, "After");
    LayoutManager layout = new FlowLayout();
    this.GASOIL1.setPreferredSize(new Dimension(this.width / 3 - 40, this.height / 2 - 80));
    this.GASOIL1.setLayout(layout);
    this.GASOIL1.setBorder(blackline);
    this.GASOIL1.setAlignmentX(1.0F);
    JPanel contentGASOIL1 = new JPanel();
    contentGASOIL1.setBackground(this.white);
    contentGASOIL1.add(this.cuve_labelg1);
    contentGASOIL1.add(this.nameCuveG1);
    contentGASOIL1.add(this.capacite_labelg1);
    contentGASOIL1.add(this.capaciteCuveSSP);
    this.GASOIL1.add(contentGASOIL1);
    this.GASOIL1.setLayout(new BoxLayout(this.GASOIL1, 1));
    JPanel content2GASOIL1 = new JPanel();
    content2GASOIL1.setBackground(this.white);
    content2GASOIL1.add(this.QTE_labelg1);
    content2GASOIL1.add(this.QTECuveSSP);
    content2GASOIL1.add(this.VIDE_labelg1);
    content2GASOIL1.add(this.VIDECuveSSP);
    this.GASOIL1.add(content2GASOIL1);
    JPanel content3GASOIL1 = new JPanel();
    content3GASOIL1.setBackground(this.white);
    content3GASOIL1.add(this.progressBarSSP);
    this.GASOIL1.add(content3GASOIL1);
    this.content4GASOIL1 = new JPanel();
    this.content4GASOIL1.setBackground(this.white);
    this.content4GASOIL1.add(this.EauLCuveG1);
    this.content4GASOIL1.add(this.eauCuveSSP);
    this.content4GASOIL1.add(this.TempCuveG1);
    this.content4GASOIL1.add(this.tempCuveSSP);
    String spaces = "                                                                  ";
    JLabel jl33 = new JLabel(spaces);
    this.content4GASOIL1.add(jl33);
    JLabel jl2 = new JLabel();
jl2.setIcon(ICON_TEMP);
    this.content4GASOIL1.add(jl2);
    this.content4GASOIL1.add(this.validbuttonG1);
    this.jl3 = new JLabel();
    this.jl3.setIcon(ICON_UP);
    this.jl3.setVisible(false);
    this.content4GASOIL1.add(this.jl3);
    
    this.GASOIL1.add(this.content4GASOIL1);
    JPanel content6GASOIL1 = new JPanel();
    content6GASOIL1.add(this.EauMMCuveG1);
    content6GASOIL1.add(this.eaumpCuveSSP);
    content6GASOIL1.setBackground(this.white);
    this.GASOIL1.add(content6GASOIL1);
    GridBagConstraints gc = new GridBagConstraints();
    gc.anchor = 25;
    
    JPanel content6GASOIL3 = new JPanel(new GridBagLayout());
    JPanel content6GASOIL1Num = new JPanel();
    JLabel l = new JLabel("1");
    l.setForeground(Color.red);
    content6GASOIL1Num.setPreferredSize(new Dimension(50, 50));
    content6GASOIL1Num.setBorder(blackline);
    content6GASOIL1Num.add(l);
    GridBagConstraints c0 = new GridBagConstraints();
    JPanel content7GASOIL1 = new JPanel(new GridBagLayout());
    content6GASOIL3.add(content6GASOIL1Num, gc);
    GridBagConstraints gc1 = new GridBagConstraints();
    gc1.insets = new Insets(0, 0, 0, -210);
    this.gs = new JSpeedometer(10, "Jeauge");
    this.gs.setColors(this.green, null, Color.BLACK);
    this.gs.setSpeed(this.progressBarSSP.getValue());
    content6GASOIL3.add((Component)this.gs, gc1);
    c0.anchor = 25;
    c0.insets = new Insets(0, 0, -150, 15);
    c0.gridx = 0;
    c0.gridy = 0;
    this.alG1 = new JLabel();
    this.alG1.setIcon(ICON_ALARM);
    this.alG1.setVisible(false);
    this.alG1.setLayout(new FlowLayout(1));
    content7GASOIL1.add(this.alG1, c0);
    c0.gridy = 1;
    c0.weightx = 1.0D;
    c0.weighty = 0.1D;
    c0.fill = 0;
    c0.anchor = 24;
    Insets i = new Insets(70, 0, 0, 10);
    c0.insets = i;
    this.progressBarG1_2.setOrientation(1);
    this.progressBarG1_2.setStringPainted(true);
    this.progressBarG1_2.setPreferredSize(new Dimension(132, 68));
    content7GASOIL1.setBackground(this.white);
    content7GASOIL1.add(this.progressBarSSP_2, c0);
    JLabel jlG1 = new JLabel();
    jlG1.setIcon(ICON_RESERVOIR);
    jlG1.setLayout(new FlowLayout(1));
    c0.anchor = 22;
    c0.insets = new Insets(0, 0, 0, 5);
    c0.gridx = 0;
    c0.gridy = 1;
    content7GASOIL1.add(jlG1, c0);
    gc.anchor = 20;
    content6GASOIL3.add(jl33);
    gc.anchor = 26;
    content6GASOIL3.add(content7GASOIL1, gc);
    content6GASOIL3.setBackground(this.white);
    this.GASOIL1.add(content6GASOIL3);
    this.GASOIL2.setPreferredSize(new Dimension(this.width / 3 - 40, this.height / 2 - 80));
    this.GASOIL2.setLayout(layout);
    this.GASOIL2.setBorder(blackline);
    this.GASOIL2.setAlignmentX(1.0F);
    JPanel contentGASOIL2 = new JPanel();
    contentGASOIL2.setBackground(this.white);
    contentGASOIL2.add(this.cuve_labelg2);
    contentGASOIL2.add(this.nameCuveG2);
    contentGASOIL2.add(this.capacite_labelg2);
    contentGASOIL2.add(this.capaciteCuveSSP2);
    this.GASOIL2.add(contentGASOIL2);
    this.GASOIL2.setLayout(new BoxLayout(this.GASOIL2, 1));
    JPanel content2GASOIL2 = new JPanel();
    content2GASOIL2.setBackground(this.white);
    content2GASOIL2.add(this.QTE_labelg2);
    content2GASOIL2.add(this.QTECuveSSP2);
    content2GASOIL2.add(this.VIDE_labelg2);
    content2GASOIL2.add(this.VIDECuveSSP2);
    this.GASOIL2.add(content2GASOIL2);
    JPanel content3GASOIL2 = new JPanel();
    content3GASOIL2.setBackground(this.white);
    content3GASOIL2.add(this.progressBarG2);
    this.GASOIL2.add(content3GASOIL2);
    JPanel content4GASOIL2 = new JPanel();
    content4GASOIL2.setBackground(this.white);
    content4GASOIL2.add(this.EauLCuveG2);
    content4GASOIL2.add(this.eauCuveSSP2);
    content4GASOIL2.add(this.TempCuveG2);
    content4GASOIL2.add(this.tempCuveSSP2);
    JLabel jlg233 = new JLabel(spaces);
    
    JLabel jlg2 = new JLabel();
    jlg2.setIcon(ICON_TEMP);
    content4GASOIL2.add(jlg2);
    content4GASOIL2.add(this.validbuttonG2);
    this.jl_2 = new JLabel();
    this.jl_2.setIcon(ICON_UP);
    
    this.jl_2.setVisible(false);
    content4GASOIL2.add(this.jl_2);
    this.GASOIL2.add(content4GASOIL2);
    JPanel content6GASOIL2 = new JPanel();
    content6GASOIL2.add(this.EauMMCuveSSP2);
    content6GASOIL2.add(this.eaumpCuveSSP2);
    content6GASOIL2.setBackground(this.white);
    this.GASOIL2.add(content6GASOIL2);
    JPanel content7GASOIL2 = new JPanel(new GridBagLayout());
    content7GASOIL2.setBackground(this.white);
    JLabel jlG2_3 = new JLabel();
    jlG2_3.setIcon(ICON_PROGRESS_BG);
    
    JLabel jlG2_5 = new JLabel(spaces);
    
    JLabel jlG2_4 = new JLabel();
    jlG2_4.setIcon(ICON_RESERVOIR);

    
    GridBagConstraints gc2 = new GridBagConstraints();
    gc2.anchor = 25;
    JPanel content6GASOIL_23 = new JPanel(new GridBagLayout());
    JPanel content6GASOIL1Num2 = new JPanel();
    JLabel l2 = new JLabel("2");
    l2.setForeground(Color.red);
    content6GASOIL1Num2.setPreferredSize(new Dimension(50, 50));
    content6GASOIL1Num2.setBorder(blackline);
    content6GASOIL1Num2.add(l2);
    content6GASOIL_23.add(content6GASOIL1Num2, gc2);
    
    GridBagConstraints gcG1 = new GridBagConstraints();
    gcG1.insets = new Insets(0, 0, 0, -210);
    this.g2 = new JSpeedometer(10, "Jeauge");
    this.g2.setColors(this.green, null, Color.BLACK);
    this.g2.setSpeed(this.progressBarG2.getValue());
    content6GASOIL_23.add((Component)this.g2, gc1);
    
    GridBagConstraints c5 = new GridBagConstraints();
    
    c5.anchor = 25;
    c5.insets = new Insets(0, 0, -150, 15);
    c5.gridx = 0;
    c5.gridy = 0;
    this.alG2 = new JLabel();
    this.alG2.setIcon(ICON_ALARM);
    this.alG2.setLayout(new FlowLayout(1));
    this.alG2.setVisible(false);
    content7GASOIL2.add(this.alG2, c5);

    
    c5.gridy = 1;
    c5.weightx = 1.0D;
    c5.weighty = 0.1D;
    c5.fill = 0;
    c5.anchor = 24;
    
    c5.insets = i;
    this.progressBarG2_2.setOrientation(1);
    this.progressBarG2_2.setStringPainted(true);
    this.progressBarG2_2.setPreferredSize(new Dimension(132, 68));
    content7GASOIL2.add(this.progressBarSSP2_2, c5);
    c5.anchor = 22;
    c5.insets = new Insets(0, 0, 0, 5);
    c5.gridx = 0;
    c5.gridy = 1;
    content7GASOIL2.add(jlG2_4, c5);
    JLabel jl_23 = new JLabel(spaces);
    gc2.anchor = 20;
    content6GASOIL_23.add(jl_23);
    gc.anchor = 26;
    content6GASOIL_23.add(content7GASOIL2, gc);
    content6GASOIL_23.setBackground(this.white);
    this.GASOIL2.add(content6GASOIL_23);
    
    this.SSP.setPreferredSize(new Dimension(this.width / 3 - 40, this.height / 2 - 80));
    this.SSP.setLayout(layout);
    this.SSP.setBorder(blackline);
    this.SSP.setAlignmentX(1.0F);
    JPanel contentGASOILSSP = new JPanel();
    contentGASOILSSP.setBackground(this.white);
    contentGASOILSSP.add(this.cuve_labelssp);
    contentGASOILSSP.add(this.nameCuveSSP);
    contentGASOILSSP.add(this.capacite_labelssp);
    contentGASOILSSP.add(this.capaciteCuveG1);
    this.SSP.add(contentGASOILSSP);
    this.SSP.setLayout(new BoxLayout(this.SSP, 1));
    JPanel content2GASOILSSP = new JPanel();
    content2GASOILSSP.setBackground(this.white);
    content2GASOILSSP.add(this.QTE_labelssp);
    content2GASOILSSP.add(this.QTECuveG1);
    content2GASOILSSP.add(this.VIDE_labelssp);
    content2GASOILSSP.add(this.VIDECuveG1);
    this.SSP.add(content2GASOILSSP);
    JPanel content3GASOILSSP = new JPanel();
    content3GASOILSSP.setBackground(this.white);
    content3GASOILSSP.add(this.progressBarG1);
    this.SSP.add(content3GASOILSSP);
    JPanel content4GASOILSSP = new JPanel();
    content4GASOILSSP.setBackground(this.white);
    content4GASOILSSP.add(this.EauLCuveSSP);
    content4GASOILSSP.add(this.eauCuveG1);
    content4GASOILSSP.add(this.TempCuveSSP);
    content4GASOILSSP.add(this.tempCuveG1);
    JLabel jlSSP33 = new JLabel(spaces);
    
    JLabel jlSSP = new JLabel();
    jlSSP.setIcon(ICON_TEMP);
    content4GASOILSSP.add(jlSSP);










    
    content4GASOILSSP.add(this.validbuttonSSP);
    this.jls_1 = new JLabel();
    this.jls_1.setIcon(ICON_UP);
    this.jls_1.setVisible(false);
    content4GASOILSSP.add(this.jls_1);
    
    this.SSP.add(content4GASOILSSP);
    JPanel content6GASOILSSP = new JPanel();
    content6GASOILSSP.add(this.EauMMCuveSSP);
    content6GASOILSSP.add(this.eaumCuveG1);
    content6GASOILSSP.setBackground(this.white);
    this.SSP.add(content6GASOILSSP);
    
    JPanel content7GASOILSSP = new JPanel(new GridBagLayout());
    content7GASOILSSP.setBackground(this.white);
    JLabel jlSSP_3 = new JLabel();
    jlSSP_3.setIcon(ICON_PROGRESS_BG);
    
    JLabel jlSSP2_4 = new JLabel();
    GridBagConstraints gc3 = new GridBagConstraints();
    gc3.anchor = 25;
    JPanel content6SSPL_23 = new JPanel(new GridBagLayout());
    JPanel content6SSP1Num2 = new JPanel();
    JLabel l3 = new JLabel("3");
    l3.setForeground(Color.red);
    content6SSP1Num2.setPreferredSize(new Dimension(50, 50));
    content6SSP1Num2.setBorder(blackline);
    content6SSP1Num2.add(l3);
    content6SSPL_23.add(content6SSP1Num2, gc3);
    
    GridBagConstraints gcSSP = new GridBagConstraints();
    
    gcSSP.insets = new Insets(0, 0, 0, -210);
    this.g3 = new JSpeedometer(10, "Jeauge");
    this.g3.setColors(this.green, null, Color.BLACK);
    this.g3.setSpeed(this.progressBarG1.getValue());
    content6SSPL_23.add((Component)this.g3, gcSSP);
    
    GridBagConstraints c = new GridBagConstraints();
    
    c.anchor = 25;
    c.insets = new Insets(0, 0, -150, 15);
    c.gridx = 0;
    c.gridy = 0;
    this.alSP1 = new JLabel();
    this.alSP1.setIcon(ICON_ALARM);
    this.alSP1.setLayout(new FlowLayout(1));
    this.alSP1.setVisible(false);
    content7GASOILSSP.add(this.alSP1, c);
    
    c.gridy = 1;
    c.weightx = 1.0D;
    c.weighty = 0.1D;
    c.fill = 0;
    c.anchor = 24;
    
    c.insets = i;
    
    jlSSP2_4.setIcon(ICON_RESERVOIR);
    
    content7GASOILSSP.add(this.progressBarG1_2, c);
    c.anchor = 22;
    c.insets = new Insets(0, 0, 0, 5);
    c.gridx = 0;
    c.gridy = 1;
    content7GASOILSSP.add(jlSSP2_4, c);
    this.SSP.add(content7GASOILSSP);
    
    gc3.anchor = 20;
    JLabel jl44 = new JLabel(spaces);
    content6SSPL_23.add(jl44);
    gc.anchor = 26;
    content6SSPL_23.add(content7GASOILSSP, gc3);
    content6SSPL_23.setBackground(this.white);
    this.SSP.add(content6SSPL_23);
    
    JSplitPane sp = new JSplitPane(1, this.GASOIL1, this.GASOIL2);
    JSplitPane sp2 = new JSplitPane(1, sp, this.SSP);
    add(sp2, "Center");
    
    this.PETROLE.setPreferredSize(new Dimension(this.width / 3 - 40, this.height / 2 - 80));
    this.PETROLE.setLayout(layout);
    this.PETROLE.setBorder(blackline);
    this.PETROLE.setAlignmentX(1.0F);
    JPanel contentGASOILPET = new JPanel();
    contentGASOILPET.setBackground(this.white);
    contentGASOILPET.add(this.cuve_labelPET);
    contentGASOILPET.add(this.nameCuvePET);
    contentGASOILPET.add(this.capacite_labelPET);
    contentGASOILPET.add(this.capaciteCuvePET);
    this.PETROLE.add(contentGASOILPET);
    this.PETROLE.setLayout(new BoxLayout(this.PETROLE, 1));
    JPanel content2GASOILPET = new JPanel();
    content2GASOILPET.setBackground(this.white);
    content2GASOILPET.add(this.QTE_labelPET);
    content2GASOILPET.add(this.QTECuvePET);
    content2GASOILPET.add(this.VIDE_labelPET);
    content2GASOILPET.add(this.VIDECuvePET);
    this.PETROLE.add(content2GASOILPET);
    JPanel content3GASOILPET = new JPanel();
    content3GASOILPET.setBackground(this.white);
    content3GASOILPET.add(this.progressBarPET);
    this.PETROLE.add(content3GASOILPET);
    JPanel content4GASOILPET = new JPanel();
    content4GASOILPET.setBackground(this.white);
    content4GASOILPET.add(this.EauLCuvePET);
    content4GASOILPET.add(this.eauCuvePET);
    content4GASOILPET.add(this.TempCuvePET);
    content4GASOILPET.add(this.tempCuvePET);
    JLabel jlPET33 = new JLabel(spaces);
    
    JLabel jlPET = new JLabel();
    jlPET.setIcon(ICON_TEMP);
    content4GASOILPET.add(jlPET);
    content4GASOILPET.add(this.validbuttonPET);
    
    this.jlp_1 = new JLabel();
    this.jlp_1.setIcon(ICON_UP);
    this.jlp_1.setVisible(false);










    
    content4GASOILPET.add(this.jlp_1);
    this.PETROLE.add(content4GASOILPET);
    JPanel content6GASOILPET = new JPanel();
    content6GASOILPET.add(this.EauMMCuvePET);
    content6GASOILPET.add(this.eaumpCuvePET);
    content6GASOILPET.setBackground(this.white);
    this.PETROLE.add(content6GASOILPET);
    JPanel content7GASOILPET = new JPanel(new GridBagLayout());
    content7GASOILPET.setBackground(this.white);
    JLabel jlPET_3 = new JLabel();
    jlPET_3.setIcon(ICON_PROGRESS_BG);
    
    JLabel jlPET_5 = new JLabel(spaces);
    
    JLabel jlPET2_4 = new JLabel();
    jlPET2_4.setIcon(ICON_RESERVOIR);

    
    GridBagConstraints gc4 = new GridBagConstraints();
    gc4.anchor = 25;
    JPanel content6PETL_23 = new JPanel(new GridBagLayout());
    JPanel content6PET1Num2 = new JPanel();
    JLabel l4 = new JLabel("4");
    l4.setForeground(Color.red);
    content6PET1Num2.setPreferredSize(new Dimension(50, 50));
    content6PET1Num2.setBorder(blackline);
    content6PET1Num2.add(l4);
    content6PETL_23.add(content6PET1Num2, gc4);
    
    GridBagConstraints gcPET = new GridBagConstraints();
    gcPET.insets = new Insets(0, 0, 0, -210);
    this.g4 = new JSpeedometer(10, "Jeauge");
    this.g4.setColors(this.green, null, Color.BLACK);
    this.g4.setSpeed(this.progressBarPET.getValue());
    content6PETL_23.add((Component)this.g4, gcPET);
    
    GridBagConstraints c6 = new GridBagConstraints();

    
    c6.anchor = 25;
    c6.insets = new Insets(0, 0, -150, 15);
    c6.gridx = 0;
    c6.gridy = 0;
    this.alSPET = new JLabel();
    this.alSPET.setIcon(ICON_ALARM);
    this.alSPET.setLayout(new FlowLayout(1));
    this.alSPET.setVisible(false);
    content7GASOILPET.add(this.alSPET, c6);

    
    c6.gridy = 1;
    c6.weightx = 1.0D;
    c6.weighty = 0.1D;
    c6.fill = 0;
    c6.anchor = 24;
    
    c6.insets = i;
    
    jlPET2_4.setIcon(ICON_RESERVOIR);
    this.progressBarPET_2.setOrientation(1);
    this.progressBarPET_2.setStringPainted(true);
    this.progressBarPET_2.setPreferredSize(new Dimension(132, 68));
    this.progressBarPET_2.setForeground(new Color(233, 159, 0));
    
    content7GASOILPET.add(this.progressBarPET_2, c6);
    c6.anchor = 22;
    c6.insets = new Insets(0, 0, 0, 5);
    c6.gridx = 0;
    c6.gridy = 1;
    content7GASOILPET.add(jlPET2_4, c6);
    JLabel jl55 = new JLabel(spaces);
    content6PETL_23.add(jl55);
    
    gc4.anchor = 26;
    content6PETL_23.add(content7GASOILPET, gc4);
    content6PETL_23.setBackground(this.white);
    this.PETROLE.add(content6PETL_23);
    
    this.GASOIL50.setPreferredSize(new Dimension(this.width / 3 - 40, this.height / 2 - 80));
    this.GASOIL50.setLayout(layout);
    this.GASOIL50.setBorder(blackline);
    this.GASOIL50.setAlignmentX(1.0F);
    JPanel contentGASOILG50 = new JPanel();
    contentGASOILG50.setBackground(this.white);
    contentGASOILG50.add(this.cuve_labelG50);
    contentGASOILG50.add(this.nameCuveG50);
    contentGASOILG50.add(this.capacite_labelG50);
    contentGASOILG50.add(this.capaciteCuveG50);
    this.GASOIL50.add(contentGASOILG50);
    this.GASOIL50.setLayout(new BoxLayout(this.GASOIL50, 1));
    JPanel content2GASOILG50 = new JPanel();
    content2GASOILG50.setBackground(this.white);
    content2GASOILG50.add(this.QTE_labelG50);
    content2GASOILG50.add(this.QTECuveG50);
    content2GASOILG50.add(this.VIDE_labelG50);
    content2GASOILG50.add(this.VIDECuveG50);
    this.GASOIL50.add(content2GASOILG50);
    JPanel content3GASOILG50 = new JPanel();
    content3GASOILG50.setBackground(this.white);
    content3GASOILG50.add(this.progressBarG50);
    this.GASOIL50.add(content3GASOILG50);
    JPanel content4GASOILG50 = new JPanel();
    content4GASOILG50.setBackground(this.white);
    content4GASOILG50.add(this.EauLCuveG50);
    content4GASOILG50.add(this.eauCuveG50);
    content4GASOILG50.add(this.TempCuveG50);
    content4GASOILG50.add(this.tempCuveG50);
    JLabel jlG5033 = new JLabel(spaces);
    
    JLabel jlG50 = new JLabel();
    jlG50.setIcon(ICON_TEMP);
    content4GASOILG50.add(jlG50);
    content4GASOILG50.add(this.validbuttonG50);
    this.jlg50 = new JLabel();
    this.jlg50.setIcon(ICON_UP);
    this.jlg50.setVisible(false);










    
    content4GASOILG50.add(this.jlg50);
    this.GASOIL50.add(content4GASOILG50);
    JPanel content6GASOILG50 = new JPanel();
    content6GASOILG50.add(this.EauMMCuveG50);
    content6GASOILG50.add(this.eaumpCuveG50);
    content6GASOILG50.setBackground(this.white);
    this.GASOIL50.add(content6GASOILG50);
    JPanel content7GASOILG50 = new JPanel(new GridBagLayout());
    content7GASOILG50.setBackground(this.white);
    JLabel jlG50_3 = new JLabel();
    jlG50_3.setIcon(ICON_PROGRESS_BG);
    
    JLabel jlG50_5 = new JLabel(spaces);
    
    JLabel jlG502_4 = new JLabel();
    jlG502_4.setIcon(ICON_RESERVOIR);
    GridBagConstraints gc5 = new GridBagConstraints();
    gc5.anchor = 25;
    JPanel content6G50_23 = new JPanel(new GridBagLayout());
    JPanel content6G50Num2 = new JPanel();
    JLabel l5 = new JLabel("5");
    l5.setForeground(Color.red);
    content6G50Num2.setPreferredSize(new Dimension(50, 50));
    content6G50Num2.setBorder(blackline);
    content6G50Num2.add(l5);
    content6G50_23.add(content6G50Num2, gc5);
    GridBagConstraints gcG50 = new GridBagConstraints();
    gcG50.insets = new Insets(0, 0, 0, -210);
    this.g5 = new JSpeedometer(10, "Jeauge");
    this.g5.setColors(this.green, null, Color.BLACK);
    this.g5.setSpeed(this.progressBarG50.getValue());
    content6G50_23.add((Component)this.g5, gcG50);
    GridBagConstraints c7 = new GridBagConstraints();
    c7.anchor = 25;
    c7.insets = new Insets(0, 0, -150, 15);
    c7.gridx = 0;
    c7.gridy = 0;
    this.alG50 = new JLabel();
    this.alG50.setIcon(ICON_ALARM);
    this.alG50.setLayout(new FlowLayout(1));
    this.alG50.setVisible(false);
    content7GASOILG50.add(this.alG50, c7);
    c7.gridy = 1;
    c7.weightx = 1.0D;
    c7.weighty = 0.1D;
    c7.fill = 0;
    c7.anchor = 24;
    c7.insets = i;
    jlG502_4.setIcon(ICON_RESERVOIR);
    this.progressBarG50_2.setOrientation(1);
    this.progressBarG50_2.setStringPainted(true);
    this.progressBarG50_2.setPreferredSize(new Dimension(132, 68));
    content7GASOILG50.add(this.progressBarG50_2, c7);
    c7.anchor = 22;
    c7.insets = new Insets(0, 0, 0, 5);
    c7.gridx = 0;
    c7.gridy = 1;
    content7GASOILG50.add(jlG502_4, c7);
    gc5.anchor = 20;
    JLabel jl66 = new JLabel(spaces);
    content6G50_23.add(jl66);
    gc5.anchor = 26;
    content6G50_23.add(content7GASOILG50, gc5);
    content6G50_23.setBackground(this.white);
    this.GASOIL50.add(content6G50_23);
    
    this.SSP2.setPreferredSize(new Dimension(this.width / 3 - 40, this.height / 2 - 80));
    this.SSP2.setLayout(layout);
    this.SSP2.setBorder(blackline);
    this.SSP2.setAlignmentX(1.0F);
    JPanel contentGASOILSSP2 = new JPanel();
    contentGASOILSSP2.setBackground(this.white);
    contentGASOILSSP2.add(this.cuve_labelSSP2);
    contentGASOILSSP2.add(this.nameCuveSSP2);
    contentGASOILSSP2.add(this.capacite_labelSSP2);
    contentGASOILSSP2.add(this.capaciteCuveG2);
    this.SSP2.add(contentGASOILSSP2);
    this.SSP2.setLayout(new BoxLayout(this.SSP2, 1));
    JPanel content2GASOILSSP2 = new JPanel();
    content2GASOILSSP2.setBackground(this.white);
    content2GASOILSSP2.add(this.QTE_labelSSP2);
    content2GASOILSSP2.add(this.QTECuveG2);
    content2GASOILSSP2.add(this.VIDE_labelSSP2);
    content2GASOILSSP2.add(this.VIDECuveG2);
    this.SSP2.add(content2GASOILSSP2);
    JPanel content3GASOILSSP2 = new JPanel();
    content3GASOILSSP2.setBackground(this.white);
    content3GASOILSSP2.add(this.progressBarSSP2);
    this.SSP2.add(content3GASOILSSP2);
    JPanel content4GASOILSSP2 = new JPanel();
    content4GASOILSSP2.setBackground(this.white);
    content4GASOILSSP2.add(this.EauLCuveSSP2);
    content4GASOILSSP2.add(this.eauCuveSSP2);
    content4GASOILSSP2.add(this.TempCuveSSP2);
    content4GASOILSSP2.add(this.tempCuveG2);
    JLabel jlSSP233 = new JLabel(spaces);
    
    JLabel jlSSP2 = new JLabel();
    jlSSP2.setIcon(ICON_TEMP);
    content4GASOILSSP2.add(jlSSP2);
    content4GASOILSSP2.add(this.validbuttonSSP2);
    this.jls2 = new JLabel();

    
    this.jls2.setIcon(ICON_UP);
    this.jls2.setVisible(false);










    
    content4GASOILSSP2.add(this.jls2);
    this.SSP2.add(content4GASOILSSP2);
    JPanel content6GASOILSSP2 = new JPanel();
    content6GASOILSSP2.add(this.EauMMCuveSSP2);
    content6GASOILSSP2.add(this.eaumCuveG2);
    content6GASOILSSP2.setBackground(this.white);
    this.SSP2.add(content6GASOILSSP2);
    JPanel content7GASOILSSP2 = new JPanel(new GridBagLayout());
    content7GASOILSSP2.setBackground(this.white);
    JLabel jlSSP2_3 = new JLabel();
    jlSSP2_3.setIcon(ICON_PROGRESS_BG);
    
    JLabel jlSSP2_5 = new JLabel(spaces);
    
    JLabel jlSSP22_4 = new JLabel();
    jlSSP22_4.setIcon(ICON_RESERVOIR);
    GridBagConstraints gc6 = new GridBagConstraints();
    gc6.anchor = 25;
    JPanel contentSSP2_23 = new JPanel(new GridBagLayout());
    JPanel content6SSP2Num2 = new JPanel();
    JLabel l6 = new JLabel("6");
    l6.setForeground(Color.red);
    content6SSP2Num2.setPreferredSize(new Dimension(50, 50));
    content6SSP2Num2.setBorder(blackline);
    content6SSP2Num2.add(l6);
    contentSSP2_23.add(content6SSP2Num2, gc6);
    
    GridBagConstraints gcSSP2 = new GridBagConstraints();
    gcSSP2.insets = new Insets(0, 0, 0, -210);
    this.g6 = new JSpeedometer(10, "Jeauge");
    this.g6.setColors(this.green, null, Color.BLACK);
    this.g6.setSpeed(this.progressBarSSP2.getValue());
    contentSSP2_23.add((Component)this.g6, gcSSP2);
    
    GridBagConstraints c2 = new GridBagConstraints();
    
    c2.anchor = 25;
    c2.insets = new Insets(0, 0, -150, 15);
    c2.gridx = 0;
    c2.gridy = 0;
    this.alSSP2 = new JLabel();
    this.alSSP2.setIcon(ICON_ALARM);
    this.alSSP2.setLayout(new FlowLayout(1));
    this.alSSP2.setVisible(false);
    content7GASOILSSP2.add(this.alSSP2, c2);
    c2.gridy = 1;
    c2.weightx = 1.0D;
    c2.weighty = 0.1D;
    c2.fill = 0;
    c2.anchor = 24;
    
    c2.insets = i;
    
    jlSSP22_4.setIcon(ICON_RESERVOIR);
    this.progressBarSSP2_2.setOrientation(1);
    this.progressBarSSP2_2.setStringPainted(true);
    this.progressBarSSP2_2.setPreferredSize(new Dimension(132, 68));
    
    content7GASOILSSP2.add(this.progressBarG2_2, c2);
    c2.anchor = 22;
    c2.insets = new Insets(0, 0, 0, 5);
    c2.gridx = 0;
    c2.gridy = 1;
    content7GASOILSSP2.add(jlSSP22_4, c2);
    gc6.anchor = 20;
    JLabel jl77 = new JLabel(spaces);
    contentSSP2_23.add(jl77);
    
    gc6.anchor = 26;
    contentSSP2_23.add(content7GASOILSSP2, gc6);
    contentSSP2_23.setBackground(this.white);
    this.SSP2.add(contentSSP2_23);
    
    JSplitPane sp3 = new JSplitPane(1, this.PETROLE, this.GASOIL50);
    JSplitPane sp4 = new JSplitPane(1, sp3, this.SSP2);
    add(sp4, "Center");
    revalidate();
  }

  private void startRefreshTimer() {
    this.refreshTimer = new Timer(AppConfig.REFRESH_SECONDS * 1000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        refreshAsync(false);
      }
    });
    this.refreshTimer.setRepeats(true);
    this.refreshTimer.start();
  }

  private void refreshAsync(final boolean initialLoad) {
    if (this.refreshWorker != null && !this.refreshWorker.isDone()) {
      return;
    }
    this.refreshWorker = new SwingWorker<List<Geauge>, Void>() {
      protected List<Geauge> doInBackground() throws Exception {
        return loadGaugeData();
      }
      protected void done() {
        try {
          applyGaugeData(get(), initialLoad);
        } catch (Exception ex) {
          AppLogger.error("Gauge refresh failed", ex);
        }
      }
    };
    this.refreshWorker.execute();
  }

  private List<Geauge> loadGaugeData() throws ClassNotFoundException {
    ConnextionFTP conn = new ConnextionFTP();
    conn.connexionFTP2(new Date());
    return conn.selectGeauge();
  }

  private void updateMovement(JLabel arrow, double current, double previous, boolean initialLoad, boolean oneLiterDelta) {
    if (initialLoad) {
      arrow.setVisible(false);
      return;
    }
    if (current < previous) {
      arrow.setIcon(ICON_DOWN);
      arrow.setVisible(true);
    } else if ((!oneLiterDelta && current > previous) || (oneLiterDelta && current - previous >= 1.0D)) {
      arrow.setIcon(ICON_UP);
      arrow.setVisible(true);
    } else {
      arrow.setVisible(false);
    }
  }

  private boolean updateAlarmIcon(JLabel alarm, double level, int min) {
    boolean active = level <= min;
    alarm.setVisible(active);
    return active;
  }

  private int percent(double level, double capacity) {
    if (capacity <= 0.0D) {
      return 0;
    }
    return (int)(level * 100.0D / capacity);
  }

  private void applyGaugeData(List<Geauge> listGeauge, boolean initialLoad) {
    if (listGeauge == null) {
      return;
    }
    DecimalFormat df = new DecimalFormat("#,###.000");
    boolean testG1 = false;
    boolean testG2 = false;
    boolean testSP1 = false;
    boolean testSP2 = false;
    boolean testPet = false;
    boolean testG50 = false;
    for (Geauge g : listGeauge) {
      double level = g.getLevel();
      if (g.getCuve().contains(AppConfig.TANK_1)) {
        this.QTECuveSSP.setText(df.format(level));
        updateMovement(this.jl3, level, this.Oqte_SSP1, initialLoad, false);
        this.Oqte_SSP1 = level;
        this.qte_SSP1 = level;
        testSP1 = updateAlarmIcon(this.alG1, level, AppConfig.ALERT_TANK_1_MIN);
        this.VIDECuveSSP.setText(df.format(g.getCreux()));
        this.progressBarSSP.setValue(percent(level, AppConfig.CAPACITY_1));
        this.progressBarSSP_2.setValue(percent(level, AppConfig.CAPACITY_1));
        this.tempCuveSSP.setText(df.format(g.getTemperature()));
        this.gs.setSpeed(this.progressBarSSP_2.getValue());
      } else if (g.getCuve().contains(AppConfig.TANK_2)) {
        this.QTECuveSSP2.setText(df.format(level));
        updateMovement(this.jl_2, level, this.Oqte_SSP2, initialLoad, false);
        this.Oqte_SSP2 = level;
        this.qte_SSP2 = level;
        testSP2 = updateAlarmIcon(this.alG2, level, AppConfig.ALERT_TANK_2_MIN);
        this.VIDECuveSSP2.setText(df.format(g.getCreux()));
        this.progressBarG2.setValue(percent(level, AppConfig.CAPACITY_2));
        this.progressBarSSP2_2.setValue(percent(level, AppConfig.CAPACITY_2));
        this.tempCuveSSP2.setText(df.format(g.getTemperature()));
        this.g2.setSpeed(this.progressBarSSP2_2.getValue());
      } else if (g.getCuve().contains(AppConfig.TANK_3)) {
        this.QTECuvePET.setText(df.format(level));
        updateMovement(this.jlp_1, level, this.Oqte_PET, initialLoad, true);
        this.Oqte_PET = level;
        this.qte_PET = level;
        testPet = updateAlarmIcon(this.alSPET, level, AppConfig.ALERT_TANK_3_MIN);
        this.VIDECuvePET.setText(df.format(g.getCreux()));
        this.progressBarPET.setValue(percent(level, AppConfig.CAPACITY_3));
        this.progressBarPET_2.setValue(percent(level, AppConfig.CAPACITY_3));
        this.tempCuvePET.setText(df.format(g.getTemperature()));
        this.g4.setSpeed(this.progressBarPET_2.getValue());
      } else if (g.getCuve().contains(AppConfig.TANK_4)) {
        this.QTECuveG50.setText(df.format(level));
        updateMovement(this.jlg50, level, this.Oqte_G50, initialLoad, true);
        this.Oqte_G50 = level;
        this.qte_G50 = level;
        testG50 = updateAlarmIcon(this.alG50, level, AppConfig.ALERT_TANK_4_MIN);
        this.VIDECuveG50.setText(df.format(g.getCreux()));
        this.progressBarG50.setValue(percent(level, AppConfig.CAPACITY_4));
        this.progressBarG50_2.setValue(percent(level, AppConfig.CAPACITY_4));
        this.tempCuveG50.setText(df.format(g.getTemperature()));
        this.g5.setSpeed(this.progressBarG50_2.getValue());
      } else if (g.getCuve().contains(AppConfig.TANK_5)) {
        this.QTECuveG1.setText(df.format(level));
        updateMovement(this.jls_1, level, this.Oqte_G1, initialLoad, true);
        this.Oqte_G1 = level;
        this.qte_G1 = level;
        this.heure.setText("UPDATE : " + g.getHeure());
        testG1 = updateAlarmIcon(this.alSP1, level, AppConfig.ALERT_TANK_5_MIN);
        this.VIDECuveG1.setText(df.format(g.getCreux()));
        this.progressBarG1.setValue(percent(level, AppConfig.CAPACITY_5));
        this.progressBarG1_2.setValue(percent(level, AppConfig.CAPACITY_5));
        this.g3.setSpeed(this.progressBarG1_2.getValue());
        this.tempCuveG1.setText(df.format(g.getTemperature()));
      } else if (g.getCuve().contains(AppConfig.TANK_6)) {
        this.QTECuveG2.setText(df.format(level));
        updateMovement(this.jls2, level, this.Oqte_G2, initialLoad, false);
        this.Oqte_G2 = level;
        this.qte_G2 = level;
        testG2 = updateAlarmIcon(this.alSSP2, level, AppConfig.ALERT_TANK_6_MIN);
        this.VIDECuveG2.setText(df.format(g.getCreux()));
        this.progressBarSSP2.setValue(percent(level, AppConfig.CAPACITY_6));
        this.progressBarG2_2.setValue(percent(level, AppConfig.CAPACITY_6));
        this.tempCuveG2.setText(df.format(g.getTemperature()));
        this.g6.setSpeed(this.progressBarG2_2.getValue());
      }
    }
    updateAlarm(testG1 || testG2 || testSP1 || testSP2 || testPet || testG50);
    revalidate();
    repaint();
  }
  
  public void initCuve() {
    refreshAsync(false);
  }


  
  public void initCuve2() {
    refreshAsync(true);
  }

  private void updateAlarm(boolean shouldRun) {
    if (shouldRun && !this.alarmActive) {
      this.alarmService.soundLoop(AppConfig.ALARM_WAV_FILE);
      this.alarmActive = true;
    } else if (!shouldRun && this.alarmActive) {
      this.alarmService.stop();
      this.alarmActive = false;
    }
  }

  
  public void initialisercuveSSP() {
    this.eauCuveSSP = new JTextField();
    this.eauCuveSSP.setText("0,000");
    this.eauCuveSSP.setPreferredSize(new Dimension(55, 15));
    this.eauCuveSSP.setHorizontalAlignment(0);
    this.eauCuveSSP.setBorder(new LineBorder(Color.white, 1));
    this.eauCuveSSP.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eauCuveSSP.setBackground(this.white);
    
    this.tempCuveSSP = new JTextField();
    
    this.tempCuveSSP.setPreferredSize(new Dimension(55, 15));
    this.tempCuveSSP.setHorizontalAlignment(0);
    this.tempCuveSSP.setBorder(new LineBorder(Color.white, 1));
    this.tempCuveSSP.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.tempCuveSSP.setBackground(this.white);
    
    this.eaumpCuveSSP = new JTextField();
    this.eaumpCuveSSP.setText("0,000");
    this.eaumpCuveSSP.setPreferredSize(new Dimension(55, 15));
    this.eaumpCuveSSP.setHorizontalAlignment(0);
    this.eaumpCuveSSP.setBorder(new LineBorder(Color.white, 1));
    this.eaumpCuveSSP.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eaumpCuveSSP.setBackground(this.white);
    
    this.EauLCuveSSP = new JLabel("Eau(L):");



    
    this.EauMMCuveSSP = new JLabel("Eau(MM):");
    
    this.cuve_labelssp = new JLabel("Cuve: ");
    this.cuve_labelssp.setBackground(Color.black);
    
    this.EauLCuveSSP.setBackground(Color.black);
    this.EauMMCuveSSP.setBackground(Color.black);
    
    this.TempCuveSSP = new JLabel("TempÃ‚Â°:");
    this.TempCuveSSP.setBackground(Color.black);
    this.QTE_labelssp = new JLabel("Qte: ");
    this.QTE_labelssp.setBackground(Color.black);
    this.VIDE_labelssp = new JLabel("Vide: ");
    this.VIDE_labelssp.setBackground(Color.black);
    
    this.nameCuveSSP = new JTextField();
    this.nameCuveSSP.setText("5-Gasoil-1");
    this.nameCuveSSP.setPreferredSize(new Dimension(110, 20));
    this.nameCuveSSP.setHorizontalAlignment(0);
    this.nameCuveSSP.setBorder(new LineBorder(Color.white, 1));
    this.nameCuveSSP.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.nameCuveSSP.setBackground(this.white);
    this.capacite_labelssp = new JLabel("CapacitÃƒÂ©:");
    this.capacite_labelssp.setBackground(Color.black);
    
    this.capaciteCuveSSP = new JTextField();
    this.capaciteCuveSSP.setText(capacityText(AppConfig.CAPACITY_1));
    this.capaciteCuveSSP.setPreferredSize(new Dimension(110, 20));
    this.capaciteCuveSSP.setHorizontalAlignment(0);
    this.capaciteCuveSSP.setBorder(new LineBorder(Color.white, 1));
    this.capaciteCuveSSP.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.capaciteCuveSSP.setBackground(this.white);
    
    this.QTECuveSSP = new JTextField();
    this.QTECuveSSP.setText("15000");
    this.QTECuveSSP.setPreferredSize(new Dimension(110, 20));
    this.QTECuveSSP.setHorizontalAlignment(0);
    this.QTECuveSSP.setBorder(new LineBorder(Color.white, 1));
    this.QTECuveSSP.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.QTECuveSSP.setForeground(Color.BLUE);
    this.QTECuveSSP.setBackground(this.white);
    
    this.VIDECuveSSP = new JTextField();
    this.VIDECuveSSP.setText("12 125");
    this.VIDECuveSSP.setPreferredSize(new Dimension(110, 20));
    this.VIDECuveSSP.setHorizontalAlignment(0);
    this.VIDECuveSSP.setBorder(new LineBorder(Color.white, 1));
    this.VIDECuveSSP.setFont(new Font("TIME NEW ROMAN", 1, 16));
    this.VIDECuveSSP.setForeground(Color.red);
    this.VIDECuveSSP.setBackground(this.white);
    this.progressBarSSP.setMinimum(0);
    this.progressBarSSP.setMaximum(100);
    this.progressBarSSP.setStringPainted(true);
    this.progressBarSSP.setValue(70);
    this.progressBarSSP.setPreferredSize(new Dimension(400, 20));
    this.progressBarSSP.setStringPainted(true);
    this.progressBarSSP.setForeground(new Color(1, 180, 35));
    this.progressBarSSP_2 = new JProgressBar() { public int getOrientation() { byte b;
          int i;
          StackTraceElement[] arrayOfStackTraceElement;
          for (i = (arrayOfStackTraceElement = (new Throwable()).getStackTrace()).length, b = 0; b < i; ) { StackTraceElement elem = arrayOfStackTraceElement[b];
            if (elem.getMethodName().equals("paintText") || elem.getMethodName().equals("paintString"))
              return 0; 
            b++; }
          
          return 1; }
         }
      ;
    this.progressBarSSP_2.setMinimum(0);
    this.progressBarSSP_2.setMaximum(100);
    this.progressBarSSP_2.setStringPainted(true);
    
    this.progressBarSSP_2.setForeground(new Color(233, 159, 0));
    this.progressBarSSP_2.setOrientation(1);
    
    Icon c = ICON_VALID;
    this.validbuttonSSP = new JButton();
    this.validbuttonSSP.setIcon(c);
    this.validbuttonSSP.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            CheckBoxInTable c = new CheckBoxInTable();
            c.setVisible(true);
          }
        });
  }
  
  public void initialisercuveGASOIL2() {
    this.eauCuveG2 = new JTextField();
    this.eauCuveG2.setText("17,71");
    this.eauCuveG2.setPreferredSize(new Dimension(55, 15));
    this.eauCuveG2.setHorizontalAlignment(0);
    this.eauCuveG2.setBorder(new LineBorder(Color.white, 1));
    this.eauCuveG2.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eauCuveG2.setBackground(this.white);
    
    this.eaumCuveG2 = new JTextField();
    this.eaumCuveG2.setText("0,000");
    this.eaumCuveG2.setPreferredSize(new Dimension(55, 15));
    this.eaumCuveG2.setHorizontalAlignment(0);
    this.eaumCuveG2.setBorder(new LineBorder(Color.white, 1));
    this.eaumCuveG2.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eaumCuveG2.setBackground(this.white);
    
    this.tempCuveG2 = new JTextField();
    this.tempCuveG2.setText("0,000");
    this.tempCuveG2.setPreferredSize(new Dimension(55, 15));
    this.tempCuveG2.setHorizontalAlignment(0);
    this.tempCuveG2.setBorder(new LineBorder(Color.white, 1));
    this.tempCuveG2.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.tempCuveG2.setBackground(this.white);
    
    this.EauLCuveG2 = new JLabel("Eau(L):");
    this.EauLCuveG2.setBackground(Color.black);
    this.EauMMCuveG2 = new JLabel("Eau(MM):");
    this.EauMMCuveG2.setBackground(Color.black);
    this.TempCuveG2 = new JLabel("TempÃ‚Â°:");
    this.TempCuveG2.setBackground(Color.black);
    this.cuve_labelg2 = new JLabel("Cuve: ");
    this.cuve_labelg2.setBackground(Color.black);
    this.QTE_labelg2 = new JLabel("QTE: ");
    this.QTE_labelg2.setBackground(Color.black);
    this.VIDE_labelg2 = new JLabel("Vide: ");
    this.VIDE_labelg2.setBackground(Color.black);
    this.nameCuveG2 = new JTextField();
    this.nameCuveG2.setText("2-SSP15M3");
    this.nameCuveG2.setPreferredSize(new Dimension(110, 20));
    this.nameCuveG2.setHorizontalAlignment(0);
    this.nameCuveG2.setBorder(this.whiteline);
    this.nameCuveG2.setBorder(new LineBorder(Color.white, 1));
    this.nameCuveG2.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.nameCuveG2.setBackground(this.white);
    this.capacite_labelg2 = new JLabel("CapacitÃƒÂ©:");
    this.capacite_labelg2.setBackground(Color.black);
    this.capaciteCuveG2 = new JTextField();
    this.capaciteCuveG2.setText(capacityText(AppConfig.CAPACITY_6));
    this.capaciteCuveG2.setPreferredSize(new Dimension(110, 20));
    this.capaciteCuveG2.setHorizontalAlignment(0);
    this.capaciteCuveG2.setBorder(new LineBorder(Color.white, 1));
    this.capaciteCuveG2.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.capaciteCuveG2.setBackground(this.white);
    this.QTECuveG2 = new JTextField();
    
    this.QTECuveG2.setPreferredSize(new Dimension(110, 20));
    this.QTECuveG2.setHorizontalAlignment(0);
    this.QTECuveG2.setBorder(new LineBorder(Color.white, 1));
    this.QTECuveG2.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.QTECuveG2.setBackground(this.white);
    this.QTECuveG2.setForeground(Color.BLUE);
    this.VIDECuveG2 = new JTextField();
    
    this.VIDECuveG2.setPreferredSize(new Dimension(110, 20));
    this.VIDECuveG2.setForeground(Color.red);
    this.VIDECuveG2.setHorizontalAlignment(0);
    this.VIDECuveG2.setBorder(new LineBorder(Color.white, 1));
    this.VIDECuveG2.setFont(new Font("TIME NEW ROMAN", 1, 16));
    this.VIDECuveG2.setBackground(this.white);
    this.progressBarG2.setMinimum(0);
    this.progressBarG2.setMaximum(100);
    this.progressBarG2.setStringPainted(true);
    this.progressBarG2.setValue(70);
    this.progressBarG2.setPreferredSize(new Dimension(400, 20));
    this.progressBarG2.setStringPainted(true);
    this.progressBarG2.setForeground(new Color(1, 180, 35));
    this.progressBarG2_2 = new JProgressBar() { public int getOrientation() { byte b;
          int i;
          StackTraceElement[] arrayOfStackTraceElement;
          for (i = (arrayOfStackTraceElement = (new Throwable()).getStackTrace()).length, b = 0; b < i; ) { StackTraceElement elem = arrayOfStackTraceElement[b];
            if (elem.getMethodName().equals("paintText") || elem.getMethodName().equals("paintString"))
              return 0; 
            b++; }
          
          return 1; }
         }
      ;
    this.progressBarG2_2.setMinimum(0);
    this.progressBarG2_2.setMaximum(100);
    this.progressBarG2_2.setStringPainted(true);
    this.progressBarG2_2.setPreferredSize(new Dimension(132, 68));
    
    this.progressBarG2_2.setForeground(new Color(233, 159, 0));
    this.progressBarG2_2.setOrientation(1);
    Icon c = ICON_VALID;
    this.validbuttonG2 = new JButton();
    this.validbuttonG2.setIcon(c);
    this.validbuttonG2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            CheckBoxInTable c = new CheckBoxInTable();
            c.setVisible(true);
          }
        });
  }
  
  public void initialisercuveGASOIL1() {
    this.gs = new JSpeedometer(10, "Jeauge");
    this.g2 = new JSpeedometer(10, "Jeauge");
    this.g3 = new JSpeedometer(10, "Jeauge");
    this.g4 = new JSpeedometer(10, "Jeauge");
    this.g5 = new JSpeedometer(10, "Jeauge");
    this.g6 = new JSpeedometer(10, "Jeauge");
    this.eauCuveG1 = new JTextField();
    this.eauCuveG1.setText("0,000");
    this.eauCuveG1.setPreferredSize(new Dimension(55, 15));
    this.eauCuveG1.setHorizontalAlignment(0);
    this.eauCuveG1.setBorder(new LineBorder(Color.white, 1));
    this.eauCuveG1.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eauCuveG1.setBackground(this.white);
    
    this.tempCuveG1 = new JTextField();
    this.tempCuveG1.setText("0,000");
    this.tempCuveG1.setPreferredSize(new Dimension(55, 15));
    this.tempCuveG1.setHorizontalAlignment(0);
    this.tempCuveG1.setBorder(new LineBorder(Color.white, 1));
    this.tempCuveG1.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.tempCuveG1.setBackground(this.white);
    
    this.eaumCuveG1 = new JTextField();
    this.eaumCuveG1.setText("0,000");
    this.eaumCuveG1.setPreferredSize(new Dimension(55, 15));
    this.eaumCuveG1.setHorizontalAlignment(0);
    this.eaumCuveG1.setBorder(new LineBorder(Color.white, 1));
    this.eaumCuveG1.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eaumCuveG1.setBackground(this.white);
    
    this.EauLCuveG1 = new JLabel("Eau(L):");

    
    this.TempCuveG1 = new JLabel("Temp:");
    this.TempCuveG1.setBackground(Color.black);
    
    this.EauMMCuveG1 = new JLabel("Eau(MM):");
    
    this.cuve_labelg1 = new JLabel("Cuve: ");
    this.cuve_labelg1.setBackground(Color.black);
    
    this.EauLCuveG1.setBackground(Color.black);
    this.EauMMCuveG1.setBackground(Color.black);
    
    this.QTE_labelg1 = new JLabel("Qte: ");
    this.heure = new JLabel("UPDATE : chargement...");
    this.heure.setBackground(Color.black);
    this.QTE_labelg1.setBackground(Color.black);
    this.VIDE_labelg1 = new JLabel("Vide: ");
    this.VIDE_labelg1.setBackground(Color.black);
    this.nameCuveG1 = new JTextField();
    this.nameCuveG1.setText("1-SSP15M3");
    this.nameCuveG1.setPreferredSize(new Dimension(110, 20));
    this.nameCuveG1.setHorizontalAlignment(0);
    this.nameCuveG1.setBorder(new LineBorder(Color.white, 1));
    this.nameCuveG1.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.nameCuveG1.setBackground(this.white);
    this.capacite_labelg1 = new JLabel("CapacitÃƒÂ©:");
    this.capacite_labelg1.setBackground(Color.black);
    
    this.capaciteCuveG1 = new JTextField();
    this.capaciteCuveG1.setText(capacityText(AppConfig.CAPACITY_5));
    this.capaciteCuveG1.setPreferredSize(new Dimension(110, 20));
    this.capaciteCuveG1.setHorizontalAlignment(0);
    this.capaciteCuveG1.setBorder(new LineBorder(Color.white, 1));
    this.capaciteCuveG1.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.capaciteCuveG1.setBackground(this.white);
    
    this.QTECuveG1 = new JTextField();
    
    this.QTECuveG1.setPreferredSize(new Dimension(110, 20));
    this.QTECuveG1.setHorizontalAlignment(0);
    this.QTECuveG1.setBorder(new LineBorder(Color.white, 1));
    this.QTECuveG1.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.QTECuveG1.setForeground(Color.BLUE);
    this.QTECuveG1.setBackground(this.white);
    
    this.VIDECuveG1 = new JTextField();
    
    this.VIDECuveG1.setPreferredSize(new Dimension(110, 20));
    this.VIDECuveG1.setHorizontalAlignment(0);
    this.VIDECuveG1.setBorder(new LineBorder(Color.white, 1));
    this.VIDECuveG1.setFont(new Font("TIME NEW ROMAN", 1, 16));
    this.VIDECuveG1.setForeground(Color.red);
    this.VIDECuveG1.setBackground(this.white);
    this.progressBarG1.setMinimum(0);
    this.progressBarG1.setMaximum(100);
    this.progressBarG1.setStringPainted(true);
    this.progressBarG1.setValue(70);
    this.progressBarG1.setPreferredSize(new Dimension(400, 20));
    this.progressBarG1.setStringPainted(true);
    this.progressBarG1.setForeground(new Color(1, 180, 35));
    this.progressBarG1_2 = new JProgressBar() { public int getOrientation() { byte b;
          int i;
          StackTraceElement[] arrayOfStackTraceElement;
          for (i = (arrayOfStackTraceElement = (new Throwable()).getStackTrace()).length, b = 0; b < i; ) { StackTraceElement elem = arrayOfStackTraceElement[b];
            if (elem.getMethodName().equals("paintText") || elem.getMethodName().equals("paintString"))
              return 0; 
            b++; }
          
          return 1; }
         }
      ;
    this.progressBarG1_2.setMinimum(0);
    this.progressBarG1_2.setMaximum(100);
    this.progressBarG1_2.setStringPainted(true);
    this.progressBarG1_2.setPreferredSize(new Dimension(132, 68));
    this.progressBarG1_2.setForeground(new Color(233, 159, 0));
    this.progressBarG1_2.setOrientation(1);
    Icon c = ICON_VALID;
    this.validbuttonG1 = new JButton();
    this.validbuttonG1.setIcon(c);
    this.validbuttonG1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            CheckBoxInTable c = new CheckBoxInTable();
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();
            c.setSize(width, height);
            c.setTitle("Historique Des Cuves");
            c.setDefaultCloseOperation(3);
            c.setVisible(true);
          }
        });
  }
  
  public void initialisercuvePET() {
    this.eauCuvePET = new JTextField();
    this.eauCuvePET.setText("0,000");
    this.eauCuvePET.setPreferredSize(new Dimension(55, 15));
    this.eauCuvePET.setHorizontalAlignment(0);
    this.eauCuvePET.setBorder(new LineBorder(Color.white, 1));
    this.eauCuvePET.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eauCuvePET.setBackground(this.white);
    
    this.tempCuvePET = new JTextField();
    this.tempCuvePET.setText("0,000");
    this.tempCuvePET.setPreferredSize(new Dimension(55, 15));
    this.tempCuvePET.setHorizontalAlignment(0);
    this.tempCuvePET.setBorder(new LineBorder(Color.white, 1));
    this.tempCuvePET.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.tempCuvePET.setBackground(this.white);
    
    this.eaumpCuvePET = new JTextField();
    this.eaumpCuvePET.setText("0,000");
    this.eaumpCuvePET.setPreferredSize(new Dimension(55, 15));
    this.eaumpCuvePET.setHorizontalAlignment(0);
    this.eaumpCuvePET.setBorder(new LineBorder(Color.white, 1));
    this.eaumpCuvePET.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eaumpCuvePET.setBackground(this.white);
    
    this.EauLCuvePET = new JLabel("Eau(L):");

    
    this.TempCuvePET = new JLabel("Temp:");
    this.TempCuvePET.setBackground(Color.black);
    
    this.EauMMCuvePET = new JLabel("Eau(MM):");
    
    this.cuve_labelPET = new JLabel("Cuve: ");
    this.cuve_labelPET.setBackground(Color.black);
    
    this.EauLCuvePET.setBackground(Color.black);
    this.EauMMCuvePET.setBackground(Color.black);
    
    this.QTE_labelPET = new JLabel("Qte: ");
    this.QTE_labelPET.setBackground(Color.black);
    this.VIDE_labelPET = new JLabel("Vide: ");
    this.VIDE_labelPET.setBackground(Color.black);
    
    this.nameCuvePET = new JTextField();
    this.nameCuvePET.setText("3-VPower-1");
    this.nameCuvePET.setPreferredSize(new Dimension(110, 20));
    this.nameCuvePET.setHorizontalAlignment(0);
    this.nameCuvePET.setBorder(new LineBorder(Color.white, 1));
    this.nameCuvePET.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.nameCuvePET.setBackground(this.white);
    this.capacite_labelPET = new JLabel("CapacitÃƒÂ©:");
    this.capacite_labelPET.setBackground(Color.black);
    
    this.capaciteCuvePET = new JTextField();
    this.capaciteCuvePET.setText(capacityText(AppConfig.CAPACITY_3));
    this.capaciteCuvePET.setPreferredSize(new Dimension(110, 20));
    this.capaciteCuvePET.setHorizontalAlignment(0);
    this.capaciteCuvePET.setBorder(new LineBorder(Color.white, 1));
    this.capaciteCuvePET.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.capaciteCuvePET.setBackground(this.white);
    
    this.QTECuvePET = new JTextField();
    
    this.QTECuvePET.setPreferredSize(new Dimension(110, 20));
    this.QTECuvePET.setHorizontalAlignment(0);
    this.QTECuvePET.setBorder(new LineBorder(Color.white, 1));
    this.QTECuvePET.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.QTECuvePET.setForeground(Color.BLUE);
    this.QTECuvePET.setBackground(this.white);
    
    this.VIDECuvePET = new JTextField();
    
    this.VIDECuvePET.setPreferredSize(new Dimension(110, 20));
    this.VIDECuvePET.setHorizontalAlignment(0);
    this.VIDECuvePET.setBorder(new LineBorder(Color.white, 1));
    this.VIDECuvePET.setFont(new Font("TIME NEW ROMAN", 1, 16));
    this.VIDECuvePET.setForeground(Color.red);
    this.VIDECuvePET.setBackground(this.white);
    this.progressBarPET.setMinimum(0);
    this.progressBarPET.setMaximum(100);
    this.progressBarPET.setStringPainted(true);
    this.progressBarPET.setValue(70);
    this.progressBarPET.setPreferredSize(new Dimension(400, 20));
    this.progressBarPET.setStringPainted(true);
    this.progressBarPET.setForeground(new Color(1, 180, 35));
    this.progressBarPET_2 = new JProgressBar() { public int getOrientation() { byte b;
          int i;
          StackTraceElement[] arrayOfStackTraceElement;
          for (i = (arrayOfStackTraceElement = (new Throwable()).getStackTrace()).length, b = 0; b < i; ) { StackTraceElement elem = arrayOfStackTraceElement[b];
            if (elem.getMethodName().equals("paintText") || elem.getMethodName().equals("paintString"))
              return 0; 
            b++; }
          
          return 1; }
         }
      ;
    this.progressBarPET_2.setMinimum(0);
    this.progressBarPET_2.setMaximum(100);
    this.progressBarPET_2.setStringPainted(true);
    
    this.progressBarPET_2.setForeground(new Color(233, 159, 0));
    this.progressBarPET_2.setOrientation(1);
    Icon c = ICON_VALID;
    this.validbuttonPET = new JButton();
    this.validbuttonPET.setIcon(c);
    this.validbuttonPET.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            CheckBoxInTable c = new CheckBoxInTable();
            c.setVisible(true);
          }
        });
  }
  
  public void initialisercuveG50() {
    this.eauCuveG50 = new JTextField();
    this.eauCuveG50.setText("0,000");
    this.eauCuveG50.setPreferredSize(new Dimension(55, 15));
    this.eauCuveG50.setHorizontalAlignment(0);
    this.eauCuveG50.setBorder(new LineBorder(Color.white, 1));
    this.eauCuveG50.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eauCuveG50.setBackground(this.white);
    
    this.tempCuveG50 = new JTextField();
    this.tempCuveG50.setText("0,000");
    this.tempCuveG50.setPreferredSize(new Dimension(55, 15));
    this.tempCuveG50.setHorizontalAlignment(0);
    this.tempCuveG50.setBorder(new LineBorder(Color.white, 1));
    this.tempCuveG50.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.tempCuveG50.setBackground(this.white);
    
    this.eaumpCuveG50 = new JTextField();
    this.eaumpCuveG50.setText("0,000");
    this.eaumpCuveG50.setPreferredSize(new Dimension(55, 15));
    this.eaumpCuveG50.setHorizontalAlignment(0);
    this.eaumpCuveG50.setBorder(new LineBorder(Color.white, 1));
    this.eaumpCuveG50.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eaumpCuveG50.setBackground(this.white);
    
    this.EauLCuveG50 = new JLabel("Eau(L):");

    
    this.TempCuveG50 = new JLabel("Temp:");
    this.TempCuveG50.setBackground(Color.black);
    
    this.EauMMCuveG50 = new JLabel("Eau(MM):");
    
    this.cuve_labelG50 = new JLabel("Cuve: ");
    this.cuve_labelG50.setBackground(Color.black);
    this.heure.setBackground(Color.black);
    this.date = new JLabel(" ");
    this.date.setBackground(Color.black);
    this.EauLCuveG50.setBackground(Color.black);
    this.EauMMCuveG50.setBackground(Color.black);
    
    this.QTE_labelG50 = new JLabel("Qte: ");
    this.QTE_labelG50.setBackground(Color.black);
    this.VIDE_labelG50 = new JLabel("Vide: ");
    this.VIDE_labelG50.setBackground(Color.black);
    
    this.nameCuveG50 = new JTextField();
    this.nameCuveG50.setText("4-GOSS-15M");
    this.nameCuveG50.setPreferredSize(new Dimension(110, 20));
    this.nameCuveG50.setHorizontalAlignment(0);
    this.nameCuveG50.setBorder(new LineBorder(Color.white, 1));
    this.nameCuveG50.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.nameCuveG50.setBackground(this.white);
    this.capacite_labelG50 = new JLabel("CapacitÃƒÂ©:");
    this.capacite_labelG50.setBackground(Color.black);
    
    this.capaciteCuveG50 = new JTextField();
    this.capaciteCuveG50.setText(capacityText(AppConfig.CAPACITY_4));
    this.capaciteCuveG50.setPreferredSize(new Dimension(110, 20));
    this.capaciteCuveG50.setHorizontalAlignment(0);
    this.capaciteCuveG50.setBorder(new LineBorder(Color.white, 1));
    this.capaciteCuveG50.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.capaciteCuveG50.setBackground(this.white);
    
    this.QTECuveG50 = new JTextField();
    
    this.QTECuveG50.setPreferredSize(new Dimension(110, 20));
    this.QTECuveG50.setHorizontalAlignment(0);
    this.QTECuveG50.setBorder(new LineBorder(Color.white, 1));
    this.QTECuveG50.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.QTECuveG50.setForeground(Color.BLUE);
    this.QTECuveG50.setBackground(this.white);
    
    this.VIDECuveG50 = new JTextField();
    
    this.VIDECuveG50.setPreferredSize(new Dimension(110, 20));
    this.VIDECuveG50.setHorizontalAlignment(0);
    this.VIDECuveG50.setBorder(new LineBorder(Color.white, 1));
    this.VIDECuveG50.setFont(new Font("TIME NEW ROMAN", 1, 16));
    this.VIDECuveG50.setForeground(Color.red);
    this.VIDECuveG50.setBackground(this.white);
    this.progressBarG50.setMinimum(0);
    this.progressBarG50.setMaximum(100);
    this.progressBarG50.setStringPainted(true);
    this.progressBarG50.setValue(70);
    this.progressBarG50.setPreferredSize(new Dimension(400, 20));
    this.progressBarG50.setStringPainted(true);
    this.progressBarG50.setForeground(new Color(1, 180, 35));
    this.progressBarG50_2 = new JProgressBar() { public int getOrientation() { byte b;
          int i;
          StackTraceElement[] arrayOfStackTraceElement;
          for (i = (arrayOfStackTraceElement = (new Throwable()).getStackTrace()).length, b = 0; b < i; ) { StackTraceElement elem = arrayOfStackTraceElement[b];
            if (elem.getMethodName().equals("paintText") || elem.getMethodName().equals("paintString"))
              return 0; 
            b++; }
          
          return 1; }
         }
      ;
    this.progressBarG50_2.setMinimum(0);
    this.progressBarG50_2.setMaximum(100);
    this.progressBarG50_2.setStringPainted(true);
    
    this.progressBarG50_2.setForeground(new Color(233, 159, 0));
    
    this.progressBarG50_2.setForeground(new Color(233, 159, 0));
    this.progressBarG50_2.setOrientation(1);
    Icon c = ICON_VALID;
    this.validbuttonG50 = new JButton();
    this.validbuttonG50.setIcon(c);
    this.validbuttonG50.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            CheckBoxInTable c = new CheckBoxInTable();
            c.setVisible(true);
          }
        });
  }
  
  public void initialisercuveSSP2() {
    this.eauCuveSSP2 = new JTextField();
    this.eauCuveSSP2.setText("0,000");
    this.eauCuveSSP2.setPreferredSize(new Dimension(55, 15));
    this.eauCuveSSP2.setHorizontalAlignment(0);
    this.eauCuveSSP2.setBorder(new LineBorder(Color.white, 1));
    this.eauCuveSSP2.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eauCuveSSP2.setBackground(this.white);
    
    this.tempCuveSSP2 = new JTextField();
    this.tempCuveSSP2.setText("0,000");
    this.tempCuveSSP2.setPreferredSize(new Dimension(55, 15));
    this.tempCuveSSP2.setHorizontalAlignment(0);
    this.tempCuveSSP2.setBorder(new LineBorder(Color.white, 1));
    this.tempCuveSSP2.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.tempCuveSSP2.setBackground(this.white);
    
    this.eaumpCuveSSP2 = new JTextField();
    this.eaumpCuveSSP2.setText("0,000");
    this.eaumpCuveSSP2.setPreferredSize(new Dimension(55, 15));
    this.eaumpCuveSSP2.setHorizontalAlignment(0);
    this.eaumpCuveSSP2.setBorder(new LineBorder(Color.white, 1));
    this.eaumpCuveSSP2.setFont(new Font("TIME NEW ROMAN", 2, 12));
    this.eaumpCuveSSP2.setBackground(this.white);
    
    this.EauLCuveSSP2 = new JLabel("Eau(L):");

    
    this.TempCuveSSP2 = new JLabel("TempÃ‚Â°:");
    this.TempCuveSSP2.setBackground(Color.black);
    
    this.EauMMCuveSSP2 = new JLabel("Eau(MM):");
    
    this.cuve_labelSSP2 = new JLabel("Cuve: ");
    this.cuve_labelSSP2.setBackground(Color.black);
    
    this.EauLCuveSSP2.setBackground(Color.black);
    this.EauMMCuveSSP2.setBackground(Color.black);
    
    this.QTE_labelSSP2 = new JLabel("Qte: ");
    this.QTE_labelSSP2.setBackground(Color.black);
    this.VIDE_labelSSP2 = new JLabel("Vide: ");
    this.VIDE_labelSSP2.setBackground(Color.black);
    
    this.nameCuveSSP2 = new JTextField();
    this.nameCuveSSP2.setText("6-Gasoil-1");
    this.nameCuveSSP2.setPreferredSize(new Dimension(110, 20));
    this.nameCuveSSP2.setHorizontalAlignment(0);
    this.nameCuveSSP2.setBorder(new LineBorder(Color.white, 1));
    this.nameCuveSSP2.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.nameCuveSSP2.setBackground(this.white);
    this.capacite_labelSSP2 = new JLabel("CapacitÃƒÂ©:");
    this.capacite_labelSSP2.setBackground(Color.black);
    
    this.capaciteCuveSSP2 = new JTextField();
    this.capaciteCuveSSP2.setText(capacityText(AppConfig.CAPACITY_2));
    this.capaciteCuveSSP2.setPreferredSize(new Dimension(110, 20));
    this.capaciteCuveSSP2.setHorizontalAlignment(0);
    this.capaciteCuveSSP2.setBorder(new LineBorder(Color.white, 1));
    this.capaciteCuveSSP2.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.heure.setFont(new Font("TIME NEW ROMAN", 1, 16));
    this.capaciteCuveSSP2.setBackground(this.white);
    
    this.QTECuveSSP2 = new JTextField();
    
    this.QTECuveSSP2.setPreferredSize(new Dimension(110, 20));
    this.QTECuveSSP2.setHorizontalAlignment(0);
    this.QTECuveSSP2.setBorder(new LineBorder(Color.white, 1));
    this.QTECuveSSP2.setFont(new Font("TIME NEW ROMAN", 1, 14));
    this.QTECuveSSP2.setForeground(Color.BLUE);
    this.QTECuveSSP2.setBackground(this.white);
    
    this.VIDECuveSSP2 = new JTextField();
    
    this.VIDECuveSSP2.setPreferredSize(new Dimension(110, 20));
    this.VIDECuveSSP2.setHorizontalAlignment(0);
    this.VIDECuveSSP2.setBorder(new LineBorder(Color.white, 1));
    this.VIDECuveSSP2.setFont(new Font("TIME NEW ROMAN", 1, 16));
    this.VIDECuveSSP2.setForeground(Color.red);
    this.VIDECuveSSP2.setBackground(this.white);
    this.progressBarSSP2.setMinimum(0);
    this.progressBarSSP2.setMaximum(100);
    this.progressBarSSP2.setStringPainted(true);
    this.progressBarSSP2.setValue(70);
    this.progressBarSSP2.setPreferredSize(new Dimension(400, 20));
    this.progressBarSSP2.setStringPainted(true);
    this.progressBarSSP2.setForeground(new Color(1, 180, 35));
    this.progressBarSSP2_2 = new JProgressBar() { public int getOrientation() { byte b;
          int i;
          StackTraceElement[] arrayOfStackTraceElement;
          for (i = (arrayOfStackTraceElement = (new Throwable()).getStackTrace()).length, b = 0; b < i; ) { StackTraceElement elem = arrayOfStackTraceElement[b];
            if (elem.getMethodName().equals("paintText") || elem.getMethodName().equals("paintString"))
              return 0; 
            b++; }
          
          return 1; }
         }
      ;
    this.progressBarSSP2_2.setMinimum(0);
    this.progressBarSSP2_2.setMaximum(100);
    this.progressBarSSP2_2.setStringPainted(true);
    this.progressBarSSP2_2.setOrientation(1);
    this.progressBarSSP2_2.setForeground(new Color(233, 159, 0));
    this.progressBarSSP2_2.setPreferredSize(new Dimension(132, 68));
    
    this.progressBarSSP2_2.setForeground(new Color(233, 159, 0));
    this.progressBarSSP2_2.setOrientation(1);
    Icon c = ICON_VALID;
    this.validbuttonSSP2 = new JButton();
    this.validbuttonSSP2.setIcon(c);
    this.validbuttonSSP2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            CheckBoxInTable c = new CheckBoxInTable();
            c.setVisible(true);
          }
        });
  }
  
  private void modernizeDashboard() {
    setBackground(this.white);
    setForeground(PRIMARY_TEXT);
    setOpaque(true);
    revalidate();
    repaint();
  }
  
  private void styleTankCard(JPanel panel) {
    if (panel == null) {
      return;
    }
    panel.setBackground(CARD_BG);
    panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(CARD_BORDER, 1),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
  }
  
  private void styleTree(Component component) {
    if (component instanceof JSplitPane) {
      JSplitPane splitPane = (JSplitPane)component;
      splitPane.setBorder(BorderFactory.createEmptyBorder());
      splitPane.setDividerSize(8);
      splitPane.setBackground(APP_BG);
    }
    if (component instanceof JPanel) {
      JPanel panel = (JPanel)component;
      if (panel != this.GASOIL1 && panel != this.GASOIL2 && panel != this.SSP &&
          panel != this.PETROLE && panel != this.GASOIL50 && panel != this.SSP2) {
        panel.setBackground(CARD_BG);
      }
    }
    if (component instanceof JLabel) {
      JLabel label = (JLabel)component;
      label.setFont(LABEL_FONT);
      label.setForeground(PRIMARY_TEXT);
    }
    if (component instanceof JTextField) {
      styleTextField((JTextField)component);
    }
    if (component instanceof JProgressBar) {
      styleProgressBar((JProgressBar)component);
    }
    if (component instanceof JButton) {
      styleButton((JButton)component);
    }
    if (component instanceof JComponent) {
      ((JComponent)component).setOpaque(true);
    }
    if (component instanceof Container) {
      Component[] children = ((Container)component).getComponents();
      for (int i = 0; i < children.length; i++) {
        styleTree(children[i]);
      }
    }
  }
  
  private void styleTextField(JTextField field) {
    field.setFont(VALUE_FONT);
    field.setBackground(FIELD_BG);
    field.setForeground(PRIMARY_TEXT);
    field.setEditable(false);
    field.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(190, 207, 216), 1),
        BorderFactory.createEmptyBorder(2, 7, 2, 7)));
    if (field == this.QTECuveG1 || field == this.QTECuveG2 || field == this.QTECuveSSP ||
        field == this.QTECuvePET || field == this.QTECuveG50 || field == this.QTECuveSSP2) {
      field.setForeground(BLUE_VALUE);
    }
    if (field == this.VIDECuveG1 || field == this.VIDECuveG2 || field == this.VIDECuveSSP ||
        field == this.VIDECuvePET || field == this.VIDECuveG50 || field == this.VIDECuveSSP2) {
      field.setForeground(RED_VALUE);
    }
  }
  
  private void styleProgressBar(JProgressBar bar) {
    bar.setFont(BASE_FONT);
    bar.setForeground(GOOD_GREEN);
    bar.setBackground(new Color(224, 232, 236));
    bar.setBorder(BorderFactory.createLineBorder(new Color(178, 194, 204), 1));
    bar.setStringPainted(true);
  }
  
  private void styleButton(JButton button) {
    button.setFont(LABEL_FONT);
    button.setFocusPainted(false);
    button.setBackground(new Color(236, 245, 240));
    button.setForeground(PRIMARY_TEXT);
    button.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(178, 203, 188), 1),
        BorderFactory.createEmptyBorder(3, 8, 3, 8)));
  }
  
  public double getQteG1() {
    return this.qteG1;
  }
  
  public void setQteG1(double qteG1) {
    this.qteG1 = qteG1;
  }
  
  public double getQteG2() {
    return this.qteG2;
  }
  
  public void setQteG2(double qteG2) {
    this.qteG2 = qteG2;
  }
  
  public double getQteSSP1() {
    return this.qteSSP1;
  }
  
  public void setQteSSP1(double qteSSP1) {
    this.qteSSP1 = qteSSP1;
  }
  
  public double getQtePET() {
    return this.qtePET;
  }
  
  public void setQtePET(double qtePET) {
    this.qtePET = qtePET;
  }
  
  public double getQteG50() {
    return this.qteG50;
  }
  
  public void setQteG50(double qteG50) {
    this.qteG50 = qteG50;
  }
  
  public double getQteSSp2() {
    return this.qteSSp2;
  }
  
  public void setQteSSp2(double qteSSp2) {
    this.qteSSp2 = qteSSp2;
  }
  
  public double getQte_G1() {
    return this.qte_G1;
  }
  
  public void setQte_G1(double qte_G1) {
    this.qte_G1 = qte_G1;
  }
  
  public double getQte_G2() {
    return this.qte_G2;
  }
  
  public void setQte_G2(double qte_G2) {
    this.qte_G2 = qte_G2;
  }
  
  public double getQte_SSP1() {
    return this.qte_SSP1;
  }
  
  public void setQte_SSP1(double qte_SSP1) {
    this.qte_SSP1 = qte_SSP1;
  }
  
  public double getQte_SSP2() {
    return this.qte_SSP2;
  }
  
  public void setQte_SSP2(double qte_SSP2) {
    this.qte_SSP2 = qte_SSP2;
  }
  
  public double getQte_PET() {
    return this.qte_PET;
  }
  
  public void setQte_PET(double qte_PET) {
    this.qte_PET = qte_PET;
  }
  
  public double getQte_G50() {
    return this.qte_G50;
  }
  
  public void setQte_G50(double qte_G50) {
    this.qte_G50 = qte_G50;
  }
}



