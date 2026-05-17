/*    */ package shell.sijoumi.etatcuve;
/*   */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Geauge
/*    */ {
/*    */   private double level;
/*    */   private double creux;
/*    */   private double eau;
/*    */   private double temperature;
/*    */   private String cuve;
/*    */   private String heure;
/*    */   
/*    */   public double getLevel() {
/* 20 */     return this.level;
/*    */   }
/*    */   public void setLevel(double level) {
/* 23 */     this.level = level;
/*    */   }
/*    */   public double getTemperature() {
/* 26 */     return this.temperature;
/*    */   }
/*    */   public void setTemperature(double temperature) {
/* 29 */     this.temperature = temperature;
/*    */   }
/*    */   public String getCuve() {
/* 32 */     return this.cuve;
/*    */   }
/*    */   public void setCuve(String cuve) {
/* 35 */     this.cuve = cuve;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getHeure() {
/* 40 */     return this.heure;
/*    */   }
/*    */   public void setHeure(String heure) {
/* 43 */     this.heure = heure;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     return "Geauge [level=" + this.level + ", creux=" + this.creux + ", eau=" + this.eau + ", temperature=" + this.temperature + 
/* 49 */       ", cuve=" + this.cuve + ", heure=" + this.heure + "]";
/*    */   }
/*    */   public double getEau() {
/* 52 */     return this.eau;
/*    */   }
/*    */   public void setEau(double eau) {
/* 55 */     this.eau = eau;
/*    */   }
/*    */   public double getCreux() {
/* 58 */     return this.creux;
/*    */   }
/*    */   public void setCreux(double creux) {
/* 61 */     this.creux = creux;
/*    */   }
/*    */ }


/* Location:              D:\Data\Projets\JaugeageCuveV1\APPJeauge.jar!\shell\sijoumi\etatcuve\Geauge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */