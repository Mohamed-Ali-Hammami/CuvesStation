/*     */ package shell.sijoumi.jeauge;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Path2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*    */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JSpeedometer
/*     */   extends JCircularGauge
/*     */ {
/*     */   private static final int NUM_MAJOR_TICKS = 14;
/*     */   private double maxSpeed;
/*     */   private String unit;
/*     */   private double currentSpeed;
/*     */   private Color indicatorColor;
/*     */   private int tickIncrement;
/*     */   
/*     */   public JSpeedometer(int increment, String unit) {
/*  56 */     this.unit = unit;
/*  57 */     this.indicatorColor = Color.BLACK;
/*  58 */     this.dialCenterDivider = 14.0F;
/*  59 */     setIncrement(increment);
/*     */   }
/*     */   
/*     */   public final void setIncrement(int increment) {
/*  63 */     this.maxSpeed = (increment * 14);
/*  64 */     this.tickIncrement = increment;
/*  65 */     repaint();
/*     */   }
/*     */   
/*     */   public int getTickIncrement() {
/*  69 */     return this.tickIncrement;
/*     */   }
/*     */   
/*     */   public void setUnit(String unit) {
/*  73 */     this.unit = unit;
/*  74 */     repaint();
/*     */   }
/*     */   
/*     */   public String getUnit() {
/*  78 */     return this.unit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColors(Color indicator, Color bezelColor, Color background) {
/*  88 */     this.indicatorColor = (indicator == null) ? Color.BLACK : indicator;
/*  89 */     setColors(bezelColor, background);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSpeed(double spd) {
/*  96 */     this.currentSpeed = spd;
/*  97 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawNeedle(Graphics2D g2d, double radius, double tickLen) {
/* 104 */     double angle = -180.0D + 280.0D * this.currentSpeed / this.maxSpeed;
/* 105 */     if (angle > 110.0D) {
/* 106 */       angle = 110.0D;
/*     */     }
/* 108 */     g2d.rotate(Math.toRadians(angle));
/*     */     
/* 110 */     double width = this.realInsideRadius / 20.0D;
/*     */     
/* 112 */     Path2D path = new Path2D.Double();
/* 113 */     path.moveTo(-width / 2.0D + 1.0D, 0.0D);
/* 114 */     path.lineTo(-1.0D, radius + tickLen + 3.0D);
/* 115 */     path.lineTo(1.0D, radius + tickLen + 3.0D);
/* 116 */     path.lineTo(width / 2.0D - 1.0D, 0.0D);
/* 117 */     path.closePath();
/*     */     
/* 119 */     g2d.fill(path);
/*     */     
/* 121 */     g2d.rotate(Math.toRadians(-angle));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/*     */     if (getWidth() <= 1 || getHeight() <= 1) {
/*     */       return;
/*     */     }
/* 130 */     Graphics2D g2d = (Graphics2D)g;
/*     */     
/* 132 */     setupForPaint(g2d);
/*     */     
/* 134 */     paintGaugeBackground(g2d);
/*     */     
/* 136 */     g2d.setColor(this.indicatorColor);
/*     */ 
/*     */     
/* 139 */     int indicatorRadius = (int)(-this.realInsideRadius + this.realInsideRadius / 10.0D);
/* 140 */     int tickLength = (int)(this.realInsideRadius + indicatorRadius);
/*     */     
/* 142 */     double angle = -180.0D;
/* 143 */     g2d.rotate(Math.toRadians(angle));
/*     */     
/* 145 */     double speedLabel = 0.0D;
/* 146 */     int smallTick = 10;
/*     */     
/* 148 */     int majorTickIncrement = 20;
/* 149 */     if (this.outsideRadius < 100) {
/* 150 */       majorTickIncrement = 40;
/*     */     }
/*     */ 
/*     */     
/* 154 */     for (int i = (int)angle; i <= 100; i += smallTick) {
/*     */ 
/*     */       
/* 157 */       if (i % majorTickIncrement == 0) {
/*     */         
/* 159 */         int lineStart = indicatorRadius + tickLength;
/*     */         
/* 161 */         g2d.setStroke(new BasicStroke(4.0F));
/* 162 */         Font font1 = g2d.getFont();
/* 163 */         Font font2 = font1.deriveFont(font1.getSize() * 1.3F);
/* 164 */         g2d.setFont(font2);
/* 165 */         String str = String.valueOf((int)Math.round(speedLabel));
/* 166 */         int j = g2d.getFontMetrics().stringWidth(str);
/* 167 */         int fontHeight = g2d.getFontMetrics().getHeight();
/*     */         
/* 169 */         AffineTransform a = g2d.getTransform();
/* 170 */         g2d.translate(j / 2 + 8, indicatorRadius + tickLength / 4);
/* 171 */         g2d.rotate(Math.toRadians(-angle));
/* 172 */         g2d.translate(-j / 2, fontHeight / 2);
/* 173 */         g2d.drawString(str, 0, 0);
/* 174 */         g2d.setTransform(a);
/*     */ 
/*     */         
/* 177 */         g2d.drawLine(0, lineStart, 0, (int)-this.realInsideRadius);
/* 178 */         g2d.setStroke(new BasicStroke(1.0F));
/* 179 */         g2d.setFont(font1);
/* 180 */         speedLabel += this.tickIncrement;
/*     */       
/*     */       }
/* 183 */       else if (this.outsideRadius > 150) {
/* 184 */         g2d.drawLine(0, indicatorRadius - tickLength / 2, 0, (int)-this.realInsideRadius);
/*     */       } 
/*     */       
/* 187 */       g2d.rotate(Math.toRadians(smallTick));
/* 188 */       angle += smallTick;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 193 */     g2d.setTransform(this.centerGaugeTransform);
/*     */     
/* 195 */     boolean validSpeed = (Double.isFinite(this.currentSpeed) && this.currentSpeed >= 0.0D);
/*     */     
/* 197 */     if (validSpeed) {
/* 198 */       drawNeedle(g2d, indicatorRadius, tickLength);
/*     */     }
/*     */     
/* 201 */     Font origFont = g2d.getFont();
/* 202 */     Font largeFont = origFont.deriveFont(origFont.getSize() * 4.0F);
/* 203 */     g2d.setFont(largeFont);
/* 204 */     String label = validSpeed ? String.valueOf((int)Math.round(this.currentSpeed)) : "?";
/* 205 */     int fontWidth = g2d.getFontMetrics().stringWidth(label);
/*     */     
/* 207 */     g2d.translate((int)(this.realInsideRadius / 2.0D), (int)(this.realInsideRadius / 2.0D));
/* 208 */     g2d.drawString(label, -fontWidth, 0);
/*     */ 
/*     */     
/* 211 */     largeFont = origFont.deriveFont(origFont.getSize() * 2.0F);
/* 212 */     g2d.setFont(largeFont);
/* 213 */     fontWidth = g2d.getFontMetrics().stringWidth(this.unit);
/* 214 */     int unitFontHeight = g2d.getFontMetrics().getHeight();
/*     */     
/* 216 */     g2d.drawString(this.unit, -fontWidth, 0 + unitFontHeight);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     g2d.setTransform(this.centerGaugeTransform);
/*     */ 
/*     */ 
/*     */     
/* 225 */     drawDialCenter(g2d);
/*     */ 
/*     */     
/* 228 */     paintBezel(g2d);
/*     */     
/* 230 */     completePaint(g2d);
/*     */   }
/*     */ }

