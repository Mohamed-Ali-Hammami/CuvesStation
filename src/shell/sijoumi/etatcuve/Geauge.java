package shell.sijoumi.etatcuve;

public class Geauge   {

	 private double level;
	 private double creux;
	 private double eau;
	 private double temperature;
	 private String cuve;
	 private String heure;
	 
	 
	 
	public Geauge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getLevel() {
		return level;
	}
	public void setLevel(double level) {
		this.level = level;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public String getCuve() {
		return cuve;
	}
	public void setCuve(String cuve) {
		this.cuve = cuve;
	}
	
	
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	 
	@Override
	public String toString() {
		return "Geauge [level=" + level + ", creux=" + creux + ", eau=" + eau + ", temperature=" + temperature
				+ ", cuve=" + cuve + ", heure=" + heure + "]";
	}
	public double getEau() {
		return eau;
	}
	public void setEau(double eau) {
		this.eau = eau;
	}
	public double getCreux() {
		return creux;
	}
	public void setCreux(double creux) {
		this.creux = creux;
	}
	 
	 
	 
	 

} 