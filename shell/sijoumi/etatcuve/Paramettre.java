/*     */ package shell.sijoumi.etatcuve;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Image;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*    */ 
/*     */ public class Paramettre
/*     */   extends JFrame
/*     */   implements ActionListener
/*     */ {
/*     */   private Integer qteG1;
/*     */   private Integer qteG2;
/*     */   private Integer qteSSP1;
/*     */   private Integer qtePET;
/*     */   private Integer qteG50;
/*     */   private Integer qteSSp2;
/*     */   
/*     */   public Paramettre() {
/*     */     this.qteSSP1 = Integer.valueOf(AppConfig.ALERT_TANK_1_MIN);
/*     */     this.qteSSp2 = Integer.valueOf(AppConfig.ALERT_TANK_2_MIN);
/*     */     this.qtePET = Integer.valueOf(AppConfig.ALERT_TANK_3_MIN);
/*     */     this.qteG50 = Integer.valueOf(AppConfig.ALERT_TANK_4_MIN);
/*     */     this.qteG1 = Integer.valueOf(AppConfig.ALERT_TANK_5_MIN);
/*     */     this.qteG2 = Integer.valueOf(AppConfig.ALERT_TANK_6_MIN);
/*  31 */     JFrame frame = new JFrame();
/*  32 */     JPanel panel = new JPanel();
/*  33 */     GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
/*  34 */     int width = gd.getDisplayMode().getWidth();
/*  35 */     int height = gd.getDisplayMode().getHeight();
/*  36 */     Color white = new Color(135, 206, 235);
/*  37 */     panel.setBackground(white);
/*  38 */     panel.setForeground(Color.BLACK);
/*  79 */     Object[][] donnees = {
/*  80 */         { AppConfig.TANK_5, getQteG1(), Integer.valueOf((int)AppConfig.CAPACITY_5)
/*  81 */         }, { AppConfig.TANK_6, getQteG2(), Integer.valueOf((int)AppConfig.CAPACITY_6)
/*  82 */         }, { AppConfig.TANK_1, getQteSSP1(), Integer.valueOf((int)AppConfig.CAPACITY_1)
/*  83 */         }, { AppConfig.TANK_3, getQtePET(), Integer.valueOf((int)AppConfig.CAPACITY_3)
/*  84 */         }, { AppConfig.TANK_4, getQteG50(), Integer.valueOf((int)AppConfig.CAPACITY_4)
/*  85 */         }, { AppConfig.TANK_2, getQteSSp2(), Integer.valueOf((int)AppConfig.CAPACITY_2) }
/*     */       };
/*     */ 
/*     */     
/*  89 */     String[] entetes = { "CUVE", "QTE MIN", "CAPACITE" };
/*     */     
/*  91 */     JTable tableau = new JTable(donnees, (Object[])entetes);
/*     */ 
/*     */     
/*  94 */     getContentPane().add(new JScrollPane(tableau), "Center");
/*     */     
/*  96 */     frame.setSize(1000, 540);
/*  97 */     frame.setTitle("Paramétrage");
/*     */     
/*  99 */     frame.setDefaultCloseOperation(3);
/*     */     
/* 101 */     JLabel lblNewLabel = new JLabel("");
/* 102 */     lblNewLabel.setSize(1000, 540);
/* 103 */     ImageIcon i = new ImageIcon(Paramettre.class.getResource("items/background.png"));
/* 104 */     Image image2 = i.getImage();
/* 105 */     Image newimg = image2.getScaledInstance(1920, 1080, 4);
/* 106 */     i = new ImageIcon(newimg);
/* 107 */     lblNewLabel.setIcon(i);
/* 108 */     frame.getContentPane().add(lblNewLabel);
/* 109 */     ImageIcon image = new ImageIcon(Paramettre.class.getResource("items/shell.png"));
/* 110 */     frame.add(panel);
/* 111 */     frame.setIconImage(image.getImage());
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getQteG1() {
/* 116 */     return this.qteG1;
/*     */   }
/*     */   
/*     */   public void setQteG1(Integer qteG1) {
/* 120 */     this.qteG1 = qteG1;
/*     */   }
/*     */   
/*     */   public Integer getQteG2() {
/* 124 */     return this.qteG2;
/*     */   }
/*     */   
/*     */   public void setQteG2(Integer qteG2) {
/* 128 */     this.qteG2 = qteG2;
/*     */   }
/*     */   
/*     */   public Integer getQteSSP1() {
/* 132 */     return this.qteSSP1;
/*     */   }
/*     */   
/*     */   public void setQteSSP1(Integer qteSSP1) {
/* 136 */     this.qteSSP1 = qteSSP1;
/*     */   }
/*     */   
/*     */   public Integer getQtePET() {
/* 140 */     return this.qtePET;
/*     */   }
/*     */   
/*     */   public void setQtePET(Integer qtePET) {
/* 144 */     this.qtePET = qtePET;
/*     */   }
/*     */   
/*     */   public Integer getQteG50() {
/* 148 */     return this.qteG50;
/*     */   }
/*     */   
/*     */   public void setQteG50(Integer qteG50) {
/* 152 */     this.qteG50 = qteG50;
/*     */   }
/*     */   
/*     */   public Integer getQteSSp2() {
/* 156 */     return this.qteSSp2;
/*     */   }
/*     */   
/*     */   public void setQteSSp2(Integer qteSSp2) {
/* 160 */     this.qteSSp2 = qteSSp2;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent arg0) {}
/*     */ }


/* Location:              D:\Data\Projets\JaugeageCuveV1\APPJeauge.jar!\shell\sijoumi\etatcuve\Paramettre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
