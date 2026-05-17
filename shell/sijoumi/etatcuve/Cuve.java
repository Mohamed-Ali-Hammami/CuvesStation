/*    */ package shell.sijoumi.etatcuve;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.GraphicsDevice;
/*    */ import java.awt.GraphicsEnvironment;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*   */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Cuve
/*    */ {
/*    */   public static void main(String[] args) {
/*    */     AppLogger.init();
/*    */     Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
/*    */       public void uncaughtException(Thread thread, Throwable throwable) {
/*    */         AppLogger.error("Uncaught exception in thread " + thread.getName(), throwable);
/*    */       }
/*    */     });
/*    */     AppLogger.info("Starting APPJeauge");
/*    */     try {
/*    */       UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
/*    */     } catch (Exception ex) {
/*    */       AppLogger.warn("Nimbus look and feel unavailable: " + ex.getMessage());
/*    */     }
/* 30 */     JFrame frame = new JFrame();
/* 31 */     JPanel panel = new GraphiqueCuve();
/* 32 */     GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
/* 33 */     int width = gd.getDisplayMode().getWidth();
/* 34 */     int height = gd.getDisplayMode().getHeight();
/* 35 */     Color white = new Color(238, 243, 246);
/* 36 */     panel.setBackground(white);
/* 37 */     panel.setForeground(Color.BLACK);
/* 38 */     frame.setSize(width, height);
/* 39 */     frame.setTitle("Etat des cuves");
/*    */     frame.getContentPane().setBackground(white);
/*    */     
/* 41 */     frame.setDefaultCloseOperation(3);
/*    */     
/* 51 */     ImageIcon image = new ImageIcon(Cuve.class.getResource("items/shell.png"));
/* 52 */     frame.add(panel);
/* 53 */     frame.setIconImage(image.getImage());
/* 54 */     frame.setVisible(true);
/*    */     AppLogger.info("APPJeauge window displayed");
/*    */   }
/*    */ }


/* Location:              D:\Data\Projets\JaugeageCuveV1\APPJeauge.jar!\shell\sijoumi\etatcuve\Cuve.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
