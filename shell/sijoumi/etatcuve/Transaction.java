/*    */ package shell.sijoumi.etatcuve;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*   */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Transaction
/*    */ {
/*    */   private Integer id;
/*    */   private String heure;
/*    */   private String article;
/*    */   private String pompe;
/*    */   private double qte;
/*    */   private double montant;
/*    */   private String pistolet;
/*    */   private String prix;
/*    */   private String dates;
/*    */   
/*    */   public Integer getId() {
/* 24 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 27 */     this.id = id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 35 */     return "Transaction [id=" + this.id + ", pompe=" + this.pompe + "]";
/*    */   }
/*    */   
/*    */   public String getPompe() {
/* 39 */     return this.pompe;
/*    */   }
/*    */   public void setPompe(String pompe) {
/* 42 */     this.pompe = pompe;
/*    */   }
/*    */   public double getQte() {
/* 45 */     return this.qte;
/*    */   }
/*    */   public void setQte(double qte) {
/* 48 */     this.qte = qte;
/*    */   }
/*    */   public double getMontant() {
/* 51 */     return this.montant;
/*    */   }
/*    */   public void setMontant(double montant) {
/* 54 */     this.montant = montant;
/*    */   }
/*    */   public String getPistolet() {
/* 57 */     return this.pistolet;
/*    */   }
/*    */   public void setPistolet(String pistolet) {
/* 60 */     this.pistolet = pistolet;
/*    */   }
/*    */   public String getPrix() {
/* 63 */     return this.prix;
/*    */   }
/*    */   public void setPrix(String prix) {
/* 66 */     this.prix = prix;
/*    */   }
/*    */   public String getDates() {
/* 69 */     return this.dates;
/*    */   }
/*    */   public void setDates(String dates) {
/* 72 */     this.dates = dates;
/*    */   }
/*    */   public String getHeure() {
/* 75 */     return this.heure;
/*    */   }
/*    */   public void setHeure(String heure) {
/* 78 */     this.heure = heure;
/*    */   }
/*    */   public String getArticle() {
/* 81 */     return this.article;
/*    */   }
/*    */   public void setArticle(String article) {
/* 84 */     this.article = article;
/*    */   }
/*    */ }


/* Location:              D:\Data\Projets\JaugeageCuveV1\APPJeauge.jar!\shell\sijoumi\etatcuve\Transaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */