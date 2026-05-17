/*    */ package shell.sijoumi.jeauge;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Container;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
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
/*    */ 
/*    */ 
/*    */ public class JSpeedometerDemo
/*    */ {
/*    */   public static void main(String[] args) throws InterruptedException {
/* 36 */     JFrame myFrame = new JFrame("Speedometer Demo");
/*    */     
/* 38 */     Container thePane = myFrame.getContentPane();
/*    */ 
/*    */     
/* 41 */     final JSpeedometer g = new JSpeedometer(10, "Jeauge");
/* 42 */     g.setColors(Color.BLUE, null, Color.BLACK);
/* 43 */     thePane.add(g);
/*    */     
/* 45 */     myFrame.pack();
/* 46 */     myFrame.setVisible(true);
/*    */ 
/*    */ 
/*    */     
/* 50 */     int b = 0;
/*    */     
/*    */     while (true) {
/* 53 */       final double spd = b;
/* 54 */       SwingUtilities.invokeLater(new Runnable()
/*    */           {
/*    */             public void run() {
/* 57 */               g.setSpeed(spd);
/*    */             }
/*    */           });
/*    */       
/* 61 */       Thread.sleep(100L);
/*    */       
/* 63 */       b = (int)(b + 1.0D);
/* 64 */       if (b > 180)
/* 65 */         b = 0; 
/*    */     } 
/*    */   }
/*    */ }


