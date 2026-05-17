/*    */ package shell.sijoumi.etatcuve;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ public class ConnectionSqlite {
/*  7 */   Connection conn = null;
/*   */ 
/*    */   
/*    */   public static Connection dbConnector() {
/*    */     try {
/* 12 */       Class.forName("org.sqlite.JDBC");
/*    */       
/* 14 */       Connection conn = DriverManager.getConnection("jdbc:sqlite:" + AppConfig.SQLITE_TANK_STATUS_DB);
/* 15 */       JOptionPane.showMessageDialog(null, "Connection succsseful");
/* 16 */       return conn;
/* 17 */     } catch (Exception e) {
/*    */       
/* 19 */       JOptionPane.showMessageDialog(null, e);
/* 20 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Data\Projets\JaugeageCuveV1\APPJeauge.jar!\shell\sijoumi\etatcuve\ConnectionSqlite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
