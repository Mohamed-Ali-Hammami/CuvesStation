/*    */ package shell.sijoumi.etatcuve;
/*    */ 
/*   */ 
/*    */ public class MainExeScan
/*    */ {
/*    */   public static void main(String[] args) {
/*    */     try {
/*  8 */       Runtime.getRuntime().exec("tonProg.exe");
/*    */     } catch (Exception e) {
/* 10 */       e.toString();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Data\Projets\JaugeageCuveV1\APPJeauge.jar!\shell\sijoumi\etatcuve\MainExeScan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */