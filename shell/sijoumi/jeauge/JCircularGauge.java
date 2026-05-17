/*     */ package shell.sijoumi.jeauge;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.MultipleGradientPaint;
/*     */ import java.awt.Paint;
/*     */ import java.awt.RadialGradientPaint;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import javax.swing.JComponent;
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
/*     */ public class JCircularGauge
/*     */   extends JComponent
/*     */ {
/*     */   protected Color bezelColor;
/*     */   protected Color background;
/*     */   protected int outsideRadius;
/*     */   protected double realInsideRadius;
/*     */   private AffineTransform origTransform;
/*     */   protected AffineTransform centerGaugeTransform;
/*  47 */   protected float dialCenterDivider = 20.0F;
/*     */   
/*  49 */   private float[] dist = new float[] { 0.0F, 0.89F, 0.9F, 0.95F, 1.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JCircularGauge() {
/*  55 */     setColors((Color)null, (Color)null);
/*     */   }
/*     */   
/*     */   protected void setBezelGradients(float[] d) {
/*  59 */     this.dist = d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setColors(Color bezelColor, Color background) {
/*  68 */     this.bezelColor = (bezelColor == null) ? Color.DARK_GRAY : bezelColor;
/*  69 */     this.background = (background == null) ? Color.WHITE : background;
/*  70 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/*  76 */     return new Dimension(150, 150);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getOutsideRadius() {
/*  82 */     Dimension size = getSize();
/*  83 */     if (size.height > size.width) {
/*  84 */       return size.width / 2;
/*     */     }
/*  86 */     return size.height / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintBezel(Graphics2D g) {
/*  94 */     int r = getOutsideRadius();
/*     */     if (r <= 0) {
/*     */       return;
/*     */     }
/*  95 */     Color[] colors = { new Color(0, 0, 0, 0), new Color(0, 0, 0, 0), this.bezelColor, Color.WHITE, this.bezelColor };
/*     */     
/*  97 */     RadialGradientPaint rgp = new RadialGradientPaint(new Point2D.Double(0.0D, 0.0D), r, this.dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
/*     */     
/*  99 */     Paint paint = g.getPaint();
/* 100 */     g.setPaint(rgp);
/* 101 */     g.fillOval(-r, -r, 2 * r, 2 * r);
/* 102 */     g.setPaint(paint);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintGaugeBackground(Graphics2D g) {
/* 108 */     g.setColor(this.background);
/* 109 */     int r = getOutsideRadius();
/*     */     if (r <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 111 */     g.fillOval(-r, -r, 2 * r, 2 * r);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawDialCenter(Graphics2D g2d) {
/* 117 */     double r = this.realInsideRadius / this.dialCenterDivider;
/* 118 */     g2d.fill(new Ellipse2D.Double(-r / 2.0D, -r / 2.0D, r, r));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setupForPaint(Graphics2D g) {
/* 123 */     this.outsideRadius = getOutsideRadius();
/* 124 */     this.realInsideRadius = (this.outsideRadius * this.dist[2]);
/*     */ 
/*     */     
/* 127 */     Graphics2D g2d = g;
/* 128 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 129 */     g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/* 130 */     g2d.setFont(new Font("Arial", 0, (int)(6L + Math.round(this.outsideRadius / 40.0D))));
/*     */ 
/*     */     
/* 133 */     this.origTransform = g2d.getTransform();
/* 134 */     Dimension size = getSize();
/* 135 */     g2d.translate(size.width / 2, size.height / 2);
/*     */     
/* 137 */     this.centerGaugeTransform = g2d.getTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void completePaint(Graphics2D g) {
/* 143 */     g.setTransform(this.origTransform);
/*     */   }
/*     */ }


