/*    */ package shell.sijoumi.etatcuve;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ 
/*    */ public class TransactionTableModel
/*    */   extends AbstractTableModel
/*    */ {
/* 11 */   String[] columnNames = new String[] { "Date", "Heure", "Produit", "Pompe", "Pistole", "Qte", "Prix", "Montant" };
/*    */   
/*    */   List<Transaction> listtTr;
/* 14 */   List<Transaction> data = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public TransactionTableModel(List<Transaction> listtTr) {
/* 18 */     if (listtTr != null) {
/* 19 */       this.data.addAll(listtTr);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRowCount() {
/* 23 */     return this.data.size();
/*    */   }
/*   */ 
/*    */   
/*    */   public int getColumnCount() {
/* 28 */     return this.columnNames.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getValueAt(int row, int colum) {
/* 33 */     Transaction personObj = this.data.get(row);
/*    */     
/* 35 */     switch (colum) { case 0:
/* 36 */         return personObj.getDates();
/* 37 */       case 1: return personObj.getHeure();
/* 38 */       case 2: return personObj.getArticle();
/* 39 */       case 3: return personObj.getPompe();
/* 40 */       case 4: return personObj.getPistolet();
/* 41 */       case 5: return Double.valueOf(personObj.getQte());
/* 42 */       case 6: return personObj.getPrix();
/* 43 */       case 7: return Double.valueOf(personObj.getMontant()); }
/* 44 */      return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getColumnName(int column) {
/* 50 */     return this.columnNames[column];
/*    */   }
/*    */   
/*    */   public void addData(Transaction person) {
/* 54 */     this.data.add(person);
/*    */   }
/*    */ }


/* Location:              D:\Data\Projets\JaugeageCuveV1\APPJeauge.jar!\shell\sijoumi\etatcuve\TransactionTableModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
